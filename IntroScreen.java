
import java.util.Timer;
import java.util.TimerTask;
import mayflower.*;

public class IntroScreen extends World{
    private Timer timer;
    // public IntroScreen(Player1 player1, Player2 player2){
        // super(player1, player2);
        // MayflowerImage introScreen = new MayflowerImage("Images/Background/IntroScreen.png");
        // introScreen.scale(1100,700);
        // setBackground(introScreen);
        
        // timer = new Timer();

        // // Schedule the task to switch to the first level after 3 seconds
        // timer.schedule(new TimerTask() {
            // public void run() {
                // // Switch to the first level
                // Mayflower.setWorld(new WorldOne());
            // }
        // },3000);  // 3000 milliseconds = 3 seconds
        
    // }
    
    public IntroScreen(){
        super();
        MayflowerImage introScreen = new MayflowerImage("Images/Background/IntroScreen.png");
        introScreen.scale(1100,700);
        setBackground(introScreen);
        Coin.setPlayer1Coins(0);
        Coin.setPlayer2Coins(0);
        
        timer = new Timer();

        // Schedule the world to switch to the first level after 3 seconds
        timer.schedule(new TimerTask() {
            public void run() {
                // Switch to the first level
                Mayflower.setWorld(new WorldOne());
            }
        },3000);  // 3000 milliseconds = 3 seconds
        
    }
    
    public void act(){
        
    }

}