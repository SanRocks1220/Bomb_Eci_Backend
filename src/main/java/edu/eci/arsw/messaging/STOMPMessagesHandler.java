package edu.eci.arsw.messaging;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.eci.arsw.model.GameMode;
import edu.eci.arsw.model.PlayerInteraction;
import edu.eci.arsw.controllers.Game;

@Controller
public class STOMPMessagesHandler {
	Game gameInstance = new Game();
	ArrayList<Integer> charactersChosen = new ArrayList<>();

	@Autowired
	SimpMessagingTemplate msgt;

    @MessageMapping("/get-board-instance.{userId}")
	public void handleBoardInstance(String message, @DestinationVariable String userId) throws Exception {
		//System.out.println("A client wants to get a board instance!");
		gameInstance.orchest(GameMode.SINGLE_PLAYER);
		String response = gameInstance.getBoard().toString();
		msgt.convertAndSend("/user/queue/get-board-instance." + userId, response);
	}

	@MessageMapping("/get-board-instance-in-game.{userId}")
	public void handleBoardInstanceInGame(String message, @DestinationVariable String userId) throws Exception {
		String[][] response1 = gameInstance.getBoard().getBordInstance();
		for(int i = 0 ; i < 12 ; ++i){
			System.out.print("" + i + " :"); 
		   for(int j = 0 ; j < 12 ; ++j){
			  System.out.print(" " + response1[i][j]);
		   }
			System.out.print("\n");
		 }
		int x = gameInstance.getPlayers().get(0).getXPosition();
		int y = gameInstance.getPlayers().get(0).getYPosition();
		System.out.print(x + ", " + y);

		String response = gameInstance.getBoard().toString();
		
		msgt.convertAndSend("/user/queue/get-board-instance-in-game." + userId, response);
	}

	@MessageMapping("/get-player-instance.{userId}")
	public void handlePlayersInstance(String message, @DestinationVariable String userId) throws Exception {
		//System.out.println("A client wants to get a player instance!");
		gameInstance.orchest(GameMode.SINGLE_PLAYER);
		String response = gameInstance.getPlayers().get(0).toString();
		msgt.convertAndSend("/user/queue/get-player-instance." + userId, response);
	}
    
	@MessageMapping("/player-interaction.{userId}")
	public void handlePlayerInteraction(PlayerInteraction pi, @DestinationVariable String userId) throws Exception {
		gameInstance.getPlayers().get(0).action(pi);
		if (pi.getKey().equals(" ")){
			String response = gameInstance.getBoard().toString();
			msgt.convertAndSend("/user/queue/get-board-instance." + userId, response);
		}
		String player = gameInstance.getPlayers().get(0).toString();
		msgt.convertAndSend("/user/queue/get-player-instance." + userId, player);
	}

	@MessageMapping("/set-chosen-character.{userId}")
	public void handleChosenCharacter(String message, @DestinationVariable String userId) throws Exception {
		//System.out.println("A client wants to get a player instance!");
		int character = Integer.parseInt(message);
		gameInstance.getPlayers().get(0).setCharacter(character);
		String response = gameInstance.getPlayers().get(0).toString();
		msgt.convertAndSend("/user/queue/get-player-instance." + userId, response);
	}

	@MessageMapping("/get-user-id")
	public void handleUserId(String message) throws Exception {
		int tokenLength = 16; // Puedes ajustar este valor según la longitud que necesites
        // Generar bytes aleatorios
        byte[] bytes = new byte[tokenLength];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);
		String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
		token = String.format("{\"token\": \"%s\"}", token);
		msgt.convertAndSend("/user/queue/get-user-id", token);
	}


	
}
