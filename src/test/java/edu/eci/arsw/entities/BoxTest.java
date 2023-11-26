package edu.eci.arsw.entities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.eci.arsw.model.PowerUpType;

public class BoxTest {
    @Test
    void testToString() {
        Box box = new Box(1, 2);
        PowerUp powerUp = new PowerUp(PowerUpType.BOMB_UP);
        box.setPowerUp(powerUp);
        assertTrue(box.toString().contains("{\"x\":1,\"y\":2"));
    }
    @Test
    void testLoquesea(){
        assertTrue(1==1);
    }
}
