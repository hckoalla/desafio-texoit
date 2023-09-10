package com.desafiotexoit.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafiotexoit.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	Movie findByTitleAndReleaseYear(String title, Long releaseYear);
	
}

