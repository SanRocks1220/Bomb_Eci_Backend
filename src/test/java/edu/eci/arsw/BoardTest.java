package edu.eci.arsw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.eci.arsw.controllers.Board;

public class BoardTest {
    
    @Test
    public void ShouldPutAllBetterBombs(){
        Board board = new Board();
        int bombs = board.getBomb();
        assertEquals(0, bombs);
    }
    @Test
    public void ShouldPutAllRadius(){
        Board board = new Board();
        int radius = board.getRadius();
        assertEquals(0, radius);
    }
    @Test
    public void ShouldPutAllShields(){
        Board board = new Board();
        int shields = board.getShield();
        assertEquals(0, shields);
    }


    @Test
    public void ShouldReturnTheJsonOfTheBoard(){
        Board board = new Board();
        String jsonMapped = board.getBoardJsonMode();
        assertTrue(jsonMapped.contains("[[{\"x\":0,\"y\":0,\"destroyable\":false,\"empty\":false}"));
    }




}
