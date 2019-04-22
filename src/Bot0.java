import java.util.Arrays;

public class Bot0 implements BotAPI {

    // The public API of Bot must not change
    // This is ONLY class that you can edit in the program
    // Rename Bot to the name of your team. Use camel case.
    // Bot may not alter the state of the game objects
    // It may only inspect the state of the board and the player objects

    private PlayerAPI me, opponent;
    private BoardAPI board;
    private CubeAPI cube;
    private MatchAPI match;
    private InfoPanelAPI info;

    Bot0(PlayerAPI me, PlayerAPI opponent, BoardAPI board, CubeAPI cube, MatchAPI match, InfoPanelAPI info) {
        this.me = me;
        this.opponent = opponent;
        this.board = board;
        this.cube = cube;
        this.match = match;
        this.info = info;
    }

    public String getName() {
        return "Bot0"; // must match the class name
    }

    //Gives score to which move is best
    public String getCommand(Plays possiblePlays) {
        // Add your code here
    	int score[] = new int [possiblePlays.number()];
    	Arrays.fill(score, 0);
    	score[countDiff(possiblePlays)]++;
    	score[blockBlotDiff(possiblePlays)]++;
    	
    	int command = 1;
    	for(int i = 0; i < possiblePlays.number(); i++) {
    		if(i != 0) {
    			if(score[i] > score[i -1]) {
    				command = i + 1;
    			}
    		} else {
    			command = 1;
    		}
    	}
        return Integer.toString(command);
    }

    public String getDoubleDecision() {
        // Add your code here
        return "n";
    }
    
    // Counts how many moves needed to bear off all pieces
    public int countDiff(Plays possiblePlays) {
    	int [][] currentPipLocations = board.get();
    	int p0 = 0, p1 = 0;
    	int command = 0;

    	//Counts the moves needed for players from current position of pips
    	for(int i = 0; i < 25; i++) {	
    		if(currentPipLocations[me.getId()][i] >= 1) {
    			p0 += i * currentPipLocations[me.getId()][i];
    			
    		}
    		else if(currentPipLocations[opponent.getId()][i] >= 1) {
    			p1 += i * currentPipLocations[opponent.getId()][i];
    		} 
    	}

    	int count1 = 0;
    	String commandString;
		
    	for(int j = 0; j < possiblePlays.number(); j++) {
    		commandString = Integer.toString(j+1);
			Command checkCommand = new Command(commandString, possiblePlays);
			
			count1 = newBoard(checkCommand.getPlay());
			// Need to add in a way to check if the play includes a hit or not
			if(count1 < p0) {
				command = j;
			} 
		}
    	return command;
    }
    
    //Check the difference in blocks of player and blots of opposing player
    //Incomplete: need to check blocks on new board of each play
    public int blockBlotDiff(Plays possiblePlays) {
    	int blocks = 0, blots = 0, diff = 0;
    	int [][] currentPipLocations = board.get();
    	
    	for(int i = 0; i < 25; i++) {
    		if(currentPipLocations[me.getId()][i] > 1) {
    			blocks++;
    		}
    		if(currentPipLocations[opponent.getId()][i] == 1) {
    			blots++;
    		}
    	}
    	
    	diff = blocks-blots;
    	return diff;
    }
    
    //Creates a new board to view look at a play
    public int newBoard(Play play) {
    	Players players = new Players();
    	Player currentPlayer = new Player(me.getId(), me.getColorName(), me.getColor());
    	Board newBoard = new Board(players);
    	newBoard.move(currentPlayer, play);
    	
    	int newCount = 0;
    	int[][] newPipLocations = newBoard.get();
    	
    	for(int i = 0; i < 25; i++) {
    		newCount += i * newPipLocations[me.getId()][i];
    	}
    	return newCount;
    }
}
