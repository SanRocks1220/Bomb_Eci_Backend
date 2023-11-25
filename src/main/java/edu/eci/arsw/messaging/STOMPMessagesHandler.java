package edu.eci.arsw.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import edu.eci.arsw.model.PlayerInteraction;
import edu.eci.arsw.controllers.Game;

@Controller
public class STOMPMessagesHandler {
    int currentClient = 0;
	
	@Autowired
	SimpMessagingTemplate msgt;

	/*

    @MessageMapping("/app/getBombDaEciInstance")
    @SendToUser("/queue/reply")
	public void handleBoardInstance() throws Exception {
		System.out.println("A client want to get a board instance!: "+currentClient);
        board = currentGame.getBoard();
		msgt.convertAndSend("/topic/foreignPlayerMoves", currentClient, board);
	}
    
	@MessageMapping("/playerInteraction.{numPlayer}")
    @SendToUser("/queue/reply")
	public void handlePlayerMovesEvent(PlayerInteraction pi,@DestinationVariable String numPlayer) throws Exception {
		System.out.println("A player has move!: "+pi);
        currentGame.calculate(pi);
		msgt.convertAndSend("/topic/foreignPlayerMoves"+numPlayer, pi);
	} */
}


