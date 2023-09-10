package com.desafiotexoit.test;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.desafiotexoit.data.ProducerRepository;
import com.desafiotexoit.entity.Producer;

@SpringBootTest
public class ProducerTest {

	@Autowired
	private ProducerRepository producerRepository;

	@Test
	public void checkQuantity() {
		List<Producer> producers = producerRepository.findAll();
		Assertions.assertEquals(359, producers.size());

		List<Producer> producersEmpty = producers.stream().filter(p -> p.getName().isBlank() || p.getName().isEmpty())
				.collect(Collectors.toList());
		Assertions.assertEquals(0, producersEmpty.size());
	}

}
