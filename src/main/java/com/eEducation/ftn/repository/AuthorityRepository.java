package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	  public Authority findByName(String name);
}
