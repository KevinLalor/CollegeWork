package softwareEngineeringProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Checker {
	
	public Color color;
	
	
	public Checker(Color col)
	{
		this.color = col;
	}
	
	public void moveChecker()
	{
		
	}
	
	public void drawChecker(int x, int y, Graphics g, Color colour, Color outline)
	{
		Graphics2D g2 = (Graphics2D) g;

		Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 40, 40);
		g2.setColor(outline);
		g2.draw(circle);
		g2.setColor(colour);
		g2.fill(circle);
		
	}
	
}
