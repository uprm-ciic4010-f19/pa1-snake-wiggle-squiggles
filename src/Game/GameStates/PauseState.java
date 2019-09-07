package Game.GameStates;

import Main.Handler;
import Resources.Images;
import UI.ClickListlener;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class PauseState extends State {

    private int count = 0;
    private UIManager uiManager;

    public PauseState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);
        
        uiManager.addObjects(new UIImageButton(500, 650, 200, 100, Images.BResume, () -> {// References the position and size of the button ( x, y , width, height)
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().gameState);
        }));

        uiManager.addObjects(new UIImageButton(500, 650+(64+20), 200, 100, Images.BMenu, () -> { //Options
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().menuState);
        }));

        uiManager.addObjects(new UIImageButton(500, (650+(64+20))+(64+20), 200, 100, Images.BExit, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().menuState);
        }));





    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
        count++;
        if( count>=30){
            count=30;
        }
        if(handler.getKeyManager().pbutt && count>=30){
            count=0;

            State.setState(handler.getGame().gameState);
        }


    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(Images.Pause,0,0,800,600,null);
        //uiManager.Render(g);
    	
    	//This will make the Pause menu fill the whole game screen 
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
        g.drawImage(Images.Pause,0,0,handler.getWidth(),handler.getHeight(),null);
        uiManager.Render(g);

    }
}
