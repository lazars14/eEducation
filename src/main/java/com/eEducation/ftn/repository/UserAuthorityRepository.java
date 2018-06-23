package com.eEducation.ftn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eEducation.ftn.model.UserAuthority;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {

}
