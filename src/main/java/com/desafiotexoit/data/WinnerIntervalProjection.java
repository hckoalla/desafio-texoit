package com.desafiotexoit.data;

public interface WinnerIntervalProjection {
	String getProducer();

	Integer getWinInterval();

	Integer getPreviousWin();

	Integer getFollowingWin();
}
