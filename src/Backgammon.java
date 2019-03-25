import javafx.scene.paint.Color;

public class Backgammon {

    public static final int NUM_PLAYERS = 2;

    private final Players players = new Players();
    private final Board board = new Board();
    private final Dice dice = new Dice();
    private final UI ui = new UI(board,players,dice);
    private int roll = 0;
	private int rollTmp = 0;

    private void announce() {
        ui.display();
        ui.displayAnnouncement();
    }

    private void getPlayerNames() {
        players.setCurrent(0);
        for (int p=0; p<players.NUM_PLAYERS; p++) {
            ui.promptPlayerName();
            String name = ui.getString();
            ui.displayString("> " + name);
            players.getCurrent().setName(name);
            ui.displayPlayerColor(players.getCurrent());
            players.next();
        }
    }

    private void rollToStart() {
        do {
            for (int p=0; p<players.NUM_PLAYERS; p++) {
                rollTmp = dice.rollDie();
                if(roll < rollTmp)
                {
                	roll = rollTmp;
                }
                ui.displayRoll(players.getCurrent(), dice);
                players.next();
            }
            if (dice.areEqual()) {
                ui.displayDiceEqual();
                roll = 0;
                rollTmp = 0;
            }
        } while (dice.areEqual());
        if (dice.previousGreaterThanCurent()) {
            players.setCurrent(0);
        } else {
            players.setCurrent(1);
        }
        ui.displayDiceWinner(players.getCurrent());
        ui.display();
    }

    private void takeTurns() {
        Command command = new Command("");
        boolean rollDice = false;
        int turnCount = 0;
        int possibleMoves = 0;
        boolean completed = false;
        Move move = new Move();
        do {
            if (rollDice) {
                dice.rollDice();
                if(move.isDouble(dice.dice[0], dice.dice[1]))
                {
                	move.dieLeft = 4;
                	possibleMoves = 4;
                }
                else 
                {
                	move.dieLeft = 2;
                	possibleMoves = 2;
                }
                ui.displayRoll(players.getCurrent(), dice);
                rollDice = false;
            }
            if(turnCount == 0 && !(completed))
            {
            	move.dieLeft = 1;
            	possibleMoves = 1;
            	completed = true;
            }
            ui.promptCommand(players.getCurrent());
            if(turnCount == 0)
            {
            	ui.displayMovesFirstTime(roll, players.getCurrent(), board, move);
            }
            else
            {
            	 if(possibleMoves == 1)
            	 {
            		 if(command.getFromPip()-command.getToPip() == dice.dice[0])
            		 {
            			 ui.displayMoves(dice.dice[1], dice.dice[1], players.getCurrent(), board, move);
            			 move.numMoves = 0;
            		 }
            		 else
            		 {
            			 ui.displayMoves(dice.dice[0], dice.dice[0], players.getCurrent(), board, move);
            			 move.numMoves = 0;
            		 }
            	 }
            	 else
            	 {
            		 ui.displayMoves(dice.dice[0], dice.dice[1], players.getCurrent(), board, move);
            		 move.numMoves = 0;
            	 }
            }
            command = ui.getCommand();
            ui.displayString("> " + command);
            if (command.isMove()) {
                board.move(players.getCurrent(),command.getFromPip(),command.getToPip());
                ui.display();
                if(turnCount == 0)
                {
                	players.next();
                	ui.display();
                	rollDice = true;
                	turnCount++;
                }
                if(command.getFromPip()-command.getToPip() == dice.dice[0] + dice.dice[1])
                {
                	move.dieLeft -= 2;
                	possibleMoves -=2;
                }
                else if(command.getFromPip()-command.getToPip() == dice.dice[0] || command.getFromPip()-command.getToPip() == dice.dice[1])
                {
                	move.dieLeft--;
                	possibleMoves--;
                }
                else if(command.getFromPip()-command.getToPip() == dice.dice[0]*3)
                {
                	move.dieLeft -= 3;
                	possibleMoves -= 3;
                }
                else if(command.getFromPip()-command.getToPip() == dice.dice[0]*4)
                {
                	move.dieLeft -= 4;
                	possibleMoves -= 4;
                }
                if(move.dieLeft == 0 && turnCount > 0)
                {
                	players.next();
                	ui.display();
                	rollDice = true;
                	turnCount++;
                }  
            } 
            else if (command.isNext()) {
            	players.next();
            	ui.display();
            	rollDice = true;
            	turnCount++; 
            }	
        } while (!command.isQuit());
    }

    private void play() {
        announce();
        getPlayerNames();
        rollToStart();
        takeTurns();
    }

    public static void main(String[] args) {
        Backgammon game = new Backgammon();
        game.play();
        System.exit(0);
    }
}
