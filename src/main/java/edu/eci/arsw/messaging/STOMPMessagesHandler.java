package edu.eci.arsw.messaging;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.eci.arsw.model.GameMode;
import edu.eci.arsw.model.PlayerInteraction;
import edu.eci.arsw.BombDaECI;
import edu.eci.arsw.controllers.Game;
import edu.eci.arsw.entities.Player;

@Controller
public class STOMPMessagesHandler {
	BombDaECI handler = new BombDaECI();
	ArrayList<String> tokens = new ArrayList<>();
	int usersLogged = 0;
	Map<String, Integer> chosenPlayers = new HashMap<>();
	ArrayList<String> players;

	@Autowired
	SimpMessagingTemplate msgt;

    @MessageMapping("/create-game.{userId}")
	public void handleGameInstance(String gameId, @DestinationVariable String userId) throws Exception {
		// Se verifica si ya se instancio el tablero de juego
		System.out.println(gameId);
		if(!handler.hasInstance(gameId)){
			handler.createGame(gameId, 1, userId, "" + usersLogged);
		}
		// Se valida que se registren 4 jugadores
		if(usersLogged < 4){
			// Se inicializa al jugador
			handler.addPlayer(gameId, userId, "" + usersLogged);
			// Se define el personaje o sprite
			msgt.convertAndSend("/user/queue/set-character." + userId, usersLogged);
			// Se aumenta el número de jugadores logeados a un tablero hasta un maximo de 4
			usersLogged++;
		}else{
			// Se retorna la instancia de tablero una vez existen 4 usuarios en un mismo juego
			String board = handler.getBoard(gameId);
			msgt.convertAndSend("/user/queue/create-game", board);
		}
	}

	@MessageMapping("/get-board-instance.{userId}")
	public void handleBoardInstanceInGame(String gameId, @DestinationVariable String userId) throws Exception {
		if (handler.hasInstance(gameId)){
			String board = handler.getBoard(gameId);
			msgt.convertAndSend("/user/queue/get-board-instance." + userId, board);
		}
	}

	@MessageMapping("/get-players.{userId}")
	public void handlePlayersInstance(String gameId, @DestinationVariable String userId) throws Exception {
		//System.out.println("A client wants to get a player instance!");
		this.players = new ArrayList<>();
		if(handler.hasInstance(gameId)){
			System.out.println(handler.getPlayers(gameId));
			// Genera una lista con los jugadores excluyendo al jugador que la solicita
			for (Player p: handler.getPlayers(gameId)) {
				if (!p.getId().equals(userId)) {
					players.add(p.toString());
				}
			}
			// Mapea en un JSON que luego entrega
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				String response = objectMapper.writeValueAsString(players);
				msgt.convertAndSend("/user/queue/get-players." + userId, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@MessageMapping("/get-my-player.{userId}")
	public void handlePlayerInstance(String gameId, @DestinationVariable String userId) throws Exception {
		//System.out.println("A client wants to get a player instance!");
		System.out.println(gameId);
		Player p = handler.getPlayer(gameId, userId);
		String response = p.toString();
		msgt.convertAndSend("/user/queue/get-player-instance." + userId, response);
	}
    
	@MessageMapping("/player-interaction.{userId}")
	public void handlePlayerInteraction(@Payload String key, @Payload String gameId, @DestinationVariable String userId) throws Exception {
		System.out.println(gameId  + ", " + gameId.replaceAll("\\\"", ""));
		System.out.println(key  + ", " + key.replaceAll("\\\"", ""));
		// Busca al jugador que interactuo para asignarle la interaccion
		handler.action(gameId.replaceAll("\\\"", ""), userId, key.replaceAll("\\\"", ""));
		// Retorna el nuevo estado del jugador
		Player p = handler.getPlayer(gameId, userId);
		String player = p.toString();
		msgt.convertAndSend("/user/queue/get-my-player." + userId, player);
		// Retorna el nuevo estado del tablero
		String board = handler.getBoard(gameId);
		msgt.convertAndSend("/user/queue/get-board-instance." + userId, board);
	}
}