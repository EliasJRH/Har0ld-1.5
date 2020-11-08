package har0ld1_5;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * Student: Elias Hawa Teacher: Mr Schwartz Assignement: RST Game
 * 
 */
public class Bullet extends JPanel {

    double rotationAngle = 0; //double for rotation angle 
    boolean targetHit = false; //boolean to check if a target was hit
    //Timer movementTimerBullet = new Timer(1, this); //movement timer for bullet
    int xVel; //ints for x and y velocity
    int yVel;
    public Timer t = new Timer();
    int lastLocationX, lastLocationY; //ints for the last location of the bullet
    public boolean mouseFired = false; //boolean to check if the mouse was clicked
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //gets the screen size of the monitor
    int width = (int) screenSize.getWidth(); //sets int width to the width of the screen
    int height = (int) screenSize.getHeight(); //sets int height to the height of the screen

    public Bullet() {
        setSize(70, 70); //set size of the class 
        setLocation(500, 250); //sets the location 
        setOpaque(false); //makes it transparent
        setVisible(true); //makes it invisible
        t.scheduleAtFixedRate(movingAction, 0, 15);

    } //constructor for bullet class

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage bullet1Img = null; //declares null buffered image variable
        try { //try catch for files 
            bullet1Img = LoadImage("images/bulletReSized.png"); //gets the player1 image by calling the load image method with this file location
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } //this is the same process for rotating the image for the player 
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
        at.rotate(Math.toRadians(rotationAngle), bullet1Img.getWidth() / 2, bullet1Img.getHeight() / 2);
        g2d.drawImage(bullet1Img, at, null);
    }

    BufferedImage LoadImage(String FileName) throws IOException {
        BufferedImage img = null; //delcares a null buffered image
        img = ImageIO.read(new File(FileName)); //gets the image file 
        return img; //returns the img value
    } //method to get the player image

    public void getRotationAngle(double angleToSet, int x, int y) {
        if (mouseFired == false) {
            rotationAngle = angleToSet; //gets the angle
            this.repaint(); //repaints the image
        }
    } //Method to get the angle of rotation based on mouse position

    public boolean moveBullet(int x, int y, int lastLocationforX, int lastLocationforY) {
        xVel = x; //gets the x and y velocity of the bullet from the frame class
        yVel = y;
        lastLocationX = lastLocationforX; //gets the last location in the x and y from the frame class so it can be set back to that position
        lastLocationY = lastLocationforY;
        mouseFired = true; //sets mouse fired to true so it doesnt rotate 
        setVisible(true); //makes it visible
        return (true);
    } //method that gets the x and y velocities of the bullet 

    public TimerTask movingAction = new TimerTask() {
        public void run() {
            setLocation(getLocation().x + xVel, getLocation().y + yVel); //constantly changing the ocation based on its x and y velocity
            if (getLocation().x > width || getLocation().x < 0 || getLocation().y < 0 || getLocation().y > height) { //checks if the bullet is out of bounds from the screen
                //if it is greater than the bounds of the screen
                xVel = 0; //set the x and y velocities to zero so it stops moving
                yVel = 0;
                mouseFired = false; //sets the mouse fired variable back to false to the bullet can move and rotate with the character
                setLocation(lastLocationX, lastLocationY); //sets its location to its last fired so it can go back to the player easily
                setVisible(false); //makes it invisible
            }
        }
    };

}
