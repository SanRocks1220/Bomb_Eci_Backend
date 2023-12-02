package edu.eci.arsw.model;

public class PlayerInteraction {
    String key, gameId;

    public PlayerInteraction() {
    }

    public PlayerInteraction(String key, String gameId) {
        this.key = key;
        this.gameId = gameId;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getGameId() {
        return this.gameId;
    }

    public void setgameId(String gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "PlayerInteraction{" + "key=" + key + ", player=" + gameId + '}';
    }
}