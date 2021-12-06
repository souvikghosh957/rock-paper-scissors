package com.game.rps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rock_paper_scissor")
public class RockPaperScissor {
	@Id
	@Column(name = "game_id")
	private int gameId;
	@Column(name = "server_score")
	private int serverScore;
	@Column(name = "user_score")
	private int userScore;
	@Column(name = "game_status")
	private String gameStatus;

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getServerScore() {
		return serverScore;
	}

	public void setServerScore(int serverScore) {
		this.serverScore = serverScore;
	}

	public int getUserScore() {
		return userScore;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	public RockPaperScissor() {
		
	}

	public RockPaperScissor(int gameId, int serverScore, int userScore, String gameStatus) {
		super();
		this.gameId = gameId;
		this.serverScore = serverScore;
		this.userScore = userScore;
		this.gameStatus = gameStatus;
	}
}
