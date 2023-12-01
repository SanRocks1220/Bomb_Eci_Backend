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
	private HashMap<Integer, Game> openGames = new HashMap<>();

	public BombDaECI(){
    }

	public synchronized static BombDaECI getInstance() {
        if(object == null) {
            object = new BombDaECI();
        }
        return object;
    }

	public void createGame(int token, int mode){
		Game game = new Game(mode);
		openGames.put(token, game);
	}

	public static void main(String[] args) {
		SpringApplication.run(BombDaECI.class, args);
	}
}
