package edu.eci.arsw.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import edu.eci.arsw.entities.Player;
import edu.eci.arsw.model.GameMode;

public class Game {

  public GameMode gameMode;

  public Board board;
  private boolean instance;
  private boolean finished;
  private Player winner;

  public HashMap<String, Player> players;

  public Game(int mode, String P1Token, String P1ch) {
    GameMode gameMode = mode == 0? GameMode.SINGLE_PLAYER : GameMode.MULTI_PLAYER;
    int character = Integer.parseInt(P1ch);
    orchest(gameMode, P1Token, character);
  }

  public void orchest(GameMode gameMode, String P1Token, int P1ch) {
    board = new Board();
    players = new HashMap<>();
    switch (gameMode) {
      case SINGLE_PLAYER:
        Player player = new Player(1, 1, "Player1", true, P1ch);
        player.setBoard(board);
        player.setId(P1Token);
        players.put(P1Token, player);
        break;

      case MULTI_PLAYER:
        Player player1 = new Player(1, 1, "Player1", false, P1ch);
        
        player1.setBoard(board);
        player1.setId(P1Token);
        players.put(P1Token, player1);
        System.out.println(P1Token);
        
        break;
    }
  }

  public String getBoard() {
    return this.board.toString();
  }

  public HashMap<String, Player> getPlayers() {
    return players;
  }

  public boolean isInstantiated (){
    return this.instance;
  }

  public void setInstantiated(){
    this.instance = true;
  }

  public void addPlayer(String P1Token, String Pch){
    if(!players.containsKey(P1Token)){
      Integer character = Integer.parseInt(Pch);
      System.out.println(P1Token);
      if(players.size() == 1){
        Player player = new Player(1, 10, "Player2", false, character);
        player.setBoard(board);
        player.setId(P1Token);
        players.put(P1Token, player);
      } else if(players.size() == 2){
        Player player = new Player(10, 1, "Player3", false, character);
        player.setBoard(board);
        player.setId(P1Token);
        players.put(P1Token, player);
      } else {
        Player player = new Player(10, 10, "Player4", false, character);
        player.setBoard(board);
        player.setId(P1Token);
        players.put(P1Token, player);
      }
    }
  }

  public void action(String player, String action) {
    isfinished();
    if(players.get(player).isAlive()){
      players.get(player).action(action);
    } else {
      System.out.println("Me Mori");
      System.out.println(players.get(player).getName());
    }
  }

  public boolean isfinished() {
    int playersAlive = 0;
    for(Player player: players.values()){
      playersAlive = player.isAlive() ? playersAlive+1 : playersAlive;
    }
    finished = playersAlive>1? false : true;
    if(finished){
      whoIstheWinner();
    }
    return finished;
  }

  private void whoIstheWinner() {
    for(Player player: players.values()){
      if(player.isAlive()){
        winner = player;
      }
    }
  }

  public Player getWinner() {
    return winner;
  }

  public Player getPlayer(String ptoken) {
    return players.get(ptoken);
  }
}
