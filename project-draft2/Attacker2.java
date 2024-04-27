import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import javax.swing.JPanel;

public class Attacker2{

    private static final int DX = 8; // amount of X pixels to move in one keystroke
    private static final int TILE_SIZE = 64;

    private JPanel panel;
    private TileMap tileMap;
    private BackgroundManager bgManager;
    private Player player;

    private int x;
    private int y;
    private Image playerImage, playerLeftImage, playerRightImage;

    private int dx;
    private int dy;

    public Attacker2(JPanel panel, TileMap t, BackgroundManager b, Player player) {
        this.panel = panel;
        tileMap = t;
        bgManager = b;
        this.player = player;

        dx = 5;
        dy = 5;

        playerLeftImage = ImageManager.loadImage("images/playerLeft.gif");
        playerRightImage = ImageManager.loadImage("images/playerRight.gif");
        playerImage = playerRightImage;
    }

    
    public void chasePlayer() {
        int playerX = player.getX();
        int playerY = player.getY();
        int attackerX = getX();
        int attackerY = getY();

        //int direction;
        // if (playerX < attackerX) {
        //     direction = 1; // Move left
        // } else {
        //     direction = 2; // Move right
        // }

        // if(playerY < )

        // if(playerX < attackerX+40){
        //     direction = 1;
        // }
        
        // if the attack X is less than player X + 40 

        if (attackerX > playerX) // if attacker X is more than the player X
            x = x - dx; // move towards player
        else
        if (attackerX < playerX) // if attacker X is less than player X 
            x = x + dx; // move away from player

        if (attackerY > playerY) // if attacker Y is more than the player Y
            y = y - dy; // move towards player
        else
        if (attackerY < playerY) // if attacker is less than the player Y
            y = y + dy; // move away from player

            /* 
        if (attackerX > playerX)
            x = x + dx; // Move towards player
        else if (attackerX < playerX)
            x = x - dx; // Move away from player
        
        if (attackerY > playerY)
            y = y + dy; // Move towards player
        else if (attackerY < playerY)
            y = y - dy; // Move away from player
        */


        // if attacker is 1px to the left of player ... just move left

        //move(direction);
    }

    public void update() {
        chasePlayer();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return playerImage;
    }

    public Point collidesWithTile(int newX, int newY) {
        int playerWidth = playerImage.getWidth(null);
        int offsetY = tileMap.getOffsetY();
        int xTile = tileMap.pixelsToTiles(newX);
        int yTile = tileMap.pixelsToTiles(newY - offsetY);

        if (tileMap.getTile(xTile, yTile) != null) {
            return new Point(xTile, yTile);
        } else {
            return null;
        }
    }
}



// public void move(int direction) {
    //     int newX = x;
    //     Point tilePos = null;

    //     if (!panel.isVisible())
    //         return;

    //     if (direction == 1) { // move left
    //         playerImage = playerLeftImage;
    //         newX = x - DX;
    //         if (newX < 0) {
    //             x = 0;
    //             return;
    //         }
    //         tilePos = collidesWithTile(newX, y);
    //     } else if (direction == 2) { // move right
    //         playerImage = playerRightImage;
    //         int playerWidth = playerImage.getWidth(null);
    //         newX = x + DX;
    //         int tileMapWidth = tileMap.getWidthPixels();
    //         if (newX + playerImage.getWidth(null) >= tileMapWidth) {
    //             x = tileMapWidth - playerImage.getWidth(null);
    //             return;
    //         }
    //         tilePos = collidesWithTile(newX + playerWidth, y);
    //     }

    //     if (tilePos != null) {
    //         if (direction == 1) {
    //             System.out.println(": Collision going left");
    //             x = ((int) tilePos.getX() + 1) * TILE_SIZE; // keep flush with right side of tile
    //         } else if (direction == 2) {
    //             System.out.println(": Collision going right");
    //             int playerWidth = playerImage.getWidth(null);
    //             x = ((int) tilePos.getX()) * TILE_SIZE - playerWidth; // keep flush with left side of tile
    //         }
    //     } else {
    //         if (direction == 1) {
    //             x = newX;
    //             bgManager.moveLeft();
    //         } else if (direction == 2) {
    //             x = newX;
    //             bgManager.moveRight();
    //         }
    //     }
    // }
