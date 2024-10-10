
import mayflower.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WorldTwo extends World {
    private String[][] tiles;
    Timer timer;
    Block b = new Block();
    private Player1 player1;
    private Player2 player2;

    public WorldTwo(Player1 player1, Player2 player2) {
        super();
        //remove all the objects from the first world when transitioning to the next. make sure world is empty
        removeObjects(getObjects(null));
        setLevelTwoBackground();
        //adding objects
        this.player1 = player1;
        this.player2 = player2;
        addObject(player1, 220, 525);
        addObject(player2, 900, 400);
        tiles = new String[10][7];

        addObject(b, 125, 600);
        addObject(new Block(), 275, 600);
        addObject(new Block(), 625, 600);
        addObject(new Block(), 515, 350);
        addObject(new Block(), 880, 470);
        addObject(new Block(), 850, 200);
        addObject(new Block(), 50, 425);
        addObject(new Block(), 270, 260);

        addObject(new Spike(), 525, 570);
        addObject(new Spike(), 500, 570);
        addObject(new Spike(), 100, 515);
        addObject(new Spike(), 150, 515);
        addObject(new Spike(), 830, 550);
        addObject(new Spike(), 680, 550);
        addObject(new Spike(),475, 370);

        buildWorld();
        addRandomObjects(1,10, 1, 5);
    }
    //array with empty strings
    public void buildWorld() {
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[r].length; c++) {
                tiles[r][c] = "";
            }
        }
    }
    //add coins randomly
    public void addRandomObjects(int startRow, int endRow, int startCol, int endCol) {
        int worldWidth = getWidth();
        int worldHeight = getHeight();
        for (int r = startRow; r < endRow; r++) {
            for (int c = startCol; c < endCol; c++) {
                int random = (int) (Math.random() * 10);
                //make sure coins are added in the world
                if (random < 8 && tiles[r][c].equals("")) {
                    int x = r * 110;
                    int y = c * 100;
                    if (x < worldWidth - 50 && y < worldHeight - 50) {
                        Coin coin = new Coin();
                        addObject(coin, x, y);
                        tiles[r][c] = "coin";
                        //make sure coins don't intersect with the block
                        if (isCoinOverlappingWithBlock(coin)) {
                            removeObject(coin);
                            tiles[r][c] = "";
                        }
                    }
                }
            }
        }
    }
    //sees if there are any blocks and coins overlappping with a loop
    public boolean isCoinOverlappingWithBlock(Coin coin) {
        for (Block block : getObjects(Block.class)) {
            if (isOverlapping(coin, block)) {
                return true;
            }
        }
        return false;
    }
    //gets dimensions of the actors and compares to see if they touch
    public boolean isOverlapping(Actor a, Actor b) {
        int ax = a.getX();
        int ay = a.getY();
        int aw = a.getImage().getWidth();
        int ah = a.getImage().getHeight();

        int bx = b.getX();
        int by = b.getY();
        int bw = b.getImage().getWidth();
        int bh = b.getImage().getHeight();

        return ax < bx + bw && ax + aw > bx && ay < by + bh && ay + ah > by;
    }

    public void act() {
        //list of coins on the world used to see when all the coins have been collected and then transition to next level
        List<Coin> remainingCoins = getObjects(Coin.class);
        if (remainingCoins.isEmpty()) {
            showText("Game Over in 3 seconds!!", 350, 350, Color.BLACK);

            Mayflower.delay(500000);
            if (Coin.getPlayer1Coins() > Coin.getPlayer2Coins()) {
                Mayflower.setWorld(new GameOverPlayer2());
            }else{
                Mayflower.setWorld(new GameOverPlayer1());
            }

                // timer = new Timer();
                // // Schedule the transition for 3 seconds
                // timer.schedule(new TimerTask() {
                // public void run() {
                // // Switch to the game over level
                // if (Coin.getPlayer1Coins() > Coin.getPlayer2Coins()) {
                // Mayflower.setWorld(new GameOverPlayer2());
                // }else{
                // Mayflower.setWorld(new GameOverPlayer1());
                // }
                // }
                // },3000);
            }
        }

    public void setLevelTwoBackground() {
            MayflowerImage levelTwoBackground = new MayflowerImage("Images/Background/level2background.png");
            levelTwoBackground.scale(1100, 700);
            setBackground(levelTwoBackground);
    }
}