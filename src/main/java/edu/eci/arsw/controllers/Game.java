package edu.eci.arsw.controllers;

import java.util.ArrayList;

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

  public ArrayList<Player> players;

  public Game() {
  }

  public void orchest(GameMode gameMode) {
    board = new Board();
    players = new ArrayList<>();
    switch (gameMode) {
      case SINGLE_PLAYER:
        player1 = new Player(1, 1, "FixedName1", false, 0);
        player1.setBoard(board);

        players.add(player1);
        break;

      case MULTI_PLAYER:
        player1 = new Player(1, 1, "FixedName1", false, 0);
        player2 = new Player(1, 10, "FixedName2", false, 0);
        player3 = new Player(10, 1, "FixedName3", false, 0);
        player4 = new Player(10, 10, "FixedName4", false, 0);

        player1.setBoard(board);
        player2.setBoard(board);
        player3.setBoard(board);
        player4.setBoard(board);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        break;
    }
  }

  public Board getBoard() {
    return this.board;
  }

  public ArrayList<Player> getPlayers() {
    return this.players;
  }

  public boolean isInstantiated (){
    return this.instance;
  }

  public void setInstantiated(){
    this.instance = true;
  }
}
