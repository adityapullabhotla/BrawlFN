
import mayflower.*;

public class MyMayflower extends Mayflower{
    
    public MyMayflower(){
        
        super("Project", 1100, 700);
    }
    //start the game on the intro screen
    public void init(){
        World w = new IntroScreen();
        Mayflower.setWorld(w);
    }
    
    
}