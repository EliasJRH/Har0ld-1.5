package har0ld1_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author s241556
 */
public class FileWriter {

    ArrayList<Integer> AllHighScores = new ArrayList<Integer>(); //array list that hold only the scores of the players
    ArrayList<String> PlayerNames = new ArrayList<String>(); //array list to hold all player information 

    public void addHighScore(int Score, String playerName, int healthLevel, int damageLevel, int speedLevel, int cash) throws IOException {
        PrintWriter fileOut; //Define print writer
        fileOut = new PrintWriter(new java.io.FileWriter("Leaderboard.txt", true)); //Get the file for the print writer,\ set to true so it appends the file
        fileOut.println(Score + " - " + playerName + " , " + healthLevel + " , " + damageLevel + " , " + speedLevel + " , " + cash + " , ");
        fileOut.close();
        sortHighScores();
    }
    //method that adds a new high score to the file 
    
    public void sortHighScores() {
        AllHighScores.clear();
        PlayerNames.clear();
        Scanner playerFileReader = null; //defines two scanners to read the lines and scores of the player
        Scanner playerScoreReader = null;
        File highScoreFile = new File("Leaderboard.txt"); //defines the file 
        String whatToWriteToFile = ""; //creates string of what to put in the file
        try {
            playerFileReader = new Scanner(highScoreFile); //gets the file to the player reader scanner
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerFileReader.nextLine(); //skips the first line that has all the info
        while (playerFileReader.hasNextLine()) { //adds all the player profiles into an array list
            PlayerNames.add(playerFileReader.nextLine());
        }
        try {
            playerScoreReader = new Scanner(highScoreFile); //the same process is performed but only for the player scores
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerScoreReader.nextLine(); //also skips the first line
        while (playerScoreReader.hasNextLine()) {
            AllHighScores.add(playerScoreReader.nextInt()); //gets only the score
            playerScoreReader.nextLine(); //skips the rest of the line
        }
        if (AllHighScores.size() > 1) { //checks if the size of the array list is larger than 1, if its not it can't be sorted
            //if it is, the program will sort everything using bubble sort, this is the most effective working with a smaller sample size
            boolean flag = true;
            int temp = 0;
            String sTemp = "";
            while (flag == true) {
                flag = false;
                for (int firstLoop = 0; firstLoop < AllHighScores.size() - 1; firstLoop++) {
                    if (AllHighScores.get(firstLoop) < AllHighScores.get(firstLoop + 1)) {
                        temp = AllHighScores.get(firstLoop);
                        sTemp = PlayerNames.get(firstLoop);
                        AllHighScores.set(firstLoop, AllHighScores.get(firstLoop + 1)); //whenever the element of the score array moves, the player names array also moves
                        PlayerNames.set(firstLoop, PlayerNames.get(firstLoop + 1));
                        AllHighScores.set(firstLoop + 1, temp);
                        PlayerNames.set(firstLoop + 1, sTemp);
                        flag = true;
                    }
                }
            }
            whatToWriteToFile = "Score, Player Name, Health Level, Damage Level, Speed Level, Cash \n"; //adds the first line of the file for what to wirte
            for (int i = 0; i < PlayerNames.size(); i++) { //takes the new sorted player profile array and adds all the elements into a string
                whatToWriteToFile += (PlayerNames.get(i) + System.lineSeparator());
            }
            PrintWriter fileOut = null; //Define print writer
            try {
                fileOut = new PrintWriter(new java.io.FileWriter("Leaderboard.txt")); //Get the file for the print writer,\ set to true so it appends the file
            } catch (IOException ex) {
                Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
            fileOut.print(whatToWriteToFile); //writes everything to the file
            fileOut.close(); //closes the print writer
        }

    } //method that sorts the high scores in the file

    boolean searchForSaveFile(String playerName) {
        File leaderboardFile = new File("Leaderboard.txt");
        Scanner findPlayerFile = null;
        try {
            findPlayerFile = new Scanner(leaderboardFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        findPlayerFile.nextLine(); //skips the first line
        while (findPlayerFile.hasNextLine()) {
            String check = findPlayerFile.nextLine(); //check string is set to the whole line
            if (check.contains(playerName)) { //if the check string contains the player name it tells the player that a save file has been found
                return (true);
            }
        }
        return (false);
    } //method that searches for a past save file 
 
    int getHealthLevel(String playerName) {
        File leaderboardFile = new File("Leaderboard.txt");
        Scanner findPlayerFile = null;
        try {
            findPlayerFile = new Scanner(leaderboardFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        findPlayerFile.nextLine(); //skips the first line
        while (findPlayerFile.hasNextLine()) {
            String check = findPlayerFile.next();
            if (check.contains(playerName)) {
                findPlayerFile.next();
                int healthLevel = findPlayerFile.nextInt();
                return (healthLevel);
            }
        }
        return (0);
    } //methods that get the health, damage, speed level and cash for past save files 

    int getDamageLevel(String playerName) {
        File leaderboardFile = new File("Leaderboard.txt");
        Scanner findPlayerFile = null;
        try {
            findPlayerFile = new Scanner(leaderboardFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        findPlayerFile.nextLine(); //skips the first line
        while (findPlayerFile.hasNextLine()) {
            String check = findPlayerFile.next();
            if (check.contains(playerName)) {
                findPlayerFile.next();
                findPlayerFile.next();
                findPlayerFile.next();
                int damageLevel = findPlayerFile.nextInt();
                return (damageLevel);
            }
        }
        return (0);
    }

    int getSpeedLevel(String playerName) {
        File leaderboardFile = new File("Leaderboard.txt");
        Scanner findPlayerFile = null;
        try {
            findPlayerFile = new Scanner(leaderboardFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        findPlayerFile.nextLine(); //skips the first line
        while (findPlayerFile.hasNextLine()) {
            String check = findPlayerFile.next();
            if (check.contains(playerName)) {
                findPlayerFile.next();
                findPlayerFile.next();
                findPlayerFile.next();
                findPlayerFile.next();
                findPlayerFile.next();
                int speedLevel = findPlayerFile.nextInt();
                return (speedLevel);
            }
        }
        return (0);
    }

    public void updateHighScore(int Score, String playerName, int healthLevel, int damageLevel, int speedLevel, int cash) {
        File leaderboardFile = new File("Leaderboard.txt"); //gets the file
        Scanner readAllData = null; //creates a scanner for the file
        String updatedFileOutput = ""; //string that contains whats gonna go into the file
        String check = ""; //string to hold a check to see if the scanner is reading the current players file
        try {
            readAllData = new Scanner(leaderboardFile); //gets the file for the scanner
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (readAllData.hasNextLine()) { //while there are still lines to read 
            check = readAllData.nextLine(); //check string reads the line
            if (check.contains(String.valueOf(playerName))) { //if statement to check if that line has the players name
                //if so the line is updated with the new stats
                updatedFileOutput += (Score + " - " + playerName + " , " + healthLevel + " , " + damageLevel + " , " + speedLevel + " , " + cash + " , " + System.lineSeparator());
            } else { //otherwise, the line is updated with the stats it just read
                updatedFileOutput += check + System.lineSeparator();
               
            }
        }
        PrintWriter fileOut = null; //Define print writer
        try {
            fileOut = new PrintWriter(new java.io.FileWriter(leaderboardFile)); //Get the file for the print writer,
        } catch (IOException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        fileOut.print(updatedFileOutput); //prints to the file
        updatedFileOutput = ""; //clears the updated file output
        fileOut.close(); //closes print writer
        sortHighScores(); //calls the sort high scores method
    } //method to update the high score of a returning player
    //method that updates high scores of save files  
}
