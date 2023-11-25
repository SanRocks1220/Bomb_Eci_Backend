package edu.eci.arsw.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Box {

    protected int x;
    protected int y;
    protected boolean hasPowerUp;
    protected PowerUp ownPowerUp;   
    protected boolean hasPlayer;
    protected Player ownPlayer;
    protected boolean hasBomb;
    protected Bomb ownBomb;
    protected boolean canMove;

    public Box(int x, int y) {
        this.x = x;
        this.y = y;
        canMove = true;
    }

    public void setPowerUp(PowerUp pu) {
        hasPowerUp = true;
        ownPowerUp = pu;
    }

    public void setPlayer(Player player) {
        canMove = false;
        hasPlayer = true;
        ownPlayer = player;
    }

    public void setBomb(Bomb bomb) {
        canMove = false;
        hasBomb = true;
        ownBomb = bomb;
    }

    public void freeBox() {
        canMove = true;
        hasPlayer = false;
        hasBomb = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Boolean isEmpty() {
        return canMove;
    }

    @Override
    public String toString(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        }catch(JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }



    
}
