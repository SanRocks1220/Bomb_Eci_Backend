package edu.eci.arsw.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.eci.arsw.model.GameMode;
import edu.eci.arsw.model.PlayerInteraction;
import edu.eci.arsw.controllers.Game;

@Controller
public class STOMPMessagesHandler {
	Game gameInstance = new Game();

	@Autowired
	SimpMessagingTemplate msgt;

    @MessageMapping("/get-board-instance")
	public void handleBoardInstance(String message) throws Exception {
		//System.out.println("A client wants to get a board instance!");
		gameInstance.orchest(GameMode.SINGLE_PLAYER);
		String response = gameInstance.getBoard().toString();
		msgt.convertAndSend("/user/queue/get-board-instance", response);
	}

	@MessageMapping("/get-player-instance")
	public void handlePlayersInstance(String message) throws Exception {
		//System.out.println("A client wants to get a player instance!");
		gameInstance.orchest(GameMode.SINGLE_PLAYER);
		String response = gameInstance.getPlayers().get(0).toString();
		msgt.convertAndSend("/user/queue/get-player-instance", response);
	}
    
	@MessageMapping("/playerInteraction.{numPlayer}")
    @SendToUser("/queue/reply")
	public void handlePlayerMovesEvent(PlayerInteraction pi,@DestinationVariable String numPlayer) throws Exception {
		System.out.println("A player has move!: ");
	}
}
