package com.eEducation.ftn.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eEducation.ftn.model.Rank;
import com.eEducation.ftn.service.RankService;
import com.eEducation.ftn.web.dto.RankDTO;

@RestController
@RequestMapping(value="api/ranks")
public class RankController {
	@Autowired
	RankService rankService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RankDTO>> getAll(){
		List<Rank> ranks = rankService.findAll();
		List<RankDTO> rankDTOs = new ArrayList<>();
		
		for(Rank r : ranks){
			rankDTOs.add(new RankDTO(r));
		}
		
		return new ResponseEntity<>(rankDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<RankDTO> getById(@PathVariable Integer id){
		Rank found = rankService.findOne(id);
		if(found == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new RankDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<RankDTO> save(@RequestBody RankDTO rank){
		Rank newRank = new Rank();
		rank.setName(rank.getName());
		
		rankService.save(newRank);
		return new ResponseEntity<>(new RankDTO(newRank), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<RankDTO> update(@RequestBody RankDTO rank){
		Rank found = rankService.findOne(rank.getId());
		if(found == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		found.setName(rank.getName());
		
		rankService.save(found);
		return new ResponseEntity<>(new RankDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		Rank found = rankService.findOne(id);
		if(found != null){
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
