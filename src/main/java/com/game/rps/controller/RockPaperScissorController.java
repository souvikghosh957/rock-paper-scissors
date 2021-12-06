package com.game.rps.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.rps.response.GameResponse;
import com.game.rps.service.RockPaperScissorService;
import com.game.rps.util.Pair;
import com.game.rps.util.RPSUtil;

/**
 * The controller class for the application.
 *
 */
@RestController
@RequestMapping("/game")
public class RockPaperScissorController {

	@Autowired
	private RockPaperScissorService rockPaperScissorService;
	
	@GetMapping("/me/{name}")
	public String home(@PathVariable String name) {		
		return "<h2> Hello " + name + "</h2>";
	}
	

	/**
	 * Generate a random token and returns for later uses
	 * @return random token id.
	 */
	@GetMapping("/start")
	public String start() {
		String startMessage = "READY";
		String gameStatus = "START";
		int gameId = ThreadLocalRandom.current().nextInt(1000, 100000);
		rockPaperScissorService.createRecord(gameId, gameStatus);
		return "<h2>" + startMessage + "</h2>" + " \n<h4>Your Token Id: " + gameId + "</h4>";
	}

	/**
	 * Takes tokenId and user input and returns server response and total score. Server response is
	 * random and it's not favored to the server.
	 * @param tokenId
	 * @param userInput
	 * @return GameResponse
	 */
	@GetMapping("/v1/{tokenId}/{userInput}")
	public GameResponse play(@PathVariable int tokenId, @PathVariable String userInput) {
		if (rockPaperScissorService.checkIfGameActive(tokenId)) {
			String serverResponse = RPSUtil.getServerResponse();
			Pair<Integer, Integer> userAndServerPoint = RPSUtil.calculateOutcome(userInput, serverResponse);
			GameResponse gameResponse = rockPaperScissorService.getGameResult(tokenId, userAndServerPoint,
					serverResponse);
			return gameResponse;
		} else {
			GameResponse gameResponse = new GameResponse();
			gameResponse.setGameStatus("Game over! Click 'Start' to play again.");
			return gameResponse;
		}
	}

	/**
	 * Takes tokenId and user input and returns server response and total score. Server response is
	 * is favored to the server's win.
	 * @param tokenId
	 * @param userInput
	 * @return GameResponse
	 */
	@GetMapping("/v2/{tokenId}/{userInput}")
	public GameResponse serverFavoured(@PathVariable int tokenId, @PathVariable String userInput) {
		if (rockPaperScissorService.checkIfGameActive(tokenId)) {
			int serverFavor = ThreadLocalRandom.current().nextInt(1, 11);
			String serverResponse = "";
			if (serverFavor >= 1 && serverFavor < 6) {
				serverResponse = RPSUtil.getServerResponse(userInput);
			} else {
				serverResponse = RPSUtil.getServerResponse();
			}
			Pair<Integer, Integer> userAndServerPoint = RPSUtil.calculateOutcome(userInput, serverResponse);
			GameResponse gameResponse = rockPaperScissorService.getGameResult(tokenId, userAndServerPoint,
					serverResponse);
			return gameResponse;
		} else {
			GameResponse gameResponse = new GameResponse();
			gameResponse.setGameStatus("Game over! Click 'Start' to play again.");
			return gameResponse;
		}
	}

}
