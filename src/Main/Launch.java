package Main;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class Launch {

    public static void main(String[] args) {
    	//changed from 800 to 1200 to fix bug
        GameSetUp game = new GameSetUp("Snake", 1200 , 1200);
        game.start();
    }
}
