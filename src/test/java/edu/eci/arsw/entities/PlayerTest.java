package edu.eci.arsw.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.eci.arsw.controllers.Board;
import edu.eci.arsw.controllers.Game;
import edu.eci.arsw.model.GameMode;


class PlayerTest {
    public Game game;
    public GameMode gameMode;
    public Board board;

    public ArrayList<Player> players;

    public Player player1;
    public Player player2;
    public Player player3;
    public Player player4;

    @BeforeEach
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
    void testToString() {
        Player player1 = new Player(1, 2, "juanito", false,1);
        System.out.println(player1.toString());
        assertTrue(player1.toString().contains("{\"xPosition\": 1, \"yPosition\": 2, \"name\": \"juanito\", \"isAlive\": true,"));
    }

    @Test
    void testMoveLeft() {
        for (Player player: players){
            player.moveLeft();
        }

        assertEquals(1,player1.getYPosition());
        assertEquals(9,player2.getYPosition());
        assertEquals(1,player3.getYPosition());
        assertEquals(9,player4.getYPosition());
    }

    @Test
    void testMoveDown() {
        for (Player player: players){
            player.moveDown();
        }

        assertEquals(2,player1.getXPosition());
        assertEquals(2,player2.getXPosition());
        assertEquals(10,player3.getXPosition());
        assertEquals(10,player4.getXPosition());
    }

    @Test
    void testMoveRight() {
        for (Player player: players){
            player.moveRight();
        }

        assertEquals(2,player1.getYPosition());
        assertEquals(10,player2.getYPosition());
        assertEquals(2,player3.getYPosition());
        assertEquals(10,player4.getYPosition());
    }
    
    @Test
    void testMoveUp() {
        for (Player player: players){
            player.moveUp();
        }

        assertEquals(1,player1.getXPosition());
        assertEquals(1,player2.getXPosition());
        assertEquals(9,player3.getXPosition());
        assertEquals(9,player4.getXPosition());
    }

    @Test
    void testlayer1Movement() {
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
    @Test
    void testFreeBox() {
        Game game = new Game();
        game.orchest(GameMode.MULTI_PLAYER);

        ArrayList<Player> players = game.getPlayers();
        Player player1 = players.get(0);

        player1.moveRight();
        player1.moveUp();

        player1.freeBox(player1.getXPosition(), player1.getYPosition());

        assertTrue(game.getBoard().getBox(player1.getXPosition(), player1.getYPosition()).isEmpty());
    }

    @Test
    void testGainShields() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        player1.gainShields();
        assertEquals(1, player1.getShields());
        player1.gainShields();
        assertEquals(1, player1.getShields());
    }

    @Test
    void testGetBombs() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertEquals(1, player1.getBombs());
        player1.increaseBombs();
        assertEquals(2, player1.getBombs());
        player1.increaseBombs();
        assertEquals(3, player1.getBombs());
        player1.increaseBombs();
        assertEquals(3, player1.getBombs()); // Se espera que no aumente más allá de 3
    }

    @Test
    void testGetCharacter() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertEquals(1, player1.getCharacter());
    }

    @Test
    void testGetExplosionRadius() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertEquals(1, player1.getExplosionRadius());
        player1.increaseExplosionRadius();
        assertEquals(2, player1.getExplosionRadius());
        player1.increaseExplosionRadius();
        assertEquals(3, player1.getExplosionRadius());
        player1.increaseExplosionRadius();
        assertEquals(4, player1.getExplosionRadius());
        player1.increaseExplosionRadius();
        assertEquals(5, player1.getExplosionRadius());
        player1.increaseExplosionRadius();
        assertEquals(5, player1.getExplosionRadius()); // Se espera que no aumente más allá de 5
    }

    @Test
    void testGetKills() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertEquals(0, player1.getKills());
        player1.increaseKills(2);
        assertEquals(2, player1.getKills());
        player1.increaseKills(3);
        assertEquals(5, player1.getKills());
    }

    @Test
    void testGetShields() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertEquals(0, player1.getShields());
        player1.gainShields();
        assertEquals(1, player1.getShields());
    }

    @Test
    void testGetXPosition() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertEquals(1, player1.getXPosition());
    }

    @Test
    void testGetYPosition() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertEquals(2, player1.getYPosition());
    }

    @Test
    void testIncreaseBombs() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        player1.increaseBombs();
        assertEquals(2, player1.getBombs());
        player1.increaseBombs();
        assertEquals(3, player1.getBombs());
        player1.increaseBombs();
        assertEquals(3, player1.getBombs()); // Se espera que no aumente más allá de 3
    }

    @Test
    void testIncreaseExplosionRadius() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        player1.increaseExplosionRadius();
        assertEquals(2, player1.getExplosionRadius());
        player1.increaseExplosionRadius();
        assertEquals(3, player1.getExplosionRadius());
        player1.increaseExplosionRadius();
        assertEquals(4, player1.getExplosionRadius());
        player1.increaseExplosionRadius();
        assertEquals(5, player1.getExplosionRadius());
        player1.increaseExplosionRadius();
        assertEquals(5, player1.getExplosionRadius()); // Se espera que no aumente más allá de 5
    }

    @Test
    void testIncreaseKills() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertEquals(0, player1.getKills());
        player1.increaseKills(2);
        assertEquals(2, player1.getKills());
        player1.increaseKills(3);
        assertEquals(5, player1.getKills());
    }

    @Test
    void testIsAlive() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertTrue(player1.isAlive());
        player1.die();
        assertFalse(player1.isAlive());
    }

    @Test
    void testIsImmortal() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertFalse(player1.isImmortal());
        player1.setImmortal(true);
        assertTrue(player1.isImmortal());
    }

    @Test
    void testSetCharacter() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertEquals(1, player1.getCharacter());
        player1.setCharacter(2);
        assertEquals(2, player1.getCharacter());
    }

    @Test
    void testSetImmortal() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertFalse(player1.isImmortal());
        player1.setImmortal(true);
        assertTrue(player1.isImmortal());
    }

    @Test
    void testSetXPosition() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertEquals(1, player1.getXPosition());
        player1.setXPosition(3);
        assertEquals(3, player1.getXPosition());
    }

    @Test
    void testSetYPosition() {
        Player player1 = new Player(1, 2, "juanito", false, 1);
        assertEquals(2, player1.getYPosition());
        player1.setYPosition(4);
        assertEquals(4, player1.getYPosition());
    }
}
