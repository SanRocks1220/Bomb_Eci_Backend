package edu.eci.arsw;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import edu.eci.arsw.controllers.Game;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw"})
public class BombDaECI {
	
	private static BombDaECI object;
	private HashMap<Integer, Game> openGames;

	private BombDaECI(){
    }

	public synchronized static BombDaECI getInstance() {
        if(object == null) {
           object = new BombDaECI();
        }
        return object;
    }

	public static void main(String[] args) {
		SpringApplication.run(BombDaECI.class, args);
	}
}
