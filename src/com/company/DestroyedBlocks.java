package com.company;

import java.util.ArrayList;
import java.util.List;

public class DestroyedBlocks {
    // getters & setters
    public List<Block> getBlocks() {
        return blocks;
    }
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
    // list to store destroyed blocks
    private List<Block> blocks;
    // inicialize list upon object creation
    public DestroyedBlocks(){
        blocks = new ArrayList<>();
    }
    // aid level snapshot class
    public DestroyedBlocks(DestroyedBlocks destroyedBlocks){
        this.blocks = destroyedBlocks.getBlocks();
    }
}
