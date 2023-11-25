package edu.eci.arsw;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.eci.arsw.entities.Box;
import edu.eci.arsw.entities.PowerUp;
import edu.eci.arsw.model.PowerUpType;

public class BoxTest {

    @Test
    public void ShouldGetMeTheJson(){
        Box box = new Box(1, 2);
        PowerUp powerUp = new PowerUp(1, 2, PowerUpType.BOMB_UP);
        box.setPowerBoost(powerUp);
        assertEquals("{\"x\":1,\"y\":2,\"empty\":true}", box.toString());
    }
    
}
