package com.game.rps.response;

public class GameResponse {

	private String serverMove;
	private String playerScore;
	private String serverScore;
	private String gameStatus;

	public String getServerMove() {
		return serverMove;
	}

	public void setServerMove(String serverMove) {
		this.serverMove = serverMove;
	}

	public String getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(String playerScore) {
		this.playerScore = playerScore;
	}

	public String getServerScore() {
		return serverScore;
	}

	public void setServerScore(String serverScore) {
		this.serverScore = serverScore;
	}
	
	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	public GameResponse() {
		
	}

	public GameResponse(String serverMove, String playerScore, String serverScore, String gameStatus) {
		super();
		this.serverMove = serverMove;
		this.playerScore = playerScore;
		this.serverScore = serverScore;
		this.gameStatus = gameStatus;
	}

}
