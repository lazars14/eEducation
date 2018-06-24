package com.eEducation.ftn.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.eEducation.ftn.model.CollegeDirection;
import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.User;
import com.eEducation.ftn.model.UserAuthority;
import com.eEducation.ftn.repository.AuthorityRepository;
import com.eEducation.ftn.repository.StudentRepository;
import com.eEducation.ftn.repository.UserAuthorityRepository;
import com.eEducation.ftn.repository.UserRepository;
import com.eEducation.ftn.security.TokenUtils;
import com.eEducation.ftn.service.CollegeDirectionService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.service.UserAuthorityService;
import com.eEducation.ftn.service.UserService;
import com.eEducation.ftn.web.dto.StudentDTO;

@RestController
@RequestMapping(value="api/students")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	CollegeDirectionService directionService;

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
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getAll(){
		List<Student> students = studentService.findAll();
		List<StudentDTO> studentDTOs = new ArrayList<>();
		
		for(Student s : students) {
			studentDTOs.add(new StudentDTO(s));
		}
		
		return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<StudentDTO> getById(@PathVariable Long id){
		Student found = studentService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		found.setSPassword("");
		
		return new ResponseEntity<>(new StudentDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<StudentDTO> add(@RequestBody StudentDTO student){
		Student newStudent = new Student();
		
		if(student.getEmail() == null || student.getsPassword() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student existing = studentRepository.findByEmail(student.getEmail());
		if(existing != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		User existingUser = userRepository.findByUsername(student.getEmail());
		if(existingUser != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(student.getIndexNumber() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student found = studentRepository.findByIndexNumber(student.getIndexNumber());
		if(found != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(student.getCollegeDirection() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		CollegeDirection direction = directionService.findOne(student.getCollegeDirection().getId());
		if(direction == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newStudent.setCollegeDirection(direction);
		newStudent.setIndexNumber(student.getIndexNumber());
		newStudent.setFirstname(student.getFirstname());
		newStudent.setLastname(student.getLastname());
		
		if(student.getAccountNumber() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student foundAccNum = studentRepository.findByAccountNumber(student.getAccountNumber());
		if(foundAccNum != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		Student foundRefNum = studentRepository.findByReferenceNumber(student.getReferenceNumber());
		if(foundRefNum != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		newStudent.setAccountNumber(student.getAccountNumber());
		newStudent.setReferenceNumber(student.getReferenceNumber());
		newStudent.setStudYear(student.getStudYear());
		newStudent.setStudYearOrdNum(student.getStudYearOrdNum());
		newStudent.setEmail(student.getEmail());
		newStudent.setSPassword(passwordEncoder.encode(student.getsPassword()));
		newStudent.setEspbPoints(student.getEspbPoints());
		
		studentService.save(newStudent);
		
		// create user
		User newUser = new User();
		newUser.setUsername(newStudent.getEmail());
		newUser.setPassword(newStudent.getSPassword());
				
		userService.save(newUser);
				
		// create userAuthority
		UserAuthority newUserAuthority = new UserAuthority();
		newUserAuthority.setUser(newUser);
				
		Authority auth = authorityRepository.findByName("ROLE_STUDENT");
				
		newUserAuthority.setAuthority(auth);
				
		userAuthorityService.save(newUserAuthority);
		
		newStudent.setSPassword("");
		return new ResponseEntity<>(new StudentDTO(newStudent), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	public ResponseEntity<StudentDTO> update(@RequestBody StudentDTO student){
		boolean updateUser = false;
		
		Student found = studentService.findOne(student.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String oldEmail = found.getEmail();
		
		if(student.getEmail() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student existing = studentRepository.findByEmail(student.getEmail());
		if(existing != null && student.getId() != existing.getId()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(!student.getEmail().equals(found.getEmail())) updateUser = true;
		
		if(student.getCollegeDirection() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		CollegeDirection direction = directionService.findOne(student.getCollegeDirection().getId());
		if(direction == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setCollegeDirection(direction);
		
		if(student.getIndexNumber() == null ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student foundIndNum = studentRepository.findByIndexNumber(student.getIndexNumber());
		// found a student with that index number, not allowed
		if(foundIndNum != null && student.getId() != foundIndNum.getId()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		found.setIndexNumber(student.getIndexNumber());
		found.setFirstname(student.getFirstname());
		found.setLastname(student.getLastname());
		
		if(student.getAccountNumber() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student foundAccNum = studentRepository.findByAccountNumber(student.getAccountNumber());
		if(foundAccNum != null && student.getId() != foundAccNum.getId()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		Student foundRefNum = studentRepository.findByReferenceNumber(student.getReferenceNumber());
		if(foundRefNum != null && student.getId() != foundRefNum.getId()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		found.setAccountNumber(student.getAccountNumber());
		found.setReferenceNumber(student.getReferenceNumber());
		found.setStudYear(student.getStudYear());
		found.setStudYearOrdNum(student.getStudYearOrdNum());
		found.setEmail(student.getEmail());
		
		
		if(student.getsPassword() != null && !student.getsPassword().equals("")) {
			found.setSPassword(passwordEncoder.encode(student.getsPassword()));
			updateUser = true;
		}
		
		
		found.setEspbPoints(student.getEspbPoints());
		
		studentService.save(found);
		
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
		
		return new ResponseEntity<>(new StudentDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Student found = studentService.findOne(id);
		if(found != null) {
			try {
				User u = userRepository.findByUsername(found.getEmail());
				UserAuthority ua = userAuthorityRepository.findByUser(u);
				
				userAuthorityService.remove(ua.getId());
				userService.remove(u.getId());
				
				studentService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/class/{classId}")
	public ResponseEntity<List<StudentDTO>> getByClass(@PathVariable Long classId){
		CollegeDirection direction = directionService.findOne(classId);
		if(direction == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Student> students = studentRepository.findByDirection(direction);
		List<StudentDTO> studentDTOs = new ArrayList<>();
		
		for(Student s : students) {
			s.setSPassword("");
			studentDTOs.add(new StudentDTO(s));
		}
		
		return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}/changeEmail")
	public ResponseEntity<String> changeEmail(@PathVariable Long id, @RequestBody String oldEmail, @RequestBody String newEmail){
		if(oldEmail == null || newEmail == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(oldEmail.equals(newEmail)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student found = studentService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(!found.getEmail().equals(oldEmail)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// there is already an user with that email
		User existing = userRepository.findByUsername(newEmail);
		if(existing != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		found.setEmail(newEmail);
		studentService.save(found);
		
		// update user
		User u = userRepository.findByUsername(oldEmail);
		UserAuthority ua = userAuthorityRepository.findByUser(u);
				
		u.setUsername(found.getEmail());
				
		userService.save(u);
				
		// update user authority
		ua.setUser(u);
				
		userAuthorityService.save(ua);
		
		// generate new token for session to be valid
		UserDetails details = userDetailsService.loadUserByUsername(u.getUsername());
		String newToken = tokenUtils.generateToken(details);
		
		
		return new ResponseEntity<String>(newToken, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}/changePassword")
	public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody String oldPassword, 
			@RequestBody String newPassword, @RequestBody String repeatPassword){
		if(oldPassword == null || newPassword == null || repeatPassword == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(newPassword.equals("") || repeatPassword.equals("")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(!newPassword.equals(repeatPassword)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Student found = studentService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		boolean oldPasswordMatches = passwordEncoder.matches(oldPassword, found.getSPassword());
		
		if(oldPasswordMatches == false) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setSPassword(passwordEncoder.encode(newPassword));
		studentService.save(found);
		
		// update user
		User u = userRepository.findByUsername(found.getEmail());
		UserAuthority ua = userAuthorityRepository.findByUser(u);
				
		u.setPassword(found.getSPassword());
				
		userService.save(u);
				
		// update user authority
		ua.setUser(u);
				
		userAuthorityService.save(ua);
		
		// generate new token for session to be valid
		UserDetails details = userDetailsService.loadUserByUsername(u.getUsername());
		String newToken = tokenUtils.generateToken(details);
		
		return new ResponseEntity<String>(newToken, HttpStatus.OK);
	}
}
