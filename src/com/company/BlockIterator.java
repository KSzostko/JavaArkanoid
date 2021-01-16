package com.company;

public class BlockIterator {
    public BlockIterator(DestroyedBlocks destroyedBlocks){
        this.destroyedBlocks = destroyedBlocks;
    }
    public DestroyedBlocks destroyedBlocks;
    private int currentElement = 0;
    //
    public boolean hasNext(){
        if(currentElement != destroyedBlocks.getBlocks().size()){
            return true;
        }else{
            return false;
        }
    }
    // get current element-1 because if list has next element i first add +1 to current and then return current ...
    public Block getNext(){
        if(!hasNext()){
            return null;
        } else{
            currentElement++;
            return destroyedBlocks.getBlocks().get(currentElement - 1);
        }
    }
    public void reset(){
        currentElement = 0;
        destroyedBlocks.getBlocks().clear();
    }

}
