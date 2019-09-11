package Display;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by AlexVR on 7/1/2018.
 */

public class DisplayScreen {

	private JFrame frame;
	private Canvas canvas;
	private String title;
	private int width, height;

	public DisplayScreen(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;



		createDisplay();
	}

	private void createDisplay(){
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setBackground(Color.black);
		

		try {
			frame.setIconImage(ImageIO.read(new File("res/Sheets/WigglesSquigglesIcon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		//creating a new color variable called purple
		Color Purple;
		//Purple RGB Values: R=102, G=0, B=153
		//The color purple selected is a lavender purple, R=150, G=125, B=182
		Purple = new Color(150,125,182);
		//Set Background to Purple
		canvas.setBackground(Purple);
		frame.add(canvas);
		frame.pack();
	}
	

	public Canvas getCanvas(){
		return canvas;
	}

	public JFrame getFrame(){
		return frame;
	}

}
