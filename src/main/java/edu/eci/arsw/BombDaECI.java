package edu.eci.arsw;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import edu.eci.arsw.controllers.Game;
import edu.eci.arsw.entities.Player;
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
		if(!openGames.get(token).isfinished()){
			openGames.get(token).action(player, action);
		} else {
			Player winner = openGames.get(token).getWinner();
		}
		
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

	public Player getPlayer(String token, String Ptoken) {
		if(openGames.containsKey(token)){
			return openGames.get(token).getPlayer(Ptoken);
		}
        return null;
    }

	public List<Player> getPlayers(String token) {
        Collection<Player> playersCollection = openGames.get(token).getPlayers().values();
		List<Player> playersList = new ArrayList<>(playersCollection);
		return playersList;
    }
}
