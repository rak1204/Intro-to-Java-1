
public class CST {
  
  CST cantStopCST = null; 
	
  
    public CST(String[] names, Color[] colors) 
    {
    	
    	cantStopCST = new CST(names, colors);
    }

   
    public CST getCST() 
    {
       
    	return cantStopCST;
    }

    
    
    public static void main(String[] args) {
        Util.showNewGameDialog("Choose player names and colors.");
    }

}
