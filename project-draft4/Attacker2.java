import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Image;
import java.awt.Point;
import javax.swing.JPanel;

public class Attacker2{

    private static final int DX = 8; // amount of X pixels to move in one keystroke
    private static final int TILE_SIZE = 64;

    private JPanel panel;
    //private TileMap tileMap;
    //private BackgroundManager bgManager;
    private Player player;

    private int x;
    private int y;
    private Image playerImage, playerLeftImage, playerRightImage;

    private int dx;
    private int dy;

    private ProjectileMotion projectileMotion;
    private boolean isActive;

    public Attacker2(JPanel panel, TileMap t, BackgroundManager b, Player player) {
        this.panel = panel;
        //tileMap = t;
        //bgManager = b;
        this.player = player;
        isActive = false;

        dx = 5;
        dy = 5;

        projectileMotion = new ProjectileMotion(panel, this, player);

        playerLeftImage = ImageManager.loadImage("images/playerLeft.gif");
        playerRightImage = ImageManager.loadImage("images/playerRight.gif");
        playerImage = playerRightImage;
    }

    
    public void chasePlayer() {
        int playerX = player.getX();
        int playerY = player.getY();
        int attackerX = getX();
        int attackerY = getY();


        if (attackerX > playerX) {// if attacker X is more than the player X
            x = x - dx; // move towards player
            deActivateProjectile();
            //launchProjectile();
            //projectileMotion.activate();
        }
        else
        if (attackerX < playerX){
            // if attacker X is less than player X 
            x = x + dx; // move away from player
            activateProjectile();
            //launchProjectile();
        } 

        if (attackerY > playerY){ // if attacker Y is more than the player Y
            y = y - dy; // move towards player
            deActivateProjectile();
            //launchProjectile();
        }
        else
        if (attackerY < playerY){ // if attacker is less than the player Y
            y = y + dy; // move away from player
            activateProjectile();
            //launchProjectile();
        }
        else{
            deActivateProjectile();
        }
    }

    public void update() {
        chasePlayer();

        if (projectileMotion.isActive()){
            projectileMotion.update();

            if(attackCollideWithPlayer()){
                projectileMotion.deActivate();
                deActivateProjectile();
                projectileMotion.setReset(true);
            }

            //projectileMotion.setReset(false);
        }
            
    }

    public void activateProjectile(){
        isActive = true;
        projectileMotion.activate();
    }

    public void deActivateProjectile(){
        isActive = false;
    }

    public boolean launchIsActive(){
        return projectileMotion.isActive();
    }

    public void drawProjectile(Graphics2D g2){
        //System.out.println("IN DRAW PROJECTILE");
        if (projectileMotion.isActive()){
            projectileMotion.draw(g2);
        }
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

    // issues lies here, need to find a way to deactivate the projectile
    public void launchProjectile () {
        //System.out.println("IN launch PROJECTILE");
        //activateProjectile();
    	//projectileMotion.activate();
    }

    public int getDirection() {
        if (playerImage == playerLeftImage)
           return 1;
        else
           return 2;
    }

    private void resetProjectilePosition() {
        projectileMotion.deActivate(); // Deactivate the projectile
        deActivateProjectile();
        // Reset the position of the projectile to the attacker's position
        //launchProjectile();
        projectileMotion.update();
        //activateProjectile();
    }

    public Rectangle2D.Double getBoundingRectangle() {
        int playerWidth = playerImage.getWidth(null);
        int playerHeight = playerImage.getHeight(null);

        return new Rectangle2D.Double (x, y, playerWidth, playerHeight);
    }

    public boolean attackCollideWithPlayer(){
        Rectangle2D.Double myRect = projectileMotion.getBoundingRectangle();
        Rectangle2D.Double batRect = player.getBoundingRectangle();
        //System.out.println("YES IT HIT");
        //return myRect.intersects(batRect); 

        if (myRect.intersects(batRect)) {
            System.out.println ("Collision with player!");
            projectileMotion.deActivate();
            return true;
        }
        else
            return false;
    }
    
}
