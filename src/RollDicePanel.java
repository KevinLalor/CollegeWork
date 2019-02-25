import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;

public class RollDicePanel extends JPanel{
	
	//Dice
	private die right;
	private die left;
	//end of instance variables
	
	// bordr layout panel with two die's and one button
	
	RollDicePanel(){
		
		right = new die();
		left  = new die();
		
		//button to roll
		
		JButton rollButton = new JButton("Roll");
		rollButton.addActionListener(new RollListener());
		
		//Panel for Two Dice
		
		JPanel dicePanel = new JPanel();
		dicePanel.setLayout(new GridLayout(1, 2, 4, 0));
		dicePanel.add(right);
		dicePanel.add(left);
		
		// Compnents to Content Pane
		this.setLayout(new BorderLayout());
		this.add(rollButton, BorderLayout.NORTH);
		this.add(dicePanel, BorderLayout.CENTER);
	}//end constructor
	
	//INNER LISTENER CLASS FOR ROLL BUTTON
	private class RollListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			right.roll();
			left.roll();
		}
	}

}
