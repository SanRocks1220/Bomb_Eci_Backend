package edu.eci.arsw.entities;

import edu.eci.arsw.controllers.Board;
import edu.eci.arsw.model.PlayerInteraction;

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
    private int character;
    private String id;

    public Player(int xPosition, int yPosition, String name, boolean isImmortal, int charachter) {
        setXPosition(xPosition);
        setYPosition(yPosition);
        setName(name);;
        setImmortal(isImmortal);
        setAlive(true);
        setCharacter(charachter);

        kills = 0;
        bombs = 1;
        explosionRadius = 1;
        shields = 0;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int charachter) {
        this.character = charachter;
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

    public void increaseKills(int k) {
        kills = kills + k;
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

    public void setId(String newId){
        this.id = newId;
    }

    public String getId(){
        return this.id;
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
        if (board.getBox(getXPosition(), getYPosition()+1).isEmpty() && getYPosition()+1 < board.getSize()-1){
            freeBox(xPosition, yPosition);
            yPosition = (yPosition < board.getSize()-2) ? yPosition + 1: yPosition;
            occupyBox();
        }
    }

    public void moveLeft() {
        if (board.getBox(getXPosition(), getYPosition()-1).isEmpty() && getYPosition() > 1){
            freeBox(xPosition, yPosition);
            yPosition = (yPosition > 1) ? yPosition - 1: yPosition;
            occupyBox();
        }
    }

    public void moveUp() {
        if (board.getBox(getXPosition()-1, getYPosition()).isEmpty() && getXPosition() > 1){
            freeBox(xPosition, yPosition);
            xPosition = (xPosition > 1) ? xPosition - 1: xPosition;
            occupyBox();
        }
    }

    public void moveDown() {
        if (board.getBox(getXPosition()+1, getYPosition()).isEmpty() && getXPosition()+1 < board.getSize()-1){
            freeBox(xPosition, yPosition);
            xPosition = (xPosition < board.getSize()-2) ? xPosition + 1: xPosition;
            occupyBox();
        }
    }
    
    @Override
    public String toString() {
        String callBack = String.format(
            "{\"xPosition\": %d, \"yPosition\": %d, \"name\": \"%s\", \"isAlive\": %s, \"isImmortal\": %s, \"kills\": %d, \"bombs\": %d, \"explosionRadius\": %d, \"shields\": %d, \"character\": %d}",
            xPosition, yPosition, name, isAlive, isImmortal, kills, bombs, explosionRadius, shields, character
        );
        return callBack;
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
        int kills = board.explode(xPosition, yPosition, explosionRadius);
        increaseKills(kills);
    }

    public void action(String key){
        switch (key) {
            case "w":
                moveUp();
                break;
            case "a":
                moveLeft();
                break;
            case "s":
                moveDown();
                break;
            case "d":
                moveRight();
                break;
            case " ":
                putBomb();
                break;
        }
    }
}
