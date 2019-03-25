import java.awt.Color;

public class Board {
	
	static Color r = new Color(255,51,51);
	static Color g = Color.GREEN;

    private static final int[] RESET = {0,0,0,0,0,0,5,0,3,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,2,0};
    private static final Color[] RESET_COLORS_PLAYER_ONE = {null,g,null,null,null,null,r,null,r,null,null,null,g,r,null,null,null,g,null,g,null,null,null,null,r};
    private static final Color[] RESET_COLORS_PLAYER_TWO = {null,r,null,null,null,null,g,null,g,null,null,null,r,g,null,null,null,r,null,r,null,null,null,null,g};
    public static final int BAR = 25;           // index of the BAR
    public static final int BEAR_OFF = 0;       // index of the BEAR OFF
    private static final int INNER_END = 6;     // index for the end of the inner board
    public static final int NUM_PIPS = 24;      // excluding BAR and BEAR OFF
    public static final int NUM_SLOTS = 26;     // including BAR and BEAR OFF
    private static final int NUM_CHECKERS = 15;


    private int[][] checkers;
    // Array that checks what color checker is on the pip number, if any
    public Color[][] pipColor;
        // 2D array of checkers
        // 1st index: is the player id
        // 2nd index is number pip number, 0 to 25
        // pip 0 is bear off, pip 25 is the bar, pips 1-24 are on the main board
        // the value in checkers is the number of checkers that the player has on the point

    Board () {
        checkers = new int[Backgammon.NUM_PLAYERS][NUM_SLOTS];
        pipColor = new Color[Backgammon.NUM_PLAYERS][NUM_PIPS+1];
        for (int player=0; player<Backgammon.NUM_PLAYERS; player++)  {
            for (int pip=0; pip<NUM_SLOTS; pip++)   {
                checkers[player][pip] = RESET[pip];
            }
        }
        // Original colour checkers on the pips
        for(int pip=0; pip<NUM_PIPS+1; pip++)
        {
        	pipColor[0][pip] = RESET_COLORS_PLAYER_ONE[pip]; 
        } 
        for(int pip=0; pip<NUM_PIPS+1; pip++)
        {
        	pipColor[1][pip] = RESET_COLORS_PLAYER_TWO[pip]; 
        } 
    }

    public void move(Player player, int from, int to) {
    	// Updates the color checker on the pip
    	if(this.getNumCheckers(player.getId(), from) == 1)
    	{
    		pipColor[player.getId()][from] = null;
    	}
        pipColor[player.getId()][to] = player.getColor(); 
        checkers[player.getId()][from]--;
        checkers[player.getId()][to]++;
    }

    public int getNumCheckers(int player, int pip) {
        return checkers[player][pip];
    }

}