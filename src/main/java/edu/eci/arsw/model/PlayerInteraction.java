package edu.eci.arsw.model;

public class PlayerInteraction {
    String key, player;
    int bombX, bombY;

    public PlayerInteraction() {
    }

    public PlayerInteraction(String key, String player, int x, int y) {
        this.bombX = x;
        this.bombY = y;
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

    public int getBombX() {
        return this.bombX;
    }

    public void setBombX(int x) {
        this.bombX = x;
    }

    public int getBombY() {
        return this.bombY;
    }

    public void setBombY(int y) {
        this.bombY = y;
    }

    @Override
    public String toString() {
        return "PlayerInteraction{" + "key=" + key + ", player=" + player + ", bombX=" + bombX + ", bombY=" + bombY + '}';
    }
}