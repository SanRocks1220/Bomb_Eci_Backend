package edu.eci.arsw.messaging;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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
import edu.eci.arsw.entities.Player;

@Controller
public class STOMPMessagesHandler {
	Game gameInstance = new Game();
	ArrayList<String> tokens = new ArrayList<>();
	int usersLogged = 0;
	Map<String, Integer> chosenPlayers = new HashMap<>();
	ArrayList<String> players;

	@Autowired
	SimpMessagingTemplate msgt;

    @MessageMapping("/get-board-instance.{userId}")
	public void handleBoardInstance(String message, @DestinationVariable String userId) throws Exception {
		// Se verifica si ya se instancio el tablero de juego
		if(!gameInstance.isInstantiated()){
			gameInstance.orchest(GameMode.MULTI_PLAYER);
		}
		if(usersLogged < 4){
			// Se inicializa al jugador
			gameInstance.getPlayers().get(usersLogged).setName(userId);
			gameInstance.getPlayers().get(usersLogged).setCharacter(usersLogged);
			// Se retorna la instancia de tablero
			String board = gameInstance.getBoard().toString();
			msgt.convertAndSend("/user/queue/get-board-instance." + userId, board);
			// Se retorna el personaje seleccionado
			String character = "" + gameInstance.getPlayers().get(usersLogged).getCharacter();
			msgt.convertAndSend("/user/queue/set-chosen-character." + userId, character);
			// Se aumenta el número de jugadores logeados a un tablero hasta un maximo de 4
			usersLogged++;
			System.out.println(gameInstance.getPlayers().get(usersLogged).toString());
		}
	}

	@MessageMapping("/get-board-instance-in-game.{userId}")
	public void handleBoardInstanceInGame(String message, @DestinationVariable String userId) throws Exception {
		// String[][] response1 = gameInstance.getBoard().getBordInstance();
		// for(int i = 0 ; i < 12 ; ++i){
		// 	System.out.print("" + i + " :");
		//    for(int j = 0 ; j < 12 ; ++j){
		// 	  System.out.print(" " + response1[i][j]);
		//    }
		// 	System.out.print("\n");
		//  }
		// int x = gameInstance.getPlayers().get(0).getXPosition();
		// int y = gameInstance.getPlayers().get(0).getYPosition();
		// System.out.print(x + ", " + y);
		String response = gameInstance.getBoard().toString();
		msgt.convertAndSend("/user/queue/get-board-instance-in-game." + userId, response);
	}

	@MessageMapping("/get-players-instance.{userId}")
	public void handlePlayersInstance(String message, @DestinationVariable String userId) throws Exception {
		//System.out.println("A client wants to get a player instance!");
		// if(!gameInstance.isInstantiated()){
		// 	gameInstance.orchest(GameMode.MULTI_PLAYER);
		// }
		this.players = new ArrayList<>();
		// Genera una lista con los jugadores excluyendo al jugador que la solicita
		for (Player p : gameInstance.getPlayers()){
			if(!p.getName().contains(userId)){
				players.add(p.toString());
			}
		}
		// Mapea en un JSON que luego entrega
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String response = objectMapper.writeValueAsString(players);
			msgt.convertAndSend("/user/queue/get-players-instance." + userId, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@MessageMapping("/get-player-instance.{userId}")
	public void handlePlayerInstance(String message, @DestinationVariable String userId) throws Exception {
		//System.out.println("A client wants to get a player instance!");
		// Genera una lista con los jugadores excluyendo al jugador que la solicita
		if(!gameInstance.isInstantiated()){
			gameInstance.orchest(GameMode.MULTI_PLAYER);
		}
		for (Player p : gameInstance.getPlayers()){
			if(p.getName().contains(userId)){
				String response = p.toString();
				msgt.convertAndSend("/user/queue/get-player-instance." + userId, response);
				break;
			}
		}
	}	
    
	@MessageMapping("/player-interaction.{userId}")
	public void handlePlayerInteraction(PlayerInteraction pi, @DestinationVariable String userId) throws Exception {
		// Busca al jugador que interactuo para asignarle la interaccion
		for (Player p : gameInstance.getPlayers()){
			if(p.getName().contains(userId)){
				// Ejecuta la acción
				p.action(pi);
				// Retorna el nuevo estado del jugador
				String player = p.toString();
				msgt.convertAndSend("/user/queue/get-player-instance." + userId, player);
				// Retorna el nuevo estado del tablero
				String board = gameInstance.getBoard().toString();
				msgt.convertAndSend("/user/queue/get-board-instance." + userId, board);
				break;
			}
		}
	}

	@MessageMapping("/get-chosen-character.{userId}")
	public void handleGetChosenCharacter(String message, @DestinationVariable String userId) throws Exception {
		//System.out.println("A client wants to get a player instance!");
		if(tokens.contains(userId)){
			String response = "" + chosenPlayers.get(userId);
			msgt.convertAndSend("/user/queue/get-chosen-character." + userId, response);
		}
	}
}