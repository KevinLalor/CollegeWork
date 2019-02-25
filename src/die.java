import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;

public class die extends JPanel {
	//instance Variables
	private int value; //shows on face of die
	private int diam = 30; // diam of spots on dice
	//class variables
	private static Random random = new Random();
	
	//white backround + pixels. Roll 
	
	public die() {
		setBackground(Color.black);
		//pref size (can change by layout)
		setPreferredSize(new Dimension(300, 300));
		roll(); //set to random initial value
		
		
		
	}
	
	//produce random roll!
	public int roll() {
	int val = random.nextInt(6) + 1;//1-6 range
	setValue(val);
	return val;
	
	}
	
	//return result of last roll
	public int getValue() {
		return value;
	}
	
	//repaint value
	public void setValue(int spots) {
		value = spots;
		repaint();
		
	}
	

	
	/** Draws spots of die face. */
	public void paintComponent(Graphics g) {
	super.paintComponent(g); // required
	int w = getWidth();
	int h = getHeight(); // should use to resize spots too.
	switch (value) {
	case 1: drawSpot(g, w/2, h/2);
	break;
	case 3: drawSpot(g, w/2, h/2);
	// Fall thru to next case
	case 2: drawSpot(g, w/4, h/4);
	drawSpot(g, 3*w/4, 3*h/4);
	break;
	case 5: drawSpot(g, w/2, h/2);
	// Fall thru to next case
	case 4: drawSpot(g, w/4, h/4);
	drawSpot(g, 3*w/4, 3*h/4);
	drawSpot(g, 3*w/4, h/4);
	drawSpot(g, w/4, 3*h/4);
	break;
	case 6: drawSpot(g, w/4, h/4);
	drawSpot(g, 3*w/4, 3*h/4);
	drawSpot(g, 3*w/4, h/4);
	drawSpot(g, w/4, 3*h/4);
	drawSpot(g, w/4, h/2);
	drawSpot(g, 3*w/4, h/2);
	break;
	}
	}//end paintComponent
	/** Utility method used by paintComponent(). */
	//================================================== method drawSpot
	private void drawSpot(Graphics g, int x, int y) {
	g.setColor(Color.white);
	g.fillOval(x-diam/2, y-diam/2, diam, diam);
	}//end drawSpot
	}//
	


