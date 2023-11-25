package edu.eci.arsw.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import edu.eci.arsw.model.PlayerInteraction;

@Controller
public class STOMPMessagesHandler {
	@Autowired
	SimpMessagingTemplate msgt;

    @MessageMapping("/app/getBombDaEciInstance")
	public void handleBoardInstance() throws Exception {
		System.out.println("A client wants to get a board instance!");
		msgt.convertAndSend("/topic/getBombDaEciInstance", "Loud and Clear");
	}
    
	@MessageMapping("/playerInteraction.{numPlayer}")
    @SendToUser("/queue/reply")
	public void handlePlayerMovesEvent(PlayerInteraction pi,@DestinationVariable String numPlayer) throws Exception {
		System.out.println("A player has move!: ");
	}
}
