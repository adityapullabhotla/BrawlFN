
import mayflower.*;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerOneFireball extends Actor{
    //which way the fireball will move
    private String direction;
    Timer timer;
    //constructor and sets the image
    public PlayerOneFireball(String direction){
        MayflowerImage fireball = new MayflowerImage("Images/Items/Fireball.png");
        setImage(fireball);
        this.direction = direction;
    }

    public void act(){
        World w = getWorld();
        //move the fireball in the direction
        if(direction.equals("left")){
            setLocation(getX() -8, getY());
        }else{
            setLocation(getX()+8, getY());
        }
        //remove the fireball once it goes off the map
        if(getX() > 1100 || getX() < 0){
            w.removeObject(this);
        }
        //kill player 2 if it touches it
        if(isTouching(Player2.class)){
            Object a = getOneIntersectingObject(Player2.class);
            Player2 player = (Player2) a;
            
            player.setPlayer2Lives(1);
            int score = player.getPlayer2Score();
            int lives = player.getPlayer2Lives();
            int numBullets = player.getPlayer2NumBullets();
            w.removeObject(player);
            timer = new Timer();

            // Schedule the respawn after 3 seconds
            timer.schedule(new TimerTask() {
            public void run() {
                // add player with updated attributes
                w.addObject(new Player2(score, lives, numBullets), 1025,0);
            }
            },3000);

        }
    }

}
