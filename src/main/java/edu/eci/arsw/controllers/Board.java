package edu.eci.arsw.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.eci.arsw.entities.Block;
import edu.eci.arsw.entities.Box;
import edu.eci.arsw.entities.PowerUp;
import edu.eci.arsw.model.PowerUpType;

public class Board implements Runnable {

    private Box[][] board;
    private static final int size = 12;
    private int bomb = 6;
    private int radius = 8;
    private int shield = 4;
    private Object lock;
    private String[][] boardInstance = {
            { "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" },
            { "1", "0", "0", "2", "0", "2", "0", "2", "2", "0", "0", "1" },
            { "1", "0", "1", "0", "0", "2", "2", "0", "0", "1", "0", "1" },
            { "1", "2", "0", "2", "2", "1", "1", "2", "2", "0", "2", "1" },
            { "1", "2", "0", "1", "0", "2", "2", "0", "1", "0", "0", "1" },
            { "1", "0", "2", "0", "2", "1", "1", "2", "0", "2", "2", "1" },
            { "1", "2", "2", "0", "2", "1", "1", "2", "0", "2", "0", "1" },
            { "1", "0", "0", "1", "0", "2", "2", "0", "1", "0", "2", "1" },
            { "1", "2", "0", "2", "2", "1", "1", "2", "2", "0", "2", "1" },
            { "1", "0", "1", "0", "0", "2", "2", "0", "0", "1", "0", "1" },
            { "1", "0", "0", "2", "2", "0", "2", "0", "2", "0", "0", "1" },
            { "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" }
        };

    public Board() {
        board = new Box[size][size];
        initializeBoard();
        generateBoosts();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (boardInstance[i][j].equals("0")) {
                    board[i][j] = new Box(i, j);
                } else if (boardInstance[i][j].equals("1")) {
                    board[i][j] = new Block(i, j, false);
                } else if (boardInstance[i][j].equals("2")) {
                    board[i][j] = new Block(i, j, true);
                }
            }
        }
    }

    /**
     * Could be in the next positions:
     * [4][1],[5][1],[6][1],[7][1]
     * [3][3],[3][4],[3][5],[3][6],[3][7],[3][8]
     * [4][3],[5][3],[6][3],[7][3],[8][3]
     * [4][8],[5][8],[6][8],[7][8],[8][8]
     * [8][4],[8][5],[8][6],[8][7]
     * [4][4],[4][5],[4][6],[4][7]
     * [7][4],[7][5],[7][6],[7][7]
     * [5][4],[6][4],[7][4]
     * [5][7],[6][7]
     * [4][10],[5][10],[6][10],[7][10]
     * 
     */
    private void generateBoosts() {
        List<int[]> validPositions = new ArrayList<>();
        validPositions = positionFiller();
        Collections.shuffle(validPositions);

        while (bomb > 0 || radius > 0 || shield > 0) {
            for (int[] position : validPositions) {
                int x = position[0];
                int y = position[1];
                Box box = board[x][y];

                if (box instanceof Block && ((Block) box).isDestroyable()) {
                    // Es una casilla destruible, coloca el boost correspondiente si aún hay
                    // disponibles.
                    if (bomb > 0) {
                        box.setPowerUp(new PowerUp(PowerUpType.BOMB_UP));
                        bomb--;
                    } else if (radius > 0) {
                        box.setPowerUp(new PowerUp(PowerUpType.RANGE_UP));
                        radius--;
                    } else if (shield > 0) {
                        box.setPowerUp(new PowerUp(PowerUpType.SHIELD));
                        shield--;
                    }
                }
            }
        }
    }
    private List<int[]> positionFiller() {
        List<int[]> validPositions = new ArrayList<>();

        int[][] positions = {
                { 4, 1 }, { 5, 1 }, { 6, 1 }, { 7, 1 }, { 3, 3 }, { 3, 4 },
                { 3, 5 }, { 3, 6 }, { 3, 7 }, { 3, 8 }, { 4, 3 }, { 5, 3 },
                { 6, 3 }, { 7, 3 }, { 8, 3 }, { 4, 8 }, { 5, 8 }, { 6, 8 },
                { 7, 8 }, { 8, 8 }, { 8, 4 }, { 8, 5 }, { 8, 6 }, { 8, 7 },
                { 4, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 7, 4 }, { 7, 5 },
                { 7, 6 }, { 7, 7 }, { 5, 4 }, { 6, 4 }, { 7, 4 }, { 5, 7 },
                { 6, 7 }, { 4, 10 }, { 5, 10 }, { 6, 10 }, { 7, 10 }
        };

        for (int[] position : positions) {
            validPositions.add(position);
        }

        return validPositions;
    }

    public int getBomb() {
        return bomb;
    }

    public int getRadius() {
        return radius;
    }

    public int getShield() {
        return shield;
    }

    public Box getBox(int xPosition, int yPosition) {
        return board[xPosition][yPosition];
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public void explode(int xPosition, int yPosition, int explosionRadius) {
        int i = xPosition;
        int j = yPosition;
        //Up
        for(int r = explosionRadius; r > 0; r--) {
            if (i-r > 0){
                if(getBox(i-r, j).hasPlayer()) {
                    getBox(i-r, j).getPlayer().die();
                    break;
                }
                else if(getBox(i-r, j).isDestroyable()) {
                    board[i-r][j] = new Box(i-r, j);
                    break;
                }
            }
        }
        //Left
        for(int r = explosionRadius; r > 0; r--) {
            if (j-r > 0){
                if(getBox(i, j-r).hasPlayer()) {
                    getBox(i, j-r).getPlayer().die();
                }
                else if(getBox(i, j-r).isDestroyable()) {
                    board[i][j-r] = new Box(i, j-r);
                }
            }
        }
        //Right
        for(int r = explosionRadius; r > 0; r--) {
            if (j+r < size-1){
                if(getBox(i, j+r).hasPlayer()) {
                    getBox(i, j+r).getPlayer().die();
                }
                else if(getBox(i, j+r).isDestroyable()) {
                    board[i][j+r] = new Box(i, j+r);
                }
            }
        }
        //Down
        for(int r = explosionRadius; r > 0; r--) {
            if (i+r < size-1){
                if(getBox(i+r, j).hasPlayer()) {
                    getBox(i+r, j).getPlayer().die();
                }
                else if(getBox(i+r, j).isDestroyable()) {
                    board[i+r][j] = new Box(i+r, j);
                }
            }
        }
    }
  
    public String getBoardJsonMode(){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBoard;
        try {
            jsonBoard = objectMapper.writeValueAsString(board);
            return jsonBoard;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public String toString(){
        String content = "";
        for (int i = 0; i < board.length; i++){
            if (i != 0 && i != 11){
                for(int j = 0; j < board[i].length; j++){
                    if(j != 0 && j != 11){
                        content = board[i][j].isEmpty() ? "0" :
                                    board[i][j].hasPowerUp() ? "3" :
                                    board[i][j].isDestroyable() ? "2" : "1";
                        this.boardInstance[i][j] = content;
                    }
                }
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(this.boardInstance);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error mapping the board";
        }
    }
}
