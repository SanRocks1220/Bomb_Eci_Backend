package edu.eci.arsw;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import edu.eci.arsw.controllers.Game;
import edu.eci.arsw.model.GameMode;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw"})
public class BombDaECI {
	
	private static BombDaECI object;
	private HashMap<String, Game> openGames = new HashMap<>();

	public BombDaECI(){
    }

	public synchronized static BombDaECI getInstance() {
        if(object == null) {
            object = new BombDaECI();
        }
        return object;
    }

	public void createGame(String token, int mode, String P1Token, String P1ch){
		Game game = new Game(mode, P1Token, P1ch);
		openGames.put(token, game);
	}

	public Game getGameInstance(String token){
		return openGames.get(token);
	}

	public static void main(String[] args) {
		SpringApplication.run(BombDaECI.class, args);
	}

	public void action(String token, String player, String action){
		openGames.get(token).action(player, action);
	}

	public void addPlayer(String token, String Ptoken, String Pch) {
		openGames.get(token).addPlayer(Ptoken, Pch);
	}

	public String getBoard(String token){
		return openGames.get(token).getBoard();
	}

	public boolean hasInstance(String token){
		return openGames.containsKey(token); 
	}
}
