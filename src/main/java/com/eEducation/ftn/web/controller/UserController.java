package com.eEducation.ftn.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eEducation.ftn.model.Student;
import com.eEducation.ftn.model.Teacher;
import com.eEducation.ftn.model.User;
import com.eEducation.ftn.model.UserAuthority;
import com.eEducation.ftn.repository.AuthorityRepository;
import com.eEducation.ftn.repository.StudentRepository;
import com.eEducation.ftn.repository.TeacherRepository;
import com.eEducation.ftn.repository.UserAuthorityRepository;
import com.eEducation.ftn.repository.UserRepository;
import com.eEducation.ftn.security.TokenUtils;
import com.eEducation.ftn.service.FileService;
import com.eEducation.ftn.service.UserAuthorityService;
import com.eEducation.ftn.service.UserService;
import com.eEducation.ftn.web.dto.LoginDTO;

@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
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
	TokenUtils tokenUtils;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	FileService fileService;
	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        try {
        	logger.info("login - username: " + loginDTO.getUsername());
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            
            Long id = null;
            String email = authentication.getName();
            String role = details.getAuthorities().iterator().next().getAuthority();
            if(role.equals("ROLE_ADMIN")) role = "admin";
            else if(role.equals("ROLE_TEACHER")) {
            	role = "teacher";
            	Teacher teacher = teacherRepository.findByEmail(email);
            	id = teacher.getId();
            }
            else if(role.equals("ROLE_STUDENT")) {
            	role = "student";
            	Student student = studentRepository.findByEmail(email);
            	id = student.getId();
            }
            
            Map<String, String> responseObject = new HashMap<String, String>();
            responseObject.put("token", tokenUtils.generateToken(details));
            responseObject.put("role", role);
            responseObject.put("user", email);
            
            // if student or teacher
            if(id != null) {
            	responseObject.put("id", id.toString());
            }
            
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (Exception ex) {
        	logger.error("failed to login for username " + loginDTO.getUsername());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
	}
	
	@RequestMapping(value = "/api/logout", method = RequestMethod.GET)
	public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
        	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){    
                new SecurityContextLogoutHandler().logout(request, response, auth);
                logger.info("successfully logged out user " + auth.getName());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
            	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
			
        } catch (Exception ex) {
        	logger.error("logout - failed to logout, exception " + ex.getStackTrace());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="api/admin/changeEmail")
	public ResponseEntity<Void> changeEmail(@RequestBody HashMap<String, String> body){
		String oldEmail = body.get("oldEmail");
		String newEmail = body.get("newEmail");
		
		logger.info("change email - params oldEmail: " + oldEmail + " and newEmail: " + newEmail);
		
		if(oldEmail == null || newEmail == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(oldEmail.equals(newEmail)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User found = userRepository.findByUsername(oldEmail);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(!found.getUsername().equals(oldEmail)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// there is already an user with that email
		User existing = userRepository.findByUsername(newEmail);
		if(existing != null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		// update user
		UserAuthority ua = userAuthorityRepository.findByUser(found);
				
		found.setUsername(newEmail);
				
		userService.save(found);
				
		// update user authority
		ua.setUser(found);
				
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

		logger.info("change email - success, oldEmail: " + oldEmail + " and newEmail: " + newEmail);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="api/admin/changePassword")
	public ResponseEntity<Void> changePassword(@RequestBody HashMap<String, String> body){
		String oldPassword = body.get("oldPassword");
		String newPassword = body.get("newPassword");
		String repeatPassword = body.get("repeatPassword");
		String email = body.get("email");
		
		logger.info("change password - params oldPassword: " + oldPassword + " newPassword: " + newPassword + 
				" repeatPassword: " + repeatPassword + " email: " + email);
		
		if(oldPassword == null || newPassword == null || repeatPassword == null || email == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(newPassword.equals("") || repeatPassword.equals("")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(!newPassword.equals(repeatPassword)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User found = userRepository.findByUsername(email);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		boolean oldPasswordMatches = passwordEncoder.matches(oldPassword, found.getPassword());
		
		if(oldPasswordMatches == false) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// update user
		UserAuthority ua = userAuthorityRepository.findByUser(found);
				
		found.setPassword(passwordEncoder.encode(newPassword));
		
		userService.save(found);
				
		// update user authority
		ua.setUser(found);
				
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
		
		logger.info("change password - success - for params oldPassword: " + oldPassword + " newPassword: " + newPassword + 
				" repeatPassword: " + repeatPassword + " email: " + email);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
