package edu.eci.arsw.entities;

public class Block extends Box{

    private boolean destroyable;

    public Block(int x, int y, boolean destroyable) {
        super(x, y);
        this.destroyable = destroyable;
        canMove = false;
    }
    
    public boolean isDestroyable() {
        return destroyable;
    }
}
