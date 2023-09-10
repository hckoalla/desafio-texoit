package com.desafiotexoit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiotexoit.core.ProducerService;
import com.desafiotexoit.dto.WinnerIntervalDTO;

@RestController
@RequestMapping("/producers")
public class ProducersController {

	@Autowired
	private ProducerService producerService;

	@GetMapping("/winner-interval")
	public ResponseEntity<WinnerIntervalDTO> winnerInterval() {
		return ResponseEntity.ok(producerService.findWinnerInterval());
	}

}