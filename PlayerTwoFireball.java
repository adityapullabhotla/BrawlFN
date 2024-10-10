
import mayflower.*;
import java.util.Timer;
import java.util.TimerTask;
public class PlayerTwoFireball extends Actor{
    private String direction;
    Timer timer;
    public PlayerTwoFireball(String direction){
        MayflowerImage fireball = new MayflowerImage("Images/Items/Fireball.png");
        setImage(fireball);
        this.direction = direction;
    }

    public void act(){
        //move the fireball in the direction
        World w = getWorld();
        if(direction.equals("left")){
            setLocation(getX() -8, getY());
        }else{
            setLocation(getX()+8, getY());
        }
        //remove the fireball if it is off the map
        if(getX() > 1100 || getX() < 0){
            w.removeObject(this);
        }
        //kill player 1 if it is touching
        if(isTouching(Player1.class)){
            Object a = getOneIntersectingObject(Player1.class);
            Player1 player = (Player1) a;
            //update player 1 attributes
            player.setPlayer1Lives(1);
            int score = player.getPlayer1Score();
            int lives = player.getPlayer1Lives();
            int numBullets = player.getPlayer1NumBullets();
            w.removeObject(player);
            timer = new Timer();

            // Schedule the respawn after 3 seconds
            timer.schedule(new TimerTask() {
            public void run() {
                // add updated player
                w.addObject(new Player1(score, lives, numBullets), 10,0);
            }
            },3000);
            
        }
    }


}