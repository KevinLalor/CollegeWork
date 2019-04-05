import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class ScorePanel extends JPanel{
	
	/*private static final int TEXT_AREA_HEIGHT = 40;
    private static final int CHARACTER_WIDTH = 47;*/
    private static final int FONT_SIZE = 12;
	
	public JTextArea score;

	public ScorePanel()
	{
		score = new JTextArea();
		score.setEditable(false);
        score.setFont(new Font("monospaced", Font.PLAIN, FONT_SIZE));
        score.setLineWrap(true);
        score.setWrapStyleWord(true);
        score.setText("Player 1 Score: 0 | Player 2 Score: 0 | Game Point: 1 | Match Point: 0");
		setLayout(new BorderLayout());
		add(score, BorderLayout.CENTER);
	}
	
}
