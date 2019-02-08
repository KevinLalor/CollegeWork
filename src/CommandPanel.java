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
   Frame For CommandPanel + Action listner for commands.
*/
public class CommandPanel extends JFrame implements ActionListener
{
	
   //Constants 
   private static final int WIDTH_OF_FRAME = 400;
   private static final int HEIGHT_OF_FRAME = 300;
   private static final int ROW_DIMENSION = 10;
   private static final int COLUMN_DIMENSION = 30;
    
   //inputs
   private String userInput;
   
   //Client
   private JLabel commandLabel;
   private JTextField commandField;
   private JButton commandButton;
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
	   
	  commandLabel = new JLabel("Enter Command: ");

      final int width_of_textfield = 15;
      commandField = new JTextField(width_of_textfield);
      commandField.setText("");
      commandField.addActionListener(this);
      
   }
   
   public void actionPerformed(ActionEvent e) 
   {
	   
       userInput = commandField.getText();
       //resultSpace.setText("This is  the User input: " + userInput + "\n");
       commandField.setText("");
       InfoPanel.printToPanel(userInput);
       
       if ( userInput.equals("Exit") || userInput.equals("exit") || userInput.equals("EXIT") || userInput.equals("2") )
       {
    	   System.exit(0) ;
       }
       
   }
   
   private void createButton()  
   {
	   
      commandButton  = new JButton("Enter");
      commandButton.addActionListener(this);

   }
 
   private void createPanel()
   {
	   
      JPanel panel = new JPanel();
      panel.add(commandLabel);
      panel.add(commandField);
      panel.add(commandButton);
      JScrollPane scrollPane = new JScrollPane(resultSpace);
      panel.add(scrollPane);      
      add(panel);
      
   }
   
}


