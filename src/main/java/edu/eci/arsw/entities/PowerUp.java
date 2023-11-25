package edu.eci.arsw.entities;

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

}
