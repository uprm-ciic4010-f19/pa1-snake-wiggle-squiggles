package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class Images {


	public static BufferedImage[] butstart;
	public static BufferedImage title;
	public static BufferedImage Pause;
	public static BufferedImage[] Resume;
	public static BufferedImage[] BTitle;
	public static BufferedImage[] Options;
	public static ImageIcon icon;

	//Added buttons
	public static BufferedImage[] BMenu; 
	public static BufferedImage[] BMenuP;
	public static BufferedImage[] BStart;
	public static BufferedImage[] BStartP;
	public static BufferedImage[] BRestart;
	public static BufferedImage[] BRestartP;
	public static BufferedImage[] BExit;
	public static BufferedImage[] BExitP;
	public static BufferedImage[] BResume;
	public static BufferedImage[] BResumeP;

	//Screen Backgrounds
	public static BufferedImage GameOver;
	
	//Apple Sprite
	public static BufferedImage GoodApple;
	public static BufferedImage RottenApple;
	public static BufferedImage RainbowApple;
	


	public Images() {

		butstart = new BufferedImage[3];
		Resume = new BufferedImage[2];
		BTitle = new BufferedImage[2];
		Options = new BufferedImage[2];

		//Added Buttons
		BMenu = new BufferedImage[2];
		BStart = new BufferedImage[2];
		BRestart = new BufferedImage[2];
		BExit = new BufferedImage[2];
		BResume = new BufferedImage[2];


		try {

			title = ImageIO.read(getClass().getResourceAsStream("/Sheets/TittleScreen.png"));//Changed the title screen
			Pause = ImageIO.read(getClass().getResourceAsStream("/Sheets/PauseScreen.png"));//Changed the pause screen
			Resume[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Resume.png"));
			Resume[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/ResumeP.png"));
			BTitle[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitle.png"));
			BTitle[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitleP.png"));
			Options[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Options.png"));
			Options[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/OptionsP.png"));
			butstart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/NormBut.png"));//normbut
			butstart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/HoverBut.png"));//hoverbut
			butstart[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ClickedBut.png"));//clickbut

			icon =  new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Sheets/icon.png")));
			//Added Buttons 
			BMenu[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BMenu.png"));
			BMenu[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BMenuP.png"));

			BStart[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BStart.png"));
			BStart[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BStartP.png"));

			BRestart[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BRestart.png"));
			BRestart[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BRestartP.png"));

			BExit[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BExit.png"));
			BExit[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BExitP.png"));

			BResume[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BResume.png"));
			BResume[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BResumeP.png"));

			//Added a new Game Over screen
			GameOver = ImageIO.read(getClass().getResourceAsStream("/Sheets/GameOverScreen.png"));
			
			//Apple Sprites
			GoodApple = ImageIO.read(getClass().getResourceAsStream("/Sheets/GoodAppleSprite.png"));
			RottenApple = ImageIO.read(getClass().getResourceAsStream("/Sheets/RottenAppleSprite.png"));
			RainbowApple = ImageIO.read(getClass().getResourceAsStream("/Sheets/RainbowAppleSprite.png"));

		}catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(Images.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

}
