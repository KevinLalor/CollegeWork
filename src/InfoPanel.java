/**
 * Written by Sean_Stewart
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author Sean_Stewart
 * 
 * A panel that shows the status of the game and the inputs made by the players 
 *
 */

public class InfoPanel extends JFrame {
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 700;
	
	private static final int AREA_ROWS = 40;
	private static final int AREA_COLUMNS = 30;
	
	private static final String INITIAL_TEXT = 	"------------------------------------------------------\n " + 
												"\tStart Menu\n"	+ 
												"------------------------------------------------------\n" +
												"    1.\tNew Game\n" +
												"    2.\tExit\n";
	
	private JTextField infoField;
	private static JTextArea infoArea;
	private String text;
	private static int count = 0;
	
	public InfoPanel() {
		text = INITIAL_TEXT;
		infoArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
		infoArea.setText(text + "\n");
		infoArea.setEditable(false);
		
		createPanel();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	public static void printToPanel(String toPrint) {
		infoArea.append("    "+toPrint + "\n");
		if(count == 0) {
			int input = Integer.parseInt(toPrint);
			switch (input) {
			case 1:
				// Call setup board function
				count++;
				break;
			case 2:
				// Call exit function
				count++;
				break;
			default:
				throw new IllegalArgumentException("Invalid Entry: " + toPrint);
			}
		}
	}

	private void createPanel() {
		JPanel panel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(infoArea);
		panel.add(scrollPane);
		add(panel);
	}
	

}
