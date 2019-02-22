import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Backgammon {

    public static final int NUM_PLAYERS = 2;

    private final Board board = new Board();
    private final UI ui = new UI(board);
    private String[] player = new String[NUM_PLAYERS];
    
    // Sets the initial game up
    private void setup() {
    	ui.display();
    }
    
    // Play the game
    private void playGame() throws InterruptedException {
    	
    	ui.displayString("Welcome to BackGammon! A game developed by GackBammon!");
    	// Assigns player names and checker colours.
    	ui.displayString("Player 1: please enter your name.");
    	player[0] = ui.getCommand();
    	ui.displayString("Welcome, " + player[0] + "! Your checker colour is red!");
    	ui.displayString("Player 2: please enter your name.");
    	player[1] = ui.getCommand();
    	ui.displayString("Welcome, " + player[1] + "! Your checker colour is green!");
    	// Rolls dice to see who goes first.
    	ui.displayString("The dice will now roll twice to see who goes first, if the two rolls are equal it will roll twice again...");
    	TimeUnit.SECONDS.sleep(1);
    	int valOne=0, valTwo=0;
    	while(valOne == valTwo)
    	{
    		ui.displayString("Dice is rolled for player 1...");
        	valOne = ui.rollDice();
        	TimeUnit.SECONDS.sleep(2);
        	ui.displayString("Dice is rolled for player 2...");
        	valTwo = ui.rollDice();
        	TimeUnit.SECONDS.sleep(2);
    	}
    	
    	int whoGoesFirst;
    	if(valOne > valTwo)
    		whoGoesFirst = 0;
    	else
    		whoGoesFirst = 1;
    	
    	// Moves players initial checker
    	this.moveChecker(whoGoesFirst, Board.NUM_PIPS , Board.NUM_PIPS-(ui.rollDicePanel.left.getValue() + ui.rollDicePanel.right.getValue()));
 
    	String command;
    	// Switches player
    	int currentPlayer = whoGoesFirst;
    	do {
    		// Switches the player 
    		if(currentPlayer == 1)
    			currentPlayer = 0;
    		else 
    			currentPlayer = 1;
    		// rollDice() function called for the player
    		ui.displayString("Player " + (currentPlayer+1) + ", please roll the dice.");
    		ui.displayString("Player " + (currentPlayer+1) + ", please select a checker to move and where to move it.\n"
    				+ "e.g., '24 20' moves the checker at pip 24 to pip 20");
    		command = ui.getCommand();
    		ui.displayString(command + " Entered");
    		int[] move = this.readCommand(command);
    		this.moveChecker(currentPlayer, move[0], move[1]);
    	}  while (!command.equals("quit")); 
    	
    }
    
    private void moveChecker(int player, int from, int to) throws InterruptedException {	
    	for(int pip = from; pip > to; pip--)
    	{
    		board.move(player, pip, pip-1);
        	ui.display(); 
        	TimeUnit.SECONDS.sleep(1);
    	}
    }
    
    private int[] readCommand(String command) {
    	
    	Scanner scan = new Scanner(command);
    	int[] move = new int[2];
    	
    	scan.useDelimiter("\\s+");
    	move[0] = scan.nextInt();
    	move[1] = scan.nextInt();
    	
    	return move;	
    }

    private void testUI() throws InterruptedException {
        // Moves a checker on to the bar and then moves it one pip at a time around the board.
        // Does this for both players.
        ui.display();
        // Changes player
        for (int player=0; player<NUM_PLAYERS; player++) {
            board.move(player,Board.NUM_PIPS,Board.BAR);
            ui.display();
            TimeUnit.SECONDS.sleep(1);
            // Moves pip for that player
            for (int pip=Board.BAR; pip>0; pip--) {
                board.move(player, pip, pip-1);
                ui.display();
                TimeUnit.SECONDS.sleep(1);
            }
        }
        String message;
        do {
            message = ui.getCommand();
            ui.displayString(message);
        } while (!message.equals("quit"));
    }

    public static void main (String[] args) throws InterruptedException {
        Backgammon game = new Backgammon();
        game.setup();
        game.playGame();
        //game.testUI();
        System.exit(0);
    }
}
