package com.company;

import java.util.ArrayList;
import java.util.List;

public class DestroyedBlocks {
    private List<Block> blocks;

    public DestroyedBlocks(){
        blocks = new ArrayList<>();
    }

    public List<Block> getBlocks() {
        return blocks;
    }
    
    public DestroyedBlocks(DestroyedBlocks destroyedBlocks){
        List<Block> new_blocklist = new ArrayList<>();
        for(int   i = 0; i<destroyedBlocks.getBlocks().size() ;i++){
            new_blocklist.add(new Block(destroyedBlocks.getBlocks().get(i)));
        }
        this.blocks = new_blocklist;
    }
}
