package com.desafiotexoit.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiotexoit.data.MovieRepository;
import com.desafiotexoit.dto.MovieDTO;
import com.desafiotexoit.entity.Movie;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private ProducerService producerService;
	@Autowired
	private StudioService studioService;

	public void createMovie(MovieDTO dto) {
		Long year = Long.parseLong(dto.getYear());
		String title = dto.getTitle().trim();
		Movie entity = movieRepository.findByTitleAndReleaseYear(title, year);
		if (entity == null) {
			movieRepository.save(new Movie(year, title, studioService.findOrCreateStudiosByNames(dto.getStudios()),
					producerService.findOrCreateProducersByNames(dto.getProducers()),
					"YES".equalsIgnoreCase(dto.getWinner().trim())));
		}
	}

}
