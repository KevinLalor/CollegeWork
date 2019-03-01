import java.awt.*;

public class Player {

    private int id;
    private String colorName;
    private Color color;
    private String name;

    Player(int number, String colorName, Color color) {
        this.id = number;
        this.name = name;
        this.colorName = colorName;
        this.color = color;
        name = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getColorName() {
        return this.colorName;
    }

    public Color getColor() {
        return this.color;
    }

    public String toString() {
        return name;
    }
}
