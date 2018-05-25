package com.eEducation.ftn.web.dto;

import java.util.Date;

import com.eEducation.ftn.model.Payment;

public class PaymentDTO {
	
	private Integer id;
    private StudentDTO student;
    private Float amount;
    private String cause;
    private Date paymentDate;
    private Boolean owes;
	
	public PaymentDTO() {}

	public PaymentDTO(Payment payment) {
		super();
		this.id = payment.getId();
		this.student = new StudentDTO(payment.getStudent());
		this.amount = payment.getAmount();
		this.cause = payment.getCause();
		this.paymentDate = payment.getPaymentDate();
		this.owes = payment.getOwes();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
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

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Boolean getOwes() {
		return owes;
	}

	public void setOwes(Boolean owes) {
		this.owes = owes;
	}

}
