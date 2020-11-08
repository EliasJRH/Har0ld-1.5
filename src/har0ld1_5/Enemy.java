package har0ld1_5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 *
 * Student: Elias Hawa Teacher: Mr Schwartz Assignement: RST Game
 *
 */
public class Enemy extends JPanel implements ActionListener {

    double rotationAngle = 0; //double for rotation angle
    int xVel, yVel; //int for x and y velocitie 
    int enemyHealth; //int for enemy health
    Timer movementTimer = new Timer(1, this); //intializes movement timer
    boolean active = false; //boolean to check if enemy is active 
    JLabel coinCount = new JLabel("+10 Coins"); //jlabel to show coins have been obtained 
    JProgressBar enemyHealthBar = new JProgressBar();

    public Enemy() {
        add(coinCount);
        add(enemyHealthBar);
        enemyHealthBar.setMaximum(10);
        UIManager.put("ProgressBar.background", Color.RED);
        UIManager.put("ProgressBar.foreground", Color.GREEN);
        coinCount.setBounds(0, 0, 69, 69); //nice
        coinCount.setVisible(false);
        movementTimer.start(); //starts the movement timer 
        setSize(80, 80);
        setOpaque(false);
        setVisible(false);
    } //constructor for the enemy

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage enemyImg = null; //declares null buffered image variable
        try { //try catch for files 
            enemyImg = LoadImage("images/zombieEnemyNew2.png"); //gets the player1 image by calling the load image method with this file location
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } //process for rotation is the same as the player and bullet 
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
        at.rotate(Math.toRadians(rotationAngle), enemyImg.getWidth() / 2, enemyImg.getHeight() / 2);
        g2d.drawImage(enemyImg, at, null);
    } //paints the enemy class  

    BufferedImage LoadImage(String FileName) throws IOException {
        BufferedImage img = null; //delcares a null buffered image
        img = ImageIO.read(new File(FileName)); //gets the image file 
        return img; //returns the img value
    } //method to get the player image

    public void getRotationAngle(double angleToSet) {
        rotationAngle = angleToSet; //gets the angle of rotation 
        repaint(); //repaints the enemy 
    } //method to get the angle at which the image should rotate

    public void getMovementVectors(int xVelFromFrame, int yVelFromFrame) {
        xVel = xVelFromFrame; //gets the x and y velocity vectors from the method called from the main frame
        yVel = yVelFromFrame;
    } //method that gets how the enemy should travel in the x and y

    public boolean checkIfDead() {
        if (enemyHealth <= 0) { //if the enemies health is less than or equal to 0
            active = false; //set the active boolean to false so they stop moving in the playing area
            Thread coinThread = new Thread() { //starts a new thread to display that the player got 10 coins
                public void run() {
                    try { //try catch statement
                        coinCount.setBounds(0, 0, 100, 100); //sets the bounds of the jlabel
                        coinCount.setVisible(true); //makes it visible
                        sleep(200); //small delay so the player can see it
                        setLocation(-500, -500); //set their location to -500,-500 so they dont affect the player
                        coinCount.setVisible(false); //sets the count visible to false
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            };
            coinThread.start(); //starts the thread
            xVel = 0; // sets their x and y velocities to 0
            yVel = 0;
            return (true); //return true
        }
        return (false); //otherwise it returns false
    } //method that is called everytime a bullet hits an enemy to check if the enemy is dead

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (active) { //if the enemy is active
            setLocation(getLocation().x + xVel, getLocation().y + yVel);
        }
        
    } //timer that moves the enemy

}
