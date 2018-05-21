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

import com.eEducation.ftn.service.PaymentService;
import com.eEducation.ftn.service.StudentService;
import com.eEducation.ftn.web.dto.PaymentDTO;

@RestController
@RequestMapping(value="api/payments")
public class PaymentController {
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PaymentDTO>> getPayments(){
		List<Payment> payments = paymentService.findAll();
		List<ParmentDTO> paymentDTOs = new ArrayList<>();
		
		for(Payment p : payments) {
			paymentDTOs.add(new PaymentDTO(p));
		}
		
		return new ResponseEntity<>(paymentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<PaymentDTO> getPayment(@PathVariable Integer id){
		Payment found = paymentService.findOne(id);
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new PaymentDTO(found), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PaymentDTO> savePayment(@RequestBody PaymentDTO payment){
		Payment newPayment = new Payment();
		
		if(payment.getAccountNumber() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// naci kako ide za find by kriterijum
		Student student = sudentService.findByAccountNumber(payment.getAccountNumber());
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		newPayment.setAccountNumber(payment.getAccountNumber());
		newPayment.setCause(payment.getCause());
		newPayment.setPaymentDate(payment.getPaymentDate());
		newPayment.setOwes(payment.getOwes());
		
		paymentService.save(newPayment);
		return new ResponseEntity<>(new PaymentDTO(newPayment), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<PaymentDTO> updatePayment(@RequestBody PaymentDTO payment){
		Payment found = paymentService.findOne(payment.getId());
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		// not allowed to change account number
		
		found.setCause(payment.getCause());
		found.setPaymentDate(payment.getPaymentDate());
		found.setOwes(payment.getOwes());
		
		paymentService.save(newPayment);
		return new ResponseEntity<>(new PaymentDTO(newPayment), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletePayment(@PathVariable Integer id){
		Payment found = paymentService.findOne(id);
		if(found != null) {
			paymentService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// collection methods
}
