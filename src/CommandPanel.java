import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
   A frame that shows the growth of an investment with variable interest,
   using a text area.
*/
public class CommandPanel extends JFrame
{
   private static final int Width_Of_Frame = 500;
   private static final int Height_Of_Frame = 500;
   
   private static final int ROW_DIMENSION = 10;
   private static final int COLUMN_DIMENSION = 30;
     
   private JLabel CommandLabel;
   private JTextField TextField;
   private JButton button;
   private JTextArea resultSpace;
   
 
   public CommandPanel()
   {  
      
      resultSpace = new JTextArea(ROW_DIMENSION, COLUMN_DIMENSION);
      
      resultSpace.setEditable(false);
            
      createTextField();
      createButton();
      createPanel();

      setSize(Width_Of_Frame, Height_Of_Frame);
   }

   private void createTextField()
   {
	  CommandLabel = new JLabel("Enter Command: ");

      final int Width_Of_TextField = 15;
      TextField = new JTextField(Width_Of_TextField);
      TextField.setText("");
   }
   
   
   
   

   private void createButton()
   {
      button = new JButton("Enter");      
      
      
      
   }
   

   private void createPanel()
   {
      JPanel panel = new JPanel();
      panel.add(CommandLabel);
      panel.add(TextField);
      panel.add(button);
      JScrollPane scrollPane = new JScrollPane(resultSpace);
      panel.add(scrollPane);      
      add(panel);
   }
}

