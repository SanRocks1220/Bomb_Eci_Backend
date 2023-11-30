package edu.eci.arsw.entities;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

public class BlockTest {
    @Test
    void testIsEmpty() {
        Block block = new Block(0, 0, false);
        assertFalse(block.isDestroyable());
    }
}
