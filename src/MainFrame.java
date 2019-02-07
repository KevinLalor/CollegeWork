package softwareEngineeringProject;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private BoardPanel boardPanel;
	
	public MainFrame()
	{
		super("BackGammon");
		
		setLayout(new BorderLayout());
		
		boardPanel = new BoardPanel();
		
		add(boardPanel, BorderLayout.CENTER);
		
		setSize(800, 538);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

}
