package com.eEducation.ftn.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eEducation.ftn.model.ExamPeriod;
import com.eEducation.ftn.service.ExamPeriodService;
import com.eEducation.ftn.web.dto.ExamPeriodDTO;

@RestController
@RequestMapping(value="api/examPeriods")
public class ExamPeriodController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExamPeriodController.class);
	
	@Autowired
	ExamPeriodService examPeriodService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ExamPeriodDTO>> getAll(){
		List<ExamPeriod> examPeriods = examPeriodService.findAll();
		List<ExamPeriodDTO> examPeriodDTOs = new ArrayList<>();
		
		for(ExamPeriod ep : examPeriods){
			examPeriodDTOs.add(new ExamPeriodDTO(ep));
		}
		
		return new ResponseEntity<>(examPeriodDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<ExamPeriodDTO> getById(@PathVariable Long id){
		ExamPeriod found = examPeriodService.findOne(id);
		if(found == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new ExamPeriodDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ExamPeriodDTO> add(@RequestBody ExamPeriodDTO examPeriod){
		ExamPeriod newExamPeriod = new ExamPeriod();
		newExamPeriod.setName(examPeriod.getName());
		newExamPeriod.setStartDate(examPeriod.getStartDate());
		newExamPeriod.setEndDate(examPeriod.getEndDate());
		
		examPeriodService.save(newExamPeriod);
		return new ResponseEntity<>(new ExamPeriodDTO(newExamPeriod), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json", value="/{id}")
	public ResponseEntity<ExamPeriodDTO> update(@RequestBody ExamPeriodDTO examPeriod){
		ExamPeriod found = examPeriodService.findOne(examPeriod.getId());
		if(found == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		found.setName(examPeriod.getName());
		found.setStartDate(examPeriod.getStartDate());
		found.setEndDate(examPeriod.getEndDate());
		
		examPeriodService.save(found);
		return new ResponseEntity<>(new ExamPeriodDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		ExamPeriod found = examPeriodService.findOne(id);
		if(found != null){
			try {
				examPeriodService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
