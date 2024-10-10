
import mayflower.*;

public class Animation{
    
    private MayflowerImage[] frames;
    private int framerate;
    private int currentFrame;
    //use string array to convert them to mayflower images
    public Animation(int rate,String[] arr){
        framerate = rate; 
        currentFrame = 0;
        frames = new MayflowerImage[arr.length];
        for(int i=0; i<frames.length; i++){
            frames[i] = new MayflowerImage(arr[i]);
        }
    }
    //scale the images
     public void scale(int w , int h){
        for(MayflowerImage frame: frames){
            frame.scale(w,h);
        }
    }
    //transparency of images    
    public void setTransparency(int transparency){
        for(MayflowerImage frame: frames){
            frame.setTransparency(transparency);
        }
    }
    //mirror for leftwards animations
    public void mirrorHorizontally(){
        for(MayflowerImage frame: frames){
            frame.mirrorHorizontally();
        }
    }
    //set the bounds of the actors
    public void setBounds(int x, int y, int w, int h){
        for(MayflowerImage image : frames){
            image.crop(x,y,w,h);
        }
    }
    //go to the next picture in the array for the animation to continue
    public MayflowerImage getNextFrame(){
        currentFrame++;
        currentFrame %= frames.length;
        return frames[currentFrame];
    } 
}