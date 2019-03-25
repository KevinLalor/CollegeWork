import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;

public class RollDicePanel extends JPanel{
	
	//Dice
	public Die right;
	public Die left;
	//end of instance variables
	
	// border layout panel with two die's and one button
	
	RollDicePanel(){
		
		right = new Die();
		left  = new Die();
		
		//button to roll
		
		JButton rollButton = new JButton("Roll");
		rollButton.addActionListener(new RollListener());
		
		//Panel for Two Dice
		
		JPanel dicePanel = new JPanel();
		Dimension size = new Dimension(250, getHeight());
		dicePanel.setPreferredSize(size);
		dicePanel.setLayout(new GridLayout(1, 2, 4, 0));
		dicePanel.add(right);
		dicePanel.add(left);
		
		// Components to Content Pane
		setLayout(new BorderLayout());
		add(rollButton, BorderLayout.NORTH);
		add(dicePanel, BorderLayout.CENTER);
	}//end constructor
	
	//INNER LISTENER CLASS FOR ROLL BUTTON
	private class RollListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			right.roll();
			left.roll();
		}
	}

}