
import mayflower.*;
import java.util.Timer;
import java.util.TimerTask;

public class Spike extends AnimatedActor{
    private Animation spikes;
    private Timer timer;
    public Spike(){
        //creates the animation images for the spikes
        String[] spikeAni = new String[8];
        for(int i=0, j=1; i<8; i++,j++){
            spikeAni[i] = "Images/Items/Spikes/Spike (" + j + ").png";
        }
        spikes = new Animation(45, spikeAni);
        spikes.scale(50,50);
        super.setAnimation(spikes);
    }
    
    public void act(){
        super.act();
        //checks to see if the players are touching the spike
        if(isTouching(Player1.class)){
            Object a = getOneIntersectingObject(Player1.class);
            Player1 player = (Player1) a;
            //update attributes
            World w = getWorld();
            player.setPlayer1Lives(1);
            int score = player.getPlayer1Score();
            int lives = player.getPlayer1Lives();
            int numBullets = player.getPlayer1NumBullets();
            w.removeObject(player);
            timer = new Timer();

            // Schedule the respawn after 3 seconds
            timer.schedule(new TimerTask() {
            public void run() {
                // adds player with updated attributes
                w.addObject(new Player1(score, lives, numBullets), 10,0);
            }
            },3000);

        }
        
        //checks to see if player 2 is touching a block
        if(isTouching(Player2.class)){
            Object a = getOneIntersectingObject(Player2.class);
            Player2 player = (Player2) a;
            //update attributes
            World w = getWorld();
            player.setPlayer2Lives(1);
            int score = player.getPlayer2Score();
            int lives = player.getPlayer2Lives();
            int numBullets = player.getPlayer2NumBullets();
            w.removeObject(player);
            timer = new Timer();

            // Schedule the respawn after 3 seconds
            timer.schedule(new TimerTask() {
            public void run() {
                // add player 2 with updated attributes
                w.addObject(new Player2(score, lives, numBullets), 1025,0);
            }
            },3000);

        }
    }
}