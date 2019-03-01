public class Command {

    private String input;
    private boolean valid, move, next, quit;
    private int fromPip, toPip;

    private boolean isPip(String string) {
        return string.matches("\\d+") && Integer.parseInt(string)>=0 && Integer.parseInt(string)<=25;
    }

    Command(String input) {
        this.input = input;
        String text = input.toLowerCase().trim();
        if (text.equals("quit")) {
            valid = true;
            move = false;
            next = false;
            quit = true;
        } else if (text.equals("next")) {
            valid = true;
            move = false;
            next = true;
            quit = false;
        } else {
            String[] words = text.split("\\s+");
            if (words.length==2 && isPip(words[0]) && isPip(words[1])) {
                fromPip = Integer.parseInt(words[0]);
                toPip = Integer.parseInt(words[1]);
                valid = true;
                move = true;
                next = false;
                quit = false;
            } else {
                valid = false;
            }
        }
    }

    public int getFromPip() {
        return fromPip;
    }

    public int getToPip() {
        return toPip;
    }

    boolean isValid() {
        return valid;
    }

    boolean isMove() {
        return move;
    }

    boolean isNext() {
        return next;
    }

    boolean isQuit() {
        return quit;
    }

    public String toString() {
        return input;
    }
}
