package edu.eci.arsw;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.eci.arsw.controllers.Board;
import edu.eci.arsw.controllers.Game;
import edu.eci.arsw.entities.Player;
import edu.eci.arsw.model.GameMode;

public class PlayerTest {

    public Game game;
    public GameMode gameMode;
    public Board board;

    public ArrayList<Player> players;

    public Player player1;
    public Player player2;
    public Player player3;
    public Player player4;

    @Before
    public void initTests() {
        game = new Game();
        gameMode = GameMode.MULTI_PLAYER;
        game.orchest(gameMode);

        players = game.getPlayers();
        player1 = players.get(0);
        player2 = players.get(1);
        player3 = players.get(2);
        player4 = players.get(3);
        
    }

    @Test
    public void testMoveDown() {
        for (Player player: players){
            player.moveDown();
        }

        assertEquals(2,player1.getXPosition());
        assertEquals(2,player2.getXPosition());
        assertEquals(10,player3.getXPosition());
        assertEquals(10,player4.getXPosition());
    }

    @Test
    public void testMoveLeft() {
        for (Player player: players){
            player.moveLeft();
        }

        assertEquals(1,player1.getYPosition());
        assertEquals(9,player2.getYPosition());
        assertEquals(1,player3.getYPosition());
        assertEquals(9,player4.getYPosition());

    }

    @Test
    public void testMoveRight() {
        for (Player player: players){
            player.moveRight();
        }

        assertEquals(2,player1.getYPosition());
        assertEquals(10,player2.getYPosition());
        assertEquals(2,player3.getYPosition());
        assertEquals(10,player4.getYPosition());
    }

    @Test
    public void testMoveUp() {
        for (Player player: players){
            player.moveUp();
        }

        assertEquals(1,player1.getXPosition());
        assertEquals(1,player2.getXPosition());
        assertEquals(9,player3.getXPosition());
        assertEquals(9,player4.getXPosition());
    }

    @Test
    public void testlayer1Movement() {
        for(int i = 0; i < 4; i++){
            player1.moveRight();
        }

        for(int i = 0; i < 2; i++){
            player1.moveUp();
        }

        for(int i = 0; i < 3; i++){
            player1.moveLeft();
        }

        for(int i = 0; i < 1; i++){
            player1.moveDown();
        }

        assertEquals(2,player1.getXPosition());
        assertEquals(1,player1.getYPosition());
    }
}
