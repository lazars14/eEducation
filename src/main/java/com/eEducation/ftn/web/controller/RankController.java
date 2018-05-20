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

import com.eEducation.ftn.service.RankService;
import com.eEducation.ftn.web.dto.RankDTO;

@RestController
@RequestMapping(value="api/ranks")
public class RankController {
	@Autowired
	RankService rankService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RankDTO>> getRanks(){
		return null;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<RankDTO> getRank(@PathVariable Integer id){
		return null;
	
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<RankDTO> saveRank(@RequestBody RankDTO rank){
		return null;

	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<RankDTO> updateRank(@RequestBody RankDTO rank){
		return null;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRank(@PathVariable Integer id){
		return null;
		
	}
	
	// collection methods
}
