
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Checker extends BoardPanel{
	
	private Color colour, outline;
	public int x, y;
	Graphics g;
	private int count = 0;
	
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
	
	public void paintComponent(Graphics g)
	{
		paintChecker(g, this.x, this.y);
	}
	
    public void moveChecker(String command)
	{
		int newX = 610;
		int newY;
    	if(count == 0)
    	{
    		newY = 20;
    	}
    	else
    	{
    		newY = 300;
    	}
    	// x and y less than newX and newY
    	int input = Integer.parseInt(command);
    	switch(input) {
    	case 3: 
    		System.out.println("3 inputted yo");
    		if(this.x < newX && this.y < newY)
    		{
    			while(this.x != newX && this.x < newX)
    			{
    				this.x ++;
    				repaint();
    			}
    			while(this.y != newY)
    			{
    				this.y ++;
    				repaint();
    			}
    		}
    		// x less than newX and y greater than newY
    		if(this.x < newX && this.y < newY)
    		{
    			while(this.x != newX && this.x < newX)
    			{
    				this.x ++;
    				repaint();
    			}
    			while(this.y != newY)
    			{
    				this.y --;
    				repaint();
    			}
    		}
    		// x greater than newX and y less than newY
    		if(this.x < newX && this.y < newY)
    		{
    			while(this.x != newX && this.x < newX)
    			{
    				this.x --;
    				repaint();
    			}
    			while(this.y != newY)
    			{
    				this.y ++;
    				repaint();
    			}
    		}
    		// x greater than newX and y greater than newY
    		if(this.x < newX && this.y < newY)
    		{
    			while(this.x != newX && this.x < newX)
    			{
    				this.x --;
    				repaint();
    			}
    			while(this.y != newY)
    			{
    				this.y --;
    				repaint();
    			}
    		}
    		count++;
    		break;
    	default:
    		break;
    	}
    	
	} 

}
