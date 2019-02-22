import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;

public class TestDice extends JFrame{
	public TestDice() {
		this.setContentPane(new RollDicePanel());
	}//constructor
	
	//jframe and content pane 
	public static void main(String[] args) {
		JFrame DiceWindow = new JFrame();
		DiceWindow.setTitle("My Dice Test");
		DiceWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DiceWindow.setContentPane(new RollDicePanel());
		DiceWindow.pack();
		DiceWindow.show();
		
		
	}//end of main in tets file
	

}
