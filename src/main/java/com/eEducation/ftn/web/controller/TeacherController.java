package com.eEducation.ftn.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eEducation.ftn.model.Authority;
import com.eEducation.ftn.model.Course;
import com.eEducation.ftn.model.Rank;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.Teacher;
import com.eEducation.ftn.model.TeacherTeachesCourse;
import com.eEducation.ftn.model.User;
import com.eEducation.ftn.model.UserAuthority;
import com.eEducation.ftn.repository.AuthorityRepository;
import com.eEducation.ftn.repository.TeacherRepository;
import com.eEducation.ftn.repository.TeacherTeachesCourseRepository;
import com.eEducation.ftn.repository.UserAuthorityRepository;
import com.eEducation.ftn.repository.UserRepository;
import com.eEducation.ftn.security.TokenUtils;
import com.eEducation.ftn.service.CourseService;
import com.eEducation.ftn.service.RankService;
import com.eEducation.ftn.service.TeacherService;
import com.eEducation.ftn.service.UserAuthorityService;
import com.eEducation.ftn.service.UserService;
import com.eEducation.ftn.web.dto.TeacherDTO;

@RestController
@RequestMapping(value="api/teachers")
public class TeacherController {
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	RankService rankService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	TeacherTeachesCourseRepository ttcRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserAuthorityService userAuthorityService;
	
	@Autowired
	UserAuthorityRepository userAuthorityRepository;
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TeacherDTO>> getAll(){
		List<Teacher> teachers = teacherService.findAll();
		List<TeacherDTO> teacherDTOs = new ArrayList<>();
		
		for(Teacher t : teachers) {
			t.setSPassword("");
			teacherDTOs.add(new TeacherDTO(t));
		}
		
		return new ResponseEntity<>(teacherDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<TeacherDTO> getById(@PathVariable Long id){
		Teacher found = teacherService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		found.setSPassword("");
		
		return new ResponseEntity<>(new TeacherDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<TeacherDTO> add(@RequestBody TeacherDTO teacher){
		Teacher newTeacher = new Teacher();
		newTeacher.setFirstname(teacher.getFirstname());
		newTeacher.setLastname(teacher.getLastname());
		
		if(teacher.getEmail() == null || teacher.getsPassword() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Teacher existing = teacherRepository.findByEmail(teacher.getEmail());
		if(existing != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		User existingUser = userRepository.findByUsername(teacher.getEmail());
		if(existingUser != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		newTeacher.setEmail(teacher.getEmail());
		newTeacher.setSPassword(passwordEncoder.encode(teacher.getsPassword()));
		newTeacher.setSPassword(teacher.getsPassword());
		
		if(teacher.getRank() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Rank rank = rankService.findOne(teacher.getRank().getId());
		if(rank == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newTeacher.setRank(rank);
		
		teacherService.save(newTeacher);
		
		// create user
		User newUser = new User();
		newUser.setUsername(newTeacher.getEmail());
		newUser.setPassword(newTeacher.getSPassword());
		
		userService.save(newUser);
		
		// create userAuthority
		UserAuthority newUserAuthority = new UserAuthority();
		newUserAuthority.setUser(newUser);
		
		Authority auth = authorityRepository.findByName("ROLE_TEACHER");
		
		newUserAuthority.setAuthority(auth);
		
		userAuthorityService.save(newUserAuthority);
		
		return new ResponseEntity<>(new TeacherDTO(newTeacher), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	public ResponseEntity<TeacherDTO> update(@RequestBody TeacherDTO teacher){
		boolean updateUser = false;
		
		Teacher found = teacherService.findOne(teacher.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String oldEmail = found.getEmail();
		
		found.setFirstname(teacher.getFirstname());
		found.setLastname(teacher.getLastname());
		
		if(teacher.getEmail() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Teacher existing = teacherRepository.findByEmail(teacher.getEmail());
		if(existing != null && teacher.getId() != existing.getId()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(!teacher.getEmail().equals(found.getEmail())) updateUser = true;
			
		found.setEmail(teacher.getEmail());
		
		if(teacher.getsPassword() != null && !teacher.getsPassword().equals("")) {
			found.setSPassword(passwordEncoder.encode(teacher.getsPassword()));
			updateUser = true;
		}

		
		if(teacher.getRank() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Rank rank = rankService.findOne(teacher.getRank().getId());
		if(rank == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setRank(rank);
		
		teacherService.save(found);
		
		// update user
		if(updateUser == true) {
			User u = userRepository.findByUsername(oldEmail);
			UserAuthority ua = userAuthorityRepository.findByUser(u);
			
			
			u.setUsername(found.getEmail());
			u.setPassword(found.getSPassword());
			
			userService.save(u);
			
			// update user authority
			ua.setUser(u);
			
			userAuthorityService.save(ua);
		}
		
		
		return new ResponseEntity<>(new TeacherDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Teacher found = teacherService.findOne(id);
		if(found != null) {
			try {
				User u = userRepository.findByUsername(found.getEmail());
				UserAuthority ua = userAuthorityRepository.findByUser(u);
				
				userAuthorityService.remove(ua.getId());
				userService.remove(u.getId());
				
				teacherService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/course/{courseId}")
	public ResponseEntity<List<TeacherDTO>> getByCourse(@PathVariable Long courseId){
		Course course = courseService.findOne(courseId);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<TeacherTeachesCourse> ttcS = ttcRepository.findByCourse(course);
		List<TeacherDTO> teacherDTOs = new ArrayList<>();
		
		for(TeacherTeachesCourse t : ttcS) {
			t.getTeacher().setSPassword("");
			teacherDTOs.add(new TeacherDTO(t.getTeacher()));
		}
		
		return new ResponseEntity<>(teacherDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}/changeEmail")
	public ResponseEntity<Void> changeEmail(@PathVariable Long id, @RequestBody HashMap<String, String> body){
		String oldEmail = body.get("oldEmail");
		String newEmail = body.get("newEmail");
		
		System.out.println("stari email je: " + oldEmail + " i novi email je: " + newEmail);
		
		
		if(oldEmail == null || newEmail == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("posle null provere");
		
		if(oldEmail.equals(newEmail)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("posle provere da li je jednak star novom");
		
		Teacher found = teacherService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("posle pronadjenog studenta");
		
		if(!found.getEmail().equals(oldEmail)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("posle provere da li je stari mail jednak pronadjenom starom");
		
		// there is already an user with that email
		User existing = userRepository.findByUsername(newEmail);
		if(existing != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		found.setEmail(newEmail);
		teacherService.save(found);
		
		// update user
		User u = userRepository.findByUsername(oldEmail);
		UserAuthority ua = userAuthorityRepository.findByUser(u);
				
		u.setUsername(found.getEmail());
				
		userService.save(u);
				
		// update user authority
		ua.setUser(u);
				
		userAuthorityService.save(ua);
		
//		can't do this, because I don't have the user's password
		// generate new token for session to be valid
//		UserDetails details = userDetailsService.loadUserByUsername(u.getUsername());
//		String newToken = tokenUtils.generateToken(details);
//		
//		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//				u.getUsername(), loginDTO.getPassword());
//		Authentication authentication = authenticationManager.authenticate(newToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}/changePassword")
	public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody HashMap<String, String> body){
		String oldPassword = body.get("oldPassword");
		String newPassword = body.get("newPassword");
		String repeatPassword = body.get("repeatPassword");
		
		if(oldPassword == null || newPassword == null || repeatPassword == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(newPassword.equals("") || repeatPassword.equals("")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(!newPassword.equals(repeatPassword)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Teacher found = teacherService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		boolean oldPasswordMatches = passwordEncoder.matches(oldPassword, found.getSPassword());
		
		if(oldPasswordMatches == false) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setSPassword(passwordEncoder.encode(newPassword));
		teacherService.save(found);
		
		// update user
		User u = userRepository.findByUsername(found.getEmail());
		UserAuthority ua = userAuthorityRepository.findByUser(u);
				
		u.setPassword(found.getSPassword());
				
		userService.save(u);
				
		// update user authority
		ua.setUser(u);
				
		userAuthorityService.save(ua);
		
		// didn't do it in email, so not going to do it here
		// generate new token for session to be valid
//		UserDetails details = userDetailsService.loadUserByUsername(u.getUsername());
//		String newToken = tokenUtils.generateToken(details);
//		
//		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//				u.getUsername(), loginDTO.getPassword());
//		Authentication authentication = authenticationManager.authenticate(newToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
