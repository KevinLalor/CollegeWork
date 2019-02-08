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
public class CommandPanel extends JFrame implements ActionListener
{
   private static final int WIDTH_OF_FRAME = 400;
   private static final int HEIGHT_OF_FRAME = 300;
   
   private static final int ROW_DIMENSION = 10;
   private static final int COLUMN_DIMENSION = 30;
   
   private String userInput;
     
   private JLabel CommandLabel;
   private JTextField textField;
   private JButton button;
   private JTextArea resultSpace;
   
 
   public CommandPanel()
   {  
      resultSpace = new JTextArea(ROW_DIMENSION, COLUMN_DIMENSION);
      
      resultSpace.setEditable(false);
            
      createTextField();
      createButton();
      createPanel();

      setSize(WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
      
   }

   private void createTextField() 
   {
	  CommandLabel = new JLabel("Enter Command: ");

      final int width_of_textfield = 15;
      textField = new JTextField(width_of_textfield);
      textField.setText("");
      textField.addActionListener(this);
   }
   
   public void actionPerformed(ActionEvent e) 
   {
       userInput = textField.getText();
       resultSpace.setText("This is  the User input: " + userInput + "\n");
       textField.setText("");
       
       if ( userInput.equals("Exit") || userInput.equals("exit") || userInput.equals("EXIT") )
       {
    	   System.exit(1) ;
       }
   }
   
   

   private void createButton()  
   {
      button  = new JButton("Enter");
      button.addActionListener(this);
   }
   

   private void createPanel()
   {
      JPanel panel = new JPanel();
      panel.add(CommandLabel);
      panel.add(textField);
      panel.add(button);
      JScrollPane scrollPane = new JScrollPane(resultSpace);
      panel.add(scrollPane);      
      add(panel);
      
   }
}

