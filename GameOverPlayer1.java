
import mayflower.*;

public class GameOverPlayer1 extends World{
    
    public GameOverPlayer1(){
        //creates the world and shows the text
        super();
        MayflowerImage overScreen = new MayflowerImage("Images/Background/Game_Over_Screen.png");
        overScreen.scale(1100,700);
        setBackground(overScreen);
        
        //displays the text saying player 2 won
        showText("Game Over: Player2 wins!!",450,350,Color.WHITE);
        showText("Press SPACE to play again", 450, 400, Color.GREEN);

    }
    
    public void act(){
        //return to intro screen if space is pressed to play again
        if(Mayflower.isKeyDown(Keyboard.KEY_SPACE)){
            Mayflower.setWorld(new IntroScreen()); //goes back to the intro screen to start a new game. 
        }
    }
}