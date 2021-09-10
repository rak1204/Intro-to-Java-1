import java.awt.Color;

public class User {
  
	private String name = null;				
	private char id = 'a';					
	private Color color = Color.black;		
	
    
    public User(String name, char id, Color color) 
    {
        this.name = name;
        this.id = id;
        this.color = color;
    }

    
    public String getName() 
    {
        return name;
    }

    
    public char getId() 
    {
        return id;
    }

   
    public Color getColor() 
    {
        return color;
    }

}
