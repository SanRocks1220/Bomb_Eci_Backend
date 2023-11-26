package edu.eci.arsw.entities;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class BlockTest {

    @Test
    void testShouldGetTheRightJson(){
        Block block = new Block(1, 2, true);
        assertTrue(block.toString().contains("{\"x\":1,\"y\":2,\"destroyable\":true,\""));
    }
}
