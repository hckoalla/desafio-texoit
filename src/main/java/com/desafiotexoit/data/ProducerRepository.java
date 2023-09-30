package com.desafiotexoit.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.desafiotexoit.entity.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

	Producer findByName(String name);

	@Query(nativeQuery = true, value ="SELECT wm.producer"
			+ "	,wm.followingwin"
			+ "	,wm.previouswin"
			+ "	,wm.wininterval"
			+ " FROM winner_interval wm"
			+ " WHERE wm.wininterval = ("
			+ "		SELECT min(wininterval)"
			+ "		FROM winner_interval"
			+ "		);")
	List<WinnerIntervalProjection> findWinnerMinorInterval();

	@Query(nativeQuery = true, value ="SELECT wm.producer"
			+ "	,wm.followingwin"
			+ "	,wm.previouswin"
			+ "	,wm.wininterval"
			+ " FROM winner_interval wm"
			+ " WHERE wm.wininterval = ("
			+ "		SELECT max(wininterval)"
			+ "		FROM winner_interval"
			+ "		)")
	List<WinnerIntervalProjection> findWinnerMajorInterval();
	
}
