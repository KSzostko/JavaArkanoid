package com.company;

public class BlockIterator {
    private DestroyedBlocks destroyedBlocks;
    private int currentElement = 0;

    public BlockIterator(DestroyedBlocks destroyedBlocks){
        this.destroyedBlocks = destroyedBlocks;
    }

    public boolean hasNext(){
        if(currentElement != destroyedBlocks.getBlocks().size()){
            return true;
        }else{
            return false;
        }
    }

    public Block getNext(){
        if(!hasNext()) {
            return null;
        } else{
            currentElement++;
            return destroyedBlocks.getBlocks().get(currentElement - 1);
        }
    }
}
