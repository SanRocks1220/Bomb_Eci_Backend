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

    @Test
    void testGetPlayer() {
        Box box = new Box(1, 2);
        Player player1 = new Player(1, 2, "juanito", false, 0);
        box.setPlayer(player1);
        assertEquals(player1, box.getPlayer());
    }

    @Test
    void testGetPowerUp() {
        Box box = new Box(1, 2);
        PowerUp powerUp = new PowerUp(PowerUpType.BOMB_UP);
        box.setPowerUp(powerUp);
        assertEquals(powerUp, box.getPowerUp());
    }

    @Test
    void testGetPuType() {
        Box box = new Box(1, 2);
        PowerUp powerUp = new PowerUp(PowerUpType.BOMB_UP);
        box.setPowerUp(powerUp);
        assertEquals(PowerUpType.BOMB_UP.toString(), box.getPuType());
    }

    @Test
    void testGetX() {
        Box box = new Box(1, 2);
        assertEquals(1, box.getX());
    }

    @Test
    void testGetY() {
        Box box = new Box(1, 2);
        assertEquals(2, box.getY());
    }

    @Test
    void testHasBomb() {
        Box box = new Box(1, 2);
        Player player1 = new Player(1, 2, "juanito", false, 0);
        box.setBomb(1, 2, player1);
        assertTrue(box.hasBomb());
    }

    @Test
    void testHasPlayer() {
        Box box = new Box(1, 2);
        Player player1 = new Player(1, 2, "juanito", false, 0);
        box.setPlayer(player1);
        assertTrue(box.hasPlayer());
    }

    @Test
    void testHasPowerUp() {
        Box box = new Box(1, 2);
        PowerUp powerUp = new PowerUp(PowerUpType.BOMB_UP);
        box.setPowerUp(powerUp);
        assertTrue(box.hasPowerUp());
    }

    @Test
    void testIsDestroyable() {
        Box box = new Box(1, 2);
        assertFalse(box.isDestroyable());
    }

    @Test
    void testIsEmpty() {
        Box box = new Box(1, 2);
        assertTrue(box.isEmpty());
    }

    @Test
    void testSetX() {
        Box box = new Box(1, 2);
        box.setX(3);
        assertEquals(3, box.getX());
    }

    @Test
    void testSetY() {
        Box box = new Box(1, 2);
        box.setY(4);
        assertEquals(4, box.getY());
    }
}
