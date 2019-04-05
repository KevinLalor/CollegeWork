import java.awt.BorderLayout;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class UI {
    // UI is the top level interface to the user interface

    private static final int FRAME_WIDTH = 1100;
    private static final int FRAME_HEIGHT = 600;

    private final BoardPanel boardPanel;
    private final InfoPanel infoPanel;
    private final CommandPanel commandPanel;
    private final ScorePanel scorePanel;

    UI (Board board, Players players) {
        infoPanel = new InfoPanel();
        commandPanel = new CommandPanel();
        JFrame frame = new JFrame();
        boardPanel = new BoardPanel(board,players);
        scorePanel = new ScorePanel();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Backgammon");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(scorePanel, BorderLayout.PAGE_START);
        frame.add(boardPanel, BorderLayout.LINE_START);
        frame.add(infoPanel, BorderLayout.LINE_END);
        frame.add(commandPanel, BorderLayout.PAGE_END);
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

    public void displayStartOfGame() {
        displayString("Welcome to Backgammon");
    }

    public void promptPlayerName() {
        displayString("Enter a player name:");
    }

    public void displayPlayerColor(Player player) {
        displayString(player + " uses " + player.getColorName() + " checkers.");
    }
    
    //new code , amount of points to win
    public void promptPointsToWin() {
    	displayString("How many points would you like to play to?");
    }
    
    //displays pints to win before starting first game
    //public void displayPointsToWin(Player player) {
    //    displayString(player + " uses " + player.getColorName() + " checkers.");
    //}

    
    public void displayRoll(Player player) {
        displayString(player + " (" + player.getColorName() + ") rolls " + player.getDice());
    }


    public void displayDiceEqual() {
        displayString("Equal. Roll again");
    }

    public void displayDiceWinner(Player player) {
        displayString(player + " wins the roll and goes first.");
    }

    public void displayGameWinner(Player player) {
        displayString(player + " WINS THE GAME!!!");
    }
    
    public void promptPlayAgain() {
    	displayString("Would you like to play again?");
    }

    public void promptCommand(Player player) {
        displayString(player + " (" + player.getColorName() + ") enter your move or quit:");
    }

    public Command getCommand(Plays possiblePlays) {
        Command command;
        do {
            String commandString = commandPanel.getString();
            command = new Command(commandString,possiblePlays);
            if (!command.isValid()) {
                displayString("Error: Command not valid.");
            }
        } while (!command.isValid());
        return command;
    }

    public void displayPlays(Player player, Plays plays) {
        displayString(player + " (" + player.getColorName() + ") available moves...");
        int index = 0;
        for (Play play : plays) {
            String code;
            if (index<26) {
                code = "" + (char) (index%26 + (int) 'A');
            } else {
                code = "" + (char) (index/26 - 1 + (int) 'A') + (char) (index % 26 + (int) 'A');
            }
            displayString(code + ". " + play);
            index++;
        }
    }

    public void displayNoMove(Player player) throws InterruptedException {
        displayString(player + " has no valid moves.");
        TimeUnit.SECONDS.sleep(1);
    }

    public void displayForcedMove(Player player) throws InterruptedException {
        displayString(player + " has a forced move.");
        TimeUnit.SECONDS.sleep(1);
    }
    
    public void updateScore(Players players)
    {
    	Player one = players.get(0);
    	Player two = players.get(1);
    	scorePanel.score.setText("Player 1 Score: " + one.getScore() + " | Player 2 Score: " + two.getScore()
    							+ " | Game Point: " + Backgammon.gamePoint + " | Match Point: " + Backgammon.matchPoint);
    }
    
    // New Code - Sean
    public void offerDoubleCube(Player player) {
    	displayString(player +", would you like to offer the Double Cube?");
    }
    
    public void promptDoubleCube(Player player) {
    	displayString(player + ", do you accept the offer of the Double Cube?");
    }
}
