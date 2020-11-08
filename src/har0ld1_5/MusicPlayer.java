/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package har0ld1_5;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;

/**
 *
 * @author Elias
 */
public class MusicPlayer {

    int songChoice = -1;
    boolean stop = false;
    Clip clip = null;

    public void pickSong(int songNum) {
        songChoice = songNum;
        playMusic(); 
    } //method that gets song choice 

    public void playMusic() {
        if (songChoice == 0) { //gets the song choice 
            clip.stop(); //stops the previously clip that was playing 
            try {
                File pathToFind = new File("music/mainGameMusic1.wav"); //gets a new song based on the song choice 
                if (pathToFind.exists()) {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(pathToFind);
                    clip = AudioSystem.getClip(); //plays the song
                    clip.open(audioInput);
                    clip.start(); //starts the song
                    clip.loop(Clip.LOOP_CONTINUOUSLY); //makes it loop endlessly 
                } else {
                    JOptionPane.showMessageDialog(null, "Can't find file!"); //other wise it tells the player that it can't find the file
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (songChoice == 1) {
            clip.stop();
            try {
                File pathToFind = new File("music/shopMusic.wav");
                if (pathToFind.exists()) {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(pathToFind);
                    clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    JOptionPane.showMessageDialog(null, "Can't find file!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                File pathToFind = new File("music/startMenuMusic.wav");
                if (pathToFind.exists()) {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(pathToFind);
                    clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                    clip.stop();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    JOptionPane.showMessageDialog(null, "Can't find file!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    } //method that plays music

}
