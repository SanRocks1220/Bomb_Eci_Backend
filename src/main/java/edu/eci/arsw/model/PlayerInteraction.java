package edu.eci.arsw.model;

public class PlayerInteraction {
    String key, player;

    public PlayerInteraction() {
    }

    public PlayerInteraction(String key, String player) {
        this.key = key;
        this.player = player;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPlayer() {
        return this.player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "PlayerInteraction{" + "key=" + key + ", player=" + player + '}';
    }
}