package com.desafiotexoit.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafiotexoit.entity.Studio;

public interface StudioRepository extends JpaRepository<Studio, Long> {

	Studio findByName(String name);
	
}
