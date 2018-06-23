package com.eEducation.ftn.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eEducation.ftn.security.TokenUtils;
import com.eEducation.ftn.web.dto.LoginDTO;

@RestController
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            
            String role = details.getAuthorities().iterator().next().getAuthority();
            if(role.equals("ROLE_ADMIN")) role = "admin";
            if(role.equals("ROLE_TEACHER")) role = "teacher";
            if(role.equals("ROLE_STUDENT")) role = "student";
            
            Map<String, String> responseObject = new HashMap<String, String>();
            responseObject.put("token", tokenUtils.generateToken(details));
            responseObject.put("role", role);
            responseObject.put("user", authentication.getName());
            
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
	}
	
	@RequestMapping(value = "/api/logout", method = RequestMethod.GET)
	public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
        	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){    
                new SecurityContextLogoutHandler().logout(request, response, auth);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
            	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
			
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
	}
}
