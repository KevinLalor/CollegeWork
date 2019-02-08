package softwareEngineeringProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Checker {
	
	private Color colour, outline;
	private int x, y;
	Graphics g;
	
	public Checker(Color col, Color outline, Graphics g, int x, int y)
	{
		this.colour = col;
		this.outline = outline;
		this.g = g;
		this.x = x;
		this.y = y;
		
		paintChecker(g, x, y);
	}
	
	public void paintChecker(Graphics g, int x, int y)
	{
		Graphics2D g2 = (Graphics2D) g;

		Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 40, 40);
		g2.setColor(outline);
		g2.draw(circle);
		g2.setColor(colour);
		g2.fill(circle);
	}
	
	public void moveChecker()
	{ 
		paintChecker(g, x, y);
	} 

}
