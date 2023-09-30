package com.desafiotexoit.job;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafiotexoit.core.MovieService;
import com.desafiotexoit.core.WinnerIntervalService;
import com.desafiotexoit.dto.MovieDTO;

@Component
public class ImportMoviesJob {

	@Autowired
	private MovieService movieService;

	@Autowired
	private WinnerIntervalService winnerIntervalService;

	@PostConstruct
	public void execute() throws IOException {
		// Read files in folder
		List<MovieDTO> movies = new ArrayList<>();
		Path folder = FileSystems.getDefault().getPath(definePath());
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder, "*.csv")) {
			for (Path filePath : stream) {
				movies.addAll(readCSVFile(filePath.toString()));
			}
		}

		// Persist file
		for (MovieDTO dto : movies) {
			movieService.createMovie(dto);
		}

		// Create View
		winnerIntervalService.createView();
	}

	private String definePath() {
		return new File(System.getProperty("user.dir")).getAbsolutePath() + "\\import";
	}

	@SuppressWarnings("deprecation")
	private List<MovieDTO> readCSVFile(String path) throws IOException {
		List<MovieDTO> data = new ArrayList<>();

		try (Reader reader = new FileReader(path);
				CSVParser csvParser = new CSVParser(reader, CSVFormat.newFormat(';').withHeader())) {

			for (CSVRecord csvRecord : csvParser) {
				MovieDTO dto = new MovieDTO();
				dto.setYear(csvRecord.get("year"));
				dto.setTitle(csvRecord.get("title"));
				dto.setStudios(csvRecord.get("studios"));
				dto.setProducers(csvRecord.get("producers"));
				dto.setWinner(csvRecord.get("winner"));
				data.add(dto);
			}
		}

		return data;
	}

}
