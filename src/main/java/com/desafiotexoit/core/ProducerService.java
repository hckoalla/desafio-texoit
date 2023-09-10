package com.desafiotexoit.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiotexoit.data.ProducerRepository;
import com.desafiotexoit.data.WinnerIntervalProjection;
import com.desafiotexoit.dto.WinnerIntervalDTO;
import com.desafiotexoit.dto.WinnerIntervalItemDTO;
import com.desafiotexoit.entity.Producer;

@Service
public class ProducerService {

	@Autowired
	private ProducerRepository producerRepository;

	public List<Producer> findOrCreateProducersByNames(String names) {
		List<Producer> producers = new ArrayList<>();
		String[] nameSplit = names.trim().split(",|\\band\\b");
		for (String name : nameSplit) {
			name = name.trim();
			if (name.isBlank() || name.isEmpty() || name.length() == 0) {
				continue;
			}
			Producer producer = producerRepository.findByName(name);
			if (producer == null) {
				producer = producerRepository.save(new Producer(name));
			}
			producers.add(producer);
		}
		return producers;
	}

	public WinnerIntervalDTO findWinnerInterval() {
		WinnerIntervalDTO dto = new WinnerIntervalDTO();

		for (WinnerIntervalProjection p : producerRepository.findWinnerMinorInterval()) {
			dto.getMin().add(new WinnerIntervalItemDTO(p.getProducer(), p.getWinInterval(), p.getPreviousWin(),
					p.getFollowingWin()));
		}

		for (WinnerIntervalProjection p : producerRepository.findWinnerMajorInterval()) {
			dto.getMax().add(new WinnerIntervalItemDTO(p.getProducer(), p.getWinInterval(), p.getPreviousWin(),
					p.getFollowingWin()));
		}

		return dto;
	}

}
