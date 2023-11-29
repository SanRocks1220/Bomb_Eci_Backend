package edu.eci.arsw.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Bomb implements Runnable{

    private int timer;
    private Player owner;
    private int xPosition;
    private int yPosition;

    public Bomb(int xPosition, int yPosition, Player player) {
        timer = 1500;
        owner = player;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    @Override
    public String toString(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        }catch(JsonProcessingException e){
            e.printStackTrace();
            return "";
        }
    }

    public void activate() {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        explode(xPosition, yPosition);
    }
    
    private void explode(int xPosition, int yPosition){
        owner.explode(xPosition, yPosition);
        owner.freeBox(xPosition, yPosition);
        owner.increaseBombs();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timer); // Retraso de 3 segundos
            explode(xPosition, yPosition);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
