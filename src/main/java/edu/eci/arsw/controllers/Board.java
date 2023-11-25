package edu.eci.arsw.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public Board() {
        board = new Box[size][size];
        initializeBoard();
        generateBoosts();
    }

    private void initializeBoard() {
        int[][] tempBoard = {
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 0, 0, 2, 0, 2, 0, 2, 2, 0, 0, 1 },
                { 1, 0, 1, 0, 0, 2, 2, 0, 0, 1, 0, 1 },
                { 1, 2, 0, 2, 2, 1, 1, 2, 2, 0, 2, 1 },
                { 1, 2, 0, 1, 0, 2, 2, 0, 1, 0, 0, 1 },
                { 1, 0, 2, 0, 2, 1, 1, 2, 0, 2, 2, 1 },
                { 1, 2, 2, 0, 2, 1, 1, 2, 0, 2, 0, 1 },
                { 1, 0, 0, 1, 0, 2, 2, 0, 1, 0, 2, 1 },
                { 1, 2, 0, 2, 2, 1, 1, 2, 2, 0, 2, 1 },
                { 1, 0, 1, 0, 0, 2, 2, 0, 0, 1, 0, 1 },
                { 1, 0, 0, 2, 2, 0, 2, 0, 2, 0, 0, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tempBoard[i][j] == 0) {
                    board[i][j] = new Box(i, j);
                } else if (tempBoard[i][j] == 1) {
                    board[i][j] = new Block(i, j, false);
                } else if (tempBoard[i][j] == 2) {
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
}
