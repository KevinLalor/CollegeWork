import java.awt.*;

public class Players {

    public static int NUM_PLAYERS = 2;

    private Player[] players;
    private int currentPlayer;

    Players() {
        players = new Player[NUM_PLAYERS];
        players[0] = new Player(0,"RED", new Color(255,51,51));
        players[1] = new Player(1,"GREEN",Color.GREEN);
        currentPlayer = 0;
    }

    public void setCurrent(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrent() {
        return players[currentPlayer];
    }

    public void next() {
        currentPlayer++;
        if (currentPlayer == NUM_PLAYERS) {
            currentPlayer = 0;
        }
    }

    public Player get(int id) {
        return players[id];
    }

}
