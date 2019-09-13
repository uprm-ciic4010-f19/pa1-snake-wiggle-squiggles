package Game.GameStates;


import Main.Handler;
import Resources.Images;
import UI.ClickListlener;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;


public class ChoosePlayerState extends State{
	private int count = 0;
	private UIManager uiManager;

	public ChoosePlayerState(Handler handler) {

		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUimanager(uiManager);


		uiManager.addObjects(new UIImageButton(150, 425, 375, 278, Images.BWiggles, new ClickListlener() {
			@Override
			//handler.getWidth()/2-64
			// handler.getHeight()/2-32

			public void onClick() { //click-on images  and do something code it also changes the images when the cursor is on top 
				handler.getMouseManager().setUimanager(null);
				handler.getGame().reStart(); //restarts game
				State.setState(handler.getGame().gameState);//Change gameState to get another type of state 
				handler.getWorld().player.choosedPlayer = 1;
			}

		}));
		
		uiManager.addObjects(new UIImageButton(660, 427, 345, 245, Images.BSquiggles, new ClickListlener() {
			@Override
			//handler.getWidth()/2-64
			// handler.getHeight()/2-32

			public void onClick() { //click-on images  and do something code it also changes the images when the cursor is on top 
				handler.getMouseManager().setUimanager(null);
				handler.getGame().reStart(); //restarts game
				State.setState(handler.getGame().gameState);//Change gameState to get another type of state 
				handler.getWorld().player.choosedPlayer = 2;
			}

		}));
		
		
		uiManager.addObjects(new UIImageButton(417, 780, 377, 310, Images.BBienve, new ClickListlener() {
			@Override
			//handler.getWidth()/2-64
			// handler.getHeight()/2-32

			public void onClick() { //click-on images  and do something code it also changes the images when the cursor is on top 
				handler.getMouseManager().setUimanager(null);
				handler.getGame().reStart(); //restarts game
				State.setState(handler.getGame().gameState);//Change gameState to get another type of state 
				handler.getWorld().player.choosedPlayer = 3;
			}

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
			g.fillRect(0,0,handler.getWidth(),handler.getHeight());
			g.drawImage(Images.ChoosePlayer,0,0,handler.getWidth(),handler.getHeight(),null);
			uiManager.Render(g);
		}
	}
