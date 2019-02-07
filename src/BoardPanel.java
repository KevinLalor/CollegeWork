package softwareEngineeringProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	
	private BufferedImage boardImage;
	
	public BoardPanel()
	{
		setLayout(new BorderLayout());
		
		try 
		{
			boardImage = ImageIO.read(this.getClass().getResource("Board.png"));
		} 
		catch (IOException ex) 
		{
			System.out.println("Could not find the image file " + ex.toString());
		}
		
	} 
	
	public void paintComponent(Graphics g)
	{
		 super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D) g;

		 g2.drawImage(boardImage, 0, 0, 600, 500, this);
		 
		 int y = 435;
		 
		 // Adding white checkers to the board.
		 while(y > 334)
		 {
			 Checker newCheckerWhite = new Checker(Color.WHITE);
			 newCheckerWhite.drawChecker(325, y, g, Color.WHITE, Color.BLACK);
			 y -= 20;
		 }
		 y = 435;
		 while(y > 394)
		 {
			 addCircle(190, y, g, Color.WHITE, Color.BLACK);
			 y -= 20;
		 }
		 y = 25;
		 while(y < 46)
		 {
			 addCircle(532, y, g, Color.WHITE, Color.BLACK);
			 y += 20;
		 }
		 y = 25;
		 while(y < 126)
		 {
			 addCircle(27, y, g, Color.WHITE, Color.BLACK);
			 y += 20;
		 }
		 
		 // Adding black checkers to the board.
		 y = 435;
		 while(y > 414)
		 {
			 addCircle(532, y, g, Color.BLACK, Color.WHITE);
			 y -= 20;
		 }
		 y = 25;
		 while(y < 126)
		 {
			 addCircle(325, y, g, Color.BLACK, Color.WHITE);
			 y += 20;
		 }
		 y = 435;
		 while(y > 334)
		 {
			 addCircle(27, y, g, Color.BLACK, Color.WHITE);
			 y -= 20;
		 }
		 y = 25;
		 while(y < 66)
		 {
			 drawCheckers(193, y, g, Color.BLACK, Color.WHITE);
			 y += 20;
		 }
		 
		 g2.drawRect(610, 20, 55, 185);
		 g2.drawRect(610, 300, 55, 185);
	}

}
