package com.desafiotexoit.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiotexoit.data.StudioRepository;
import com.desafiotexoit.entity.Studio;

@Service
public class StudioService {

	@Autowired
	private StudioRepository studioRepository;

	public List<Studio> findOrCreateStudiosByNames(String names) {
		List<Studio> studios = new ArrayList<>();
		String[] nameSplit = names.trim().split(",");
		for (String name : nameSplit) {
			name = name.trim();
			if (name.isBlank() || name.isEmpty() || name.length() == 0) {
				continue;
			}
			Studio studio = studioRepository.findByName(name);
			if (studio == null) {
				studio = studioRepository.save(new Studio(name));
			}
			studios.add(studio);
		}
		return studios;
	}

}
