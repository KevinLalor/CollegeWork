import java.awt.BorderLayout;
import javax.swing.*;

public class UI {

    private static final int FRAME_WIDTH = 1300;
    private static final int FRAME_HEIGHT = 600;

    private final BoardPanel boardPanel;
    private final InfoPanel infoPanel;
    private final CommandPanel commandPanel;
    private final RollDicePanel rollDicePanel;

    UI (Board board, Players players, Dice dice) {
        infoPanel = new InfoPanel();
        commandPanel = new CommandPanel();
        JFrame frame = new JFrame();
        boardPanel = new BoardPanel(board,players);
        rollDicePanel = new RollDicePanel();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Backgammon");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(boardPanel, BorderLayout.LINE_START);
        frame.add(infoPanel, BorderLayout.CENTER);
        frame.add(commandPanel, BorderLayout.PAGE_END);
        frame.add(rollDicePanel, BorderLayout.LINE_END);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public String getString() {
        return commandPanel.getString();
    }


    public void display() {
        boardPanel.refresh();
    }

    public void displayString(String string) {
        infoPanel.addText(string);
    }
    
    // Displays the possible moves
    public void displayMoves(int dieOne, int dieTwo, Player player, Board board, Move move)
	{
    	/* System.out.println(player.getId());
    	System.out.println(player.getColor()); */
		int pip;
		// Scan the board looking for pips to move
		for(pip=Board.NUM_PIPS; pip>=0; pip--)
		{
			// Checks if pip is correct player colour and that there are actually checker(s) on the pip
			if(board.getNumCheckers(player.getId(), pip) > 0 && board.pipColor[player.getId()][pip].equals(player.getColor()))
			{ 
				// Gathers all the possible moves together
				if(move.dieLeft == 4)
				{
					move.checkDie(dieOne, player, board, pip);
					move.checkDice(dieOne, dieTwo, player, board, pip);
					move.checkThreeDie(dieOne, player, board, pip);
					move.checkFourDie(dieOne, player, board, pip);
				}
				else if(move.dieLeft == 3)
				{
					move.checkDie(dieOne, player, board, pip);
					move.checkDice(dieOne, dieTwo, player, board, pip);
					move.checkThreeDie(dieOne, player, board, pip);
				}
				else if(move.dieLeft == 2)
				{
					if(move.isDouble(dieOne, dieTwo))
					{
						move.checkDie(dieOne, player, board, pip);
						move.checkDice(dieOne, dieTwo, player, board, pip);
					}
					else
					{
						move.checkDie(dieOne, player, board, pip);
						move.checkDie(dieTwo, player, board, pip);
						move.checkDice(dieOne, dieTwo, player, board, pip);
					}
				}
				else if(move.dieLeft == 1)
				{
					move.checkDie(dieOne, player, board, pip);
				}
			}
		}
		
		for(int i=0; i<move.numMoves; i++)
		{
			displayString(move.legalMoves[i]);
		}
	} 
    
    public void displayMovesFirstTime(int die, Player player, Board board, Move move)
    {
    	int pip;
		// Scan the board looking for pips to move
		for(pip=Board.NUM_PIPS; pip>=0; pip--)
		{
			// Checks if pip is correct player colour and that there are actually checker(s) on the pip
			if(board.getNumCheckers(player.getId(), pip) > 0 && board.pipColor[player.getId()][pip].equals(player.getColor()))
			{ 
				move.checkDie(die, player, board, pip);
			}
		}
		
		for(int i=0; i<move.numMoves; i++)
		{
			displayString(move.legalMoves[i]);
		}
    }

    public void displayAnnouncement() {
        displayString("Welcome to Backgammon");
    }

    public void promptPlayerName() {
        displayString("Enter a player name:");
    }

    public void displayPlayerColor(Player player) {
        displayString(player + " uses " + player.getColorName() + " checkers.");
    }

    public void displayRoll(Player player, Dice dice) {
        displayString(player + " rolls " + dice);
    }

    public void displayDiceEqual() {
        displayString("Equal. Roll again");
    }

    public void displayDiceWinner(Player player) {
        displayString(player + " wins the roll and goes first.");
    }

    public void promptCommand(Player player) {
        displayString(player + " (" + player.getColorName() + ") enter your move, next or quit:");
    }

    public Command getCommand() {
        Command command;
        do {
            String commandString = commandPanel.getString();
            command = new Command(commandString);
            if (!command.isValid()) {
                displayString("Error: Command not valid.");
            }
        } while (!command.isValid());
        return command;
    }
}