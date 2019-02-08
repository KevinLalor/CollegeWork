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
	private Checker[] whiteCheckers = new Checker[15];
	private Checker[] blackCheckers = new Checker[15];
	
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
		 
		 int i=0;
		 // Creating and drawing the white checkers
		 int y=435;
		 for(i=0; i<5; i++)
		 {
			 whiteCheckers[i] = new Checker(Color.WHITE, Color.BLACK, g2, 325, y);
			 y -= 20;
		 }
		 y=435;
		 for(i=5; i<8; i++)
		 {
			 whiteCheckers[i] = new Checker(Color.WHITE, Color.BLACK, g2, 190, y);
			 y -= 20;
		 }
		 y=25;
		 for(i=8; i<13; i++)
		 {
			 whiteCheckers[i] = new Checker(Color.WHITE, Color.BLACK, g2, 27, y);
			 y += 20;
		 }
		 y=25;
		 for(i=13; i<15; i++)
		 {
			 whiteCheckers[i] = new Checker(Color.WHITE, Color.BLACK, g2, 532, y);
			 y += 20;
		 }
		 
		 // Creating and drawing the black checkers
		 y=25;
		 for(i=0; i<5; i++)
		 {
			 blackCheckers[i] = new Checker(Color.BLACK, Color.WHITE, g2, 325, y);
			 y += 20;
		 }
		 y=25;
		 for(i=5; i<8; i++)
		 {
			 blackCheckers[i] = new Checker(Color.BLACK, Color.WHITE, g2, 193, y);
			 y += 20;
		 }
		 y=435;
		 for(i=8; i<13; i++)
		 {
			 blackCheckers[i] = new Checker(Color.BLACK, Color.WHITE, g2, 27, y);
			 y -= 20;
		 }
		 y=435;
		 for(i=13; i<15; i++)
		 {
			 blackCheckers[i] = new Checker(Color.BLACK, Color.WHITE, g2, 532, y);
			 y -= 20;
		 }

		 g2.drawRect(610, 20, 55, 185);
		 g2.drawRect(610, 300, 55, 185);
	}

}
