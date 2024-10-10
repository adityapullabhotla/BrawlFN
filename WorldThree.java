
// import mayflower.*;
// import java.util.Timer;
// import java.util.TimerTask;
// import java.util.List;

// public class WorldThree extends MyWorld{
    // Timer timer;
    // private int numCoins;
    // Block b = new Block();
    // public WorldThree(Player1 player1, Player2 player2){
        // super(player1, player2);
        // super.setLevelThreeBackground();
    // }
    
    // public void act(){
        // World w = b.getWorld();
        // List<Coin> remainingCoins = w.getObjects(Coin.class);
        // if(remainingCoins.isEmpty()){
            // w.showText("Moving on to level 2 in 3 seconds!!", 350, 350, Color.BLACK);
            
            // timer = new Timer();
            // // change world after 3 seconds
            // timer.schedule(new TimerTask() {
                    // public void run() {
                        // // Switch to the second level
                        // if(Coin.getPlayer1Coins() > Coin.getPlayer2Coins()){
                            // Mayflower.setWorld(new GameOverPlayer2());
                        // }
                        // else{
                            // Mayflower.setWorld(new GameOverPlayer1());
                        // }
                    // }
                // },3000);
        // }
    // }
// }