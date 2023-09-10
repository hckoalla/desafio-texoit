package com.desafiotexoit.test;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.desafiotexoit.data.MovieRepository;
import com.desafiotexoit.entity.Movie;

@SpringBootTest
public class MovieTest {

	@Autowired
	private MovieRepository movieRepository;

	@Test
	public void checkQuantity() {
		List<Movie> movies = movieRepository.findAll();
		Assertions.assertEquals(206, movies.size());
		
		List<Movie> moviesWinner = movies.stream().filter(m -> m.getWinner()).collect(Collectors.toList());
		Assertions.assertEquals(42, moviesWinner.size());
	}

}
