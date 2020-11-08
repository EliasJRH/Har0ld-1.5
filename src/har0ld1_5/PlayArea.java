package har0ld1_5;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * Student: Elias Hawa Teacher: Mr Schwartz Assignement: RST Game
 *
 */
public class PlayArea extends JFrame implements KeyListener, MouseMotionListener, MouseListener, ActionListener {

    MusicPlayer playTrack = new MusicPlayer();
    Player thePlayer = new Player(); //creating new instances of the player and bullet classes
    Bullet Bullet1 = new Bullet(); //creating 5 instances of the bullet class
    Bullet Bullet2 = new Bullet();
    Bullet Bullet3 = new Bullet();
    Bullet Bullet4 = new Bullet();
    Bullet Bullet5 = new Bullet();
    Enemy Enemy1 = new Enemy(); //creating 16 instances of the enemy class
    Enemy Enemy2 = new Enemy();
    Enemy Enemy3 = new Enemy();
    Enemy Enemy4 = new Enemy();
    Enemy Enemy5 = new Enemy();
    Enemy Enemy6 = new Enemy();
    Enemy Enemy7 = new Enemy();
    Enemy Enemy8 = new Enemy();
    Enemy Enemy11 = new Enemy();
    Enemy Enemy12 = new Enemy();
    Enemy Enemy13 = new Enemy();
    Enemy Enemy14 = new Enemy();
    Enemy Enemy15 = new Enemy();
    Enemy Enemy16 = new Enemy();
    Enemy Enemy17 = new Enemy();
    bulletReturn bulletReturnPoint = new bulletReturn(); //creating an instance of the bullet return class
    FileWriter highScoreWriter = new FileWriter();
    JLabel healthImg = new JLabel();
    JLabel speedImg = new JLabel();
    JLabel damageImg = new JLabel();
    JLabel healthTxt = new JLabel("Upgrade Health + 50");
    JLabel speedTxt = new JLabel("Upgrade Speed + 1");
    JLabel damageTxt = new JLabel("Upgrade Damage + 10");
    int waveCount = 0; //integer for the wave count
    Border blackline = BorderFactory.createLineBorder(Color.black); //creates a black border with border factor
    Rectangle playerHitBoxTL = new Rectangle(thePlayer.getLocation().x, thePlayer.getLocation().y, 50, 50); //adds rectangles that serve as hit boxes for the 4 sides of the character and the bullet
    Rectangle playerHitBoxTR = new Rectangle(thePlayer.getLocation().x + 60, thePlayer.getLocation().y, 50, 50);
    Rectangle playerHitBoxBL = new Rectangle(thePlayer.getLocation().x, thePlayer.getLocation().y + 60, 50, 50);
    Rectangle playerHitBoxBR = new Rectangle(thePlayer.getLocation().x + 60, thePlayer.getLocation().y + 60, 50, 50);
    //Hit boxes for the 5 bullets
    Rectangle Bullet1HitBox = new Rectangle(775, 466, 60, 60);
    Rectangle Bullet2HitBox = new Rectangle(775, 466, 60, 60);
    Rectangle Bullet3HitBox = new Rectangle(775, 466, 60, 60);
    Rectangle Bullet4HitBox = new Rectangle(775, 466, 60, 60);
    Rectangle Bullet5HitBox = new Rectangle(775, 466, 60, 60);
    //buttons
    JButton ctrlsButton = new JButton("Controls");
    JButton startGameButton = new JButton("New Game");
    JButton healthBttn = new JButton("Upgrade Health (500 Coins)");
    JButton damageBttn = new JButton("Upgrade Damage (1000 Coins)");
    JButton speedBttn = new JButton("Upgrade speed (200 Coins)");
    JButton exitButton = new JButton("Exit game");
    JButton continueButton = new JButton("Get back to the fight!");
    JButton loadSaveButton = new JButton("Load Save File");
    // player health bar
    JProgressBar healthBar = new JProgressBar();
    //Hit boxes for the enemies
    Rectangle Enemy1HitBox = new Rectangle(50, 0, 50, 50);
    Rectangle Enemy2HitBox = new Rectangle(250, 0, 50, 50);
    Rectangle Enemy3HitBox = new Rectangle(650, 0, 50, 50);
    Rectangle Enemy4HitBox = new Rectangle(850, 0, 50, 50);
    Rectangle Enemy5HitBox = new Rectangle(1050, 0, 50, 50);
    Rectangle Enemy6HitBox = new Rectangle(1250, 0, 50, 50);
    Rectangle Enemy7HitBox = new Rectangle(50, 750, 50, 50);
    Rectangle Enemy8HitBox = new Rectangle(250, 750, 50, 50);
    Rectangle Enemy11HitBox = new Rectangle(450, 750, 50, 50);
    Rectangle Enemy12HitBox = new Rectangle(650, 750, 50, 50);
    Rectangle Enemy13HitBox = new Rectangle(850, 750, 50, 50);
    Rectangle Enemy14HitBox = new Rectangle(1050, 750, 50, 50);
    Rectangle Enemy15HitBox = new Rectangle(1250, 750, 50, 50);
    Rectangle Enemy16HitBox = new Rectangle(0, 500, 50, 50);
    Rectangle Enemy17HitBox = new Rectangle(1250, 500, 50, 50);
    //Point objects that hold the default positions of all the enemies
    Point defaultGunBarrel = new Point(814, 424);
    Point Enemy1Location = new Point(50, 0);
    Point Enemy2Location = new Point(250, 0);
    Point Enemy3Location = new Point(650, 0);
    Point Enemy4Location = new Point(850, 0);
    Point Enemy5Location = new Point(1050, 0);
    Point Enemy6Location = new Point(1250, 0);
    Point Enemy7Location = new Point(50, 750);
    Point Enemy8Location = new Point(250, 750);
    Point Enemy11Location = new Point(450, 750);
    Point Enemy12Location = new Point(650, 750);
    Point Enemy13Location = new Point(850, 750);
    Point Enemy14Location = new Point(1050, 750);
    Point Enemy15Location = new Point(1250, 750);
    Point Enemy16Location = new Point(0, 500);
    Point Enemy17Location = new Point(1250, 500);
    Point EnemyUnUsedLocation = new Point(-500, -500);
    Point PlayerUnUsedLocation = new Point(5000, 5000);
    //booleans to set all the enemies to activated
    Thread gameThread; //thread that starts the game
    Thread displayGameOver;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //gets the screen size of the monitor
    int width = (int) screenSize.getWidth(); //sets int width to the width of the screen
    int height = (int) screenSize.getHeight(); //sets int height to the height of the screen
    double rotationAngle = 0; //double variable to hold the angle at which the player will be rotated
    Timer movementTimer = new Timer(1, this); //movement timer that controls a bunch of actions
    boolean mouseFired = false; //boolean to check if mouse is fired
    boolean returningPlayer = false; //boolean to check if the player as a save file
    int dx = 0; //the x and y velocity ints for how much the player and its bullets should move
    int dy = 0;
    int mouseX = 0; //x and y coordinates of the position of the mouse
    int mouseY = 0;
    int enemyCount = 0; //int for the number of enemies on the screen
    int score = 0; //player score, goes up by 100 every time they kill a zombie
    int cash = 0; //player cash goes up by 10 for every zombie killed
    int healthLevel, damageLevel, speedLevel; //int values to hold the players health damage and speed upgrades
    ArrayList<Integer> enemySendOffList = new ArrayList<Integer>(); //array list that stores the enemies that are being sent off
    JLabel gameText = new JLabel("", SwingConstants.CENTER); //jlabel to hold game text
    boolean gameActive = false;
    JList savesList = new JList(); //list to hold all the save files
    //stuff to display stats before the game starts
    JTextField displayHealth = new JTextField("");
    JTextField displayDamage = new JTextField("");
    JTextField displaySpeed = new JTextField("");
    JLabel healthDisplayer = new JLabel("Health Level");
    JLabel damageDisplayer = new JLabel("Damage Level");
    JLabel speedDisplayer = new JLabel("Speed Level");
    JFrame selectSave = new JFrame("Select a Save File");
    //ints to hold the enemy health damage and speed buffs
    int enemyHealthBuff = 0, enemyDamageBuff = 0, enemySpeedBuff;
    String playerName = ""; //string to hold player name

    public PlayArea() {
        setTitle("Har0ld 1.5, Revenge of the Dino");
        setSize(width, height); //sets the size of the frame
        setResizable(false); //makes it so that the frame isnt resizable
        setVisible(true); //sets the frame to make it visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets the frame so the program stops when its closed
        setLayout(null); //Makes the layout null so I can freely put stuff in
        addMouseMotionListener(this); //adds all the input listeners to the frame
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocus(); //requests focus so the listeners can work
        playTrack.playMusic(); //starts the menu music
        addAllComponentsAndLocations(); //adds all the components to the frame
        buildStartMenu(); //calls the build start menu method to start the game
        repaint();
    } //Constructor for the JFrame

    public void addAllComponentsAndLocations() {
        add(gameText, SwingConstants.CENTER); //adds the start count text
        gameText.setFont(new Font("Helvetica Bold", Font.PLAIN, 50)); //sets the font and the bounds
        gameText.setBounds(0, 150, width, 500); //setting bounds
        gameText.setHorizontalAlignment(SwingConstants.CENTER);
        gameText.setVisible(false); //initially sets this as invisilbe
        add(thePlayer); //adds the player into the game
        thePlayer.setLocation(500, 250); //sets its default location
        thePlayer.setVisible(false); //sets it visible to false
        add(healthBar);//adds the health bar
        add(Bullet1); //adds the bullets
        add(Bullet2);
        add(Bullet3);
        add(Bullet4);
        add(Bullet5);
        Bullet1.setLocation(775, 466); //sets their default location
        Bullet2.setLocation(775, 466);
        Bullet3.setLocation(775, 466);
        Bullet4.setLocation(775, 466);
        Bullet5.setLocation(775, 466);
        Bullet1.setVisible(false);
        Bullet2.setVisible(false);
        Bullet3.setVisible(false);
        Bullet4.setVisible(false);
        Bullet5.setVisible(false);
        ctrlsButton.setBounds(2 * (width / 9), 500, 200, 100);
        ctrlsButton.addActionListener(new ctrlButtonAction());
        loadSaveButton.setBounds(4 * (width / 9), 500, 200, 100);
        loadSaveButton.addActionListener(new loadSaveButtonAction());
        startGameButton.setBounds(6 * (width / 9), 500, 200, 100);
        startGameButton.addActionListener(new newGameButtonAction());
        add(ctrlsButton);
        add(startGameButton);
        add(loadSaveButton);
        add(Enemy1); //adds the enemies
        add(Enemy2);
        add(Enemy3);
        add(Enemy4);
        add(Enemy5);
        add(Enemy6);
        add(Enemy7);
        add(Enemy8);
        add(Enemy11);
        add(Enemy12);
        add(Enemy13);
        add(Enemy14);
        add(Enemy15);
        add(Enemy16);
        add(Enemy17);
        add(bulletReturnPoint); //adds the bullet return point
        bulletReturnPoint.setLocation(775, 466); //sets location of bullet return point
        Enemy1.setLocation(EnemyUnUsedLocation); //sets the enemies at various locations around the corner of the screen
        Enemy2.setLocation(EnemyUnUsedLocation);
        Enemy3.setLocation(EnemyUnUsedLocation);
        Enemy4.setLocation(EnemyUnUsedLocation);
        Enemy5.setLocation(EnemyUnUsedLocation);
        Enemy6.setLocation(EnemyUnUsedLocation);
        Enemy7.setLocation(EnemyUnUsedLocation);
        Enemy8.setLocation(EnemyUnUsedLocation);
        Enemy11.setLocation(EnemyUnUsedLocation);
        Enemy12.setLocation(EnemyUnUsedLocation);
        Enemy13.setLocation(EnemyUnUsedLocation);
        Enemy14.setLocation(EnemyUnUsedLocation);
        Enemy15.setLocation(EnemyUnUsedLocation);
        Enemy16.setLocation(EnemyUnUsedLocation);
        Enemy17.setLocation(EnemyUnUsedLocation);
        add(speedImg); //adds all of the powerups but sets them visible to false
        add(healthImg);
        add(damageImg);
        add(speedTxt);
        add(healthTxt);
        add(damageTxt);
        add(speedBttn);
        add(healthBttn);
        add(damageBttn);
        add(exitButton);
        add(continueButton);
        speedImg.setVisible(false); //sets all these as invisible
        healthImg.setVisible(false);
        damageImg.setVisible(false);
        speedTxt.setVisible(false);
        healthTxt.setVisible(false);
        damageTxt.setVisible(false);
        speedBttn.setVisible(false);
        healthBttn.setVisible(false);
        damageBttn.setVisible(false);
        exitButton.setVisible(false);
        continueButton.setVisible(false);
        healthBar.setLocation(thePlayer.getLocation().x + thePlayer.getWidth(), thePlayer.getLocation().y + thePlayer.getHeight() + (thePlayer.getHeight() / 2));
        healthBar.setSize(thePlayer.getWidth(), thePlayer.getHeight() / 8); //sets the location and size of the health bar
        healthBar.setForeground(Color.green);
        healthBar.setVisible(false); //makes it invisible
        revalidate();
        repaint();
    } //method that adds all the components to the frame

    public void buildStartMenu() {
        gameText.setText("<html><div style='text-align: center;'>Har0ld 1.5 "
                + "<br>Revenge of the Dino</div></html>"); //sets the text properly
        gameText.setBounds(0, 0, this.getWidth(), gameText.getPreferredSize().height);
        gameText.setHorizontalAlignment(SwingConstants.CENTER);
        gameText.setVisible(true);
        thePlayer.setVisible(true);
        thePlayer.setLocation(width / 2 - (thePlayer.getWidth() / 2), 300); //sets the player to the centre of the screen
        playerHitBoxTL.setLocation(thePlayer.getLocation().x, thePlayer.getLocation().y); //changing the position of the hitboxes
        playerHitBoxTR.setLocation(thePlayer.getLocation().x + 60, thePlayer.getLocation().y);
        playerHitBoxBL.setLocation(thePlayer.getLocation().x, thePlayer.getLocation().y + 60);
        playerHitBoxBR.setLocation(thePlayer.getLocation().x + 60, thePlayer.getLocation().y + 60);
        ctrlsButton.setVisible(true);
        startGameButton.setVisible(true);
    } //method that makes all the components for the start menu visible'

    class ctrlButtonAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // what happens when button1 is clicked.
            File controlsFile = new File("Controls.txt"); //opens up the controls text file
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(controlsFile);
            } catch (IOException ex) {
                Logger.getLogger(PlayArea.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    } //action listener for the controls button

    class loadSaveButtonAction implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            File allSaves = new File("Leaderboard.txt"); //gets the text file 
            Scanner getSaves = null; //creates a scanner
            try {
                getSaves = new Scanner(allSaves); //tells the scanner to read from the file
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PlayArea.class.getName()).log(Level.SEVERE, null, ex);
            }
            getSaves.nextLine(); //skips the first line
            ArrayList<String> saveFiles = new ArrayList<String>(); //initializes an array list
            while (getSaves.hasNextLine()) { //adds stuff to the array list 
                getSaves.nextInt();
                getSaves.next();
                saveFiles.add(getSaves.next());
                getSaves.nextLine();
            }
            savesList = new JList(saveFiles.toArray()); //makes a jlist from the array
            savesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); //display stuff
            savesList.setLayoutOrientation(JList.VERTICAL);
            savesList.setVisibleRowCount(-1);
            savesList.addListSelectionListener(new displayStats());
            selectSave.setLayout(null); //displayer stuff
            selectSave.setResizable(false);
            selectSave.setBounds(550, 200, 450, 450);
            JScrollPane listScroller = new JScrollPane(savesList); //adds a jscrollpane to the frame
            listScroller.setBounds(25, 25, 150, 300);
            JButton select = new JButton("select"); //adds a button to the frame
            select.addActionListener(new selectName()); //adds the action listener to select the same
            select.setBounds(200, 25, 100, 100);
            healthDisplayer.setBounds(325, 25, healthDisplayer.getPreferredSize().width, 25); //adds text and text boxes to display levels
            displayHealth.setBounds(325, 50, 35, 25);
            damageDisplayer.setBounds(325, 125, damageDisplayer.getPreferredSize().width, 25);
            displayDamage.setBounds(325, 150, 35, 25);
            speedDisplayer.setBounds(325, 225, speedDisplayer.getPreferredSize().width, 25);
            displaySpeed.setBounds(325, 250, 35, 25);
            selectSave.add(listScroller); //adds everything to the frame 
            selectSave.add(select);
            selectSave.add(displayHealth);
            selectSave.add(displayDamage);
            selectSave.add(displaySpeed);
            selectSave.add(healthDisplayer);
            selectSave.add(damageDisplayer);
            selectSave.add(speedDisplayer);
            selectSave.setVisible(true);
        }
    } //action listener for the load save button

    class displayStats implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            String playerName = (String) savesList.getSelectedValue();
            displayHealth.setText(String.valueOf(highScoreWriter.getHealthLevel(playerName)));
            displayDamage.setText(String.valueOf(highScoreWriter.getDamageLevel(playerName)));
            displaySpeed.setText(String.valueOf(highScoreWriter.getSpeedLevel(playerName)));

        }
    }  //list selection listener to display stats when a save file is selected

    class selectName implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            playerName = (String) savesList.getSelectedValue();
            healthLevel = highScoreWriter.getHealthLevel(String.valueOf(playerName));
            damageLevel = highScoreWriter.getDamageLevel(String.valueOf(playerName));
            speedLevel = highScoreWriter.getSpeedLevel(String.valueOf(playerName));
            returningPlayer = true;
            buildMainGame();
            selectSave.dispose();
        }
    } //action listener for the select save file button

    class newGameButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            playerName = JOptionPane.showInputDialog("Welcome to the fight, \n"
                    + "Please enter your name \n"
                    + "(Your name can be used to access past saves on your progress", ""); //gets the player name once the player starts
            ctrlsButton.setVisible(false); //sets all the buttons visible to false
            startGameButton.setVisible(false);
            buildMainGame(); //builds and starts main game

        }
    } //action listener for the start game button

    public void buildMainGame() {
        healthBar.setMinimum(0); //sets up the health bar
        healthBar.setMaximum(50 + (50 * healthLevel));
        healthBar.setValue(healthBar.getMaximum());
        healthBar.setVisible(true); //makes it visible
        thePlayer.setLocation(width / 2 - (thePlayer.getWidth() / 2), 300);
        playerHitBoxTL.setLocation(thePlayer.getLocation().x, thePlayer.getLocation().y);
        playerHitBoxTR.setLocation(thePlayer.getLocation().x + 60, thePlayer.getLocation().y);
        playerHitBoxBL.setLocation(thePlayer.getLocation().x, thePlayer.getLocation().y + 60);
        playerHitBoxBR.setLocation(thePlayer.getLocation().x + 60, thePlayer.getLocation().y + 60);
        healthBar.setLocation(thePlayer.getLocation().x, thePlayer.getLocation().y + thePlayer.getHeight() + (thePlayer.getHeight() / 10));
        healthBar.setVisible(true); //makes the health bar visible
        score = 0; //resets the score back to 0
        thePlayer.Health = 50 + (50 * healthLevel);
        Enemy1.setLocation(EnemyUnUsedLocation); //sets the locations of all the enemies to their unused position
        Enemy2.setLocation(EnemyUnUsedLocation);
        Enemy3.setLocation(EnemyUnUsedLocation);
        Enemy4.setLocation(EnemyUnUsedLocation);
        Enemy5.setLocation(EnemyUnUsedLocation);
        Enemy6.setLocation(EnemyUnUsedLocation);
        Enemy7.setLocation(EnemyUnUsedLocation);
        Enemy8.setLocation(EnemyUnUsedLocation);
        Enemy11.setLocation(EnemyUnUsedLocation);
        Enemy12.setLocation(EnemyUnUsedLocation);
        Enemy13.setLocation(EnemyUnUsedLocation);
        Enemy14.setLocation(EnemyUnUsedLocation);
        Enemy15.setLocation(EnemyUnUsedLocation);
        Enemy16.setLocation(EnemyUnUsedLocation);
        Enemy17.setLocation(EnemyUnUsedLocation);
        Bullet1.setLocation(775, 466);
        Bullet2.setLocation(775, 466);
        Bullet3.setLocation(775, 466);
        Bullet4.setLocation(775, 466);
        Bullet5.setLocation(775, 466);
        gameText.setVisible(true);
        ctrlsButton.setVisible(false);
        startGameButton.setVisible(false);
        loadSaveButton.setVisible(false);
        speedImg.setVisible(false);
        healthImg.setVisible(false);
        damageImg.setVisible(false);
        speedTxt.setVisible(false);
        healthTxt.setVisible(false);
        damageTxt.setVisible(false);
        speedBttn.setVisible(false);
        healthBttn.setVisible(false);
        damageBttn.setVisible(false);
        exitButton.setVisible(false);
        continueButton.setVisible(false);
        thePlayer.setVisible(true); //sets the player visible to true
        playTrack.pickSong(0); //plays the main song
        movementTimer.start(); //starts the movement timer
        waveCount = 0;
        enemyCount = 0;
        startWaves(); //calls the start waves method to start the game
    } //method that adds all components to the frame for the actual game

    public void buildShop() {
        ImageIcon speedImageIcon = new ImageIcon("images/speedImage.png"); //image icon declarations for the upgrades
        ImageIcon healthImageIcon = new ImageIcon("images/heartImage.jpg");
        ImageIcon damageImageIcon = new ImageIcon("images/bulletImage.jpg");
        playTrack.pickSong(1); //plays a new song
        Enemy1.setLocation(EnemyUnUsedLocation); //sets the locations of all the enemies to their unused position
        Enemy2.setLocation(EnemyUnUsedLocation);
        Enemy3.setLocation(EnemyUnUsedLocation);
        Enemy4.setLocation(EnemyUnUsedLocation);
        Enemy5.setLocation(EnemyUnUsedLocation);
        Enemy6.setLocation(EnemyUnUsedLocation);
        Enemy7.setLocation(EnemyUnUsedLocation);
        Enemy8.setLocation(EnemyUnUsedLocation);
        Enemy11.setLocation(EnemyUnUsedLocation);
        Enemy12.setLocation(EnemyUnUsedLocation);
        Enemy13.setLocation(EnemyUnUsedLocation);
        Enemy14.setLocation(EnemyUnUsedLocation);
        Enemy15.setLocation(EnemyUnUsedLocation);
        Enemy16.setLocation(EnemyUnUsedLocation);
        Enemy17.setLocation(EnemyUnUsedLocation);
        thePlayer.setLocation(thePlayer.getLocation().x + 1000, thePlayer.getLocation().y + 1000); //moves the player and the bullets away
        playerHitBoxTL.setLocation(thePlayer.getLocation().x, thePlayer.getLocation().y); //adds rectangles that serve as hit boxes for the 4 sides of the character and the bullet
        playerHitBoxTR.setLocation(thePlayer.getLocation().x + 60, thePlayer.getLocation().y);
        playerHitBoxBL.setLocation(thePlayer.getLocation().x, thePlayer.getLocation().y + 60);
        playerHitBoxBR.setLocation(thePlayer.getLocation().x + 60, thePlayer.getLocation().y + 60);
        Bullet1.setLocation(Bullet1.getLocation().x + 1000, Bullet1.getLocation().y + 1000);
        Bullet2.setLocation(Bullet2.getLocation().x + 1000, Bullet2.getLocation().y + 1000);
        Bullet3.setLocation(Bullet3.getLocation().x + 1000, Bullet3.getLocation().y + 1000);
        Bullet4.setLocation(Bullet4.getLocation().x + 1000, Bullet4.getLocation().y + 1000);
        Bullet5.setLocation(Bullet5.getLocation().x + 1000, Bullet5.getLocation().y + 1000);
        gameText.setText("<html><div style='text-align: center;'>Welcome to the SHOP<br> Cash: " + cash + "</div></html>"); //changes game text
        gameText.setBounds(0, 50, this.getWidth(), gameText.getPreferredSize().height);
        gameText.setHorizontalAlignment(SwingConstants.CENTER);
        ctrlsButton.setVisible(false); //sets components visibility
        startGameButton.setVisible(false);
        speedImg.setVisible(true);
        healthImg.setVisible(true);
        damageImg.setVisible(true);
        speedTxt.setVisible(true);
        healthTxt.setVisible(true);
        damageTxt.setVisible(true);
        speedBttn.setVisible(true);
        healthBttn.setVisible(true);
        damageBttn.setVisible(true);
        exitButton.setVisible(true);
        continueButton.setVisible(true);
        healthBar.setVisible(false);
        speedImg.setIcon(speedImageIcon);
        healthImg.setIcon(healthImageIcon);
        damageImg.setIcon(damageImageIcon);
        speedImg.setBounds(350, 250, 150, 150);
        healthImg.setBounds(650, 250, 150, 150);
        damageImg.setBounds(950, 250, 150, 150);
        speedTxt.setBounds(375, 400, 300, 50);
        healthTxt.setBounds(675, 400, 300, 50);
        damageTxt.setBounds(975, 400, 300, 50);
        speedBttn.setBounds(335, 500, 200, 50);
        healthBttn.setBounds(635, 500, 200, 50);
        damageBttn.setBounds(935, 500, 200, 50);
        exitButton.setBounds(10, 750, 100, 50);
        continueButton.setBounds(600, 750, 300, 50);
        speedBttn.addActionListener(new speedButtonAction());
        healthBttn.addActionListener(new healthButtonAction());
        damageBttn.addActionListener(new damageButtonAction());
        exitButton.addActionListener(new exitButtonAction());
        continueButton.addActionListener(new continueButtonAction());
    } //method that makes all the components for the regular shop visible

    class exitButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }

    } //class for the action to exit the game

    class continueButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            Enemy1.movementTimer.start(); //stats all the enemy movemnet timers
            Enemy2.movementTimer.start();
            Enemy3.movementTimer.start();
            Enemy4.movementTimer.start();
            Enemy5.movementTimer.start();
            Enemy6.movementTimer.start();
            Enemy7.movementTimer.start();
            Enemy8.movementTimer.start();
            Enemy11.movementTimer.start();
            Enemy12.movementTimer.start();
            Enemy13.movementTimer.start();
            Enemy14.movementTimer.start();
            Enemy15.movementTimer.start();
            Enemy16.movementTimer.start();
            Enemy17.movementTimer.start();
            Enemy1.active = false; //sets all the enemies active as false
            Enemy2.active = false;
            Enemy3.active = false;
            Enemy4.active = false;
            Enemy5.active = false;
            Enemy6.active = false;
            Enemy7.active = false;
            Enemy8.active = false;
            Enemy11.active = false;
            Enemy12.active = false;
            Enemy13.active = false;
            Enemy14.active = false;
            Enemy15.active = false;
            Enemy16.active = false;
            Enemy17.active = false;
            buildMainGame(); //builds the main game
        }

    } //class for the action continue the game

    class healthButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (cash >= 500) { //check if the player has sufficient funds
                cash -= 500; //subtracts that from their current amount
                healthLevel += 1; //increases the level of whatever they've upgrade
                JOptionPane.showMessageDialog(null, "You have bought: Health Upgrade, Level: " + healthLevel + "\n"
                        + "You will now have a base of " + (50 + (50 * healthLevel)) + " starting health");
                gameText.setText("<html><div style='text-align: center;'>Welcome to the SHOP<br> Cash: " + cash + "</div></html>");
                gameText.setBounds(0, 50, width, gameText.getPreferredSize().height);
                gameText.setHorizontalAlignment(SwingConstants.CENTER);
                highScoreWriter.updateHighScore(score, playerName, healthLevel, damageLevel, speedLevel, cash);
            } else { //other wise it tells the player they don't have enough money
                JOptionPane.showMessageDialog(null, "Insufficient funds \n"
                        + "Must have 500 cash to upgrade to next level");
            } //this is the same for each upgrade class
        } //action performed for upgrading health
    } //class for the action to purchase the health upgrade

    class damageButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (cash >= 1000) {
                cash -= 1000;
                damageLevel += 1;
                JOptionPane.showMessageDialog(null, "You have bought: Damage Upgrade, Level: " + damageLevel + "\n"
                        + "Your bullets will now do " + (10 + (10 * damageLevel)) + " damage to zombies");
                highScoreWriter.updateHighScore(score, playerName, healthLevel, damageLevel, speedLevel, cash);
                gameText.setText("<html><div style='text-align: center;'>Welcome to the SHOP<br> Cash: " + cash + "</div></html>");
                gameText.setBounds(0, 50, width, gameText.getPreferredSize().height);
                gameText.setHorizontalAlignment(SwingConstants.CENTER);
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient funds \n"
                        + "Must have 1000 cash to upgrade to next level");
            }
        }

    } //class for the action to purchase the damage  upgrade

    class speedButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (cash >= 200) {
                cash -= 200;
                speedLevel += 1;
                JOptionPane.showMessageDialog(null, "You have bought: Speed Upgrade, Level: " + speedLevel + "\n"
                        + "You will now move at a speed of " + (5 + (speedLevel)) + " units");
                highScoreWriter.updateHighScore(score, playerName, healthLevel, damageLevel, speedLevel, cash);
                gameText.setText("<html><div style='text-align: center;'>Welcome to the SHOP<br> Cash: " + cash + "</div></html>");
                gameText.setBounds(0, 50, width, gameText.getPreferredSize().height);
                gameText.setHorizontalAlignment(SwingConstants.CENTER);
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient funds \n"
                        + "Must have 200 cash to upgrade to next level");
            }
        }
    }//class for the action to purchase the speed  upgrade

    public void startWaves() {
        gameActive = true;
        enemySendOffList.clear(); //clears the enemy send off list
        waveCount++; //increases the wave count by one
        enemyHealthBuff = 0; //resets all the buffs
        enemySpeedBuff = 0;
        enemyDamageBuff = 0;
        enemyCount = waveCount; //The enemy count is equal to the wave count
        gameThread = new Thread(() -> {
            if (gameActive) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlayArea.class.getName()).log(Level.SEVERE, null, ex);
                }
                gameText.setVisible(false);
                gameText.setText("WAVE " + waveCount + " WILL START IN 3");
                gameText.setVisible(true);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlayArea.class.getName()).log(Level.SEVERE, null, ex);
                }
                gameText.setVisible(false);
                gameText.setText("WAVE " + waveCount + " WILL START IN 2");
                gameText.setVisible(true);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlayArea.class.getName()).log(Level.SEVERE, null, ex);
                }
                gameText.setVisible(false);
                gameText.setText("WAVE " + waveCount + " WILL START IN 1");
                gameText.setVisible(true);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlayArea.class.getName()).log(Level.SEVERE, null, ex);
                }
                gameText.setText("ZOMBIES REMAINING :" + enemyCount);
                int buff = (int) (Math.floor(waveCount / 5));
                enemyHealthBuff = buff * 10; //every 5 waves the enemies will get stronger
                enemyDamageBuff = buff * 10;
                enemySpeedBuff = buff;
                for (int i = 0; i < waveCount; i++) { //for the wave count
                    int enemyToSend = (int) (Math.random() * ((15 - 1) + 1) + 1); //a random number from 1 to 16 is generated
                    enemySendOffList.add(enemyToSend); //and put on an array list
                }
                while (!enemySendOffList.isEmpty()) { //while the array list is not empty, it will keep running through it so every enemy spawns
                    //This avoid the problem of 2 enemies being spawned in the same cycle
                    for (int j = 0; j < enemySendOffList.size(); j++) {
                        //for each elements in the enemy send off list
                        //for any number from 1-15, some zombie will activate
                        if (enemySendOffList.get(j) == 1 && !Enemy1.active) { //the zombie must first not be activated for it to be sent out, this way the array can stack up zomies in once place
                            Enemy1.active = true; //the enemy is set as active
                            Enemy1.setLocation(Enemy1Location); //their location is set to their location
                            Enemy1.enemyHealth = 20 + enemyHealthBuff; //the enemies health is restored to 20 plus whatever the health buff is
                            Enemy1.enemyHealthBar.setMaximum(Enemy1.enemyHealth);
                            Enemy1.enemyHealthBar.setValue(Enemy1.enemyHealth); //Sets the max value of the enemies health to 20 plus whatever the health buff is
                            enemySendOffList.set(j, 20); //the element of the array is removed
                        } //this is the same for every enemy
                        else if (enemySendOffList.get(j) == 2 && !Enemy2.active) {
                            Enemy2.active = true;
                            Enemy2.setLocation(Enemy2Location);
                            Enemy2.enemyHealth = 20 + enemyHealthBuff;
                            Enemy2.enemyHealthBar.setMaximum(Enemy2.enemyHealth);
                            Enemy2.enemyHealthBar.setValue(Enemy2.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        else if (enemySendOffList.get(j) == 3 && !Enemy3.active) {
                            Enemy3.active = true;
                            Enemy3.setLocation(Enemy3Location);
                            Enemy3.enemyHealth = 20 + enemyHealthBuff;
                            Enemy3.enemyHealthBar.setMaximum(Enemy3.enemyHealth);
                            Enemy3.enemyHealthBar.setValue(Enemy3.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        else if (enemySendOffList.get(j) == 4 && !Enemy4.active) {
                            Enemy4.active = true;
                            Enemy4.setLocation(Enemy4Location);
                            Enemy4.enemyHealth = 20 + enemyHealthBuff;
                            Enemy4.enemyHealthBar.setMaximum(Enemy4.enemyHealth);
                            Enemy4.enemyHealthBar.setValue(Enemy4.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        else if (enemySendOffList.get(j) == 5 && !Enemy5.active) {
                            Enemy5.active = true;
                            Enemy5.setLocation(Enemy5Location);
                            Enemy5.enemyHealth = 20 + enemyHealthBuff;
                            Enemy5.enemyHealthBar.setMaximum(Enemy5.enemyHealth);
                            Enemy5.enemyHealthBar.setValue(Enemy5.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        else if (enemySendOffList.get(j) == 6 && !Enemy6.active) {
                            Enemy6.active = true;
                            Enemy6.setLocation(Enemy6Location);
                            Enemy6.enemyHealth = 20 + enemyHealthBuff;
                            Enemy6.enemyHealthBar.setMaximum(Enemy6.enemyHealth);
                            Enemy6.enemyHealthBar.setValue(Enemy6.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        else if (enemySendOffList.get(j) == 7 && !Enemy7.active) {
                            Enemy7.active = true;
                            Enemy7.setLocation(Enemy7Location);
                            Enemy7.enemyHealth = 20 + enemyHealthBuff;
                            Enemy7.enemyHealthBar.setMaximum(Enemy7.enemyHealth);
                            Enemy7.enemyHealthBar.setValue(Enemy7.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        else if (enemySendOffList.get(j) == 8 && !Enemy8.active) {
                            Enemy8.active = true;
                            Enemy8.setLocation(Enemy8Location);
                            Enemy8.enemyHealth = 20 + enemyHealthBuff;
                            Enemy8.enemyHealthBar.setMaximum(Enemy8.enemyHealth);
                            Enemy8.enemyHealthBar.setValue(Enemy8.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        else if (enemySendOffList.get(j) == 9 && !Enemy11.active) {
                            Enemy11.active = true;
                            Enemy11.setLocation(Enemy11Location);
                            Enemy11.enemyHealth = 20 + enemyHealthBuff;
                            Enemy11.enemyHealthBar.setMaximum(Enemy11.enemyHealth);
                            Enemy11.enemyHealthBar.setValue(Enemy11.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        else if (enemySendOffList.get(j) == 10 && !Enemy12.active) {
                            Enemy12.active = true;
                            Enemy12.setLocation(Enemy12Location);
                            Enemy12.enemyHealth = 20 + enemyHealthBuff;
                            Enemy12.enemyHealthBar.setMaximum(Enemy12.enemyHealth);
                            Enemy12.enemyHealthBar.setValue(Enemy12.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        else if (enemySendOffList.get(j) == 11 && !Enemy13.active) {
                            Enemy13.active = true;
                            Enemy13.setLocation(Enemy13Location);
                            Enemy13.enemyHealth = 20 + enemyHealthBuff;
                            Enemy13.enemyHealthBar.setMaximum(Enemy13.enemyHealth);
                            Enemy13.enemyHealthBar.setValue(Enemy13.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        else if (enemySendOffList.get(j) == 12 && !Enemy14.active) {
                            Enemy14.active = true;
                            Enemy14.setLocation(Enemy14Location);
                            Enemy14.enemyHealth = 20 + enemyHealthBuff;
                            Enemy14.enemyHealthBar.setMaximum(Enemy14.enemyHealth);
                            Enemy14.enemyHealthBar.setValue(Enemy14.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        else if (enemySendOffList.get(j) == 13 && !Enemy15.active) {
                            Enemy15.active = true;
                            Enemy15.setLocation(Enemy15Location);
                            Enemy15.enemyHealth = 20 + enemyHealthBuff;
                            Enemy15.enemyHealthBar.setMaximum(Enemy15.enemyHealth);
                            Enemy15.enemyHealthBar.setValue(Enemy15.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        else if (enemySendOffList.get(j) == 14 && !Enemy16.active) {
                            Enemy16.active = true;
                            Enemy16.setLocation(Enemy16Location);
                            Enemy16.enemyHealth = 20 + enemyHealthBuff;
                            Enemy16.enemyHealthBar.setMaximum(Enemy16.enemyHealth);
                            Enemy16.enemyHealthBar.setValue(Enemy16.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        else if (enemySendOffList.get(j) == 15 && !Enemy17.active) {
                            Enemy17.active = true;
                            Enemy17.setLocation(Enemy17Location);
                            Enemy17.enemyHealth = 20 + enemyHealthBuff;
                            Enemy17.enemyHealthBar.setMaximum(Enemy17.enemyHealth);
                            Enemy17.enemyHealthBar.setValue(Enemy17.enemyHealth);
                            enemySendOffList.set(j, 20);
                        }
                        try {
                            Thread.sleep(500); //sleep that delays the zombies coming out
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PlayArea.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }); //thread that allows to come otu periodically
        gameThread.start(); //starts the game thread

        //method is only called once all the enemies have been defeated
    } //method that starts the waves of enemies

    @Override
    public void keyTyped(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE) {
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();
            float xDistance = x - (thePlayer.getLocation().x + thePlayer.getWidth() / 2); //gets the distance in the x from the x coordinate of the mouse to the x center of the player
            float yDistance = y - (thePlayer.getLocation().y + thePlayer.getHeight() / 2); //gets the distance in the y from the y coordinate of the mouse to the y center of the player
            //These two float values help to create an imaginary line from the center of the character to the mouse position
            rotationAngle = Math.toDegrees(Math.atan2(yDistance, xDistance));  //Gets the angle in radians from this line to an imaginary x axis
            int xVel = (int) Math.round((20 * Math.cos(Math.toRadians(rotationAngle)))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int yVel = (int) Math.round((20 * Math.sin(Math.toRadians(rotationAngle))));
            //mouseFired = true;
            if (!Bullet1.mouseFired){
                Bullet1.moveBullet(xVel, yVel, Bullet1.getLocation().x, Bullet1.getLocation().y); //calls the move bullet method in the other class to move the bullet
                Bullet1.mouseFired = true; //sets the first bullet fired to true
            }else if (!Bullet2.mouseFired){
                Bullet2.moveBullet(xVel, yVel, Bullet2.getLocation().x, Bullet2.getLocation().y);
                Bullet2.mouseFired = true;
                //and so on and so forth
            }else if (!Bullet3.mouseFired){
                Bullet3.moveBullet(xVel, yVel, Bullet3.getLocation().x, Bullet3.getLocation().y);
                Bullet3.mouseFired = true;
            }else if (!Bullet4.mouseFired){
                Bullet4.moveBullet(xVel, yVel, Bullet4.getLocation().x, Bullet4.getLocation().y);
                Bullet4.mouseFired = true;
            }else if (!Bullet5.mouseFired){
                Bullet5.moveBullet(xVel, yVel, Bullet5.getLocation().x, Bullet5.getLocation().y);
                Bullet5.mouseFired = true;
            }
        }
    } //un used

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //Gets the key inputted
        if (code == KeyEvent.VK_W) { //if the key pressed was w
            dy = -5 - speedLevel;
        } //sets the y velocity to -5 moving it up
        if (code == KeyEvent.VK_A) { //if the key pressed was a
            dx = -5 - speedLevel;
        } //sets the x velocity to -5 moving it left
        if (code == KeyEvent.VK_S) { //if the key pressed was s
            dy = 5 + speedLevel; //sets the y velocity to 5 moving it down
        }
        if (code == KeyEvent.VK_D) { //if the key pressed was d
            dx = 5 + speedLevel; //sets the x velocity to 5 moving to the right
        }
        if (code == KeyEvent.VK_SPACE) {
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();
            float xDistance = x - (thePlayer.getLocation().x + thePlayer.getWidth() / 2); //gets the distance in the x from the x coordinate of the mouse to the x center of the player
            float yDistance = y - (thePlayer.getLocation().y + thePlayer.getHeight() / 2); //gets the distance in the y from the y coordinate of the mouse to the y center of the player
            //These two float values help to create an imaginary line from the center of the character to the mouse position
            rotationAngle = Math.toDegrees(Math.atan2(yDistance, xDistance));  //Gets the angle in radians from this line to an imaginary x axis
            int xVel = (int) Math.round((20 * Math.cos(Math.toRadians(rotationAngle)))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int yVel = (int) Math.round((20 * Math.sin(Math.toRadians(rotationAngle))));
            //mouseFired = true;
            if (!Bullet1.mouseFired){
                Bullet1.moveBullet(xVel, yVel, Bullet1.getLocation().x, Bullet1.getLocation().y); //calls the move bullet method in the other class to move the bullet
                Bullet1.mouseFired = true; //sets the first bullet fired to true
            }else if (!Bullet2.mouseFired){
                Bullet2.moveBullet(xVel, yVel, Bullet2.getLocation().x, Bullet2.getLocation().y);
                Bullet2.mouseFired = true;
                //and so on and so forth
            }else if (!Bullet3.mouseFired){
                Bullet3.moveBullet(xVel, yVel, Bullet3.getLocation().x, Bullet3.getLocation().y);
                Bullet3.mouseFired = true;
            }else if (!Bullet4.mouseFired){
                Bullet4.moveBullet(xVel, yVel, Bullet4.getLocation().x, Bullet4.getLocation().y);
                Bullet4.mouseFired = true;
            }else if (!Bullet5.mouseFired){
                Bullet5.moveBullet(xVel, yVel, Bullet5.getLocation().x, Bullet5.getLocation().y);
                Bullet5.mouseFired = true;
            }
        }
    } //Method for key presses to change the velocity of the player

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        //Once those keys are released the velocity is set back to 0
        if (code == KeyEvent.VK_W) {
            dy = 0;
        }
        if (code == KeyEvent.VK_A) {
            dx = 0;
        }
        if (code == KeyEvent.VK_S) {
            dy = 0;
        }
        if (code == KeyEvent.VK_D) {
            dx = 0;
        }
    } //Method for key released to set the velocities back to zero

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } //un used

    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX(); //gets the x coordinate of the mouse when it's moved
        int mouseY = e.getY(); //gets the y coordinate of the mouse when it's moved
        float xDistance = mouseX - (thePlayer.getLocation().x + thePlayer.getWidth() / 2); //gets the distance in the x from the x coordinate of the mouse to the x center of the player
        float yDistance = mouseY - (thePlayer.getLocation().y + thePlayer.getHeight() / 2); //gets the distance in the y from the y coordinate of the mouse to the y center of the player
//These two float values help to create an imaginary line from the center of the character to the mouse position
        rotationAngle = Math.toDegrees(Math.atan2(yDistance, xDistance)) - 10.0;  //Gets the angle in radians from this line to an imaginary x axis
        thePlayer.getRotationAngle(rotationAngle); //calls the get rotation method in the player class so the player image can rotate
        if (mouseX <= thePlayer.getLocation().x && !Bullet1.mouseFired) { //constantly checking the x and y positions of the mouse agaisnt the x and y positions of the character
            //In this case, if the x position of the mouse is less than the x position of the character
            Bullet1.setLocation(thePlayer.getLocation().x, Bullet1.getLocation().y);
            Bullet1HitBox.setLocation(thePlayer.getLocation().x, Bullet1.getLocation().y);
            //the location of where the bullet would be is set to the far left side
            //This is slightly changed for every possible general area of the mouse
        }
        if (mouseX >= thePlayer.getLocation().x && !Bullet1.mouseFired) { //if the mouse is on the far right side of the character
            Bullet1.setLocation(thePlayer.getLocation().x + thePlayer.getWidth(), Bullet1.getLocation().y);
            Bullet1HitBox.setLocation(thePlayer.getLocation().x + thePlayer.getWidth(), Bullet1.getLocation().y);
        }
        if (mouseY <= thePlayer.getLocation().y && !Bullet1.mouseFired) { //if the mouse is above the character
            Bullet1.setLocation(Bullet1.getLocation().x, thePlayer.getLocation().y);
            Bullet1HitBox.setLocation(Bullet1.getLocation().x, thePlayer.getLocation().y);
        }
        if (mouseY >= thePlayer.getLocation().y && !Bullet1.mouseFired) { //if the mouse is below the character
            Bullet1.setLocation(Bullet1.getLocation().x, thePlayer.getLocation().y + thePlayer.getHeight());
            Bullet1HitBox.setLocation(Bullet1.getLocation().x, thePlayer.getLocation().y + thePlayer.getHeight());
        }
        if (mouseX > thePlayer.getLocation().x && mouseX < thePlayer.getLocation().x + thePlayer.getWidth() && !Bullet1.mouseFired) { //if the mouse is in between the far left and ride side of the charatcer
            Bullet1.setLocation(mouseX, Bullet1.getLocation().y);
            Bullet1HitBox.setLocation(mouseX, Bullet1.getLocation().y);
        }
        if (mouseY >= thePlayer.getLocation().y && mouseY <= thePlayer.getLocation().y + thePlayer.getHeight() && !Bullet1.mouseFired) { //if the mouse in between the top and bottom of the character
            Bullet1.setLocation(Bullet1.getLocation().x, mouseY);
            Bullet1HitBox.setLocation(Bullet1.getLocation().x, mouseY);
        }
        if (!Bullet1.mouseFired) { //when mouse hasn't been fired, the method get rotation angle in the bullet class is called so it can rotate
            Bullet1.getRotationAngle(rotationAngle, mouseX, mouseY);
        }
        //This is all that is done for the bullet in terms of movement, the process is the same for all 5 bullets

        ////////////////// BULLET 2 //////////////////////////
        if (mouseX <= thePlayer.getLocation().x && !Bullet2.mouseFired) { //constantly checking the x and y positions of the mouse agaisnt the x and y positions of the character
            //In this case, if the x position of the mouse is less than the x position of the character
            Bullet2.setLocation(thePlayer.getLocation().x, Bullet2.getLocation().y);
            Bullet2HitBox.setLocation(thePlayer.getLocation().x, Bullet2.getLocation().y);
            //the location of where the bullet would be is set to the far left side
            //This is slightly changed for every possible general area of the mouse
        }
        if (mouseX >= thePlayer.getLocation().x && !Bullet2.mouseFired) { //if the mouse is on the far right side of the character
            Bullet2.setLocation(thePlayer.getLocation().x + thePlayer.getWidth(), Bullet2.getLocation().y);
            Bullet2HitBox.setLocation(thePlayer.getLocation().x + thePlayer.getWidth(), Bullet2.getLocation().y);
        }
        if (mouseY <= thePlayer.getLocation().y && !Bullet2.mouseFired) { //if the mouse is above the character
            Bullet2.setLocation(Bullet2.getLocation().x, thePlayer.getLocation().y);
            Bullet2HitBox.setLocation(Bullet2.getLocation().x, thePlayer.getLocation().y);
        }
        if (mouseY >= thePlayer.getLocation().y && !Bullet2.mouseFired) { //if the mouse is below the character
            Bullet2.setLocation(Bullet2.getLocation().x, thePlayer.getLocation().y + thePlayer.getHeight());
            Bullet2HitBox.setLocation(Bullet2.getLocation().x, thePlayer.getLocation().y + thePlayer.getHeight());
        }
        if (mouseX >= thePlayer.getLocation().x && mouseX <= thePlayer.getLocation().x + thePlayer.getWidth() && !Bullet2.mouseFired) { //if the mouse is in between the far left and ride side of the charatcer
            Bullet2.setLocation(mouseX, Bullet2.getLocation().y);
            Bullet2HitBox.setLocation(mouseX, Bullet2.getLocation().y);
        }
        if (mouseY >= thePlayer.getLocation().y && mouseY <= thePlayer.getLocation().y + thePlayer.getHeight() && !Bullet2.mouseFired) { //if the mouse in between the top and bottom of the character
            Bullet2.setLocation(Bullet2.getLocation().x, mouseY);
            Bullet2HitBox.setLocation(Bullet2.getLocation().x, mouseY);
        }
        if (!Bullet2.mouseFired) {
            Bullet2.getRotationAngle(rotationAngle, mouseX, mouseY);
        }

        ////////////////// BULLET 3 //////////////////////////
        if (mouseX <= thePlayer.getLocation().x && !Bullet3.mouseFired) { //constantly checking the x and y positions of the mouse agaisnt the x and y positions of the character
            //In this case, if the x position of the mouse is less than the x position of the character
            Bullet3.setLocation(thePlayer.getLocation().x, Bullet3.getLocation().y);
            Bullet3HitBox.setLocation(thePlayer.getLocation().x, Bullet3.getLocation().y);
            //the location of where the bullet would be is set to the far left side
            //This is slightly changed for every possible general area of the mouse
        }
        if (mouseX >= thePlayer.getLocation().x && !Bullet3.mouseFired) { //if the mouse is on the far right side of the character
            Bullet3.setLocation(thePlayer.getLocation().x + thePlayer.getWidth(), Bullet3.getLocation().y);
            Bullet3HitBox.setLocation(thePlayer.getLocation().x + thePlayer.getWidth(), Bullet3.getLocation().y);
        }
        if (mouseY <= thePlayer.getLocation().y && !Bullet3.mouseFired) { //if the mouse is above the character
            Bullet3.setLocation(Bullet3.getLocation().x, thePlayer.getLocation().y);
            Bullet3HitBox.setLocation(Bullet3.getLocation().x, thePlayer.getLocation().y);
        }
        if (mouseY >= thePlayer.getLocation().y && !Bullet3.mouseFired) { //if the mouse is below the character
            Bullet3.setLocation(Bullet3.getLocation().x, thePlayer.getLocation().y + thePlayer.getHeight());
            Bullet3HitBox.setLocation(Bullet3.getLocation().x, thePlayer.getLocation().y + thePlayer.getHeight());
        }
        if (mouseX >= thePlayer.getLocation().x && mouseX <= thePlayer.getLocation().x + thePlayer.getWidth() && !Bullet3.mouseFired) { //if the mouse is in between the far left and ride side of the charatcer
            Bullet3.setLocation(mouseX, Bullet3.getLocation().y);
            Bullet3HitBox.setLocation(mouseX, Bullet3.getLocation().y);
        }
        if (mouseY >= thePlayer.getLocation().y && mouseY <= thePlayer.getLocation().y + thePlayer.getHeight() && !Bullet3.mouseFired) { //if the mouse in between the top and bottom of the character
            Bullet3.setLocation(Bullet3.getLocation().x, mouseY);
            Bullet3HitBox.setLocation(Bullet3.getLocation().x, mouseY);
        }
        if (!Bullet3.mouseFired) {
            Bullet3.getRotationAngle(rotationAngle, mouseX, mouseY);
        }

        ////////////////// BULLET 4 //////////////////////////
        if (mouseX <= thePlayer.getLocation().x && !Bullet4.mouseFired) { //constantly checking the x and y positions of the mouse agaisnt the x and y positions of the character
            //In this case, if the x position of the mouse is less than the x position of the character
            Bullet4.setLocation(thePlayer.getLocation().x, Bullet4.getLocation().y);
            Bullet4HitBox.setLocation(thePlayer.getLocation().x, Bullet4.getLocation().y);
            //the location of where the bullet would be is set to the far left side
            //This is slightly changed for every possible general area of the mouse
        }
        if (mouseX >= thePlayer.getLocation().x && !Bullet4.mouseFired) { //if the mouse is on the far right side of the character
            Bullet4.setLocation(thePlayer.getLocation().x + thePlayer.getWidth(), Bullet4.getLocation().y);
            Bullet4HitBox.setLocation(thePlayer.getLocation().x + thePlayer.getWidth(), Bullet4.getLocation().y);
        }
        if (mouseY <= thePlayer.getLocation().y && !Bullet4.mouseFired) { //if the mouse is above the character
            Bullet4.setLocation(Bullet4.getLocation().x, thePlayer.getLocation().y);
            Bullet4HitBox.setLocation(Bullet4.getLocation().x, thePlayer.getLocation().y);
        }
        if (mouseY >= thePlayer.getLocation().y && !Bullet4.mouseFired) { //if the mouse is below the character
            Bullet4.setLocation(Bullet4.getLocation().x, thePlayer.getLocation().y + thePlayer.getHeight());
            Bullet4HitBox.setLocation(Bullet4.getLocation().x, thePlayer.getLocation().y + thePlayer.getHeight());
        }
        if (mouseX >= thePlayer.getLocation().x && mouseX <= thePlayer.getLocation().x + thePlayer.getWidth() && !Bullet4.mouseFired) { //if the mouse is in between the far left and ride side of the charatcer
            Bullet4.setLocation(mouseX, Bullet4.getLocation().y);
            Bullet4HitBox.setLocation(mouseX, Bullet4.getLocation().y);
        }
        if (mouseY >= thePlayer.getLocation().y && mouseY <= thePlayer.getLocation().y + thePlayer.getHeight() && !Bullet4.mouseFired) { //if the mouse in between the top and bottom of the character
            Bullet4.setLocation(Bullet4.getLocation().x, mouseY);
            Bullet4HitBox.setLocation(Bullet4.getLocation().x, mouseY);
        }
        if (!Bullet4.mouseFired) {
            Bullet4.getRotationAngle(rotationAngle, mouseX, mouseY);
        }

        ////////////////// BULLET 5 //////////////////////////
        if (mouseX <= thePlayer.getLocation().x && !Bullet5.mouseFired) { //constantly checking the x and y positions of the mouse agaisnt the x and y positions of the character
            //In this case, if the x position of the mouse is less than the x position of the character
            Bullet5.setLocation(thePlayer.getLocation().x, Bullet5.getLocation().y);
            Bullet5HitBox.setLocation(thePlayer.getLocation().x, Bullet5.getLocation().y);
            //the location of where the bullet would be is set to the far left side
            //This is slightly changed for every possible general area of the mouse
        }
        if (mouseX >= thePlayer.getLocation().x && !Bullet5.mouseFired) { //if the mouse is on the far right side of the character
            Bullet5.setLocation(thePlayer.getLocation().x + thePlayer.getWidth(), Bullet5.getLocation().y);
            Bullet5HitBox.setLocation(thePlayer.getLocation().x + thePlayer.getWidth(), Bullet5.getLocation().y);
        }
        if (mouseY <= thePlayer.getLocation().y && !Bullet5.mouseFired) { //if the mouse is above the character
            Bullet5.setLocation(Bullet5.getLocation().x, thePlayer.getLocation().y);
            Bullet5HitBox.setLocation(Bullet5.getLocation().x, thePlayer.getLocation().y);
        }
        if (mouseY >= thePlayer.getLocation().y && !Bullet5.mouseFired) { //if the mouse is below the character
            Bullet5.setLocation(Bullet5.getLocation().x, thePlayer.getLocation().y + thePlayer.getHeight());
            Bullet5HitBox.setLocation(Bullet5.getLocation().x, thePlayer.getLocation().y + thePlayer.getHeight());
        }
        if (mouseX >= thePlayer.getLocation().x && mouseX <= thePlayer.getLocation().x + thePlayer.getWidth() && !Bullet5.mouseFired) { //if the mouse is in between the far left and ride side of the charatcer
            Bullet5.setLocation(mouseX, Bullet5.getLocation().y);
            Bullet5HitBox.setLocation(mouseX, Bullet5.getLocation().y);
        }
        if (mouseY >= thePlayer.getLocation().y && mouseY <= thePlayer.getLocation().y + thePlayer.getHeight() && !Bullet5.mouseFired) { //if the mouse in between the top and bottom of the character
            Bullet5.setLocation(Bullet5.getLocation().x, mouseY);
            Bullet5HitBox.setLocation(Bullet5.getLocation().x, mouseY);
        }
        if (!Bullet5.mouseFired) {
            Bullet5.getRotationAngle(rotationAngle, mouseX, mouseY);
        }

        ////////////////// BULLET RETURN POINT ////////////////////
        //The bullet return point exists so that when the bullet hits something it collides with it goes back to the player
        //the movement of the bullet return point is the same as the bullet
        if (mouseX >= thePlayer.getLocation().x) { //if the mouse is on the far right side of the character
            bulletReturnPoint.setLocation(thePlayer.getLocation().x + thePlayer.getWidth(), bulletReturnPoint.getLocation().y);
        }
        if (mouseY <= thePlayer.getLocation().y) { //if the mouse is above the character
            bulletReturnPoint.setLocation(bulletReturnPoint.getLocation().x, thePlayer.getLocation().y);
        }
        if (mouseY >= thePlayer.getLocation().y) { //if the mouse is below the character
            bulletReturnPoint.setLocation(bulletReturnPoint.getLocation().x, thePlayer.getLocation().y + thePlayer.getHeight());
        }
        if (mouseX >= thePlayer.getLocation().x && mouseX <= thePlayer.getLocation().x + thePlayer.getWidth()) { //if the mouse is in between the far left and ride side of the charatcer
            bulletReturnPoint.setLocation(mouseX, bulletReturnPoint.getLocation().y);
        }
        if (mouseY >= thePlayer.getLocation().y && mouseY <= thePlayer.getLocation().y + thePlayer.getHeight()) { //if the mouse in between the top and bottom of the character
            bulletReturnPoint.setLocation(bulletReturnPoint.getLocation().x, mouseY);
        }

    } //Method that rotates stuff based on where the mouse is on the screen

    @Override
    public void mouseClicked(MouseEvent e) {
        //

    } //un used

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX(); //gets the x coordinate of the mouse when it's moved
        int y = e.getY(); //gets the y coordinate of the mouse when it's moved
        float xDistance = x - (thePlayer.getLocation().x + thePlayer.getWidth() / 2); //gets the distance in the x from the x coordinate of the mouse to the x center of the player
        float yDistance = y - (thePlayer.getLocation().y + thePlayer.getHeight() / 2); //gets the distance in the y from the y coordinate of the mouse to the y center of the player
        //These two float values help to create an imaginary line from the center of the character to the mouse position
        rotationAngle = Math.toDegrees(Math.atan2(yDistance, xDistance));  //Gets the angle in radians from this line to an imaginary x axis
        int xVel = (int) (20 * Math.cos(Math.toRadians(rotationAngle))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
        int yVel = (int) (20 * Math.sin(Math.toRadians(rotationAngle)));
        if (!Bullet1.mouseFired){
            Bullet1.moveBullet(xVel, yVel, Bullet1.getLocation().x, Bullet1.getLocation().y); //calls the move bullet method in the other class to move the bullet
            Bullet1.mouseFired = true; //sets the first bullet fired to true
        }else if (!Bullet2.mouseFired){
            Bullet2.moveBullet(xVel, yVel, Bullet2.getLocation().x, Bullet2.getLocation().y);
            Bullet2.mouseFired = true;
            //and so on and so forth
        }else if (!Bullet3.mouseFired){
            Bullet3.moveBullet(xVel, yVel, Bullet3.getLocation().x, Bullet3.getLocation().y);
            Bullet3.mouseFired = true;
        }else if (!Bullet4.mouseFired){
            Bullet4.moveBullet(xVel, yVel, Bullet4.getLocation().x, Bullet4.getLocation().y);
            Bullet4.mouseFired = true;
        }else if (!Bullet5.mouseFired){
            Bullet5.moveBullet(xVel, yVel, Bullet5.getLocation().x, Bullet5.getLocation().y);
            Bullet5.mouseFired = true;
        }
    } //mouse click that fires bullets

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } //un used

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } //un used

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } //un used

    @Override
    public void actionPerformed(ActionEvent e1) {
        playerAndObjectKeyMovement(); //the timer calls all the methods, essentially checks for things happening in the game
        playerCollisionDetection(); //also organizes the code a lot nicer
        enemyBulletCollision();
        enemyImageRotationAndMovement();
        healthBar.setValue(thePlayer.Health); //always updating the health bar
        if (enemyCount == 0) {
            startWaves();
        }
    } //Method for general movement and collision detection

    public void playerAndObjectKeyMovement() {
        ///////////////////// MOVING OBJECTS BASED ON KEY INPUTS ///////////////
        //every objects position is always technically changing, however they only change based on their velocity, so when their velocity is 0 they don't move
        //checks to see if the player is going out of bounds
        if ((thePlayer.getLocation().x + dx) < 0) {
            dx = 0;
        }
        else if ((thePlayer.getLocation().x + thePlayer.getWidth() + dx) > width) {
            dx = 0;
        }
        else if ((thePlayer.getLocation().y + dy) < 0) {
            dy = 0;
        }
        else if ((thePlayer.getLocation().y + thePlayer.getHeight() + dy) > height) {
            dy = 0;
        }
        thePlayer.setLocation(thePlayer.getLocation().x + dx, thePlayer.getLocation().y + dy);
        //moves the player based on his velocity
        if (!Bullet1.mouseFired) {
            Bullet1.setLocation(Bullet1.getLocation().x + dx, Bullet1.getLocation().y + dy);
        }
        if (!Bullet2.mouseFired) {
            Bullet2.setLocation(Bullet2.getLocation().x + dx, Bullet2.getLocation().y + dy);
        }
        if (!Bullet3.mouseFired) {
            Bullet3.setLocation(Bullet3.getLocation().x + dx, Bullet3.getLocation().y + dy);
        }
        if (!Bullet4.mouseFired) {
            Bullet4.setLocation(Bullet4.getLocation().x + dx, Bullet4.getLocation().y + dy);
        }
        if (!Bullet5.mouseFired) {
            Bullet5.setLocation(Bullet5.getLocation().x + dx, Bullet5.getLocation().y + dy);
        }
        //moves all the bullets if their not fired
        playerHitBoxTL.setLocation(playerHitBoxTL.getLocation().x + dx, playerHitBoxTL.getLocation().y + dy);
        playerHitBoxTR.setLocation(playerHitBoxTR.getLocation().x + dx, playerHitBoxTR.getLocation().y + dy);
        playerHitBoxBL.setLocation(playerHitBoxBL.getLocation().x + dx, playerHitBoxBL.getLocation().y + dy);
        playerHitBoxBR.setLocation(playerHitBoxBR.getLocation().x + dx, playerHitBoxBR.getLocation().y + dy);
        //always chaning the location of the bullet hit boxes to the location of the bullet
        Bullet1HitBox.setLocation(Bullet1.getLocation().x, Bullet1.getLocation().y);
        Bullet2HitBox.setLocation(Bullet2.getLocation().x, Bullet2.getLocation().y);
        Bullet3HitBox.setLocation(Bullet3.getLocation().x, Bullet3.getLocation().y);
        Bullet4HitBox.setLocation(Bullet4.getLocation().x, Bullet4.getLocation().y);
        Bullet5HitBox.setLocation(Bullet5.getLocation().x, Bullet5.getLocation().y);
        bulletReturnPoint.setLocation(bulletReturnPoint.getLocation().x + dx, bulletReturnPoint.getLocation().y + dy);
        healthBar.setLocation(healthBar.getLocation().x + dx, healthBar.getLocation().y + dy);
        //moves the player hit box and the bullet return point

        ///////////////////////////////////////////////////////////////////////
    } //Method that holds all the stuff for movement based on key presses

    public void playerCollisionDetection() {
        ///////////////////////MOVING OBJECTS BASED ON COLLISION OF THE CHARACTER //////////
        //moves all the player hit boxes, bullets, the player  and the bullet return point
        if ((playerHitBoxTL.intersects(Enemy1HitBox)) || (playerHitBoxTL.intersects(Enemy2HitBox)) || (playerHitBoxTL.intersects(Enemy3HitBox)) || (playerHitBoxTL.intersects(Enemy4HitBox)) || playerHitBoxTL.intersects(Enemy5HitBox) || playerHitBoxTL.intersects(Enemy6HitBox) || playerHitBoxTL.intersects(Enemy7HitBox) || playerHitBoxTL.intersects(Enemy8HitBox) || playerHitBoxTL.intersects(Enemy11HitBox) || playerHitBoxTL.intersects(Enemy12HitBox) || playerHitBoxTL.intersects(Enemy13HitBox) || playerHitBoxTL.intersects(Enemy14HitBox) || playerHitBoxTL.intersects(Enemy15HitBox) || playerHitBoxTL.intersects(Enemy16HitBox) || playerHitBoxTL.intersects(Enemy17HitBox)) {
            if (((thePlayer.getLocation().x + thePlayer.getWidth() + 70) > width) || ((thePlayer.getLocation().y + thePlayer.getHeight() + 70) > height)) {
                thePlayer.setLocation(thePlayer.getLocation().x - 140, thePlayer.getLocation().y - 140);
                playerHitBoxTL.setLocation(playerHitBoxTL.getLocation().x - 140, playerHitBoxTL.getLocation().y - 140);
                playerHitBoxTR.setLocation(playerHitBoxTR.getLocation().x - 140, playerHitBoxTR.getLocation().y - 140);
                playerHitBoxBL.setLocation(playerHitBoxBL.getLocation().x - 140, playerHitBoxBL.getLocation().y - 140);
                playerHitBoxBR.setLocation(playerHitBoxBR.getLocation().x - 140, playerHitBoxBR.getLocation().y - 140);
                Bullet1.setLocation(Bullet1.getLocation().x - 140, Bullet1.getLocation().y - 140);
                Bullet2.setLocation(Bullet2.getLocation().x - 140, Bullet2.getLocation().y - 140);
                Bullet3.setLocation(Bullet3.getLocation().x - 140, Bullet3.getLocation().y - 140);
                Bullet4.setLocation(Bullet4.getLocation().x - 140, Bullet4.getLocation().y - 140);
                Bullet5.setLocation(Bullet5.getLocation().x - 140, Bullet5.getLocation().y - 140);
                bulletReturnPoint.setLocation(bulletReturnPoint.getLocation().x - 140, bulletReturnPoint.getLocation().y - 140);
                healthBar.setLocation(healthBar.getLocation().x - 140, healthBar.getLocation().y - 140);
            } else {
                //moves everything down and to the right if the character is hit from the top left
                thePlayer.setLocation(thePlayer.getLocation().x + 70, thePlayer.getLocation().y + 70);
                playerHitBoxTL.setLocation(playerHitBoxTL.getLocation().x + 70, playerHitBoxTL.getLocation().y + 70);
                playerHitBoxTR.setLocation(playerHitBoxTR.getLocation().x + 70, playerHitBoxTR.getLocation().y + 70);
                playerHitBoxBL.setLocation(playerHitBoxBL.getLocation().x + 70, playerHitBoxBL.getLocation().y + 70);
                playerHitBoxBR.setLocation(playerHitBoxBR.getLocation().x + 70, playerHitBoxBR.getLocation().y + 70);
                Bullet1.setLocation(Bullet1.getLocation().x + 70, Bullet1.getLocation().y + 70);
                Bullet2.setLocation(Bullet2.getLocation().x + 70, Bullet2.getLocation().y + 70);
                Bullet3.setLocation(Bullet3.getLocation().x + 70, Bullet3.getLocation().y + 70);
                Bullet4.setLocation(Bullet4.getLocation().x + 70, Bullet4.getLocation().y + 70);
                Bullet5.setLocation(Bullet5.getLocation().x + 70, Bullet5.getLocation().y + 70);
                bulletReturnPoint.setLocation(bulletReturnPoint.getLocation().x + 70, bulletReturnPoint.getLocation().y + 70);
                healthBar.setLocation(healthBar.getLocation().x + 70, healthBar.getLocation().y + 70);
            }
            thePlayer.Health -= 10 + enemyDamageBuff;
        } //this process is the same for when the player gets hit anywhere
        else if ((playerHitBoxTR.intersects(Enemy1HitBox)) || (playerHitBoxTR.intersects(Enemy2HitBox)) || (playerHitBoxTR.intersects(Enemy3HitBox)) || (playerHitBoxTR.intersects(Enemy4HitBox)) || playerHitBoxTR.intersects(Enemy5HitBox) || playerHitBoxTR.intersects(Enemy6HitBox) || playerHitBoxTR.intersects(Enemy7HitBox) || playerHitBoxTR.intersects(Enemy8HitBox) || playerHitBoxTR.intersects(Enemy11HitBox) || playerHitBoxTR.intersects(Enemy12HitBox) || playerHitBoxTR.intersects(Enemy13HitBox) || playerHitBoxTR.intersects(Enemy14HitBox) || playerHitBoxTR.intersects(Enemy15HitBox) || playerHitBoxTR.intersects(Enemy16HitBox) || playerHitBoxTR.intersects(Enemy17HitBox)) {
            //moves everything down and to the left if the character is hit from the top right
            if (((thePlayer.getLocation().x - 70) < 0) || ((thePlayer.getLocation().y + thePlayer.getHeight() + 70) > height)) {
                thePlayer.setLocation(thePlayer.getLocation().x + 140, thePlayer.getLocation().y - 140);
                playerHitBoxTL.setLocation(playerHitBoxTL.getLocation().x + 140, playerHitBoxTL.getLocation().y - 140);
                playerHitBoxTR.setLocation(playerHitBoxTR.getLocation().x + 140, playerHitBoxTR.getLocation().y - 140);
                playerHitBoxBL.setLocation(playerHitBoxBL.getLocation().x + 140, playerHitBoxBL.getLocation().y - 140);
                playerHitBoxBR.setLocation(playerHitBoxBR.getLocation().x + 140, playerHitBoxBR.getLocation().y - 140);
                Bullet1.setLocation(Bullet1.getLocation().x + 140, Bullet1.getLocation().y - 140);
                Bullet2.setLocation(Bullet2.getLocation().x + 140, Bullet2.getLocation().y - 140);
                Bullet3.setLocation(Bullet3.getLocation().x + 140, Bullet3.getLocation().y - 140);
                Bullet4.setLocation(Bullet4.getLocation().x + 140, Bullet4.getLocation().y - 140);
                Bullet5.setLocation(Bullet5.getLocation().x + 140, Bullet5.getLocation().y - 140);
                bulletReturnPoint.setLocation(bulletReturnPoint.getLocation().x + 140, bulletReturnPoint.getLocation().y - 140);
                healthBar.setLocation(healthBar.getLocation().x + 140, healthBar.getLocation().y - 140);
            } else {
                thePlayer.setLocation(thePlayer.getLocation().x - 70, thePlayer.getLocation().y + 70);
                playerHitBoxTL.setLocation(playerHitBoxTL.getLocation().x - 70, playerHitBoxTL.getLocation().y + 70);
                playerHitBoxTR.setLocation(playerHitBoxTR.getLocation().x - 70, playerHitBoxTR.getLocation().y + 70);
                playerHitBoxBL.setLocation(playerHitBoxBL.getLocation().x - 70, playerHitBoxBL.getLocation().y + 70);
                playerHitBoxBR.setLocation(playerHitBoxBR.getLocation().x - 70, playerHitBoxBR.getLocation().y + 70);
                Bullet1.setLocation(Bullet1.getLocation().x - 70, Bullet1.getLocation().y + 70);
                Bullet2.setLocation(Bullet2.getLocation().x - 70, Bullet2.getLocation().y + 70);
                Bullet3.setLocation(Bullet3.getLocation().x - 70, Bullet3.getLocation().y + 70);
                Bullet4.setLocation(Bullet4.getLocation().x - 70, Bullet4.getLocation().y + 70);
                Bullet5.setLocation(Bullet5.getLocation().x - 70, Bullet5.getLocation().y + 70);
                bulletReturnPoint.setLocation(bulletReturnPoint.getLocation().x - 70, bulletReturnPoint.getLocation().y + 70);
                healthBar.setLocation(healthBar.getLocation().x - 70, healthBar.getLocation().y + 70);
            }
            thePlayer.Health -= 10 + enemyDamageBuff;
        }
        else if ((playerHitBoxBL.intersects(Enemy1HitBox)) || (playerHitBoxBL.intersects(Enemy2HitBox)) || (playerHitBoxBL.intersects(Enemy3HitBox)) || (playerHitBoxBL.intersects(Enemy4HitBox)) || playerHitBoxBL.intersects(Enemy5HitBox) || playerHitBoxBL.intersects(Enemy6HitBox) || playerHitBoxBL.intersects(Enemy7HitBox) || playerHitBoxBL.intersects(Enemy8HitBox) || playerHitBoxBL.intersects(Enemy11HitBox) || playerHitBoxBL.intersects(Enemy12HitBox) || playerHitBoxBL.intersects(Enemy13HitBox) || playerHitBoxBL.intersects(Enemy14HitBox) || playerHitBoxBL.intersects(Enemy15HitBox) || playerHitBoxBL.intersects(Enemy16HitBox) || playerHitBoxBL.intersects(Enemy17HitBox)) {
            //moves everything up and to the right if the character is hit from the bottom left
            if (((thePlayer.getLocation().x + thePlayer.getWidth() + 70) > width) || ((thePlayer.getLocation().y - 70) < 0)) {
                thePlayer.setLocation(thePlayer.getLocation().x - 140, thePlayer.getLocation().y + 140);
                playerHitBoxTL.setLocation(playerHitBoxTL.getLocation().x - 140, playerHitBoxTL.getLocation().y + 140);
                playerHitBoxTR.setLocation(playerHitBoxTR.getLocation().x - 140, playerHitBoxTR.getLocation().y + 140);
                playerHitBoxBL.setLocation(playerHitBoxBL.getLocation().x - 140, playerHitBoxBL.getLocation().y + 140);
                playerHitBoxBR.setLocation(playerHitBoxBR.getLocation().x - 140, playerHitBoxBR.getLocation().y + 140);
                Bullet1.setLocation(Bullet1.getLocation().x - 140, Bullet1.getLocation().y + 140);
                Bullet2.setLocation(Bullet2.getLocation().x - 140, Bullet2.getLocation().y + 140);
                Bullet3.setLocation(Bullet3.getLocation().x - 140, Bullet3.getLocation().y + 140);
                Bullet4.setLocation(Bullet4.getLocation().x - 140, Bullet4.getLocation().y + 140);
                Bullet5.setLocation(Bullet5.getLocation().x - 140, Bullet5.getLocation().y + 140);
                bulletReturnPoint.setLocation(bulletReturnPoint.getLocation().x - 140, bulletReturnPoint.getLocation().y + 140);
                healthBar.setLocation(healthBar.getLocation().x - 140, healthBar.getLocation().y + 140);
            } else {
                thePlayer.setLocation(thePlayer.getLocation().x + 70, thePlayer.getLocation().y - 70);
                playerHitBoxTL.setLocation(playerHitBoxTL.getLocation().x + 70, playerHitBoxTL.getLocation().y - 70);
                playerHitBoxTR.setLocation(playerHitBoxTR.getLocation().x + 70, playerHitBoxTR.getLocation().y - 70);
                playerHitBoxBL.setLocation(playerHitBoxBL.getLocation().x + 70, playerHitBoxBL.getLocation().y - 70);
                playerHitBoxBR.setLocation(playerHitBoxBR.getLocation().x + 70, playerHitBoxBR.getLocation().y - 70);
                Bullet1.setLocation(Bullet1.getLocation().x + 70, Bullet1.getLocation().y - 70);
                Bullet2.setLocation(Bullet2.getLocation().x + 70, Bullet2.getLocation().y - 70);
                Bullet3.setLocation(Bullet3.getLocation().x + 70, Bullet3.getLocation().y - 70);
                Bullet4.setLocation(Bullet4.getLocation().x + 70, Bullet4.getLocation().y - 70);
                Bullet5.setLocation(Bullet5.getLocation().x + 70, Bullet5.getLocation().y - 70);
                bulletReturnPoint.setLocation(bulletReturnPoint.getLocation().x + 70, bulletReturnPoint.getLocation().y - 70);
                healthBar.setLocation(healthBar.getLocation().x + 70, healthBar.getLocation().y - 70);
            }
            thePlayer.Health -= 10 + enemyDamageBuff;
        }
        else if ((playerHitBoxBR.intersects(Enemy1HitBox)) || (playerHitBoxBR.intersects(Enemy2HitBox)) || (playerHitBoxBR.intersects(Enemy3HitBox)) || (playerHitBoxBR.intersects(Enemy4HitBox)) || playerHitBoxBR.intersects(Enemy5HitBox) || playerHitBoxBR.intersects(Enemy6HitBox) || playerHitBoxBR.intersects(Enemy7HitBox) || playerHitBoxBR.intersects(Enemy8HitBox) || playerHitBoxBR.intersects(Enemy11HitBox) || playerHitBoxBR.intersects(Enemy12HitBox) || playerHitBoxBR.intersects(Enemy13HitBox) || playerHitBoxBR.intersects(Enemy14HitBox) || playerHitBoxBR.intersects(Enemy15HitBox) || playerHitBoxBR.intersects(Enemy16HitBox) || playerHitBoxBR.intersects(Enemy17HitBox)) {
            //moves everything up and to the left if its hit from the bottom right
            if (((thePlayer.getLocation().x - 70) < 0) || ((thePlayer.getLocation().y - 70) < 0)) {
                thePlayer.setLocation(thePlayer.getLocation().x + 140, thePlayer.getLocation().y + 140);
                playerHitBoxTL.setLocation(playerHitBoxTL.getLocation().x + 140, playerHitBoxTL.getLocation().y + 140);
                playerHitBoxTR.setLocation(playerHitBoxTR.getLocation().x + 140, playerHitBoxTR.getLocation().y + 140);
                playerHitBoxBL.setLocation(playerHitBoxBL.getLocation().x + 140, playerHitBoxBL.getLocation().y + 140);
                playerHitBoxBR.setLocation(playerHitBoxBR.getLocation().x + 140, playerHitBoxBR.getLocation().y + 140);
                Bullet1.setLocation(Bullet1.getLocation().x + 140, Bullet1.getLocation().y + 140);
                Bullet2.setLocation(Bullet2.getLocation().x + 140, Bullet2.getLocation().y + 140);
                Bullet3.setLocation(Bullet3.getLocation().x + 140, Bullet3.getLocation().y + 140);
                Bullet4.setLocation(Bullet4.getLocation().x + 140, Bullet4.getLocation().y + 140);
                Bullet5.setLocation(Bullet5.getLocation().x + 140, Bullet5.getLocation().y + 140);
                bulletReturnPoint.setLocation(bulletReturnPoint.getLocation().x + 140, bulletReturnPoint.getLocation().y + 140);
                healthBar.setLocation(healthBar.getLocation().x + 140, healthBar.getLocation().y + 140);
            } else {
                thePlayer.setLocation(thePlayer.getLocation().x - 70, thePlayer.getLocation().y - 70);
                playerHitBoxTL.setLocation(playerHitBoxTL.getLocation().x - 70, playerHitBoxTL.getLocation().y - 70);
                playerHitBoxTR.setLocation(playerHitBoxTR.getLocation().x - 70, playerHitBoxTR.getLocation().y - 70);
                playerHitBoxBL.setLocation(playerHitBoxBL.getLocation().x - 70, playerHitBoxBL.getLocation().y - 70);
                playerHitBoxBR.setLocation(playerHitBoxBR.getLocation().x - 70, playerHitBoxBR.getLocation().y - 70);
                Bullet1.setLocation(Bullet1.getLocation().x - 70, Bullet1.getLocation().y - 70);
                Bullet2.setLocation(Bullet2.getLocation().x - 70, Bullet2.getLocation().y - 70);
                Bullet3.setLocation(Bullet3.getLocation().x - 70, Bullet3.getLocation().y - 70);
                Bullet4.setLocation(Bullet4.getLocation().x - 70, Bullet4.getLocation().y - 70);
                Bullet5.setLocation(Bullet5.getLocation().x - 70, Bullet5.getLocation().y - 70);
                bulletReturnPoint.setLocation(bulletReturnPoint.getLocation().x - 70, bulletReturnPoint.getLocation().y - 70);
                healthBar.setLocation(healthBar.getLocation().x - 70, healthBar.getLocation().y - 70);
            }
            thePlayer.Health -= 10 + enemyDamageBuff;
        }
        if (thePlayer.Health <= 0) { //checks if the player health is 0
            thePlayer.setVisible(false); //if so makes the player invisible
            movementTimer.stop();
            Enemy1.movementTimer.stop();
            Enemy2.movementTimer.stop();
            Enemy3.movementTimer.stop();
            Enemy4.movementTimer.stop();
            Enemy5.movementTimer.stop();
            Enemy6.movementTimer.stop();
            Enemy7.movementTimer.stop();
            Enemy8.movementTimer.stop();
            Enemy11.movementTimer.stop();
            Enemy12.movementTimer.stop();
            Enemy13.movementTimer.stop();
            Enemy14.movementTimer.stop();
            Enemy15.movementTimer.stop();
            Enemy16.movementTimer.stop();
            Enemy17.movementTimer.stop();
            playerDeath(); //then builds the shop
        }
    } //Method that does stuff based on if the player is hit by an enemy

    public void enemyBulletCollision() {

        ////////////////COLLISION OF THE BULLET WITH THE ENEMY//////////////////
        if ((Enemy1HitBox.intersects(Bullet1HitBox) || Enemy1HitBox.intersects(Bullet2HitBox) || Enemy1HitBox.intersects(Bullet3HitBox) || Enemy1HitBox.intersects(Bullet4HitBox) || Enemy1HitBox.intersects(Bullet5HitBox)) && Enemy1.active) {
            //If the enemy hit box is intersecting with any of the bullet hit boxes and the enemy is active, this prevents the player from constantly shooting the zombies leading to buugs
            if (Bullet1HitBox.intersects(Enemy1HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy and if its mouse fired method was true, this is so the player can't just run up and kill all the zombies
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0 by calling the move bullet method and setting everything to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy1.enemyHealth -= (10 + (10 * damageLevel)); //sets the enemies health down 10
                Enemy1.enemyHealthBar.setValue(Enemy1.enemyHealth); //changes the value of the enemyhealth bar
                boolean killCounter1 = Enemy1.checkIfDead(); //checks if the enemy is dead
                if (killCounter1) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }                  //This code is the same for each bullet for each enemy
            }
            else if (Bullet2HitBox.intersects(Enemy1HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy1.enemyHealth -= (10 + (10 * damageLevel));
                Enemy1.enemyHealthBar.setValue(Enemy1.enemyHealth); 
                boolean killCounter1 = Enemy1.checkIfDead(); //checks if the enemy is dead
                if (killCounter1) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet3HitBox.intersects(Enemy1HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy1.enemyHealth -= (10 + (10 * damageLevel));
                Enemy1.enemyHealthBar.setValue(Enemy1.enemyHealth); 
                boolean killCounter1 = Enemy1.checkIfDead(); //checks if the enemy is dead
                if (killCounter1) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet4HitBox.intersects(Enemy1HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy1.enemyHealth -= (10 + (10 * damageLevel));
                Enemy1.enemyHealthBar.setValue(Enemy1.enemyHealth); 
                boolean killCounter1 = Enemy1.checkIfDead(); //checks if the enemy is dead
                if (killCounter1) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet5HitBox.intersects(Enemy1HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy1.enemyHealth -= (10 + (10 * damageLevel));
                Enemy1.enemyHealthBar.setValue(Enemy1.enemyHealth); 
                boolean killCounter1 = Enemy1.checkIfDead(); //checks if the enemy is dead
                if (killCounter1) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }
            }
        }
        else if ((Enemy2HitBox.intersects(Bullet1HitBox) || Enemy2HitBox.intersects(Bullet2HitBox) || Enemy2HitBox.intersects(Bullet3HitBox) || Enemy2HitBox.intersects(Bullet4HitBox) || Enemy2HitBox.intersects(Bullet5HitBox)) && Enemy2.active) {
            if (Bullet1HitBox.intersects(Enemy2HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy2.enemyHealth -= (10 + (10 * damageLevel));
                Enemy2.enemyHealthBar.setValue(Enemy2.enemyHealth);
                boolean killCounter2 = Enemy2.checkIfDead(); //checks if the enemy is dead
                if (killCounter2) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet2HitBox.intersects(Enemy2HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy2.enemyHealth -= (10 + (10 * damageLevel));
                Enemy2.enemyHealthBar.setValue(Enemy2.enemyHealth);
                boolean killCounter2 = Enemy2.checkIfDead(); //checks if the enemy is dead
                if (killCounter2) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet3HitBox.intersects(Enemy2HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy2.enemyHealth -= (10 + (10 * damageLevel));
                Enemy2.enemyHealthBar.setValue(Enemy2.enemyHealth);
                boolean killCounter2 = Enemy2.checkIfDead(); //checks if the enemy is dead
                if (killCounter2) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet4HitBox.intersects(Enemy2HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy2.enemyHealth -= (10 + (10 * damageLevel));
                Enemy2.enemyHealthBar.setValue(Enemy2.enemyHealth);
                boolean killCounter2 = Enemy2.checkIfDead(); //checks if the enemy is dead
                if (killCounter2) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet5HitBox.intersects(Enemy2HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy2.enemyHealth -= (10 + (10 * damageLevel));
                Enemy2.enemyHealthBar.setValue(Enemy2.enemyHealth);
                boolean killCounter2 = Enemy2.checkIfDead(); //checks if the enemy is dead
                if (killCounter2) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }
            }
        }
        else if ((Enemy3HitBox.intersects(Bullet1HitBox) || Enemy3HitBox.intersects(Bullet2HitBox) || Enemy3HitBox.intersects(Bullet3HitBox) || Enemy3HitBox.intersects(Bullet4HitBox) || Enemy3HitBox.intersects(Bullet5HitBox)) && Enemy3.active) {

            if (Bullet1HitBox.intersects(Enemy3HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy3.enemyHealth -= (10 + (10 * damageLevel));
                Enemy3.enemyHealthBar.setValue(Enemy3.enemyHealth);
                boolean killCounter3 = Enemy3.checkIfDead(); //checks if the enemy is dead
                if (killCounter3) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet2HitBox.intersects(Enemy3HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy3.enemyHealth -= (10 + (10 * damageLevel));
                Enemy3.enemyHealthBar.setValue(Enemy3.enemyHealth);
                boolean killCounter3 = Enemy3.checkIfDead(); //checks if the enemy is dead
                if (killCounter3) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet3HitBox.intersects(Enemy3HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy3.enemyHealth -= (10 + (10 * damageLevel));
                Enemy3.enemyHealthBar.setValue(Enemy3.enemyHealth);
                boolean killCounter3 = Enemy3.checkIfDead(); //checks if the enemy is dead
                if (killCounter3) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet4HitBox.intersects(Enemy3HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy3.enemyHealth -= (10 + (10 * damageLevel));
                Enemy3.enemyHealthBar.setValue(Enemy3.enemyHealth);
                boolean killCounter3 = Enemy3.checkIfDead(); //checks if the enemy is dead
                if (killCounter3) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet5HitBox.intersects(Enemy3HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy3.enemyHealth -= (10 + (10 * damageLevel));
                Enemy3.enemyHealthBar.setValue(Enemy3.enemyHealth);
                boolean killCounter3 = Enemy3.checkIfDead(); //checks if the enemy is dead
                if (killCounter3) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);//the kill count decreases by one, this is so the next wave can start
                    score += 100;
                    cash += 10;
                }
            }
        }
        else if ((Enemy4HitBox.intersects(Bullet1HitBox) || Enemy4HitBox.intersects(Bullet2HitBox) || Enemy4HitBox.intersects(Bullet3HitBox) || Enemy4HitBox.intersects(Bullet4HitBox) || Enemy4HitBox.intersects(Bullet5HitBox)) && Enemy4.active) {

            if (Bullet1HitBox.intersects(Enemy4HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy4.enemyHealth -= (10 + (10 * damageLevel));
                Enemy4.enemyHealthBar.setValue(Enemy4.enemyHealth);
                boolean killCounter4 = Enemy4.checkIfDead(); //checks if the enemy is dead
                if (killCounter4) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet2HitBox.intersects(Enemy4HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy4.enemyHealth -= (10 + (10 * damageLevel));
                Enemy4.enemyHealthBar.setValue(Enemy4.enemyHealth);
                boolean killCounter4 = Enemy4.checkIfDead(); //checks if the enemy is dead
                if (killCounter4) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet3HitBox.intersects(Enemy4HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy4.enemyHealth -= (10 + (10 * damageLevel));
                Enemy4.enemyHealthBar.setValue(Enemy4.enemyHealth);
                boolean killCounter4 = Enemy4.checkIfDead(); //checks if the enemy is dead
                if (killCounter4) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet4HitBox.intersects(Enemy4HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy4.enemyHealth -= (10 + (10 * damageLevel));
                Enemy4.enemyHealthBar.setValue(Enemy4.enemyHealth);
                boolean killCounter4 = Enemy4.checkIfDead(); //checks if the enemy is dead
                if (killCounter4) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet5HitBox.intersects(Enemy4HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy4.enemyHealth -= (10 + (10 * damageLevel));
                Enemy4.enemyHealthBar.setValue(Enemy4.enemyHealth);
                boolean killCounter4 = Enemy4.checkIfDead(); //checks if the enemy is dead
                if (killCounter4) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
        }
        if ((Enemy5HitBox.intersects(Bullet1HitBox) || Enemy5HitBox.intersects(Bullet2HitBox) || Enemy5HitBox.intersects(Bullet3HitBox) || Enemy5HitBox.intersects(Bullet4HitBox) || Enemy5HitBox.intersects(Bullet5HitBox)) & Enemy5.active) {

            if (Bullet1HitBox.intersects(Enemy5HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy5.enemyHealth -= (10 + (10 * damageLevel));
                Enemy5.enemyHealthBar.setValue(Enemy5.enemyHealth);
                boolean killCounter5 = Enemy5.checkIfDead(); //checks if the enemy is dead
                if (killCounter5) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet2HitBox.intersects(Enemy5HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy5.enemyHealth -= (10 + (10 * damageLevel));
                Enemy5.enemyHealthBar.setValue(Enemy5.enemyHealth);
                boolean killCounter5 = Enemy5.checkIfDead(); //checks if the enemy is dead
                if (killCounter5) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet3HitBox.intersects(Enemy5HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy5.enemyHealth -= (10 + (10 * damageLevel));
                Enemy5.enemyHealthBar.setValue(Enemy5.enemyHealth);
                boolean killCounter5 = Enemy5.checkIfDead(); //checks if the enemy is dead
                if (killCounter5) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet4HitBox.intersects(Enemy5HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy5.enemyHealth -= (10 + (10 * damageLevel));
                Enemy5.enemyHealthBar.setValue(Enemy5.enemyHealth);
                boolean killCounter5 = Enemy5.checkIfDead(); //checks if the enemy is dead
                if (killCounter5) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet5HitBox.intersects(Enemy5HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy5.enemyHealth -= (10 + (10 * damageLevel));
                Enemy5.enemyHealthBar.setValue(Enemy5.enemyHealth);
                boolean killCounter5 = Enemy5.checkIfDead(); //checks if the enemy is dead
                if (killCounter5) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
        }
        if ((Enemy6HitBox.intersects(Bullet1HitBox) || Enemy6HitBox.intersects(Bullet2HitBox) || Enemy6HitBox.intersects(Bullet3HitBox) || Enemy6HitBox.intersects(Bullet4HitBox) || Enemy6HitBox.intersects(Bullet5HitBox)) && Enemy6.active) {

            if (Bullet1HitBox.intersects(Enemy6HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy6.enemyHealth -= (10 + (10 * damageLevel));
                Enemy6.enemyHealthBar.setValue(Enemy6.enemyHealth);
                boolean killCounter6 = Enemy6.checkIfDead(); //checks if the enemy is dead
                if (killCounter6) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet2HitBox.intersects(Enemy6HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy6.enemyHealth -= (10 + (10 * damageLevel));
                Enemy6.enemyHealthBar.setValue(Enemy6.enemyHealth);
                boolean killCounter6 = Enemy6.checkIfDead(); //checks if the enemy is dead
                if (killCounter6) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet3HitBox.intersects(Enemy6HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy6.enemyHealth -= (10 + (10 * damageLevel));
                Enemy6.enemyHealthBar.setValue(Enemy6.enemyHealth);
                boolean killCounter6 = Enemy6.checkIfDead(); //checks if the enemy is dead
                if (killCounter6) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet4HitBox.intersects(Enemy6HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy6.enemyHealth -= (10 + (10 * damageLevel));
                Enemy6.enemyHealthBar.setValue(Enemy6.enemyHealth);
                boolean killCounter6 = Enemy6.checkIfDead(); //checks if the enemy is dead
                if (killCounter6) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet5HitBox.intersects(Enemy6HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy6.enemyHealth -= (10 + (10 * damageLevel));
                Enemy6.enemyHealthBar.setValue(Enemy6.enemyHealth);
                boolean killCounter6 = Enemy6.checkIfDead(); //checks if the enemy is dead
                if (killCounter6) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
        }
        if ((Enemy7HitBox.intersects(Bullet1HitBox) || Enemy7HitBox.intersects(Bullet2HitBox) || Enemy7HitBox.intersects(Bullet3HitBox) || Enemy7HitBox.intersects(Bullet4HitBox) || Enemy7HitBox.intersects(Bullet5HitBox)) && Enemy7.active) {

            if (Bullet1HitBox.intersects(Enemy7HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy7.enemyHealth -= (10 + (10 * damageLevel));
                Enemy7.enemyHealthBar.setValue(Enemy7.enemyHealth);
                boolean killCounter7 = Enemy7.checkIfDead(); //checks if the enemy is dead
                if (killCounter7) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet2HitBox.intersects(Enemy7HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy7.enemyHealth -= (10 + (10 * damageLevel));
                Enemy7.enemyHealthBar.setValue(Enemy7.enemyHealth);
                boolean killCounter7 = Enemy7.checkIfDead(); //checks if the enemy is dead
                if (killCounter7) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet3HitBox.intersects(Enemy7HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy7.enemyHealth -= (10 + (10 * damageLevel));
                Enemy7.enemyHealthBar.setValue(Enemy7.enemyHealth);
                boolean killCounter7 = Enemy7.checkIfDead(); //checks if the enemy is dead
                if (killCounter7) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet4HitBox.intersects(Enemy7HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy7.enemyHealth -= (10 + (10 * damageLevel));
                Enemy7.enemyHealthBar.setValue(Enemy7.enemyHealth);
                boolean killCounter7 = Enemy7.checkIfDead(); //checks if the enemy is dead
                if (killCounter7) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet5HitBox.intersects(Enemy7HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy7.enemyHealth -= (10 + (10 * damageLevel));
                Enemy7.enemyHealthBar.setValue(Enemy7.enemyHealth);
                boolean killCounter7 = Enemy7.checkIfDead(); //checks if the enemy is dead
                if (killCounter7) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
        }
        if ((Enemy8HitBox.intersects(Bullet1HitBox) || Enemy8HitBox.intersects(Bullet2HitBox) || Enemy8HitBox.intersects(Bullet3HitBox) || Enemy8HitBox.intersects(Bullet4HitBox) || Enemy8HitBox.intersects(Bullet5HitBox)) && Enemy8.active) {

            if (Bullet1HitBox.intersects(Enemy8HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy8.enemyHealth -= (10 + (10 * damageLevel));
                Enemy8.enemyHealthBar.setValue(Enemy8.enemyHealth);
                boolean killCounter8 = Enemy8.checkIfDead(); //checks if the enemy is dead
                if (killCounter8) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet2HitBox.intersects(Enemy8HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy8.enemyHealth -= (10 + (10 * damageLevel));
                Enemy8.enemyHealthBar.setValue(Enemy8.enemyHealth);
                Enemy8.enemyHealth -= (10 + (10 * damageLevel));
                Enemy8.enemyHealthBar.setValue(Enemy8.enemyHealth);
                boolean killCounter8 = Enemy8.checkIfDead(); //checks if the enemy is dead
                if (killCounter8) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet3HitBox.intersects(Enemy8HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy8.enemyHealth -= (10 + (10 * damageLevel));
                Enemy8.enemyHealthBar.setValue(Enemy8.enemyHealth);
                boolean killCounter8 = Enemy8.checkIfDead(); //checks if the enemy is dead
                if (killCounter8) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet4HitBox.intersects(Enemy8HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy8.enemyHealth -= (10 + (10 * damageLevel));
                Enemy8.enemyHealthBar.setValue(Enemy8.enemyHealth);
                boolean killCounter8 = Enemy8.checkIfDead(); //checks if the enemy is dead
                if (killCounter8) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet5HitBox.intersects(Enemy8HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy8.enemyHealth -= (10 + (10 * damageLevel));
                Enemy8.enemyHealthBar.setValue(Enemy8.enemyHealth);
                boolean killCounter8 = Enemy8.checkIfDead(); //checks if the enemy is dead
                if (killCounter8) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
        }
        if ((Enemy11HitBox.intersects(Bullet1HitBox) || Enemy11HitBox.intersects(Bullet2HitBox) || Enemy11HitBox.intersects(Bullet3HitBox) || Enemy11HitBox.intersects(Bullet4HitBox) || Enemy11HitBox.intersects(Bullet5HitBox)) && Enemy11.active) {

            if (Bullet1HitBox.intersects(Enemy11HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy11.enemyHealth -= (10 + (10 * damageLevel));
                Enemy11.enemyHealthBar.setValue(Enemy11.enemyHealth);
                boolean killCounter11 = Enemy11.checkIfDead(); //checks if the enemy is dead
                if (killCounter11) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet2HitBox.intersects(Enemy11HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy11.enemyHealth -= (10 + (10 * damageLevel));
                Enemy11.enemyHealthBar.setValue(Enemy11.enemyHealth);
                boolean killCounter11 = Enemy11.checkIfDead(); //checks if the enemy is dead
                if (killCounter11) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet3HitBox.intersects(Enemy11HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy11.enemyHealth -= (10 + (10 * damageLevel));
                Enemy11.enemyHealthBar.setValue(Enemy11.enemyHealth);
                boolean killCounter11 = Enemy11.checkIfDead(); //checks if the enemy is dead
                if (killCounter11) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet4HitBox.intersects(Enemy11HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy11.enemyHealth -= (10 + (10 * damageLevel));
                Enemy11.enemyHealthBar.setValue(Enemy11.enemyHealth);
                boolean killCounter11 = Enemy11.checkIfDead(); //checks if the enemy is dead
                if (killCounter11) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet5HitBox.intersects(Enemy11HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy11.enemyHealth -= (10 + (10 * damageLevel));
                Enemy11.enemyHealthBar.setValue(Enemy11.enemyHealth);
                boolean killCounter11 = Enemy11.checkIfDead(); //checks if the enemy is dead
                if (killCounter11) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
        }
        if ((Enemy12HitBox.intersects(Bullet1HitBox) || Enemy12HitBox.intersects(Bullet2HitBox) || Enemy12HitBox.intersects(Bullet3HitBox) || Enemy12HitBox.intersects(Bullet4HitBox) || Enemy12HitBox.intersects(Bullet5HitBox)) && Enemy12.active) {

            if (Bullet1HitBox.intersects(Enemy12HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy12.enemyHealth -= (10 + (10 * damageLevel));
                Enemy12.enemyHealthBar.setValue(Enemy12.enemyHealth);
                boolean killCounter12 = Enemy12.checkIfDead(); //checks if the enemy is dead
                if (killCounter12) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet2HitBox.intersects(Enemy12HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy12.enemyHealth -= (10 + (10 * damageLevel));
                Enemy12.enemyHealthBar.setValue(Enemy12.enemyHealth);
                boolean killCounter12 = Enemy12.checkIfDead(); //checks if the enemy is dead
                if (killCounter12) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet3HitBox.intersects(Enemy12HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy12.enemyHealth -= (10 + (10 * damageLevel));
                Enemy12.enemyHealthBar.setValue(Enemy12.enemyHealth);
                boolean killCounter12 = Enemy12.checkIfDead(); //checks if the enemy is dead
                if (killCounter12) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet4HitBox.intersects(Enemy12HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy12.enemyHealth -= (10 + (10 * damageLevel));
                Enemy12.enemyHealthBar.setValue(Enemy12.enemyHealth);
                boolean killCounter12 = Enemy12.checkIfDead(); //checks if the enemy is dead
                if (killCounter12) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet5HitBox.intersects(Enemy12HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy12.enemyHealth -= (10 + (10 * damageLevel));
                Enemy12.enemyHealthBar.setValue(Enemy12.enemyHealth);
                boolean killCounter12 = Enemy12.checkIfDead(); //checks if the enemy is dead
                if (killCounter12) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
        }
        if ((Enemy13HitBox.intersects(Bullet1HitBox) || Enemy13HitBox.intersects(Bullet2HitBox) || Enemy13HitBox.intersects(Bullet3HitBox) || Enemy13HitBox.intersects(Bullet4HitBox) || Enemy13HitBox.intersects(Bullet5HitBox)) && Enemy13.active) {

            if (Bullet1HitBox.intersects(Enemy13HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy13.enemyHealth -= (10 + (10 * damageLevel));
                Enemy13.enemyHealthBar.setValue(Enemy13.enemyHealth);
                boolean killCounter13 = Enemy13.checkIfDead(); //checks if the enemy is dead
                if (killCounter13) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet2HitBox.intersects(Enemy13HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy13.enemyHealth -= (10 + (10 * damageLevel));
                Enemy13.enemyHealthBar.setValue(Enemy13.enemyHealth);
                boolean killCounter13 = Enemy13.checkIfDead(); //checks if the enemy is dead
                if (killCounter13) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet3HitBox.intersects(Enemy13HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy13.enemyHealth -= (10 + (10 * damageLevel));
                Enemy13.enemyHealthBar.setValue(Enemy13.enemyHealth);
                boolean killCounter13 = Enemy13.checkIfDead(); //checks if the enemy is dead
                if (killCounter13) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet4HitBox.intersects(Enemy13HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy13.enemyHealth -= (10 + (10 * damageLevel));
                Enemy13.enemyHealthBar.setValue(Enemy13.enemyHealth);
                boolean killCounter13 = Enemy13.checkIfDead(); //checks if the enemy is dead
                if (killCounter13) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet5HitBox.intersects(Enemy13HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy13.enemyHealth -= (10 + (10 * damageLevel));
                Enemy13.enemyHealthBar.setValue(Enemy13.enemyHealth);
                boolean killCounter13 = Enemy13.checkIfDead(); //checks if the enemy is dead
                if (killCounter13) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
        }
        if ((Enemy14HitBox.intersects(Bullet1HitBox) || Enemy14HitBox.intersects(Bullet2HitBox) || Enemy14HitBox.intersects(Bullet3HitBox) || Enemy14HitBox.intersects(Bullet4HitBox) || Enemy14HitBox.intersects(Bullet5HitBox)) && Enemy14.active) {

            if (Bullet1HitBox.intersects(Enemy14HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy14.enemyHealth -= (10 + (10 * damageLevel));
                Enemy14.enemyHealthBar.setValue(Enemy14.enemyHealth);
                boolean killCounter14 = Enemy14.checkIfDead(); //checks if the enemy is dead
                if (killCounter14) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet2HitBox.intersects(Enemy14HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy14.enemyHealth -= (10 + (10 * damageLevel));
                Enemy14.enemyHealthBar.setValue(Enemy14.enemyHealth);
                boolean killCounter14 = Enemy14.checkIfDead(); //checks if the enemy is dead
                if (killCounter14) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet3HitBox.intersects(Enemy14HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy14.enemyHealth -= (10 + (10 * damageLevel));
                Enemy14.enemyHealthBar.setValue(Enemy14.enemyHealth);
                boolean killCounter14 = Enemy14.checkIfDead(); //checks if the enemy is dead
                if (killCounter14) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet4HitBox.intersects(Enemy14HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy14.enemyHealth -= (10 + (10 * damageLevel));
                Enemy14.enemyHealthBar.setValue(Enemy14.enemyHealth);
                boolean killCounter14 = Enemy14.checkIfDead(); //checks if the enemy is dead
                if (killCounter14) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet5HitBox.intersects(Enemy14HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy14.enemyHealth -= (10 + (10 * damageLevel));
                Enemy14.enemyHealthBar.setValue(Enemy14.enemyHealth);
                boolean killCounter14 = Enemy14.checkIfDead(); //checks if the enemy is dead
                if (killCounter14) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
        }
        if ((Enemy15HitBox.intersects(Bullet1HitBox) || Enemy15HitBox.intersects(Bullet2HitBox) || Enemy15HitBox.intersects(Bullet3HitBox) || Enemy15HitBox.intersects(Bullet4HitBox) || Enemy15HitBox.intersects(Bullet5HitBox)) && Enemy15.active) {
            if (Bullet1HitBox.intersects(Enemy15HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy15.enemyHealth -= (10 + (10 * damageLevel));
                Enemy15.enemyHealthBar.setValue(Enemy15.enemyHealth);
                boolean killCounter15 = Enemy15.checkIfDead(); //checks if the enemy is dead
                if (killCounter15) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet2HitBox.intersects(Enemy15HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy15.enemyHealth -= (10 + (10 * damageLevel));
                Enemy15.enemyHealthBar.setValue(Enemy15.enemyHealth);
                boolean killCounter15 = Enemy15.checkIfDead(); //checks if the enemy is dead
                if (killCounter15) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet3HitBox.intersects(Enemy15HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy15.enemyHealth -= (10 + (10 * damageLevel));
                Enemy15.enemyHealthBar.setValue(Enemy15.enemyHealth);
                boolean killCounter15 = Enemy15.checkIfDead(); //checks if the enemy is dead
                if (killCounter15) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet4HitBox.intersects(Enemy15HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy15.enemyHealth -= (10 + (10 * damageLevel));
                Enemy15.enemyHealthBar.setValue(Enemy15.enemyHealth);
                boolean killCounter15 = Enemy15.checkIfDead(); //checks if the enemy is dead
                if (killCounter15) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet5HitBox.intersects(Enemy15HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy15.enemyHealth -= (10 + (10 * damageLevel));
                Enemy15.enemyHealthBar.setValue(Enemy15.enemyHealth);
                boolean killCounter15 = Enemy15.checkIfDead(); //checks if the enemy is dead
                if (killCounter15) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
        }
        if ((Enemy16HitBox.intersects(Bullet1HitBox) || Enemy16HitBox.intersects(Bullet2HitBox) || Enemy16HitBox.intersects(Bullet3HitBox) || Enemy16HitBox.intersects(Bullet4HitBox) || Enemy16HitBox.intersects(Bullet5HitBox)) && Enemy16.active) {
            if (Bullet1HitBox.intersects(Enemy16HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy16.enemyHealth -= (10 + (10 * damageLevel));
                Enemy16.enemyHealthBar.setValue(Enemy16.enemyHealth);
                boolean killCounter16 = Enemy16.checkIfDead(); //checks if the enemy is dead
                if (killCounter16) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet2HitBox.intersects(Enemy16HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy16.enemyHealth -= (10 + (10 * damageLevel));
                Enemy16.enemyHealthBar.setValue(Enemy16.enemyHealth);
                boolean killCounter16 = Enemy16.checkIfDead(); //checks if the enemy is dead
                if (killCounter16) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet3HitBox.intersects(Enemy16HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy16.enemyHealth -= (10 + (10 * damageLevel));
                Enemy16.enemyHealthBar.setValue(Enemy16.enemyHealth);
                boolean killCounter16 = Enemy16.checkIfDead(); //checks if the enemy is dead
                if (killCounter16) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet4HitBox.intersects(Enemy16HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy16.enemyHealth -= (10 + (10 * damageLevel));
                Enemy16.enemyHealthBar.setValue(Enemy16.enemyHealth);
                boolean killCounter16 = Enemy16.checkIfDead(); //checks if the enemy is dead
                if (killCounter16) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
            else if (Bullet5HitBox.intersects(Enemy16HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy16.enemyHealth -= (10 + (10 * damageLevel));
                Enemy16.enemyHealthBar.setValue(Enemy16.enemyHealth);
                boolean killCounter16 = Enemy16.checkIfDead(); //checks if the enemy is dead
                if (killCounter16) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;//the kill count decreases by one, this is so the next wave can start
                }
            }
        }
        if ((Enemy17HitBox.intersects(Bullet1HitBox) || Enemy17HitBox.intersects(Bullet2HitBox) || Enemy17HitBox.intersects(Bullet3HitBox) || Enemy17HitBox.intersects(Bullet4HitBox) || Enemy17HitBox.intersects(Bullet5HitBox)) && Enemy17.active) {
            Enemy17.enemyHealth -= (10 + (10 * damageLevel));
            if (Bullet1HitBox.intersects(Enemy17HitBox) && Bullet1.mouseFired) { //checks for if each individual bullet hit the enemy
                Bullet1.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet1HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet1.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet1.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet1.setVisible(false); //sets the bullet visible to false
                Enemy17.enemyHealth -= (10 + (10 * damageLevel));
                Enemy17.enemyHealthBar.setValue(Enemy17.enemyHealth);
                boolean killCounter17 = Enemy17.checkIfDead(); //checks if the enemy is dead
                if (killCounter17) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet2HitBox.intersects(Enemy17HitBox) && Bullet2.mouseFired) {
                Bullet2.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet2HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet2.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet2.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet2.setVisible(false); //sets the bullet visible to false
                Enemy17.enemyHealth -= (10 + (10 * damageLevel));
                Enemy17.enemyHealthBar.setValue(Enemy17.enemyHealth);
                boolean killCounter17 = Enemy17.checkIfDead(); //checks if the enemy is dead
                if (killCounter17) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet3HitBox.intersects(Enemy17HitBox) && Bullet3.mouseFired) {
                Bullet3.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet3HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet3.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet3.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet3.setVisible(false); //sets the bullet visible to false
                Enemy17.enemyHealth -= (10 + (10 * damageLevel));
                Enemy17.enemyHealthBar.setValue(Enemy17.enemyHealth);
                boolean killCounter17 = Enemy17.checkIfDead(); //checks if the enemy is dead
                if (killCounter17) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet4HitBox.intersects(Enemy17HitBox) && Bullet4.mouseFired) {
                Bullet4.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet4HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet4.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet4.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet4.setVisible(false); //sets the bullet visible to false
                Enemy17.enemyHealth -= (10 + (10 * damageLevel));
                Enemy17.enemyHealthBar.setValue(Enemy17.enemyHealth);
                boolean killCounter17 = Enemy17.checkIfDead(); //checks if the enemy is dead
                if (killCounter17) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;
                }
            }
            else if (Bullet5HitBox.intersects(Enemy17HitBox) && Bullet5.mouseFired) {
                Bullet5.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y); //if it did, sets the bullet and its hit box back to the bullet return point
                Bullet5HitBox.setLocation(bulletReturnPoint.getLocation().x, bulletReturnPoint.getLocation().y);
                Bullet5.moveBullet(0, 0, 0, 0); //sets the bullets x and y velocity to 0
                Bullet5.mouseFired = false; //makes the mouse fired boolean of the bullet false so it continues to move with the player
                Bullet5.setVisible(false); //sets the bullet visible to false
                Enemy17.enemyHealth -= (10 + (10 * damageLevel));
                Enemy17.enemyHealthBar.setValue(Enemy17.enemyHealth);
                boolean killCounter17 = Enemy17.checkIfDead(); //checks if the enemy is dead
                if (killCounter17) { //if they are dead
                    enemyCount--;
                    gameText.setText("ZOMBIES REMAINING: " + enemyCount);
                    score += 100;
                    cash += 10;
                }
            }
        }

        //////////////////////////////////////////////////////////////////////////
    } //method that detects collision with an enemy and a bullet

    public void enemyImageRotationAndMovement() {
        //////////////////ROTATING THE ZOMBIES BASED ON PLAYER POSITION /////////
        int playerX = thePlayer.getLocation().x + thePlayer.getWidth() / 2;
        int playerY = thePlayer.getLocation().y + thePlayer.getHeight() / 2;
        double rotationAngleForEnemies = 0; //different int for rotation angle so its easier to differentiate
        float xDistance1 = playerX - (Enemy1.getLocation().x + Enemy1.getWidth() / 2); //gets the distance in the x from the x coordinate of the mouse to the x center of the player
        float yDistance1 = playerY - (Enemy1.getLocation().y + Enemy1.getHeight() / 2); //gets the distance in the y from the y coordinate of the mouse to the y center of the player
        //These two float values help to create an imaginary line from the center of the character to the mouse position
        //The hit boxes will always needs to be on the enemy so there is no need to program any additional movement for them
        Enemy1HitBox.setLocation(Enemy1.getLocation().x, Enemy1.getLocation().y);
        Enemy2HitBox.setLocation(Enemy2.getLocation().x, Enemy2.getLocation().y);
        Enemy3HitBox.setLocation(Enemy3.getLocation().x, Enemy3.getLocation().y);
        Enemy4HitBox.setLocation(Enemy4.getLocation().x, Enemy4.getLocation().y);
        Enemy5HitBox.setLocation(Enemy5.getLocation().x, Enemy5.getLocation().y);
        Enemy6HitBox.setLocation(Enemy6.getLocation().x, Enemy6.getLocation().y);
        Enemy7HitBox.setLocation(Enemy7.getLocation().x, Enemy7.getLocation().y);
        Enemy8HitBox.setLocation(Enemy8.getLocation().x, Enemy8.getLocation().y);
        Enemy11HitBox.setLocation(Enemy11.getLocation().x, Enemy11.getLocation().y);
        Enemy12HitBox.setLocation(Enemy12.getLocation().x, Enemy12.getLocation().y);
        Enemy13HitBox.setLocation(Enemy13.getLocation().x, Enemy13.getLocation().y);
        Enemy14HitBox.setLocation(Enemy14.getLocation().x, Enemy14.getLocation().y);
        Enemy15HitBox.setLocation(Enemy15.getLocation().x, Enemy15.getLocation().y);
        Enemy16HitBox.setLocation(Enemy16.getLocation().x, Enemy16.getLocation().y);
        Enemy17HitBox.setLocation(Enemy17.getLocation().x, Enemy17.getLocation().y);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance1, xDistance1));  //Gets the angle in radians from this line to an imaginary x axis
        Enemy1.getRotationAngle(rotationAngleForEnemies);
        //this is repeated for every enemy
        if (Enemy1.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e1XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e1YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy1.setVisible(true);
            Enemy1.getMovementVectors(e1XVel, e1YVel); //calls the get movments vectors method from the enemy so it knows where to turn
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance2 = playerX - (Enemy2.getLocation().x + Enemy2.getWidth() / 2);
        float yDistance2 = playerY - (Enemy2.getLocation().y + Enemy2.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance2, xDistance2));
        Enemy2.getRotationAngle(rotationAngleForEnemies);
        if (Enemy2.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e2XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e2YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));

            Enemy2.setVisible(true);
            Enemy2.getMovementVectors(e2XVel, e2YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance3 = playerX - (Enemy3.getLocation().x + Enemy3.getWidth() / 2);
        float yDistance3 = playerY - (Enemy3.getLocation().y + Enemy3.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance3, xDistance3));
        Enemy3.getRotationAngle(rotationAngleForEnemies);
        if (Enemy3.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e3XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e3YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy3.setVisible(true);
            Enemy3.getMovementVectors(e3XVel, e3YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance4 = playerX - (Enemy4.getLocation().x + Enemy4.getWidth() / 2);
        float yDistance4 = playerY - (Enemy4.getLocation().y + Enemy4.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance4, xDistance4));
        Enemy4.getRotationAngle(rotationAngleForEnemies);
        if (Enemy4.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e4XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e4YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy4.setVisible(true);
            Enemy4.getMovementVectors(e4XVel, e4YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance5 = playerX - (Enemy5.getLocation().x + Enemy5.getWidth() / 2);
        float yDistance5 = playerY - (Enemy5.getLocation().y + Enemy5.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance5, xDistance5));
        Enemy5.getRotationAngle(rotationAngleForEnemies);
        if (Enemy5.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e5XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e5YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy5.setVisible(true);
            Enemy5.getMovementVectors(e5XVel, e5YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance6 = playerX - (Enemy6.getLocation().x + Enemy6.getWidth() / 2);
        float yDistance6 = playerY - (Enemy6.getLocation().y + Enemy6.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance6, xDistance6));
        Enemy6.getRotationAngle(rotationAngleForEnemies);
        if (Enemy6.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e6XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e6YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy6.setVisible(true);
            Enemy6.getMovementVectors(e6XVel, e6YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance7 = playerX - (Enemy7.getLocation().x + Enemy7.getWidth() / 2);
        float yDistance7 = playerY - (Enemy7.getLocation().y + Enemy7.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance7, xDistance7));
        Enemy7.getRotationAngle(rotationAngleForEnemies);
        if (Enemy7.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e7XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e7YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy7.setVisible(true);
            Enemy7.getMovementVectors(e7XVel, e7YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance8 = playerX - (Enemy8.getLocation().x + Enemy8.getWidth() / 2);
        float yDistance8 = playerY - (Enemy8.getLocation().y + Enemy8.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance8, xDistance8));
        Enemy8.getRotationAngle(rotationAngleForEnemies);
        if (Enemy8.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e8XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e8YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy8.setVisible(true);
            Enemy8.getMovementVectors(e8XVel, e8YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance11 = playerX - (Enemy11.getLocation().x + Enemy11.getWidth() / 2);
        float yDistance11 = playerY - (Enemy11.getLocation().y + Enemy11.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance11, xDistance11));
        Enemy11.getRotationAngle(rotationAngleForEnemies);
        if (Enemy11.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e11XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e11YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy11.setVisible(true);
            Enemy11.getMovementVectors(e11XVel, e11YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance12 = playerX - (Enemy12.getLocation().x + Enemy12.getWidth() / 2);
        float yDistance12 = playerY - (Enemy12.getLocation().y + Enemy12.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance12, xDistance12));
        Enemy12.getRotationAngle(rotationAngleForEnemies);
        if (Enemy12.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e12XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e12YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy12.setVisible(true);
            Enemy12.getMovementVectors(e12XVel, e12YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance13 = playerX - (Enemy13.getLocation().x + Enemy13.getWidth() / 2);
        float yDistance13 = playerY - (Enemy13.getLocation().y + Enemy13.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance13, xDistance13));
        Enemy13.getRotationAngle(rotationAngleForEnemies);
        if (Enemy13.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e13XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e13YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy13.setVisible(true);
            Enemy13.getMovementVectors(e13XVel, e13YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance14 = playerX - (Enemy14.getLocation().x + Enemy14.getWidth() / 2);
        float yDistance14 = playerY - (Enemy14.getLocation().y + Enemy14.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance14, xDistance14));
        Enemy14.getRotationAngle(rotationAngleForEnemies);
        if (Enemy14.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e14XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e14YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy14.setVisible(true);
            Enemy14.getMovementVectors(e14XVel, e14YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance15 = playerX - (Enemy15.getLocation().x + Enemy15.getWidth() / 2);
        float yDistance15 = playerY - (Enemy15.getLocation().y + Enemy15.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance15, xDistance15));
        Enemy15.getRotationAngle(rotationAngleForEnemies);
        if (Enemy15.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e15XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e15YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy15.setVisible(true);
            Enemy15.getMovementVectors(e15XVel, e15YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance16 = playerX - (Enemy16.getLocation().x + Enemy16.getWidth() / 2);
        float yDistance16 = playerY - (Enemy16.getLocation().y + Enemy16.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance16, xDistance16));
        Enemy16.getRotationAngle(rotationAngleForEnemies);
        if (Enemy16.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e16XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e16YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy16.setVisible(true);
            Enemy16.getMovementVectors(e16XVel, e16YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
        float xDistance17 = playerX - (Enemy17.getLocation().x + Enemy17.getWidth() / 2);
        float yDistance17 = playerY - (Enemy17.getLocation().y + Enemy17.getHeight() / 2);
        rotationAngleForEnemies = Math.toDegrees(Math.atan2(yDistance17, xDistance17));
        Enemy17.getRotationAngle(rotationAngleForEnemies);
        if (Enemy17.active) { //If the enemy 1 activate boolean is true, it means the game randomly selected that monster to move
            //Similarily to the bullets, the game gets its x and y velocities based off
            int e17XVel = (int) Math.round((2 + enemySpeedBuff) * Math.cos(Math.toRadians(rotationAngleForEnemies))); //sets the velocity in the x of the bullet to a ratio of how fast it should be fired if it's horizontal speed was 10
            int e17YVel = (int) Math.round((2 + enemySpeedBuff) * Math.sin(Math.toRadians(rotationAngleForEnemies)));
            Enemy17.setVisible(true);
            Enemy17.getMovementVectors(e17XVel, e17YVel);
            //the program constantly sends these values to the enemy so they can move properly
        }
    } //Method that deals with enemy image rotation and how the enemies moves towards the character

    public void playerDeath() {
        gameText.setText("YOU HAVE FALLEN TO THE ZOMBIES");//changes the game text
        healthBar.setValue(0);
        update(this.getGraphics()); //updates the frame
        try { //waits for a bit
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PlayArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!returningPlayer) { //if the player was not a returning one then their new score is added to the leaderboard
            try {
                highScoreWriter.addHighScore(score, playerName, healthLevel, damageLevel, speedLevel, cash);
            } catch (IOException ex) {
                Logger.getLogger(PlayArea.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { //otherwise, they score is updated from their previous game
            highScoreWriter.updateHighScore(score, String.valueOf(playerName), healthLevel, damageLevel, speedLevel, cash);
        }
        gameActive = false;
        buildShop();

    } //method that checks for the player death

}

//trust me, if i could have any of these in an array and work through a for loop, I would
