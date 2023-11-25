package edu.eci.arsw.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Bomb{

    private int timer;

    public Bomb() {
        timer = 3000;
    }

    @Override
    public Boolean isEmpty(){
        return false;
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
