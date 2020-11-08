package har0ld1_5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * Student: Elias Hawa Teacher: Mr Schwartz Assignement: RST Game
 *
 */
public class Player extends JPanel { //class to hold to player object

    double rotationAngle = 0; //double to hold the value for the angle of rotation
    int Health; //health int
    Point point = new Point(90,85);

    public Player() {
        setSize(130, 130); //sets size
        Health = 50; //sets health
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //gets the screen size of the monitor
        int width = (int) screenSize.getWidth(); //sets int width to the width of the screen
        int height = (int) screenSize.getHeight(); //sets int height to the height of the screen
        setLocation(500, 250); //sets the default location of the character in roughly the center of the scree
        setOpaque(false); //makes the image transparent

    } //constructor for the player

    public void paintComponent(Graphics g) {
        super.paintComponent(g); //paint component stuff
        BufferedImage player1Img = null; //declares null buffered image variable
        try { //try catch for files 
            player1Img = LoadImage("images/new.png"); //gets the player1 image by calling the load image method with this file location
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        Graphics2D g2d = (Graphics2D) g; //creates 2d graphics component
        AffineTransform at = AffineTransform.getTranslateInstance(0,20); //affine transform to fix the position of the character in the jlabel
        at.rotate(Math.toRadians(rotationAngle), player1Img.getWidth() / 2, player1Img.getHeight() / 2); //rotates the object around the center of the image with the rotation angle
        g2d.drawImage(player1Img, at, null); //draws the image with the advanced affine transform setting
    } //paints the image to the jpanel

    BufferedImage LoadImage(String FileName) throws IOException {
        BufferedImage img = null; //delcares a null buffered image
        img = ImageIO.read(new File(FileName)); //gets the image file 
        return img; //returns the img value
    } //method to get the player image
    
    public void checkIfDead(){
        if (Health <= 0){  //if the players health is less than or equal to 0
            setVisible(false); //they're set visible to false
        }
    } //method to check if the player is dead 

    public void getRotationAngle(double angleToSet) {
        rotationAngle = angleToSet; //gets the angle    
        this.repaint(); //repaints the image
    } //Method to get the angle of rotation based on mouse position

}
