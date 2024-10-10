
import mayflower.*;

public class Coin extends AnimatedActor{
    private Animation coins;
    static int player1Coins;
    static int player2Coins;
    public Coin(){
        //creates coin animation
        String[] coinAni = new String[10];
        for(int i=0,j=1; i<10; i++,j++){
            coinAni[i] = "Images/Items/Coin (" + j + ").png";
        }
        coins = new Animation(45, coinAni);
        coins.scale(50,50);
        super.setAnimation(coins);
    }
    
    public void act(){
        super.act();
        //checks to see if coin is touching a player. removes the coin, updates player score.
        if(isTouching(Player1.class)){
            Object a = getOneIntersectingObject(Player1.class);
            Player1 player = (Player1) a;
            player1Coins++; //used to determine winner
            World w = getWorld();
            w.removeObject(this);
            player.setPlayer1Score(1);
            
        }
        
        //checks to see if coin is touching a player. removes the coin, updates player score.
        if(isTouching(Player2.class)){
            Object a = getOneIntersectingObject(Player2.class);
            Player2 player = (Player2) a;
            player2Coins++; //used to determine winner
            World w = getWorld();
            w.removeObject(this);
            player.setPlayer2Score(1);
            
        }
    }
    
    public static int getPlayer1Coins(){
        return player1Coins;
    }
    
    public static int getPlayer2Coins(){
        return player2Coins;
    }
    
    public static void setPlayer2Coins(int x){
        player2Coins = x;
    }
    public static void setPlayer1Coins(int x){
        player1Coins = x;
    }
}