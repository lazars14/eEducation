package com.eEducation.ftn.web.dto;

import java.util.Date;

import com.eEducation.ftn.model.Payment;
import com.eEducation.ftn.model.Student;

public class PaymentDTO {
	
	private Integer id;
	private Float amount;
    private String cause;
    private Date pDate;
    private Boolean deleted;
    private Student studentId;
	
	public PaymentDTO() {}
	
	public PaymentDTO(Payment payment) {
		this.id = payment.getId();
		this.amount = payment.getAmount();
		this.cause = payment.getCause();
		this.pDate = payment.getPDate();
		this.deleted = payment.getDeleted();
		this.studentId = payment.getStudentId();
	}
	
	public PaymentDTO(Integer id, Float amount, String cause, Date pDate, Boolean deleted, Student studentId) {
		super();
		this.id = id;
		this.amount = amount;
		this.cause = cause;
		this.pDate = pDate;
		this.deleted = deleted;
		this.studentId = studentId;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Date getpDate() {
		return pDate;
	}

	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Student getStudentId() {
		return studentId;
	}

	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}
}
