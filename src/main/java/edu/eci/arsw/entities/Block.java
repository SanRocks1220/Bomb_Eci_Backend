package edu.eci.arsw.entities;

public class Block extends Box{

    public Block(int x, int y, boolean destroyable) {
        super(x, y);
        this.destroyable = destroyable;
        canMove = false;
    }

    @Override
    public Boolean isEmpty() {
        return false;
    }
}
