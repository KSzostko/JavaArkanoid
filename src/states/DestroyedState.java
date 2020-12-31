package states;

import com.company.Block;

public class DestroyedState extends State
{
    public DestroyedState(Block block)
    {
        super(block);
    }

    @Override
    public void changeSound() {

    }

    @Override
    public void changeImage() {

    }
}
