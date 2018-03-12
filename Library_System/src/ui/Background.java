package ui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Background extends Canvas
{

	 Image image;
	
	public Background()	
	{
		
//		image=Toolkit.getDefaultToolkit().getImage("C:/Users/uses/workspace/Library_System/src/pictures/background.jpg");
	}
	
	public void paint(Graphics g)
	{
		
		Graphics2D g2 = (Graphics2D) (g);
		
		int width = getSize().width, height = getSize().height;
		
		g2.drawImage(image,0,0,width,height,this);
		
	}
	
	
}