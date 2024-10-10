
import mayflower.*;

public class GameOverPlayer2 extends World{
    
    public GameOverPlayer2(){
        //creates world
        super();
        MayflowerImage overScreen = new MayflowerImage("Images/Background/Game_Over_Screen.png");
        overScreen.scale(1100,700);
        setBackground(overScreen);
        
        //displays text that player 1 won
        showText("Game Over: Player1 wins!!",450,350,Color.WHITE);
        showText("Press SPACE to play again", 450, 400, Color.GREEN);
        
    }
    
    public void act(){
        //if space pressed return to intro screen to play again
        if(Mayflower.isKeyDown(Keyboard.KEY_SPACE)){
            Mayflower.setWorld(new IntroScreen()); //goes back to the intro screen to start a new game. 
        }
    }
}
