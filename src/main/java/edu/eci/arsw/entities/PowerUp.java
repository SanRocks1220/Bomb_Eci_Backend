package edu.eci.arsw.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.eci.arsw.model.PowerUpType;

public class PowerUp{

    private PowerUpType type;

    public PowerUp(PowerUpType type) {
        this.type = type;
    }

    public void aplyEffect(Player player) {
        switch (type) {
            case SHIELD:
                player.gainShields();
                break;
            case BOMB_UP:
                player.increaseBombs();
                break;
            case RANGE_UP:
                player.increaseExplosionRadius();
                break;
        }
    }

    public PowerUpType getType() {
        return type;
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
