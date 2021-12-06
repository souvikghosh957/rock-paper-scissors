package com.game.rps.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Util class for common methods
 *
 */
public class RPSUtil {
	
	/**
	 * Generate a random server move and returns it's value.
	 * @return server's move
	 */
	public static String getServerResponse() {
		int serversCall = ThreadLocalRandom.current().nextInt(1, 4);
		String response = "";
		switch (serversCall) {
		case 1: {
			response = "Rock";
			break;
		}
		case 2: {
			response = "Paper";
			break;
		}
		case 3: {
			response = "Scissors";
			break;
		}
		default:
			break;
		}

		return response;
	}
	
	/**
	 * Generate a server move such that the server always wins
	 * @param userInput - user given input
	 * @return server's move
	 */
	public static String getServerResponse(String userInput) {
		if (userInput != null && !userInput.isBlank() && userInput.equalsIgnoreCase("Rock")) {
			return "Paper";
		} else if (userInput != null && !userInput.isBlank() && userInput.equalsIgnoreCase("Paper")) {
			return "Scissors";
		} else if (userInput != null && !userInput.isBlank() && userInput.equalsIgnoreCase("Scissors")) {
			return "Rock";
		} else {
			return "";
		}
	}

	/**
	 * Takes in both the move and calculate the result
	 * @param userInput
	 * @param serverResponse
	 * @return A pair of user's point and server's point.
	 */
	public static Pair<Integer, Integer> calculateOutcome(String userInput, String serverResponse) {
		if (userInput.equalsIgnoreCase(serverResponse)) {
			return new Pair<Integer, Integer>(0, 0);
		} else if (userInput.equalsIgnoreCase("Rock") && serverResponse.equalsIgnoreCase("Scissors")) {
			return new Pair<Integer, Integer>(1, 0);
		} else if (userInput.equalsIgnoreCase("Scissors") && serverResponse.equalsIgnoreCase("Rock")) {
			return new Pair<Integer, Integer>(0, 1);
		} else if (userInput.equalsIgnoreCase("Paper") && serverResponse.equalsIgnoreCase("Rock")) {
			return new Pair<Integer, Integer>(1, 0);
		} else if (userInput.equalsIgnoreCase("Rock") && serverResponse.equalsIgnoreCase("Paper")) {
			return new Pair<Integer, Integer>(0, 1);
		} else if (userInput.equalsIgnoreCase("Scissors") && serverResponse.equalsIgnoreCase("Paper")) {
			return new Pair<Integer, Integer>(1, 0);
		} else if (userInput.equalsIgnoreCase("Paper") && serverResponse.equalsIgnoreCase("Scissors")) {
			return new Pair<Integer, Integer>(0, 1);
		} else {
			System.out.println("Incorrect user or server input = (" + userInput + ", " + serverResponse + ")");
			return new Pair<Integer, Integer>(0, 0);
		}
	}

	
}
