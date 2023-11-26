package edu.eci.arsw.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.eci.arsw.controllers.Board;

public class Player{

    private int xPosition;
    private int yPosition;

    private String name;
    private boolean isAlive;
    private boolean isImmortal;
    

    private int kills;
    private int bombs;
    private int explosionRadius;
    private int shields;
    private Board board;
    private int charachter;

    public Player(int xPosition, int yPosition, String name, boolean isImmortal, int charachter) {
        setXPosition(xPosition);
        setYPosition(yPosition);
        setName(name);;
        setImmortal(isImmortal);
        setAlive(true);
        setCharachter(charachter);

        kills = 0;
        bombs = 1;
        explosionRadius = 1;
        shields = 0;
    }

    public int getCharachter() {
        return charachter;
    }

    public void setCharachter(int charachter) {
        this.charachter = charachter;
    }

    public int getXPosition() {
        return xPosition;
    }
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    private void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
        this.board.getBox(getXPosition(), getYPosition()).setPlayer(this);
    }

    public Boolean isImmortal() {
        return isImmortal;
    }

    public void setImmortal(Boolean isImmortal) {
        this.isImmortal = isImmortal;
    }

    public int getKills() {
        return kills;
    }

    public void increaseKills() {
        kills++;
    }

    public int getBombs() {
        return bombs;
    }

    public int getShields() {
        return shields;
    }

    public void increaseBombs() {
        bombs = (bombs<3)?bombs+1:bombs;
    }

    public int getExplosionRadius() {
        return explosionRadius;
    }

    public void increaseExplosionRadius() {
        explosionRadius = (explosionRadius<5)?explosionRadius+1:explosionRadius;
    }

    public void gainShields() {
        shields = (shields<1)?shields+1:shields;
    }

    public void die() {
        if(!isImmortal){
            if(shields>0){
                shields--;
            }
            else{
                setAlive(false);
            }
        }
    }

    public void moveRight() {
        if (board.getBox(getXPosition(), getYPosition()+1).isEmpty()){
            freeBox(xPosition, yPosition);
            yPosition++;
            occupyBox();
        }
    }

    public void moveLeft() {
        if (board.getBox(getXPosition(), getYPosition()-1).isEmpty()){
            freeBox(xPosition, yPosition);
            yPosition--;
            occupyBox();
        }
    }

    public void moveUp() {
        if (board.getBox(getXPosition()-1, getYPosition()).isEmpty()){
            freeBox(xPosition, yPosition);
            xPosition--;
            occupyBox();
        }
    }

    public void moveDown() {
        if (board.getBox(getXPosition()+1, getYPosition()).isEmpty()){
            freeBox(xPosition, yPosition);
            xPosition++;
            occupyBox();
        }
    }
    
    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        String callBack = "{player: {xPosition:" + xPosition + ", yPosition:" + yPosition + ", name:" + name + ", isAlive:" + isAlive
                + ", isImmortal:" + isImmortal + ", kills:" + kills + ", bombs:" + bombs + ", explosionRadius:"
                + explosionRadius + ", shields:" + shields + ", charachter:" + charachter + "}}";
        try {
            String json = objectMapper.writeValueAsString(callBack);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error mapping the board";
        }
    }

    public void freeBox(int xPosition, int yPosition) {
        board.getBox(getXPosition(), getYPosition()).freeBox();
    }

    private void occupyBox() {
        board.getBox(getXPosition(), getYPosition()).setPlayer(this);
    }

    public void putBomb() {
        if(bombs > 0){
            bombs--;
            board.getBox(xPosition, yPosition).setBomb(xPosition, yPosition, this);
        }
    }

    public void explode(int xPosition, int yPosition) {
        board.explode(xPosition, yPosition, explosionRadius);
    }
}
