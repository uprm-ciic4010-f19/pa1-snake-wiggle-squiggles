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

		// References the position and size of the button ( x, y , width, height, Image)
		uiManager.addObjects(new UIImageButton(450, 550, 250, 140, Images.BResume, () -> {
			handler.getMouseManager().setUimanager(null);
			State.setState(handler.getGame().gameState);
		}));

		uiManager.addObjects(new UIImageButton(450, 550+(64+70), 250, 140, Images.BMenu, () -> { 
			handler.getMouseManager().setUimanager(null);
			State.setState(handler.getGame().menuState);
		}));

		uiManager.addObjects(new UIImageButton(450, (550+(64+70))+(64+70), 250, 140, Images.BExit, () -> {
			handler.getMouseManager().setUimanager(null);
			System.exit(0);
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
		
		g.fillRect(0,0,handler.getWidth(),handler.getHeight());//This will make the Pause menu fill the whole game screen 
		g.drawImage(Images.Pause,0,0,handler.getWidth(),handler.getHeight(),null);
		uiManager.Render(g);

	}
}
