package edu.eci.arsw.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import edu.eci.arsw.entities.Player;
import edu.eci.arsw.model.GameMode;

public class Game {

  public GameMode gameMode;

  public Board board;

  public Player player1;
  public Player player2;
  public Player player3;
  public Player player4;
  private boolean instance;
  private Player winner;

  public HashMap<Integer, Player> players;

  public Game(int mode) {
    GameMode gameMode = mode == 0? GameMode.SINGLE_PLAYER : GameMode.MULTI_PLAYER;
    orchest(gameMode);
  }

  public void orchest(GameMode gameMode) {
    board = new Board();
    players = new HashMap<>();
    switch (gameMode) {
      case SINGLE_PLAYER:
        player1 = new Player(1, 1, "Player1", true, 0);
        player1.setBoard(board);

        players.put(1, player1);
        break;

      case MULTI_PLAYER:
        player1 = new Player(1, 1, "Player1", false, 0);
        player2 = new Player(1, 10, "Player2", false, 0);
        player3 = new Player(10, 1, "Player3", false, 0);
        player4 = new Player(10, 10, "Player4", false, 0);

        player1.setBoard(board);
        player2.setBoard(board);
        player3.setBoard(board);
        player4.setBoard(board);

        players.put(1, player1);
        players.put(2, player2);
        players.put(3, player3);
        players.put(4, player4);
        
        break;
    }
  }

  public Board getBoard() {
    return this.board;
  }

  public HashMap<Integer, Player> getPlayers() {
    return this.players;
  }

  public boolean isInstantiated (){
    return this.instance;
  }

  public void setInstantiated(){
    this.instance = true;
  }
}
