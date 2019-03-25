/**
 * 
 * @author Sean Stewart
 * 
 * Conditions for pip movement
 *
 */
public class MoveConditions {
	InfoPanel info = new InfoPanel();
	
	public boolean condsMet = true;		

	// Function for checking the format of the input given by the user.
	public boolean checkFormat(String s) {
		char tmp1, tmp2;	// Temporary variables to store chars of the user input given.
		int start, end;		// Variables that will be given the start and end position of the pip.
		
		// Switch statement to check for the valid string sizes and
		// convert the the string to integer values which are then
		// passed to the checkNum() function.
		switch(s.length()) {
		case 3:
			if(s.charAt(1) == 32) {
				tmp1 = s.charAt(0);
				start = Character.getNumericValue(tmp1);
				
				tmp1 = s.charAt(2);
				end = Character.getNumericValue(tmp1);
				
				checkNum(start, end);
			}
			break;
			
		case 4:
			if(s.charAt(1) == 32) {
				tmp1 = s.charAt(0);
				start = Character.getNumericValue(tmp1);
				
				tmp1 = s.charAt(2);
				tmp2 = s.charAt(3);
				end = 10*(Character.getNumericValue(tmp1)) + Character.getNumericValue(tmp2);
				
				checkNum(start, end);
			}
			
			if(s.charAt(2) == 32) {
				tmp1 = s.charAt(0);
				tmp2 = s.charAt(1);
				start = 10*(Character.getNumericValue(tmp1)) + Character.getNumericValue(tmp2);
				
				tmp1 = s.charAt(3);
				end = Character.getNumericValue(tmp1);
				
				checkNum(start, end);
			}
			break;
			
		case 5:
			if(s.charAt(3) == 32) {
				tmp1 = s.charAt(0);
				tmp2 = s.charAt(1);
				start = 10*(Character.getNumericValue(tmp1)) + Character.getNumericValue(tmp2);
				
				tmp1 = s.charAt(2);
				tmp2 = s.charAt(3);
				end = 10*(Character.getNumericValue(tmp1)) + Character.getNumericValue(tmp2);
				
				checkNum(start, end);
			}
			break;
			
		default:
			info.addText("Invalid input! Please re-enter!");
			condsMet = false;
			break;
		}
		return condsMet;
	}
	
	// Function to check if the numbers given in the input are valid
	private void checkNum(int start, int end) {
		if(start >= 0 && start <= 25 && end >= 0 && end <= 25)
			if(start > end)
				condsMet = true;
			else {
				info.addText("Starting position must be greater than the end position! Please re-enter!");
				condsMet = false;
			}
		else {
			info.addText("Invalid numbers given! Please re-enter!");
			condsMet = false;
		}
			
	}
}
