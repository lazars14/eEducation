package com.eEducation.ftn.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eEducation.ftn.service.AppUserService;
import com.eEducation.ftn.web.dto.AppUserDTO;

@RestController
@RequestMapping(value="api/appUsers")
public class AppUserController {
	@Autowired
	AppUserService appUserService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AppUserDTO>> getAppUsers(){
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<AppUserDTO> getAppUser(@PathVariable Long id){
		return null;
	
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<AppUserDTO> saveAppUser(@RequestBody AppUserDTO appUser){
		return null;

	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<AppUserDTO> updateAppUser(@RequestBody AppUserDTO appUser){
		return null;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAppUser(@PathVariable Long id){
		return null;
		
	}
	
	// collection methods
}
