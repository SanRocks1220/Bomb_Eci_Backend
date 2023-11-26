package edu.eci.arsw.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    void testExplode() {
        Board board = new Board(); // Ajusta según tu implementación
        int xPosition = 1;
        int yPosition = 3;
        int explosionRadius = 2;
        board.getBox(xPosition, yPosition);
        board.explode(xPosition, yPosition, explosionRadius);
        assertTrue(board.getBox(xPosition, yPosition).isDestroyable());
    }

    @Test
    void testGetBoardJsonMode() {
        Board board = new Board();
        String jsonMapped = board.getBoardJsonMode();
        assertTrue(jsonMapped.contains("[[{\"x\":0,\"y\":0,\"destroyable\""));
    }

    @Test
    void testGetBomb() {
        Board board = new Board();
        int bombs = board.getBomb();
        assertEquals(0, bombs);
    }

    @Test
    void testGetBox() {
        Board board = new Board();
        int xPosition = 1;
        int yPosition = 1;
        int explosionRadius = 1;

        board.getBox(xPosition, yPosition);

        assertTrue(board.getBox(xPosition, yPosition) != null);
    }

    @Test
    void testGetRadius() {
        Board board = new Board();
        int radius = board.getRadius();
        assertEquals(0, radius);

    }

    @Test
    void testGetShield() {
        Board board = new Board();
        int shields = board.getShield();
        assertEquals(0, shields);
    }
}
