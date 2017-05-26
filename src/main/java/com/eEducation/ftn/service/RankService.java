package com.eEducation.ftn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eEducation.ftn.model.Rank;
import com.eEducation.ftn.repository.RankRepository;

@Service
public class RankService {
	@Autowired
	RankRepository rankRepository;
	
	public Rank findOne(Long id) {
		return rankRepository.findOne(id);
	}

	public List<Rank> findAll() {
		return rankRepository.findAll();
	}
	
	public Page<Rank> findAll(Pageable page) {
		return rankRepository.findAll(page);
	}

	public Rank save(Rank rank) {
		return rankRepository.save(rank);
	}

	public void remove(Long id) {
		rankRepository.delete(id);
	}
}
