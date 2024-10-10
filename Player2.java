
import mayflower.*;
import java.util.Timer;
import java.util.TimerTask;

public class Player2 extends AnimatedActor{
    //animations
    private Animation walkRight;
    private Animation walkLeft;

    private Animation idleRight;
    private Animation idleLeft;

    private Animation jumpRight;
    private Animation jumpLeft;

    private Animation fallRight;
    private Animation fallLeft; 
    
    //jumping. This is to control how much you can jump
    private boolean alreadyJumping;
    private int jumpHeight;
    private static final int JUMP_STEP = 6;
    private static final int NUM_STEPS = 50;
    private static final int MAX_HEIGHT = JUMP_STEP * NUM_STEPS;
    private String currentAction;
    private String direction = "";
    private PlayerTwoFireball fireball;
    private String lastKey = "";
    
    Timer timer;
    static int score;
    static int lives;
    static int numBullets;
    
    //constructor with parameters
    public Player2(int score, int lives, int bullets){
        
        this.score = score;
        this.lives = lives;
        this.numBullets = bullets;
        alreadyJumping = false;
        //creates all the animations
        String[] walkAni = new String[4];
        for(int i=0, j=1; i<4; i++,j++){
            walkAni[i] = "Images/Characters/Player_Walk ("+ j +").png";
        }
        walkRight = new Animation(50, walkAni);
        walkRight.scale(60,60);
        setWalkRightAnimation(walkRight);

        walkLeft = new Animation(45, walkAni);
        walkLeft.scale(60,60);
        walkLeft.mirrorHorizontally();
        setWalkLeftAnimation(walkLeft);
        
        
        String[] idleAni = new String[6];
        for(int i=0,j=1; i<6;i++,j++){
            idleAni[i] = "Images/Characters/Player_Idle (" + j+ ").png";
        }
        idleRight = new Animation(45, idleAni);
        idleRight.scale(60,60);
        setIdleRightAnimation(idleRight);
        //super.setAnimation(idleRight);
        
        idleLeft = new Animation(45, idleAni);
        idleLeft.scale(60,60);
        idleLeft.mirrorHorizontally();
        setIdleLeftAnimation(idleLeft);
        
        
        
        String[] jumpAni = new String[6];
        for(int i=0,j=1; i<6; i++,j++){
            jumpAni[i] = "Images/Characters/Player_Jump (" + j + ").png";
        }
        jumpRight = new Animation(45, jumpAni);
        jumpRight.scale(60,60);
        setJumpRightAnimation(jumpRight);
        
        jumpLeft = new Animation(45, jumpAni);
        jumpLeft.scale(60,60);
        jumpLeft.mirrorHorizontally();
        setJumpLeftAnimation(jumpLeft);
        
        
        String[] fallAni = new String[3];
        for(int i=0,j=1; i<3; i++,j++){
            fallAni[i] = "Images/Characters/Player_Fall (" + j + ").png";
        }
        
        fallRight = new Animation(45,fallAni);
        fallRight.scale(60,60);
        setFallRightAnimation(fallRight);
        super.setAnimation(fallRight);
        
        fallLeft = new Animation(45, fallAni);
        fallLeft.scale(60,60);
        fallLeft.mirrorHorizontally();
        setFallLeftAnimation(fallLeft);
    }
    
    //constructor without parameters
    public Player2(){
        
        score = 0;
        lives = 5;
        numBullets = 10;
        alreadyJumping = false;
        
        String[] walkAni = new String[4];
        for(int i=0, j=1; i<4; i++,j++){
            walkAni[i] = "Images/Characters/Player_Walk ("+ j +").png";
        }
        walkRight = new Animation(50, walkAni);
        walkRight.scale(60,60);
        setWalkRightAnimation(walkRight);

        walkLeft = new Animation(45, walkAni);
        walkLeft.scale(60,60);
        walkLeft.mirrorHorizontally();
        setWalkLeftAnimation(walkLeft);
        
        
        String[] idleAni = new String[6];
        for(int i=0,j=1; i<6;i++,j++){
            idleAni[i] = "Images/Characters/Player_Idle (" + j+ ").png";
        }
        idleRight = new Animation(45, idleAni);
        idleRight.scale(60,60);
        setIdleRightAnimation(idleRight);
        //super.setAnimation(idleRight);
        
        idleLeft = new Animation(45, idleAni);
        idleLeft.scale(60,60);
        idleLeft.mirrorHorizontally();
        setIdleLeftAnimation(idleLeft);
        
        
        
        String[] jumpAni = new String[6];
        for(int i=0,j=1; i<6; i++,j++){
            jumpAni[i] = "Images/Characters/Player_Jump (" + j + ").png";
        }
        jumpRight = new Animation(45, jumpAni);
        jumpRight.scale(60,60);
        setJumpRightAnimation(jumpRight);
        
        jumpLeft = new Animation(45, jumpAni);
        jumpLeft.scale(60,60);
        jumpLeft.mirrorHorizontally();
        setJumpLeftAnimation(jumpLeft);
        
        
        String[] fallAni = new String[3];
        for(int i=0,j=1; i<3; i++,j++){
            fallAni[i] = "Images/Characters/Player_Fall (" + j + ").png";
        }
        
        fallRight = new Animation(45,fallAni);
        fallRight.scale(60,60);
        setFallRightAnimation(fallRight);
        super.setAnimation(fallRight);
        
        fallLeft = new Animation(45, fallAni);
        fallLeft.scale(60,60);
        fallLeft.mirrorHorizontally();
        setFallLeftAnimation(fallLeft);
    }


    // public boolean isFalling(){
        // if(isTouching(Block.class)){
            // return false;
        // }
        // return true;
    // }
    //check to see if it is falling or not
    public boolean isFalling(){
        boolean ret;
        setLocation(getX(), getY() + 2);
        ret = isTouching(Block.class);
        setLocation(getX(), getY() - 2);
        return !ret;
    }
    //see if its touching a block
    public boolean isBlocked(){
        if(this.isTouching(Block.class)){
            return true; 
        }
        return false; 
    }
    //sees if its already jumping so you can't keep going up
    public boolean isJumping(){
        if(Mayflower.isKeyDown(Keyboard.KEY_W)){
            return true;
        }
        return false;
    }

    public void act(){
        super.act();
        //create world and text with the players attributes
        World w = getWorld();
        String newAction = null;
        w.showText("Player2- Score: " + this.getPlayer2Score() + " Lives: " + this.getPlayer2Lives() + " Bullets Left: " + this.getPlayer2NumBullets(),10, 60, Color.BLACK);
        //game over sees if lives are 0 and then changes world to game over
        if(this.isPlayer2GameOver()){
            Mayflower.setWorld(new GameOverPlayer2());
            System.out.println("game over");
        }
        //loes a life if you fall off the map
         if(getY()>690){
            //update attributes
            this.setPlayer2Lives(1);
            int score = this.getPlayer2Score();
            int lives = this.getPlayer2Lives();
            int numBullets = this.getPlayer2NumBullets();
            w.removeObject(this);
            timer = new Timer();

            // Schedule the respawn after 3 seconds
            timer.schedule(new TimerTask() {
            public void run() {
                //add player with updates attributews
                w.addObject(new Player2(score, lives, numBullets), 10,0);
            }
            },3000);
        }

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
        //gravity
        setLocation(getX(), getY()+2);
        if(isBlocked() == true){
            setLocation(getX(), getY()-2);
            alreadyJumping = false;
        }

        //jump and move right
        if(isJumping() && !alreadyJumping && Mayflower.isKeyDown(Keyboard.KEY_D)){
            // setLocation(getX(), getY()-200);
            // alreadyJumping = true;
            setLocation(getX()+2, getY()-JUMP_STEP);
            jumpHeight += JUMP_STEP;
            if(isTouching(Block.class)){
                setLocation(getX()-2, getY()+JUMP_STEP);
            }
            newAction = "jumpRight";
            direction = "right";
            //makes it so it stopes jumping
            if(jumpHeight >= MAX_HEIGHT){
                alreadyJumping = true;
                jumpHeight = 0;
            }
            lastKey = "W";
        }
        //jump and move left
        else if(isJumping() && !alreadyJumping && Mayflower.isKeyDown(Keyboard.KEY_A)){
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
        //jump up
        else if(isJumping() && !alreadyJumping){
            // setLocation(getX(), getY()-200);
            // alreadyJumping = true;
            setLocation(getX(), getY()-JUMP_STEP);
            jumpHeight += JUMP_STEP;
            if(isTouching(Block.class)){
                setLocation(getX(), getY()+JUMP_STEP);
            }
            
            if(direction != null & direction.equals("left")){
                newAction = "jumpLeft";
                direction = "left";
            }
            else{
                newAction = "jumpRight";
                direction = "right";
            }
            
            if(jumpHeight >= MAX_HEIGHT){
                alreadyJumping = true;
                jumpHeight = 0;
            }
            lastKey = "W";
        }
        //move right
        else if(Mayflower.isKeyDown(Keyboard.KEY_D) && getX()<1050){
            lastKey = "D";
            setLocation(getX() + 2, getY());
            newAction = "walkRight";
            direction = "right";

            if(isTouching(Block.class)){
                setLocation(getX()-2, getY());
            }
        }
        //move left
        else if(Mayflower.isKeyDown(Keyboard.KEY_A) && getX() > 0){
            lastKey = "A";
            setLocation(getX()-2, getY());
            newAction = "walkLeft";
            direction = "left";

            if(isTouching(Block.class)){
                setLocation(getX()+2, getY());
            }
        }
        //shoot
        else if(Mayflower.isKeyDown(Keyboard.KEY_S) && !lastKey.equals("S") && numBullets >0){
            //creates a new fireball and calls the act method. use the direction to know which direction the fireball goes
            fireball = new PlayerTwoFireball(direction);
            w.addObject(fireball, this.getX(), this.getY());
            fireball.act();
            setLastKey("S");
            setPlayer2NumBullets(1);
            //play sound effect
            Mayflower.playMusic("Images/ShootingSoundEffect2.mov");
        }
        //if no key is pressed what action is happening
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

        //sets the current action to new action and the animations
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
    
        public void setPlayer2Score(int x){
        score += x;
    }
    public static int getPlayer2Score(){
        return score;
    }
    
    public void setPlayer2Lives(int x){
        lives -= x;
        System.out.println(getPlayer2Lives());
    }
    public static int getPlayer2Lives(){
        return lives;
    }
    
    public void setPlayer2NumBullets(int x){
        numBullets -= x;
    }
    public static int getPlayer2NumBullets(){
        return numBullets;
    }
    
    public void updateText(){
        World w = getWorld();
        w.removeText(10,60);
        w.showText("Player2- Score: " + this.getPlayer2Score() + " Lives: " + this.getPlayer2Lives() + " Bullets Left: " + getPlayer2NumBullets(), 10, 60, Color.BLACK);
    }
    //if lives are 0
    public boolean isPlayer2GameOver(){
        if(getPlayer2Lives() <= 0){
            return true;
        }
        return false;
    }
    
    public void setBackground(){
        
    }
    
}