
import mayflower.*;

public class AnimatedActor extends Actor{
    private Animation animation;
    private Timer animationTimer;
    
    //creates a new timer for the framerate
    public AnimatedActor(){
        animationTimer = new Timer(99000000);
    }
    
    public void setAnimation(Animation a){
        animation = a;
    }
    //goes through each image in the 2D array and transitions between them
    public void act(){
        if(animation != null && animationTimer.isDone()){
            MayflowerImage currentFrame = animation.getNextFrame();
            setImage(currentFrame);
            animationTimer.reset();
        }
    }
    
}