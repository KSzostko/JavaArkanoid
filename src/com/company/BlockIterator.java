package com.company;

public class BlockIterator {
    public DestroyedBlocks destroyedBlocks = new DestroyedBlocks();
    private int currentElement = 0;
    //
    public boolean hasNext(){
        if(destroyedBlocks.getBlocks().get(currentElement+1) != null){
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
        destroyedBlocks.getBlocks().clear();
    }

}
