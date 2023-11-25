package edu.eci.arsw;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.eci.arsw.entities.Block;

public class BlockTest {


    @Test
    public void ShouldGetTheRightJson(){
        Block block = new Block(1, 2, true);
        assertEquals("{\"x\":1,\"y\":2,\"destroyable\":true,\"empty\":false}", block.toString());
    }
    
}
