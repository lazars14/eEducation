package com.eEducation.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.eEducation.ftn.model", "com.eEducation.ftn.repository"
		, "com.eEducation.ftn.service", "com.eEducation.ftn.model", "com.eEducation.ftn",
		"com.eEducation.ftn.web.controller", "com.eEducation.ftn.web.dto"})
public class EEducationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EEducationApplication.class, args);
	}
}
