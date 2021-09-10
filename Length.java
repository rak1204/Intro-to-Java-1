
public class Length {
  
  	private int size; 			
	private char[] track = null; 	
	
  
    public Length(int roll) 
    {
        
    	if(roll > 7)
        	size = (12 - roll + 1) * 2;  
        else
        	size = (roll - 1) * 2;
        
    	size += 1; 
    	
    	
    	track = new char[size];
    	
    	
    	for(int i = 0; i < track.size; i++)
    		track[i] = Util.EMPTY_SLOT;
    }

    
    
    public boolean isOwned() 
    {
    	
    	return  track[size - 1] == Util.NEUTRAL_ID || 
    			track[size - 1] == Util.PLAYER_IDS[0] ||
    			track[size - 1] == Util.PLAYER_IDS[1] ||
    			track[size - 1] == Util.PLAYER_IDS[2] ||
    			track[size - 1] == Util.PLAYER_IDS[3];						
    		   
    }

    
   
    public char getOwner() 
    {
    	return track[size - 1];	
    }

    
   
    public void advance(char player) 
    {
       
    	boolean theresNeutralCone = false;
        
        boolean theresPlayerCone = false;
        
        
        int neutralConePos = 0;	
        int playerPos = 0;		
        
        boolean placedConeDone = false; 
        
     
        for(int i = 0; i < track.size; i++)
        {
        	if(track[i] == Util.NEUTRAL_ID)
        	{
    			theresNeutralCone = true;
    			neutralConePos = i;
        	}
        	
        	if(track[i] == player)
        	{
        		theresPlayerCone = true;
        		playerPos = i;
        	}
        }
        
     
        if(theresNeutralCone)
        {
        	for(int s = neutralConePos + 1; s < track.size && 
        		!placedConeDone; s++ )
        	{
        		if(track[s] == Util.EMPTY_SLOT)
        		{
        			track[s] = Util.NEUTRAL_ID;
        			placedConeDone = true;
        			track[neutralConePos] = Util.EMPTY_SLOT;
        		}
        	}
        }
        
       
        else if(theresPlayerCone)
        {
        	for(int s = playerPos + 1; s < track.size && !placedConeDone; s++)
        	{
        		if(track[s] == Util.EMPTY_SLOT)
        		{
        			track[s] = Util.NEUTRAL_ID;
        			placedConeDone = true;
        		}
        	}
        }
        
       
        else if(!theresNeutralCone && !theresPlayerCone)
        {
        	for(int s = 0; s < track.size && !placedConeDone; s++ )
        	{
        		if(track[s] == Util.EMPTY_SLOT)
        		{
        			track[s] = Util.NEUTRAL_ID;
        			placedConeDone = true;
        		}
        	}
        }
        
    }

    
   
    public boolean active() 
    {
    	boolean theresNeutralCone = false;  
    	
    	
    	for(int i = 0; i < track.size - 1; i++)
    	{
    		if(track[i] == Util.NEUTRAL_ID)
    			theresNeutralCone = true;
    	}
    	
    	
        return theresNeutralCone; 
    }

    
  
    public void commit(char id) 
    {
    	
    	for(int i = 0; i < track.size; i++)
    	{
    		if(track[i] == id)
    			track[i] = Util.EMPTY_SLOT;
    	}
    	
    	
    	for(int i = 0; i < track.size; i++)
    	{
    		if(track[i] == Util.NEUTRAL_ID) 
    			track[i] = id;
    	}
    
    	
    	if(getOwner() == id)  
    	{
    		for(int i = 0; i < track.size - 1; i++)
    		{
    			if(track[i] != id)
    				track[i] = Util.EMPTY_SLOT;
    		}
    			
    	}
    }

    
   
    public void clear() 
    {
    	
    	for(int i = 0; i < track.size; i++) 
    	{
    		if(track[i] == Util.NEUTRAL_ID)
    			track[i] = Util.EMPTY_SLOT; 
    	}
    }

    
    
    public int getsize() 
    {
        return (size - 1); 
    }

    
    public char getIdAt(int i) 
    {
       
    	return track[i];
    }

}
