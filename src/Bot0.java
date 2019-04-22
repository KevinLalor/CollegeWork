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
    	int i=0;
    	// Applies score based on pip-count difference
    	for(Play play: possiblePlays)
    	{
    		score[i] = countDiff(play);
    		i++;
    	}
    	// score[blockBlotDiff(possiblePlays)]++;
    	
    	int command = 1;
    	for(i = 0; i < possiblePlays.number()-1; i++) {
    		if(score[i+1] > score[i]) {
				command = i + 2;
			} 
    		System.out.println("Score " + (i+1) + ": " + score[i]);
    	}
        return Integer.toString(command);
    }

    public String getDoubleDecision() {
        // Add your code here
        return "n";
    }
    
    /*Return pip count difference betweeen opponent and bot.
      The higher the value, the better the move */
    public int countDiff(Play play) {
    	int [][] currentPipLocations = new int [2][26];
    	for(int[] row:currentPipLocations)
    	{
    		Arrays.fill(row, 0);
    	}
    	int p0 = 0, p1 = 0;
    	int score = 0, i = 0, j = 0;
    	for(i=0; i<2; i++)
    	{
    		for(j=0; j<26; j++)
    		{
    			currentPipLocations[i][j] = board.getNumCheckers(i, j);
    		}
    	}
    	
		p0 = updatePValue(me.getId(), currentPipLocations, play);
		p1 = updatePValue(opponent.getId(), currentPipLocations, play);
		
		System.out.println("p1 = " + p1);
		System.out.println("p0 = " + p0);
		
		return p1-p0;
    }
    
    // Returns the new pip count for the player after a play
    private int updatePValue(int player, int[][] pipLocations, Play play)
    {	
    	for (Move move : play) {
    		pipLocations[me.getId()][move.getFromPip()]--;
            pipLocations[me.getId()][move.getToPip()]++;
            int opposingPlayerId = opponent.getId();
            if (move.getToPip()<Board.BAR && move.getToPip()>Board.BEAR_OFF &&
                    pipLocations[opposingPlayerId][25-move.getToPip()] == 1) {
                pipLocations[opposingPlayerId][25-move.getToPip()]--;
                pipLocations[opposingPlayerId][Board.BAR]++;
            }
    	}
    	int i=0;
    	int pValue=0;
    	for(i = 0; i < 26; i++) {	
    		if(pipLocations[player][i] >= 1) {
    			pValue += i * pipLocations[player][i];  
    		}
    	}
    	return pValue;
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

}
