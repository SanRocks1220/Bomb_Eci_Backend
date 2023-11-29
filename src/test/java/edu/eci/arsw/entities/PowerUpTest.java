package edu.eci.arsw.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.eci.arsw.entities.Player;
import edu.eci.arsw.entities.PowerUp;
import edu.eci.arsw.model.PowerUpType;

public class PowerUpTest {

    Player player;
    
    PowerUp shield;
    PowerUp bombUp;
    PowerUp rangeUp;


    @Before
    public void initTests() {
        player = new Player(0, 0, "test", false, 0);

        shield = new PowerUp(PowerUpType.SHIELD);
        bombUp = new PowerUp(PowerUpType.BOMB_UP);
        rangeUp = new PowerUp(PowerUpType.RANGE_UP);
    }

    @Test
    public void testGetType() {
        assertEquals(PowerUpType.SHIELD, shield.getType());
        assertEquals(PowerUpType.BOMB_UP, bombUp.getType());
        assertEquals(PowerUpType.RANGE_UP, rangeUp.getType());
    }

    @Test
    public void testAplyShield() {
        int originalShield = player.getShields();
        shield.applyEffect(player);
        assertEquals(originalShield + 1, player.getShields());
    }

    @Test
    public void testAplyBombUp() {
        int originalBombs = player.getBombs();
        bombUp.applyEffect(player);
        assertEquals(originalBombs + 1, player.getBombs());
    }

    @Test
    public void testAplyRangeUp() {
        int originalRadius = player.getExplosionRadius();
        rangeUp.applyEffect(player);
        assertEquals(originalRadius + 1, player.getExplosionRadius());
    }

    @Test
    public void ShouldGetTheJsonForPowerUp(){
        PowerUp newBombUp = new PowerUp(PowerUpType.BOMB_UP);
        assertEquals(newBombUp.toString(), bombUp.toString());
    }
}


