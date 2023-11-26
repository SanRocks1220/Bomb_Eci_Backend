package edu.eci.arsw.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.eci.arsw.model.PowerUpType;

class BoxTest {
    @Test
    void testToString() {
        Box box = new Box(1, 2);
        PowerUp powerUp = new PowerUp(PowerUpType.BOMB_UP);
        box.setPowerUp(powerUp);
        assertTrue(box.toString().contains("{\"x\":1,\"y\":2"));
    }

    @Test
    void testSetPowerUp(){
        Box box = new Box(1, 3);
        box.setPowerUp(new PowerUp(PowerUpType.BOMB_UP));
        assertTrue(box.hasPowerUp());
    }

    @Test
    void testSetPlayer(){
        Box box = new Box(1, 2);
        Player player1 = new Player(1, 2, "juanito", false, 0);
        box.setPlayer(player1);
        assertTrue(box.hasPlayer());
    }

    @Test
    void testSetBomb(){
        Box box = new Box(1, 2);
        Player player1 = new Player(1, 2, "juanito", false, 0);
        box.setBomb(1, 2, player1);
        assertTrue(box.hasBomb());
    }

    @Test
    void testFreeBox(){
        Box box = new Box(1, 2);
        Player player1 = new Player(1, 2, "juanito", false, 0);
        box.setPlayer(player1);
        box.setBomb(1, 2, player1);
        box.freeBox();
        assertFalse(box.hasPlayer());
    }


}
