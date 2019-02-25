import java.awt.BorderLayout;
import javax.swing.*;

public class UI {

    private static final int FRAME_WIDTH = 1300;
    private static final int FRAME_HEIGHT = 600;

    private final BoardPanel boardPanel;
    private final InfoPanel infoPanel;
    private final CommandPanel commandPanel;
    public RollDicePanel rollDicePanel;

    UI(Board board) {
        infoPanel = new InfoPanel();
        commandPanel = new CommandPanel();
        JFrame frame = new JFrame();
        boardPanel = new BoardPanel(board);
        rollDicePanel = new RollDicePanel();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Backgammon");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(boardPanel, BorderLayout.LINE_START);
        frame.add(infoPanel, BorderLayout.CENTER);
        frame.add(commandPanel,BorderLayout.PAGE_END);
        frame.add(rollDicePanel,BorderLayout.LINE_END);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public String getCommand() {
        return commandPanel.getCommand();
    }

    public void display() {
        boardPanel.refresh();
    }

    public void displayString(String string) {
        infoPanel.addText(string);
    }
    
    public int rollDice() {
    	rollDicePanel.right.roll();
    	rollDicePanel.left.roll();
    	if (rollDicePanel.right.roll() == rollDicePanel.left.roll()){
    		return rollDicePanel.left.roll() + rollDicePanel.left.roll() + rollDicePanel.left.roll() + rollDicePanel.left.roll();
    	}
    	else {
    		return rollDicePanel.right.getValue() + rollDicePanel.left.getValue();
    	}
    }
}