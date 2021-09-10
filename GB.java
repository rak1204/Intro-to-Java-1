
public class GB {
  
  private final int noLengths = 11;		 
	private final int LengthLength = 12;  
	private final int neutralCones = 3;  
	private final int wNum = 3;		  
	private User[] pArr = null; 
	private int tCnt = 0;		 
	private Length[] tArr = new Length[noLengths];
	private int neuCone = 0; 	
	
   
    public GB(String[] names, Color[] colors) {
    pArr = new User[names.length];
    	
    	for(int i = 0; i < names.length; i++)
    		pArr[i] = new User(names[i],Util.User_IDS[i],colors[i]);
    	for(int i = 0; i < noLengths; i++)    
    	   tArr[i] = new Length(i+2);   
    }

    
   
    public User getCurrentUser() 
    {
    	
    	return pArr[tCnt];
    }

    
    
    public User[] getUsers() 
    {
    	
        return pArr;
    }

    
   
    public Length[] getLengths() 
    {  
    	
        return tArr;
    }

    
    
    public boolean isWinner(User toCheck) 
    {
        int toChecksOwnedLengths = 0; 	
        
    	
        for(int t = 0; t < tArr.length; t++) 
        {									   
        	if( tArr[t].getOwner() == toCheck.getId() )
        		toChecksOwnedLengths++;
        }
    	
        
        return toChecksOwnedLengths == wNum;  
        
    }

    
  
    public String isValidChoice(int sum) 
    {
        String retString = null; 			
        
        
        if(sum < 2)
        	sum = sum + 2;
       
        neuCone = 0; 
        
        
        for(int t = 0; t < tArr.length; t++)
        {
        	for(int s = 0; s < tArr[t].getLength() + 1; s++)
        	{
        		if(tArr[t].getIdAt(s) == Util.NEUTRAL_ID)
        			neuCone++;
        	}
        }
        
        				
    	if( tArr[sum - 2].isOwned() )  								
    		retString = "Length owned";
    	
        
        if( neuCone >= neutralCones && 
        	!tArr[sum - 2].active() )
        	retString = "No neutral cones";
     					
    	if( tArr[sum - 2].isOwned() )  								
    		retString = "Length owned";
    	
        return retString;
    }
    
    
    public void startNewTurn() 
    {
    	neuCone = 0;	
    }

    
    
    public boolean didUserBust(int[] rolls) 
    {
    	
    	ArrayList<Integer> pPairs = new ArrayList<Integer>();				
    	
    	
    	for (int i = 0; i < rolls.length; i++) 
    	{
    		for (int j = 0; j < rolls.length; j++ ) 
    		{
    			if(i != j)
    			{
    				pPairs.add(rolls[i] + rolls[j]);
    			}
    		}
    	}
    	
    	neuCone = 0;  
    	for(int i = 0; i < tArr.length; i++)
    	{
    		for(int j = 0; j < tArr[i].getLength() + 1; j++)
    		{
    			if(tArr[i].getIdAt(j) == Util.NEUTRAL_ID)
    				neuCone++;
    		}
    	}
    	
    	
    	for (int k = 0; k < pPairs.size(); k++)
    	{
    		
    		if( (neuCone < neutralCones) || 
    			tArr[pPairs.get(k) - 2].active() ) 
    		{ 					                   
    			return false;
    		}
    	}
    	
    	return true;
    }

    
   
    public void handleUserChoice(int[] rolls, int firstPairSum) 
    {
        int rSum = 0; 		
    	int srSum = 0;	
    	boolean fSum = false; 
    	
    	if(!didUserBust(rolls)){
    		
    		for(int s = 0; s < rolls.length; s++)
    		{
    			rSum += rolls[s];
    		}
    		
    		srSum = rSum - firstPairSum;
    	}
    	
    	
    	if( (neuCone < neutralCones) || 
    		tArr[firstPairSum - 2].active())
    	{
    		tArr[firstPairSum - 2].advance(getCurrentUser().getId());
    		fSum = true;
    	}
    	
    	neuCone = 0; 
    	for(int i = 0; i < tArr.length; i++)
    	{
    		for(int j = 0; j < tArr[i].getLength() + 1; j++)
    		{
    			if(tArr[i].getIdAt(j) == Util.NEUTRAL_ID)
    				neuCone++;
    		}
    	}
    	
    	
    	if( fSum &&  ( neuCone < neutralCones || tArr[srSum - 2].active() ) && !tArr[srSum - 2].isOwned() )
    	{
    		tArr[srSum-2].advance(getCurrentUser().getId());
    	}
    	
    	System.out.print(this.toString());										
    }
    
    
   
    public boolean endTurn(boolean busted) 
    {
    	for(int t = 0; t < tArr.length; t++)
    	{
    		if(tArr[t].active() || 
    		   tArr[t].getOwner() == Util.NEUTRAL_ID)
    		{
    			
    			if(busted)		
    				tArr[t].clear();

    			
    			else  
    				tArr[t].commit(getCurrentUser().getId());
    		}
    	}
    	
    	System.out.print(this.toString());										
    	
    	
    	if (isWinner( getCurrentUser()))
    		return true;
    	else 
    	{
    		advanceUser();
    		return false;
    	}	
    }

    
    public void advanceUser() 
    {
    	
    	if(tCnt >= pArr.length - 1)								
    	{
    		tCnt = 0;
    	}	
    	else
    		tCnt++;
    }

    
  
  
    public String toString() 
    {
    	
    	String totalString;			
    	String ownerRow = "";		
    	
    	
    	char[][] hBoard = new char[tArr.length][LengthLength];
    	
    	
    	char[][] vBoard = new char[LengthLength][tArr.length];
    	
    	String boardString = "";		
    	
    	final char SPACE = '-';		   
    	
    	for(int o = 0; o < tArr.length; o++)
    	{
    		ownerRow += tArr[o].getOwner();
    	}
    	
    	
    	for(int v = 0; v < tArr.length ; v++)
    	{
    		for(int s = 0; s < hBoard[v].length; s++)
    		{
    			hBoard[v][s] = SPACE;
    		}
    	}
    	
    	
    	for(int v = 0; v < hBoard.length; v++)
    	{
    		int i = 0;	//Index for individual Lengths.
    		int jump = hBoard[v].length/2 - tArr[v].getLength()/2;
    		
    		
    		for(int s = jump; s < jump + tArr[v].getLength(); s++)
    		{
    			hBoard[v][s] = tArr[v].getIdAt(i);
    			i++;
    		}
    	}
    	
    	
    	for(int r = 0; r < vBoard.length; r++)
    	{
    		for(int c = 0; c < vBoard[r].length; c++)
    		{
    			vBoard[r][c] = hBoard[c][vBoard.length - r - 1];
    		}
    	}
    	
    	
    	for(int r = 0; r < vBoard.length; r++)
    	{
    		for(int c = 0; c < vBoard[r].length; c++)
    		{
    			boardString += vBoard[r][c];
    		}
    		
    		boardString += "\n";	//Make new lines
    	}
    	
    	
    	totalString = "OWNERS: \n" + ownerRow + "\nBOARD: \n" + boardString;
       
    	return totalString;
    }

}
