package edu.eci.arsw.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.eci.arsw.controllers.Board;


public class PlayerTest {

    @Test
    void testToString() {
        Player player1 = new Player(1, 2, "juanito", false,1);
        assertTrue(player1.toString().contains("{\"name\":\"juanito\",\"kills\":0"));
    }
}
