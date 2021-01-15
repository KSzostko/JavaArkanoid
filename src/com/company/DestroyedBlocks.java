package com.company;

import java.util.ArrayList;
import java.util.List;

public class DestroyedBlocks {
    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    private List<Block> blocks;
    public DestroyedBlocks(){
        blocks = new ArrayList<>();
    }
}
