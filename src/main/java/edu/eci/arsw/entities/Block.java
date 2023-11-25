package edu.eci.arsw.entities;

public class Block extends Box{

    private boolean destroyable;

    public Block(int x, int y, boolean destroyable) {
        super(x, y);
        this.destroyable = destroyable;
    }
    
    public boolean isDestroyable() {
        return destroyable;
    }

    @Override
    public Boolean isEmpty(){
        return false;
    }
}
