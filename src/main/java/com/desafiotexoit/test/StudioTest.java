package com.desafiotexoit.test;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.desafiotexoit.data.StudioRepository;
import com.desafiotexoit.entity.Studio;

@SpringBootTest
public class StudioTest {

	@Autowired
	private StudioRepository studioRepository;

	@Test
	public void checkQuantity() {
		List<Studio> studios = studioRepository.findAll();
		Assertions.assertEquals(59, studios.size());

		List<Studio> studiosEmpty = studios.stream().filter(p -> p.getName().isBlank() || p.getName().isEmpty())
				.collect(Collectors.toList());
		Assertions.assertEquals(0, studiosEmpty.size());
	}

}
