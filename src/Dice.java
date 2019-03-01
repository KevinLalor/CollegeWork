class Dice {

    private boolean oneDieRoll;
    private int[] dice;
    private int die, previousDie;

    Dice() {
        oneDieRoll = false;
        dice = new int[]{1, 1};
        die = 1;
        previousDie = 1;
    }

    public void rollDie() {
        previousDie = die;
        die = 1 + (int)(Math.random()*6);
        oneDieRoll = true;
    }

    public void rollDice() {
        dice[0] = 1 + (int)(Math.random() * 6);
        dice[1] = 1 + (int)(Math.random() * 6);
        oneDieRoll = false;
    }

    public boolean isDouble() {
        return (dice[0] == dice[1]);
    }

    public boolean areEqual() {return previousDie == die;}

    public boolean previousGreaterThanCurent() {
        return previousDie > die;
    }

    public String toString() {
        String roll;
        if (oneDieRoll) {
            roll = "[" + die + "]";
        } else {
            roll = "[" + dice[0] + "," + dice[1] + "]";
        }
        return roll;
    }
}
