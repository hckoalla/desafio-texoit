package com.desafiotexoit.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.desafiotexoit.entity.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

	Producer findByName(String name);

	@Query(nativeQuery = true, value ="SELECT producer_name as producer"
			+ "			,followingWin"
			+ "			,previousWin"
			+ "			,followingWin - previousWin AS winInterval"
			+ "		FROM ("
			+ "			SELECT p.name AS producer_name"
			+ "				,m.release_year AS followingWin"
			+ "				,LAG(m.release_year) OVER ("
			+ "					PARTITION BY p.id ORDER BY m.release_year"
			+ "					) AS previousWin"
			+ "			FROM producer p"
			+ "			LEFT JOIN movie_producers mp ON mp.producers_id = p.id"
			+ "			LEFT JOIN movie m ON mp.movies_id = m.id"
			+ "			WHERE m.winner = true"
			+ "			)"
			+ "		WHERE previousWin IS NOT NULL"
			+ "		ORDER BY winInterval ASC LIMIT 1")
	List<WinnerIntervalProjection> findWinnerMinorInterval();

	@Query(nativeQuery = true, value ="SELECT producer_name as producer"
			+ "			,followingWin"
			+ "			,previousWin"
			+ "			,followingWin - previousWin AS winInterval"
			+ "		FROM ("
			+ "			SELECT p.name AS producer_name"
			+ "				,m.release_year AS followingWin"
			+ "				,LAG(m.release_year) OVER ("
			+ "					PARTITION BY p.id ORDER BY m.release_year"
			+ "					) AS previousWin"
			+ "			FROM producer p"
			+ "			LEFT JOIN movie_producers mp ON mp.producers_id = p.id"
			+ "			LEFT JOIN movie m ON mp.movies_id = m.id"
			+ "			WHERE m.winner = true"
			+ "			)"
			+ "		WHERE previousWin IS NOT NULL"
			+ "		ORDER BY winInterval DESC LIMIT 1")
	List<WinnerIntervalProjection> findWinnerMajorInterval();
	
}
