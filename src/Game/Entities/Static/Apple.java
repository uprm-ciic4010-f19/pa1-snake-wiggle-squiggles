package Game.Entities.Static;

import Main.Handler;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Apple {

    private Handler handler;

    public int xCoord;
    public int yCoord;
   
    private boolean appleGoodness = true; //created property
    
    public Apple(Handler handler,int x, int y){
        this.handler=handler;
        this.xCoord=x;
        this.yCoord=y;
    }

    public boolean isGood(){//getter for the state of the apple
    	return appleGoodness;
    }
    
    public void setGood(boolean state){//setter for the state of the apple
    	appleGoodness = state;
    }
    

}
