public class Backgammon {

    public static final int NUM_PLAYERS = 2;

    private final Players players = new Players();
    private final Board board = new Board();
    private final Dice dice = new Dice();
    private final UI ui = new UI(board,players,dice);

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
                dice.rollDie();
                ui.displayRoll(players.getCurrent(), dice);
                players.next();
            }
            if (dice.areEqual()) {
                ui.displayDiceEqual();
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
        Command command;
        boolean rollDice = false;
        do {
            if (rollDice) {
                dice.rollDice();
                ui.displayRoll(players.getCurrent(), dice);
                rollDice = false;
            }
            ui.promptCommand(players.getCurrent());
            command = ui.getCommand();
            ui.displayString("> " + command);
            if (command.isMove()) {
                board.move(players.getCurrent(),command.getFromPip(),command.getToPip());
                ui.display();
            } else if (command.isNext()) {
                players.next();
                ui.display();
                rollDice = true;
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
