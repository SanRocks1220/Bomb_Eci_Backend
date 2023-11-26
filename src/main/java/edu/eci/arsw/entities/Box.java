package edu.eci.arsw.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Box {

    protected int x;
    protected int y;
    protected boolean hasPowerUp;
    protected PowerUp ownPowerUp;   
    private boolean hasPlayer;
    private Player ownPlayer;
    private boolean hasBomb;
    private Bomb ownBomb;
    private int xPositionBomb;
    private int yPositionBomb;
    private Player bombOwner;
    protected boolean canMove;
    protected boolean destroyable;

    public Box(int x, int y) {
        this.x = x;
        this.y = y;
        canMove = true;
        destroyable = false;
    }

    public void setPowerUp(PowerUp pu) {
        hasPowerUp = true;
        ownPowerUp = pu;
    }

    public void setPlayer(Player player) {
        canMove = false;
        hasPlayer = true;
        ownPlayer = player;
        if(hasPowerUp){
            ownPowerUp.applyEffect(player);
            hasPowerUp = false;
        }
    }

    public void setBomb(int xPosition, int yPosition, Player owner) {
        hasBomb = true;
        xPositionBomb = xPosition;
        yPositionBomb = yPosition;
        bombOwner = owner;
    }

    public void freeBox() {
        if(hasPlayer == true && hasBomb == true){
            hasPlayer = false;
            ownBomb = new Bomb(xPositionBomb, yPositionBomb, bombOwner);
        }
        else {
            canMove = true;
            hasPlayer = false;
            hasBomb = false;
        }
        
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

    public Player getPlayer() {
        return ownPlayer;
    }

    public boolean hasPlayer() {
        return hasPlayer;
    }

    public boolean isDestroyable() {
        return destroyable;
    }

    public boolean hasPowerUp(){
        return hasPowerUp;
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
