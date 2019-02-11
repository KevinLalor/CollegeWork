
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private BoardPanel boardPanel;
	private CommandPanel commandPanel;
	private InfoPanel infoPanel;
	private Checker currentChecker;
	
	public MainFrame()
	{
		super("BackGammon");
		
		setLayout(new BorderLayout());
		
		boardPanel = new BoardPanel();
		commandPanel = new CommandPanel();
		infoPanel = new InfoPanel();
		currentChecker = boardPanel.currentChecker;
		
		commandPanel.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				infoPanel.printToPanel(text);
			    currentChecker.moveChecker(text);
			}		
		});
		
		add(boardPanel, BorderLayout.CENTER);
		add(commandPanel, BorderLayout.SOUTH);
		add(infoPanel, BorderLayout.EAST);
		
		setSize(1200, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

}
