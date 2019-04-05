import java.util.concurrent.TimeUnit;

public class Backgammon {
    // This is the main class for the Backgammon game. It orchestrates the running of the game.

    public static final int NUM_PLAYERS = 2;
    public static int  matchPoint = 0;
    public static int gamePoint = 1;
    private static int gameCount = 0;

    private final Players players = new Players();
    private final Board board = new Board(players);
    private final UI ui = new UI(board,players);

    private void getPlayerNames() {
        for (Player player : players) {
            ui.promptPlayerName();
            String name = ui.getString();
            ui.displayString("> " + name);
            player.setName(name);
            ui.displayPlayerColor(player);
        }
    }
    //new code
    private int getPointsToWin() {
    	
    	ui.promptPointsToWin();
    	String number = ui.getString();
        matchPoint = Integer.parseInt(number);
    	ui.displayString("Points to win = " + number);
    	System.out.println(matchPoint);
    	return matchPoint;
    }
    
   

    private void rollToStart() {
        do {
            for (Player player : players) {
                player.getDice().rollDie();
                ui.displayRoll(player);
            }
            if (players.isEqualDice()) {
                ui.displayDiceEqual();
            }
        } while (players.isEqualDice());
        players.setCurrentAccordingToDieRoll();
        ui.displayDiceWinner(players.getCurrent());
        ui.display();
    }

    private void takeTurns() throws InterruptedException {
        Command command = new Command();
        boolean firstMove = true;
        do {
            Player currentPlayer = players.getCurrent();
            Dice currentDice;
            if (firstMove) {
                currentDice = new Dice(players.get(0).getDice().getDie(),players.get(1).getDice().getDie());
                firstMove = false;
            } else {
                currentPlayer.getDice().rollDice();
                ui.displayRoll(currentPlayer);
                currentDice = currentPlayer.getDice();
            }
            Plays possiblePlays;
            possiblePlays = board.getPossiblePlays(currentPlayer,currentDice);
            if (possiblePlays.number()==0) {
                ui.displayNoMove(currentPlayer);
            } else if (possiblePlays.number()==1) {
                ui.displayForcedMove(currentPlayer);
                board.move(currentPlayer, possiblePlays.get(0));
            } else {
                ui.displayPlays(currentPlayer, possiblePlays);
                ui.promptCommand(currentPlayer);
                command = ui.getCommand(possiblePlays);
                ui.displayString("> " + command);
                if (command.isMove()) {
                    board.move(currentPlayer, command.getPlay());
                } else if (command.isCheat()) {
                    board.cheat();
                }
            }
            ui.display();
            TimeUnit.SECONDS.sleep(2);
            players.advanceCurrentPlayer();
            ui.display();
        } while (!command.isQuit() && !board.isGameOver());
    }

    private void play() throws InterruptedException {
//        board.setUI(ui);
        ui.display();
        if(gameCount == 0)
        {
        	ui.displayStartOfGame();
            getPlayerNames();
            //new code
            getPointsToWin();
            ui.updateScore(players);
        }
        rollToStart();
        takeTurns();
        if (board.isGameOver() && !(board.isMatchOver(players))) {
            ui.displayGameWinner(board.getWinner());
            gamePoint = 1;
            gameCount++;
            if(board.getWinner() == players.get(0)) {
            	players.get(0).increaseScore();
            }
            else {
            	players.get(1).increaseScore();
            }
            TimeUnit.SECONDS.sleep(5);
            board.reset();
            ui.updateScore(players);
            play();
        }
        if(board.isMatchOver(players)) {
        	if(board.getWinner() == players.get(0)) {
            	players.get(0).increaseScore();
            }
            else {
            	players.get(1).increaseScore();
            }
        	ui.updateScore(players);
        	System.out.println("Match is over");
        	String choice = "";
        	boolean validAnswer = false;
        	while(validAnswer == false) {
        		ui.promptPlayAgain();
        		choice = ui.getString();
        		ui.displayString("> " + choice);
        		if(choice.equals("yes") || choice.equals("Yes"))
        		{
        			gamePoint = 1;
        			board.reset();
        			play();
        			validAnswer = true;
        		}
        		else if (choice.equals("no") || choice.equals("No"))
        		{
        			System.exit(0);
        		}
        		else 
        		{
        			ui.displayString("Invalid choice, please answer again");
        		}
        	}
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Backgammon game = new Backgammon();
        game.play();
        System.exit(0);
    }
}
