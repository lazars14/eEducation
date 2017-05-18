package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eEducation.ftn.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

}
