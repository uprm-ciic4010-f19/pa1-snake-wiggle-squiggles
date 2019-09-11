package Game.Entities.Dynamic;

import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import Game.GameStates.State;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Player {

	public int lenght;
	public boolean justAte;
	private Handler handler;

	public int xCoord;
	public int yCoord;

	public double rawScore = 0.0;//calculates score then rounds it
	public int playerScore = 0;//total score

	public int appleCounter = 0;//counts amount of good apples the player has eaten

	public int moveCounter;

	public int stepCounter = 0;//initialized variable that monitors amount of steps of the player

	//Additional feature
	public boolean superPower = false;//creates a new apple that adds to the score
	
	//initializing speed variable 
	public int speed;

	public String direction;

	public Player(Handler handler){
		this.handler = handler;
		xCoord = 0;
		yCoord = 0;
		moveCounter = 0;
		direction= "Right";
		justAte = false;
		lenght= 1;

		//Variable that will control the speed of the snake
		speed = 12;

	}


	public void tick(){

		if(stepCounter == 100) {//if the player reaches 120 steps before eating an apple, then apple will be rotten
			handler.getWorld().getApple().setGood(false);
		}

		moveCounter++;
		if(moveCounter>=speed) { //moves the snake at x steps in one second
			checkCollisionAndMove();
			moveCounter=0;
		}

		//Added conditions to prevent backtracking
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)&& direction!= "Down"){  
			direction="Up";
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)&& direction!= "Up"){
			direction="Down";
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)&& direction!= "Right"){
			direction="Left";
		}if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)&& direction!= "Left"){
			direction="Right";
		}

		//When pressing N key it adds a tail to the player
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)){

			handler.getWorld().body.addLast(new Tail(xCoord, yCoord,handler));
		}

		//When Pressing Esc key the Pause Menu will appear
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			State.setState(handler.getGame().pauseState);
		}

		// Implementing input '+'and '-'speed variations
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS)) {
			checkCollisionAndMove();
			speed++;

		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS)) {
			checkCollisionAndMove();
			speed--;

		}

	}	

	public void checkCollisionAndMove(){
		handler.getWorld().playerLocation[xCoord][yCoord]=false;
		int x = xCoord;
		int y = yCoord;
		switch (direction){
		//Adds to the stepCounter every time a x or y coordinate changes
		case "Left":
			stepCounter++;
			if(xCoord==0){
				xCoord = handler.getWorld().GridWidthHeightPixelCount - 1;
			}else{
				xCoord--;
			}
			break;
		case "Right":
			stepCounter++;
			if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){
				xCoord = 0;
			}else{
				xCoord++;
			}
			break;
		case "Up":
			stepCounter++;
			if(yCoord==0){
				yCoord = handler.getWorld().GridWidthHeightPixelCount - 1;
			}else{
				yCoord--;
			}
			break;
		case "Down":
			stepCounter++;
			if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
				yCoord = 0;
			}else{
				yCoord++;
			}
			break;
		}
		handler.getWorld().playerLocation[xCoord][yCoord]=true;


		if(handler.getWorld().appleLocation[xCoord][yCoord]){
			Eat();
			
		}

		if(!handler.getWorld().body.isEmpty()) {	
			handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
			handler.getWorld().body.removeLast();
			handler.getWorld().body.addFirst(new Tail(x, y,handler));


		}
		//Body Collision Checker
		for(int i =1; i < handler.getWorld().body.size(); i++) {
			if(handler.getWorld().body.get(0).x == handler.getWorld().body.get(i).x
					&& handler.getWorld().body.get(0).y == handler.getWorld().body.get(i).y) {
				kill();
			}
		}
	}



	public void render(Graphics g,Boolean[][] playeLocation){

		//Color Randomizer
		int R = (int)(Math.random()*256);
		int G = (int)(Math.random()*256);
		int B= (int)(Math.random()*256);
		Color randomcolor = new Color(R, G, B);

		Random r = new Random();
		for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
			for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

				if(playeLocation[i][j]){
					g.setColor(randomcolor);//set the player color
					g.fillRect((i*handler.getWorld().GridPixelsize),
							(j*handler.getWorld().GridPixelsize),
							handler.getWorld().GridPixelsize,
							handler.getWorld().GridPixelsize);

				}
				//Implementing .isGood() Property
				if(handler.getWorld().appleLocation[i][j]){

					if(handler.getWorld().getApple().isGood()==false) {
						//if the apple is rotten it will change the image and the color of the apple
						g.setColor(Color.YELLOW);
						g.fillRect((i*handler.getWorld().GridPixelsize),
								(j*handler.getWorld().GridPixelsize),
								handler.getWorld().GridPixelsize,
								handler.getWorld().GridPixelsize);

						g.drawImage(Images.RottenApple, ((i*handler.getWorld().GridPixelsize)-8),((j*handler.getWorld().GridPixelsize)-8),33, 33,null);
					}

					else if(superPower==true) {//checks to see if additional feature applies
						g.setColor(Color.magenta);
						g.fillRect((i*handler.getWorld().GridPixelsize),
								(j*handler.getWorld().GridPixelsize),
								handler.getWorld().GridPixelsize,
								handler.getWorld().GridPixelsize);
						g.drawImage(Images.RainbowApple, ((i*handler.getWorld().GridPixelsize)-8),((j*handler.getWorld().GridPixelsize)-8),33, 33,null);
						appleCounter = 0;
					}
					
					else {

						g.fillRect((i*handler.getWorld().GridPixelsize),
								(j*handler.getWorld().GridPixelsize),
								handler.getWorld().GridPixelsize,
								handler.getWorld().GridPixelsize);

						g.drawImage(Images.GoodApple, ((i*handler.getWorld().GridPixelsize)-8),((j*handler.getWorld().GridPixelsize)-8),33, 33,null);
					}

					


				}
			}
		}
	}


	public void Eat(){
		stepCounter = 0; //resets the stepCounter every time the player eats
		handler.getWorld().appleLocation[xCoord][yCoord]=false;
		handler.getWorld().appleOnBoard=false;
		speed = speed - 1;//increases speed one by one every time the player eats
		appleCounter++;
		if(appleCounter==5) {//when the player eats a special apple the score is doubled
			superPower = true;
			playerScore = 2*(playerScore + (int)rawScore);
			appleCounter = 0;
		}
		else {
			superPower = false;
		}

		if(handler.getWorld().getApple().isGood()==false) {//checks to see if the apple is rotten
			playerScore = playerScore - (int)rawScore;//Subtracts to the player if it eats a rotten apple
			appleCounter = 0;//resets counter of good apples eaten to 0
			if(playerScore < 0) {
				playerScore = 0;//prevents score to go below 0 and become NAN
			}

			if(lenght == 1) {
				kill();//prevents negative tails, kills player if it hasn't eaten a good apple
			}
			else {
				handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
				handler.getWorld().body.removeLast();//removes a segment of the tail
				lenght--;
			}
		}
		else {
			
			handler.getWorld().getApple().setGood(true);//if the player eats it maintains the goodness true
			rawScore = Math.round(Math.sqrt((2*playerScore) + 1));//calculates score then rounds it
			playerScore = playerScore + (int)rawScore;
			lenght++;
			Tail tail= null;

			switch (direction){
			case "Left":
				if( handler.getWorld().body.isEmpty()){//checks if body is empty or not 
					if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
						tail = new Tail(this.xCoord+1,this.yCoord,handler);
					}else{
						if(this.yCoord!=0){
							tail = new Tail(this.xCoord,this.yCoord-1,handler);
						}else{
							tail =new Tail(this.xCoord,this.yCoord+1,handler);
						}
					}
				}else{//when body is not empty
					if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
						tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
					}else{
						if(handler.getWorld().body.getLast().y!=0){
							tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
						}else{
							tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

						}
					}

				}
				break;
			case "Right":
				if( handler.getWorld().body.isEmpty()){
					if(this.xCoord!=0){
						tail=new Tail(this.xCoord-1,this.yCoord,handler);
					}else{
						if(this.yCoord!=0){
							tail=new Tail(this.xCoord,this.yCoord-1,handler);
						}else{
							tail=new Tail(this.xCoord,this.yCoord+1,handler);
						}
					}
				}else{
					if(handler.getWorld().body.getLast().x!=0){
						tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
					}else{
						if(handler.getWorld().body.getLast().y!=0){
							tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
						}else{
							tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
						}
					}

				}
				break;
			case "Up":
				if( handler.getWorld().body.isEmpty()){
					if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
						tail=(new Tail(this.xCoord,this.yCoord+1,handler));
					}else{
						if(this.xCoord!=0){
							tail=(new Tail(this.xCoord-1,this.yCoord,handler));
						}else{
							tail=(new Tail(this.xCoord+1,this.yCoord,handler));
						}
					}
				}else{
					if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
						tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
					}else{
						if(handler.getWorld().body.getLast().x!=0){
							tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
						}else{
							tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
						}
					}

				}
				break;
			case "Down":
				if( handler.getWorld().body.isEmpty()){
					if(this.yCoord!=0){
						tail=(new Tail(this.xCoord,this.yCoord-1,handler));
					}else{
						if(this.xCoord!=0){
							tail=(new Tail(this.xCoord-1,this.yCoord,handler));
						}else{
							tail=(new Tail(this.xCoord+1,this.yCoord,handler));
						} System.out.println("Tu biscochito");
					}
				}else{
					if(handler.getWorld().body.getLast().y!=0){
						tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
					}else{
						if(handler.getWorld().body.getLast().x!=0){
							tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
						}else{
							tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
						}
					}

				}
				break;
			}
			handler.getWorld().body.addLast(tail);//adds tail
			handler.getWorld().playerLocation[tail.x][tail.y] = true;//acknowledge that there is a body there now
		}
	}

	public void kill(){
		lenght = 0;
		for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
			for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

				handler.getWorld().playerLocation[i][j]= false;

			}
			State.setState(handler.getGame().gameoverState);//when the snake collides with it self it will appear the Game Over State

		}

	}

	public boolean isJustAte() {
		return justAte;
	}

	public void setJustAte(boolean justAte) {
		this.justAte = justAte;
	}
}
