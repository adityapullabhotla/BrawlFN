
import mayflower.*;
import java.util.List;

public class WorldOne extends World {
    private String[][] tiles; //2D array for coins
    Timer timer;
    Block b = new Block(); //this is to get the world.
    private Player1 player1;
    private Player2 player2;

    public WorldOne() {
        this.player1 = new Player1(0, 5, 10); //add players to the world
        this.player2 = new Player2(0, 5, 10);
        addObject(player1, 220, 525);
        addObject(player2, 700, 525);
        setLevelOneBackground(); //set the background
        //Mayflower.showBounds(true);
        tiles = new String[10][7]; //size of 2d array
        
        //adding objects
        addObject(b, 125, 600);
        addObject(new Block(), 275, 600);
        addObject(new Block(), 625, 600);
        addObject(new Block(), 775, 600);
        addObject(new Block(), 515, 350);
        addObject(new Block(), 880, 470);
        addObject(new Block(), 200, 425);

        addObject(new Spike(), 525, 570);
        addObject(new Spike(), 500, 570);
        addObject(new Spike(), 100, 450);
        addObject(new Spike(), 150, 450);
        addObject(new Spike(), 830, 550);

        buildWorld();
        addRandomObjects(3, 8, 3, 6);
    }
    
    //fills array with empty strings
    public void buildWorld() {
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[r].length; c++) {
                tiles[r][c] = "";
            }
        }
    }
    
    //adds coins randomly
    public void addRandomObjects(int startRow, int endRow, int startCol, int endCol) {
        int worldWidth = getWidth();
        int worldHeight = getHeight();
        for (int r = startRow; r < endRow; r++) {
            for (int c = startCol; c < endCol; c++) {
                int random = (int) (Math.random() * 10); //random number to add the coins
                if (random < 7 && tiles[r][c].equals("")) {
                    int x = r * 110; //multiply by the world dimension divisble 
                    int y = c * 100;
                    //make sure coin is added in the world
                    if (x < worldWidth - 50 && y < worldHeight - 50) { 
                        Coin coin = new Coin();
                        addObject(coin, x, y);
                        tiles[r][c] = "coin";
                        //make sure coin isn't overlapping with a block
                        if (isCoinOverlappingWithBlock(coin)) {
                            removeObject(coin);
                            tiles[r][c] = "";
                        }
                    }
                }
            }
        }
    }

    public boolean isCoinOverlappingWithBlock(Coin coin) {
        //go through all the blocks and see if they are overlapping with any coins
        for (Block block : getObjects(Block.class)) {
            if (isOverlapping(coin, block)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOverlapping(Actor a, Actor b) {
        //getting the dimensions of each object
        int ax = a.getX();
        int ay = a.getY();
        int aw = a.getImage().getWidth();
        int ah = a.getImage().getHeight();

        int bx = b.getX();
        int by = b.getY();
        int bw = b.getImage().getWidth();
        int bh = b.getImage().getHeight();
        //check to see if they intersect
        return ax < bx + bw && ax + aw > bx && ay < by + bh && ay + ah > by;
    }

    public void act() {
        //list of all the coin objects so that if there are none left you move on to the next level
        List<Coin> remainingCoins = getObjects(Coin.class);
        if (remainingCoins.isEmpty()) {
            showText("Moving on to level 2 in 3 seconds!!", 350, 350, Color.BLACK);

            Mayflower.delay(5000);
            Mayflower.setWorld(new WorldTwo(player1, player2));

        }

    }
    
    
    public void setLevelOneBackground() {
        MayflowerImage levelOneBackground = new MayflowerImage("Images/Background/level1background.png");
        levelOneBackground.scale(1100, 700);
        setBackground(levelOneBackground);
    }

    public Player1 getPlayer1() {
        return player1;
    }

    public Player2 getPlayer2() {
        return player2;
    }
}