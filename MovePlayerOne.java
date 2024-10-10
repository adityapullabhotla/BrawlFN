
import mayflower.*;

public class MovePlayerOne extends AnimatedActor{
    
    
    private Animation walkRight;
    private Animation walkLeft;
    
    private Animation idleRight;
    private Animation idleLeft;
    
    private Animation jumpRight;
    private Animation jumpLeft;
    
    private Animation fallRight;
    private Animation fallLeft; 
    
    private boolean alreadyJumping;
    private int jumpHeight;
    private static final int JUMP_STEP = 3;
    private static final int NUM_STEPS = 60;
    private static final int MAX_HEIGHT = JUMP_STEP * NUM_STEPS;
    private String currentAction;
    private String direction = "";
    private PlayerOneFireball fireball;
    private String lastKey = "";
    
    public MovePlayerOne(){
        alreadyJumping = false;
    }
    
    public boolean isFalling(){
        if(isTouching(Block.class)){
            return false;
        }
        return true;
    }
    
    public boolean isJumping(){
        if(Mayflower.isKeyDown(Keyboard.KEY_UP)){
            return true;
        }
        return false;
    }
    
    public void act(){
        super.act();
        
        World w = getWorld();
        String newAction = null;
        
        
        if(currentAction == null){
            if(direction.equals("left")){
                currentAction = "idleLeft";
                newAction = "idleLeft";
                direction = "left";
            }
            else{
                currentAction = "idleRight";
                newAction = "idleRight";
                direction = "right";
            }
            
        }
        
        if(isFalling() && direction.equals("left")){
            setLocation(getX(), getY()+1);
            newAction = "fallLeft";
        }
        else if(isFalling()){
            setLocation(getX(), getY()+1);
            newAction = "fallRight";
        }
        
        if(!isFalling()){
            setLocation(getX(), getY()-1);
            alreadyJumping = false;
        }
        
        if(isJumping() && !alreadyJumping && Mayflower.isKeyDown(Keyboard.KEY_RIGHT)){
            // setLocation(getX(), getY()-200);
            // alreadyJumping = true;
            setLocation(getX()+2, getY()-JUMP_STEP);
            jumpHeight += JUMP_STEP;
            if(isTouching(Block.class)){
                setLocation(getX()-2, getY()+JUMP_STEP);
            }
            newAction = "jumpRight";
            direction = "right";
            if(jumpHeight >= MAX_HEIGHT){
                alreadyJumping = true;
                jumpHeight = 0;
            }
            lastKey = "W";
        }
        else if(isJumping() && !alreadyJumping && Mayflower.isKeyDown(Keyboard.KEY_LEFT)){
            // setLocation(getX(), getY()-200);
            // alreadyJumping = true;
            setLocation(getX()-2, getY()-JUMP_STEP);
            jumpHeight += JUMP_STEP;
            if(isTouching(Block.class)){
                setLocation(getX()+2, getY()+JUMP_STEP);
            }
            newAction = "jumpLeft";
            direction = "left";
            if(jumpHeight >= MAX_HEIGHT){
                alreadyJumping = true;
                jumpHeight = 0;
            }
            lastKey = "W";
        }
        else if(isJumping() && !alreadyJumping){
            // setLocation(getX(), getY()-200);
            // alreadyJumping = true;
            setLocation(getX(), getY()-JUMP_STEP);
            jumpHeight += JUMP_STEP;
            if(isTouching(Block.class)){
                setLocation(getX(), getY()+JUMP_STEP);
            }
            newAction = "jumpRight";
            direction = "right";
            if(jumpHeight >= MAX_HEIGHT){
                alreadyJumping = true;
                jumpHeight = 0;
            }
            lastKey = "W";
        }
        else if(Mayflower.isKeyDown(Keyboard.KEY_RIGHT) && getX()<1025){
            lastKey = "D";
            setLocation(getX() + 2, getY());
            newAction = "walkRight";
            direction = "right";
            
            if(isTouching(Block.class)){
                setLocation(getX()-2, getY());
            }
        }
        else if(Mayflower.isKeyDown(Keyboard.KEY_LEFT) && getX() > 0){
            lastKey = "A";
            setLocation(getX()-2, getY());
            newAction = "walkLeft";
            direction = "left";
            
            if(isTouching(Block.class)){
                setLocation(getX()+2, getY());
            }
        }
        else if(Mayflower.isKeyDown(Keyboard.KEY_DOWN) && !lastKey.equals("down")){
            fireball = new PlayerOneFireball(direction);
            w.addObject(fireball, this.getX(), this.getY());
            fireball.act();
            setLastKey("down");
        }
        
        else{
            if(direction.equals("left")){
                if(isFalling()){
                    newAction = "fallLeft";
                    direction = "left";
                }
                else{
                    newAction = "idleLeft";
                    direction = "left";
                }
            }
            else{
                if(isFalling()){
                    newAction = "fallRight";
                    direction = "right";
                }
                else{
                    newAction = "idleRight";
                    direction = "right";
                }
            }
        }
        
        
        // else if(Mayflower.isKeyDown(Keyboard.KEY_DOWN)){
            // //lastKey = "S";
            // setLocation(getX(), getY() + 2);
        // }
        
        if(newAction != null && !currentAction.equals(newAction)){
            if(newAction.equals("walkRight")){
                super.setAnimation(walkRight);
            }
            else if(newAction.equals("walkLeft")){
                super.setAnimation(walkLeft);
            }
            else if(newAction.equals("idleRight")){
                super.setAnimation(idleRight);
            }
            else if(newAction.equals("idleLeft")){
                super.setAnimation(idleLeft);
            }
            else if(newAction.equals("jumpRight")){
                super.setAnimation(jumpRight);
            }
            else if(newAction.equals("jumpLeft")){
                super.setAnimation(jumpLeft);
            }
            else if(newAction.equals("fallRight")){
                super.setAnimation(fallRight);
            }
            else if(newAction.equals("fallLeft")){
                super.setAnimation(fallLeft);
            }
        }            
        currentAction = newAction;

    }
    
    public void setAnimation(Animation a){
        super.setAnimation(a);
    }
    
    public void setWalkRightAnimation(Animation a){
        this.walkRight = a;
    }
    public void setWalkLeftAnimation(Animation a){
        this.walkLeft= a;
    }
    
    public void setIdleRightAnimation(Animation a){
        this.idleRight = a;
    }
    public void setIdleLeftAnimation(Animation a){
        this.idleLeft = a;
    }
    
    public void setJumpRightAnimation(Animation a){
        this.jumpRight = a;
    }
    public void setJumpLeftAnimation(Animation a){
        this.jumpLeft = a;
    }
    
    public void setFallRightAnimation(Animation a){
        this.fallRight = a;
    }
    public void setFallLeftAnimation(Animation a){
        this.fallLeft = a;
    }
    
    public String getDirection(){
        return direction;
    }
    
    public String getLastKey(){
        return lastKey;
    }
    public void setLastKey(String key){
        lastKey = key;
    }
}