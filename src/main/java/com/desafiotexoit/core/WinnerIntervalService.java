package com.desafiotexoit.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class WinnerIntervalService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void createView() {
		String dropSQL = "DROP VIEW IF EXISTS WINNER_INTERVAL";
		jdbcTemplate.execute(dropSQL);
		
		String createSQL = " CREATE VIEW WINNER_INTERVAL "
				+ " AS "
				+ " SELECT producer_name AS producer"
				+ "	,followingWin"
				+ "	,previousWin"
				+ "	,followingWin - previousWin AS winInterval"
				+ " FROM ("
				+ "	SELECT p.name AS producer_name"
				+ "		,m.release_year AS followingWin"
				+ "		,LAG(m.release_year) OVER ("
				+ "			PARTITION BY p.id ORDER BY m.release_year"
				+ "			) AS previousWin"
				+ "	FROM producer p"
				+ "	LEFT JOIN movie_producers mp ON mp.producers_id = p.id"
				+ "	LEFT JOIN movie m ON mp.movies_id = m.id"
				+ "	WHERE m.winner = true"
				+ "	)"
				+ " WHERE previousWin IS NOT NULL";
		jdbcTemplate.execute(createSQL);
	}

}
