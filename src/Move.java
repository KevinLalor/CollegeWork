import java.awt.Color;

class Move
{
	
	public int numMoves;
	int dieLeft;
    private int destOne, destTwo, dest, secondDie;
	// Only used for doubles 
	private int destThree, destFour;
	public String[] legalMoves;
	private Color[] colors = new Color[] {new Color(255,51,51), Color.GREEN};
	
	Move()
	{
		numMoves = 0;
		dieLeft = 0;
		legalMoves = new String[50];
	} 
	
	public void checkDice(int dieOne, int dieTwo, Player player, Board board, int pip)
	{
		// If it's possible to move for value of dieOne + dieTwo
		if(pip-(dieOne+dieTwo) >= 0)
		{	
			destOne = pip-dieOne;
			destTwo = pip-(dieOne+dieTwo);
			secondDie = pip-dieTwo;
			// If where it lands is on an opponent's checker
			if(landsOpponentsChecker((dieOne+dieTwo), player, board, pip))
			{
				int opponent;
				if(player.getId() == 0)
					opponent = 1;
				else
					opponent = 0;
				// If destTwo is a hit
				if(isHit((dieOne+dieTwo), board, pip, opponent)) 
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + "*");
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + "*");
				}
				// If only destTwo is a hit
				if(isHit((dieOne+dieTwo), board, pip, opponent) && !(isHit(dieOne, board, pip, opponent)))
				{
					numMoves++;
					if(isBar(pip))
					{
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo + "*");
					}
					else
					{
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destTwo + "*");
					}
				}
				// If destOne and destTwo are both hits
				if(isHit(dieOne, board, pip, opponent) && isHit((dieOne+dieTwo), board, pip, opponent))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destTwo + "*");
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destTwo + "*");
				}
				//##################################
				// If secondDie isnt a hit and destTwo is a hit
				if(isHit((dieOne+dieTwo), board, pip, opponent) && !(isHit(dieTwo, board, pip, opponent))
						&& !(isDuplicate(pip, destOne, secondDie, board, opponent)))
				{
					numMoves++;
					if(isBar(pip))
					{
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + secondDie + " " + secondDie + "-" + destTwo + "*");
					}
					else
					{
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + secondDie + " " + secondDie + "-" + destTwo + "*");
					}
				}
				// If secondDie then destTwo are both hits
				if(isHit(dieOne, board, pip, opponent) && isHit((dieOne+dieTwo), board, pip, opponent)
						&& !(isDuplicate(pip, destOne, secondDie, board, opponent)))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + secondDie + "* " + secondDie + "-" + destTwo + "*");
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + secondDie + "* " + secondDie + "-" + destTwo + "*");
				}
			}
			// Only have to deal with bear-off here as never gonna be bear-off if lands on opponent's checker
			else
			{
				int opponent;
				if(player.getId() == 0)
					opponent = 1;
				else
					opponent = 0;
				
				numMoves++;
				if(isBar(pip))
				{
					legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo);
					legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo);
				}
				else if(isBearOff(pip, (dieOne+dieTwo)))
				{
					legalMoves[numMoves-1] = ("Move " + numMoves + ":" + pip + "-" + destOne + " " + destOne + "-OFF");
					legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-OFF");
				}
				else
				{
					legalMoves[numMoves-1] = ("Move " + numMoves + ":" + pip + "-" + destOne + " " + destOne + "-" + destTwo);
					legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo);
				}
				
				if(isBar(pip) && !(isDuplicate(pip, destOne, secondDie, board, opponent)))
				{
					legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + secondDie + " " + secondDie + "-" + destTwo);
				}
				else if(isBearOff(pip, (dieOne+dieTwo)) && !(isDuplicate(pip, destOne, secondDie, board, opponent)))
				{
					legalMoves[numMoves-1] = ("Move " + numMoves + ":" + pip + "-" + secondDie + " " + secondDie + "-OFF");
				}
				else if(!(isDuplicate(pip, destOne, secondDie, board, opponent)))
				{
					legalMoves[numMoves-1] = ("Move " + numMoves + ":" + pip + "-" + secondDie + " " + secondDie + "-" + destTwo);
				}
					
			}
		}
	}
	
	public void checkDie(int die, Player player, Board board, int pip)
	{
		// If it's possible to move for value of one of the dies
		if(pip-die >= 0)
		{	
			dest = pip-die;
			// If where it lands is on an opponent's checker
			if(landsOpponentsChecker(die, player, board, pip))
			{
				int opponent;
				if(player.getId() == 0)
					opponent = 1;
				else
					opponent = 0;
				// If it's a hit
				if(isHit(die, board, pip, opponent))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + dest + "*");
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + dest + "*");
				}
			}
			// Only have to deal with bear-off here as never gonna be bear-off if lands on opponent's checker
			else
			{
				numMoves++;
				if(isBar(pip))
					legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + dest);
				else if(isBearOff(pip, die))
					legalMoves[numMoves-1] = ("Move " + numMoves + ":" + pip + "-OFF");
				else
					legalMoves[numMoves-1] = ("Move " + numMoves + ":" + pip + "-" + dest);
					
			}
		}
	}
	
	// Only used in the case of a double
	public void checkThreeDie(int die, Player player, Board board, int pip)
	{
		// If it's possible to move for value of die*3
		if(pip-(die*3) >= 0)
		{	
			destOne = pip-die;
			destTwo = pip-(die*2);
			destThree = pip-(die*3);
			// If where it lands is on an opponent's checker
			if(landsOpponentsChecker((die*3), player, board, pip))
			{
				int opponent;
				if(player.getId() == 0)
					opponent = 1;
				else
					opponent = 0;
				// If destThree is a hit regardless of destOne and destTwo
				if(isHit(die*3, board, pip, opponent))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destThree + "*");
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destThree + "*");
				}
				// If destOne and destThree are hits regardless of destTwo
				if(isHit(die, board, pip, opponent) && isHit(die*3, board, pip, opponent))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destThree + "*");
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destThree + "*");
				}
				// If destOne is not a hit and destThree is a hit regardless of destTwo
				if(isHit(die, board, pip, opponent) && isHit(die*3, board, pip, opponent))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destThree + "*");
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destThree + "*");
				}
				// If destTwo and destThree are hits regardless of destOne
				if(isHit(die, board, pip, opponent) && isHit(die*3, board, pip, opponent))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + "* " + destTwo + "-" + destThree + "*");
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + "* " + destTwo + "-" + destThree + "*");
				}
				// If destTwo is not a hit and destThree is a hit regardless of destOne
				if(isHit(die, board, pip, opponent) && isHit(die*3, board, pip, opponent))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + " " + destTwo + "-" + destThree + "*");
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + " " + destTwo + "-" + destThree + "*");
				}
				// If only destThree is a hit
				if(isHit(die*3, board, pip, opponent) && !(isHit(die, board, pip, opponent)) 
						&& !(isHit(die*2, board, pip, opponent)))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo + " "
								+ destTwo + "-" + destThree + "*");
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destTwo + " "
								+ destTwo + "-" + destThree + "*");
				}
				// If only destOne and destThree are hits
				if(isHit(die, board, pip, opponent) && !(isHit(die*2, board, pip, opponent)) 
						&& isHit(die*3, board, pip, opponent))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destTwo + " "
								+ destTwo + "-" + destThree + "*");
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destTwo + " "
								+ destTwo + "-" + destThree + "*");
				}
				// If only destTwo and destThree are hits
				if(!(isHit(die, board, pip, opponent)) && isHit(die*2, board, pip, opponent)
						&& isHit(die*3, board, pip, opponent))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo + "* "
								+ destTwo + "-" + destThree + "*");
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destTwo + "* "
								+ destTwo + "-" + destThree + "*");
				}
				// If destOne, destTwo and destThree are all hits
				if(isHit(die, board, pip, opponent) && isHit(die*2, board, pip, opponent)
						&& isHit(die*3, board, pip, opponent))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destTwo + "* "
								+ destTwo + "-" + destThree + "*");
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destTwo + "* "
								+ destTwo + "-" + destThree + "*");
				}
			}
			// Only have to deal with bear-off here as never gonna be bear-off if lands on opponent's checker
			else
			{
				numMoves++;
				if(isBar(pip))
				{
					legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo + " "
							+ destTwo + "-" + destThree);
					legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destThree);
				}
				else if(isBearOff(pip, die*3))
				{
					legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destTwo + " "
							+ destTwo + "-OFF");
					legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-OFF");
				}
				else 
				{
					legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destTwo + " "
							+ destTwo + "-" + destThree);
					legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destThree);
				}
				int opponent;
				if(player.getId() == 0)
					opponent = 1;
				else
					opponent = 0;
				// If destOne is a hit
				if(isHit(die, board, pip, opponent))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destThree);
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destThree);
				}
				// If destOne isn't a hit
				if(!(isHit(die, board, pip, opponent)))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destThree);
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destThree);
				}
				// If destTwo is a hit
				if(isHit(die*2, board, pip, opponent))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + "* " + destTwo + "-" + destThree);
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + "* " + destTwo + "-" + destThree);
				}
				// If destTwo isn't a hit
				if(!(isHit(die*2, board, pip, opponent)))
				{
					numMoves++;
					if(isBar(pip))
						legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + " " + destTwo + "-" + destThree);
					else
						legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + " " + destTwo + "-" + destThree);
				}
					
			}
		}
	}
	
	// Only used in the case of a double
	public void checkFourDie(int die, Player player, Board board, int pip)
	{
		// If it's possible to move for value of die*4
				if(pip-(die*4) >= 0)
				{	
					destOne = pip-die;
					destTwo = pip-(die*2);
					destThree = pip-(die*3);
					destFour = pip-(die*4);
					// If where it lands is on an opponent's checker
					if(landsOpponentsChecker((die*4), player, board, pip))
					{
						int opponent;
						if(player.getId() == 0)
							opponent = 1;
						else
							opponent = 0;
						//If 4 is a hit regardless of 1, 2 and 3
						if(isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destFour + "*");
						}
						// If 1 and 4 are hits regardless of 2 and 3
						if(isHit(die*4, board, pip, opponent) && isHit(die, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "*" + " " + destOne + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "*" + " " + destOne + "-" + destFour + "*");
						}
						// If 1 is not a hit and 4 is a hit regardless of 2 and 3
						if(isHit(die*4, board, pip, opponent) && !(isHit(die, board, pip, opponent)))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destFour + "*");
						}
						// If 2 is a hit and 4 is a hit regardless of 1 and 3
						if(isHit(die*4, board, pip, opponent) && isHit(die*2, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + "* " + destTwo + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + "* " + destTwo + "-" + destFour + "*");
						}
						// If 2 is not a hit and 4 is a hit regardless of 1 and 3
						if(isHit(die*4, board, pip, opponent) && !(isHit(die*2, board, pip, opponent)))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + " " + destTwo + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + " " + destTwo + "-" + destFour + "*");
						}
						// If 3 is a hit and 4 is a hit regardless of 1 and 2
						if(isHit(die*4, board, pip, opponent) && isHit(die*3, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destThree + "* " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destThree + "* " + destThree + "-" + destFour + "*");
						}
						// If 3 is not a hit and 4 is a hit regardless of 1 and 2
						if(isHit(die*4, board, pip, opponent) && !(isHit(die*3, board, pip, opponent)))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destThree + " " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destThree + " " + destThree + "-" + destFour + "*");
						}
						//########################
						// If 1, 2 and 4 are hits regardless of 3
						if(isHit(die, board, pip, opponent) && isHit(die*2, board, pip, opponent) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destTwo + 
										"* " + destTwo + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destTwo + 
										"* " + destTwo + "-" + destFour + "*");
						}
						// If 1 is a hit and 2 is not a hit and 4 is a hit regardless of 3
						if(isHit(die, board, pip, opponent) && !(isHit(die*2, board, pip, opponent)) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destTwo + 
										" " + destTwo + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destTwo + 
										" " + destTwo + "-" + destFour + "*");
						}
						// If 1 is not a hit and 2 is a hit and 4 is a hit regardless of 3
						if(!(isHit(die, board, pip, opponent)) && isHit(die*2, board, pip, opponent) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo + 
										"* " + destTwo + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destTwo + 
										"* " + destTwo + "-" + destFour + "*");
						}
						// If 1 and 2 are not hits and 4 is a hit regardless of 3
						if(!(isHit(die, board, pip, opponent)) && !(isHit(die*2, board, pip, opponent)) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo + 
										" " + destTwo + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destTwo + 
										" " + destTwo + "-" + destFour + "*");
						}
						//###########################
						// If 2, 3 and 4 are hits regardless of 1
						if(isHit(die*2, board, pip, opponent) && isHit(die*3, board, pip, opponent) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + "* " + destTwo + "-" + destThree + 
										"* " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + "* " + destTwo + "-" + destThree + 
										"* " + destThree + "-" + destFour + "*");
						}
						// If 2 is a hit and 3 is not a hit and 4 is a hit regardless of 1
						if(isHit(die*2, board, pip, opponent) && !(isHit(die*3, board, pip, opponent)) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + "* " + destTwo + "-" + destThree + 
										" " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + "* " + destTwo + "-" + destThree + 
										" " + destThree + "-" + destFour + "*");
						}
						// If 2 is not a hit and 3 is a hit and 4 is a hit regardless of 1
						if(!(isHit(die*2, board, pip, opponent)) && isHit(die*3, board, pip, opponent) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + " " + destTwo + "-" + destThree + 
										"* " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + " " + destTwo + "-" + destThree + 
										"* " + destThree + "-" + destFour + "*");
						}
						// If 2 and 3 are not hits and 4 is a hit regardless of 1
						if(!(isHit(die*2, board, pip, opponent)) && !(isHit(die*3, board, pip, opponent)) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + " " + destTwo + "-" + destThree + 
										" " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + " " + destTwo + "-" + destThree + 
										" " + destThree + "-" + destFour + "*");
						}
						//#########################
						// If 1, 3 and 4 are hits regardless of 2
						if(isHit(die, board, pip, opponent) && isHit(die*3, board, pip, opponent) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destThree + 
										"* " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destThree + 
										"* " + destThree + "-" + destFour + "*");
						}
						// If 1 is a hit and 3 is not a hit and 4 is a hit regardless of 2
						if(isHit(die, board, pip, opponent) && !(isHit(die*3, board, pip, opponent)) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destThree + 
										" " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destThree + 
										" " + destThree + "-" + destFour + "*");
						}
						// If 1 is not a hit and 3 is a hit and 4 is a hit regardless of 2
						if(!(isHit(die, board, pip, opponent)) && isHit(die*3, board, pip, opponent) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destThree + 
										"* " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destThree + 
										"* " + destThree + "-" + destFour + "*");
						}
						// If 1 and 3 are not hits and 4 is a hit regardless of 2
						if(!(isHit(die, board, pip, opponent)) && !(isHit(die*3, board, pip, opponent)) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destThree + 
										" " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destThree + 
										" " + destThree + "-" + destFour + "*");
						}
						// If only destFour is a hit
						if(!(isHit(die, board, pip, opponent)) && !(isHit(die*2, board, pip, opponent)) 
								&& !(isHit(die*3, board, pip, opponent)) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo + " "
										+ destTwo + "-" + destThree + " " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destTwo + " "
										+ destTwo + "-" + destThree + " " + destThree + "-" + destFour + "*");
						}
						// If only destOne and destFour are hits
						if(isHit(die, board, pip, opponent) && !(isHit(die*2, board, pip, opponent))
								&& !(isHit(die*3, board, pip, opponent)) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destTwo + " "
										+ destTwo + "-" + destThree + " " + " " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destTwo + " "
										+ destTwo + "-" + destThree + " " + " " + destThree + "-" + destFour + "*");
						}
						// If only destTwo and destFour are hits
						if(!(isHit(die, board, pip, opponent)) && isHit(die*2, board, pip, opponent)
								&& !(isHit(die*3, board, pip, opponent)) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo + "* "
										+ destTwo + "-" + destThree + " " + " " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destTwo + "* "
										+ destTwo + "-" + destThree + " " + " " + destThree + "-" + destFour + "*");
						}
						// If only destThree and destFour are hits
						if(!(isHit(die, board, pip, opponent)) && !(isHit(die*2, board, pip, opponent))
								&& isHit(die*3, board, pip, opponent) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo + " "
										+ destTwo + "-" + destThree + "* " + " " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destTwo + " "
										+ destTwo + "-" + destThree + "* " + " " + destThree + "-" + destFour + "*");
						}
						// If only destOne, destThree and destFour are hits
						if(isHit(die, board, pip, opponent) && !(isHit(die*2, board, pip, opponent))
								&& isHit(die*3, board, pip, opponent) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destTwo + " "
										+ destTwo + "-" + destThree + "*" + " " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destTwo + " "
										+ destTwo + "-" + destThree + "*" + " " + destThree + "-" + destFour + "*");
						}
						// If only destOne, destTwo and destFour are hits
						if(isHit(die, board, pip, opponent) && isHit(die*2, board, pip, opponent)
								&& !(isHit(die*3, board, pip, opponent)) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destTwo + "* "
										+ destTwo + "-" + destThree + "" + " " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destTwo + "* "
										+ destTwo + "-" + destThree + "" + " " + destThree + "-" + destFour + "*");
						}
						// If only destTwo, destThree and destFour are hits
						if(!(isHit(die, board, pip, opponent)) && isHit(die*2, board, pip, opponent)
								&& isHit(die*3, board, pip, opponent) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo + "* "
										+ destTwo + "-" + destThree + "*" + " " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destTwo + "* "
										+ destTwo + "-" + destThree + "*" + " " + destThree + "-" + destFour + "*");
						}
						// If destOne, destTwo, destThree and destFour are all hits
						if(isHit(die, board, pip, opponent) && isHit(die*2, board, pip, opponent)
								&& isHit(die*3, board, pip, opponent) && isHit(die*4, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destTwo + "* "
										+ destTwo + "-" + destThree + "*" + " " + destThree + "-" + destFour + "*");
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destTwo + "* "
										+ destTwo + "-" + destThree + "*" + " " + destThree + "-" + destFour + "*");
						}
					}
					// Only have to deal with bear-off here as never gonna be bear-off if lands on opponent's checker
					else
					{
						numMoves++;
						if(isBar(pip))
						{
							legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo + " "
									+ destTwo + "-" + destThree + " " + destThree + "-" + destFour);
							legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destFour);
						}			
						else if(isBearOff(pip, die*4))
						{
							legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destTwo + " "
									+ destTwo + "-" + destThree + " " + destThree + "-OFF");
							legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-OFF");
						}
						else 
						{
							legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destTwo + " "
									+ destTwo + "-" + destThree + " " + destThree + "-" + destFour);
							legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destFour);
						}
						
						int opponent;
						if(player.getId() == 0)
							opponent = 1;
						else
							opponent = 0;
						// If 1 is a hit
						if(isHit(die, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "*" + " " + destOne + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "*" + " " + destOne + "-" + destFour);
						}
						// If 1 is not a hit
						if(!(isHit(die, board, pip, opponent)))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destFour);
						}
						// If 2 is a hit
						if(isHit(die*2, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + "* " + destTwo + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + "* " + destTwo + "-" + destFour);
						}
						// If 2 is not a hit
						if(!(isHit(die*2, board, pip, opponent)))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + " " + destTwo + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + " " + destTwo + "-" + destFour);
						}
						// If 3 is a hit
						if(isHit(die*3, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destThree + "* " + destThree + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destThree + "* " + destThree + "-" + destFour);
						}
						// If 3 is not a hit
						if(!(isHit(die*3, board, pip, opponent)))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destThree + " " + destThree + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destThree + " " + destThree + "-" + destFour);
						}
						//###################
						// If 1 and 2 are hits
						if(isHit(die, board, pip, opponent) && isHit(die*2, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destTwo + 
										"* " + destTwo + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destTwo + 
										"* " + destTwo + "-" + destFour);
						}
						// If 1 is a hit and 2 is not a hit
						if(isHit(die, board, pip, opponent) && !(isHit(die*2, board, pip, opponent)))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destTwo + 
										" " + destTwo + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destTwo + 
										" " + destTwo + "-" + destFour);
						}
						// If 1 is not a hit and 2 is a hit
						if(!(isHit(die, board, pip, opponent)) && isHit(die*2, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo + 
										"* " + destTwo + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destTwo + 
										"* " + destTwo + "-" + destFour);
						}
						// If 1 and 2 are not hits
						if(!(isHit(die, board, pip, opponent)) && !(isHit(die*2, board, pip, opponent)))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destTwo + 
										" " + destTwo + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destTwo + 
										" " + destTwo + "-" + destFour);
						}
						//###########################
						// If 2 and 3 are hits
						if(isHit(die*2, board, pip, opponent) && isHit(die*3, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + "* " + destTwo + "-" + destThree + 
										"* " + destThree + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + "* " + destTwo + "-" + destThree + 
										"* " + destThree + "-" + destFour);
						}
						// If 2 is a hit and 3 is not a hit
						if(isHit(die*2, board, pip, opponent) && !(isHit(die*3, board, pip, opponent)))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + "* " + destTwo + "-" + destThree + 
										" " + destThree + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + "* " + destTwo + "-" + destThree + 
										" " + destThree + "-" + destFour);
						}
						// If 2 is not a hit and 3 is a hit
						if(!(isHit(die*2, board, pip, opponent)) && isHit(die*3, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + " " + destTwo + "-" + destThree + 
										"* " + destThree + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + " " + destTwo + "-" + destThree + 
										"* " + destThree + "-" + destFour);
						}
						// If 2 and 3 are not hits
						if(!(isHit(die*2, board, pip, opponent)) && !(isHit(die*3, board, pip, opponent)))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destTwo + " " + destTwo + "-" + destThree + 
										" " + destThree + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destTwo + " " + destTwo + "-" + destThree + 
										" " + destThree + "-" + destFour);
						}
						//#####################
						// If 1 and 3 are hits
						if(isHit(die, board, pip, opponent) && isHit(die*3, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destThree + 
										"* " + destThree + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destThree + 
										"* " + destThree + "-" + destFour);
						}
						// If 1 is a hit and 3 is not a hit
						if(isHit(die, board, pip, opponent) && !(isHit(die*3, board, pip, opponent)))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + "* " + destOne + "-" + destThree + 
										" " + destThree + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + "* " + destOne + "-" + destThree + 
										" " + destThree + "-" + destFour);
						}
						// If 1 is not a hit and 3 is a hit
						if(!(isHit(die, board, pip, opponent)) && isHit(die*3, board, pip, opponent))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destThree + 
										"* " + destThree + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destThree + 
										"* " + destThree + "-" + destFour);
						}
						// If 1 and 3 are not hits
						if(!(isHit(die, board, pip, opponent)) && !(isHit(die*3, board, pip, opponent)))
						{
							numMoves++;
							if(isBar(pip))
								legalMoves[numMoves-1] = ("Move " + numMoves + ": BAR" + "-" + destOne + " " + destOne + "-" + destThree + 
										" " + destThree + "-" + destFour);
							else
								legalMoves[numMoves-1] = ("Move " + numMoves + ": " + pip + "-" + destOne + " " + destOne + "-" + destThree + 
										" " + destThree + "-" + destFour);
						}
							
					}
				}
	}
	
	public boolean isHit(int dice, Board board, int pip, int opponent)
	{
		int player;
		Color colorOpponent;
		int n;
		if(opponent == 0)
		{
			player = 1;
			colorOpponent = colors[0];
		}
		else
		{
			player = 0;
			colorOpponent = colors[1];
		}	

		n = (24-(pip-dice))*2;
		n = 23-n;
		n = (pip-dice)-n;
		// System.out.println("n = " + n);
		
		if(board.pipColor[player][pip-dice] != null)
		{
			if(board.pipColor[player][pip-dice].equals(colorOpponent) && board.getNumCheckers(opponent, n) == 1)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public boolean isBearOff(int pip, int dice)
	{
		if((pip-dice) == Board.BEAR_OFF)
		{
			return true;
		}
		else
			return false;
	}
	
	public boolean isBar(int pip)
	{
		if(pip == Board.BAR)
			return true;
		else
			return false;
	}
	
	public boolean isDouble(int dieOne, int dieTwo)
	{
		if(dieOne == dieTwo)
		{
			return true;
		}
		else 
			return false;
	}
	
	public boolean isDuplicate(int pip, int dieOne, int dieTwo, Board board, int opponent)
	{
		boolean duplicate = false;
		if(isHit((dieTwo), board, pip, opponent) && isHit((dieOne), board, pip, opponent)
				|| !(isHit((dieTwo), board, pip, opponent)) && !(isHit((dieOne), board, pip, opponent)))
		{
			duplicate = true;
		}
		else
		{
			duplicate = false;
		}
		
		return duplicate;
		
	}
	
	public boolean landsOpponentsChecker(int dice, Player player, Board board, int pip)
	{
		if(pip-dice >= 0)
		{
			if(board.pipColor[player.getId()][pip-(dice)] != player.getColor() && 
					board.pipColor[player.getId()][pip-(dice)] != null)
			{
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

}
