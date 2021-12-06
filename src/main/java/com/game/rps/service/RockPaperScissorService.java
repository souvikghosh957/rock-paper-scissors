package com.game.rps.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.rps.model.RockPaperScissor;
import com.game.rps.repo.RockPaperScissorRepo;
import com.game.rps.response.GameResponse;
import com.game.rps.util.Pair;

/**
 * Service class for the application.
 *
 */
@Service
public class RockPaperScissorService {

	@Autowired
	private RockPaperScissorRepo rockPaperScissorRepo;

	/**
	 * Create the a new record in the DB for the new tokenID
	 * @param gameId
	 * @param gameStatus
	 */
	public void createRecord(int gameId, String gameStatus) {
		RockPaperScissor rockPaperScissor = new RockPaperScissor(gameId, 0, 0, gameStatus);
		rockPaperScissorRepo.save(rockPaperScissor);
	}

	/**
	 *  Calculate, save and returns total game points, status and server's move.
	 * @param tokenId
	 * @param userAndServerPoint
	 * @param serverMove
	 * @return GameResponse
	 */
	public GameResponse getGameResult(int tokenId, Pair<Integer, Integer> userAndServerPoint, String serverMove) {

		Optional<RockPaperScissor> rockPaperScissor = rockPaperScissorRepo.findById(tokenId);
		int serverScore = rockPaperScissor.get().getServerScore();
		int userScore = rockPaperScissor.get().getUserScore();
		userScore = userScore + userAndServerPoint.first;
		serverScore = serverScore + userAndServerPoint.second;
		String gameStatus = rockPaperScissor.get().getGameStatus();
		if (gameStatus.equalsIgnoreCase("START")) {
			gameStatus = "IN PROGRESS";
		} else if (userScore == 3) {
			gameStatus = "USER WON!";
		} else if (serverScore == 3) {
			gameStatus = "SERVER WON!";
		}
		
		RockPaperScissor rpsResonse = prepareRockPaperScissor(tokenId, serverScore, userScore, gameStatus);		
		rockPaperScissorRepo.save(rpsResonse);
		GameResponse gameResponse = prepareGameResponse(userScore, serverScore, gameStatus, serverMove);
		return gameResponse;
	}

	/**
	 * Prepare object to save the updated score and status
	 * @param tokenId
	 * @param serverScore
	 * @param userScore
	 * @param gameStatus
	 * @return RockPaperScissor
	 */
	private RockPaperScissor prepareRockPaperScissor(int tokenId, int serverScore, int userScore,
			String gameStatus) {
		RockPaperScissor rockPaperScissor = new RockPaperScissor(tokenId, serverScore, userScore, gameStatus);
		return rockPaperScissor;
	}

	/**
	 * Prepare the final response which will be returned to the user
	 * @param userScore
	 * @param serverScore
	 * @param gameStatus
	 * @param serverMove
	 * @return GameResponse
	 */
	private GameResponse prepareGameResponse(int userScore, int serverScore, String gameStatus, String serverMove) {
		return new GameResponse(serverMove, String.valueOf(userScore), String.valueOf(serverScore), gameStatus);
	}

	/**
	 * Checks if the game is still active by verifying the game status by tokenId
	 * @param tokenId
	 * @return true if game is active
	 */
	public boolean checkIfGameActive(int tokenId) {
		Optional<RockPaperScissor> rockPaperScissor = rockPaperScissorRepo.findById(tokenId);
		if (rockPaperScissor.isEmpty() || rockPaperScissor.get().getGameStatus().equalsIgnoreCase("USER WON!")
				|| rockPaperScissor.get().getGameStatus().equalsIgnoreCase("SERVER WON!")) {
			return false;
		}
		return true;
	}

}
