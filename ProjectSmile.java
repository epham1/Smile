/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsmile;

/**
 *
 * @author lamtk
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class ProjectSmile {
    public static void main(String[] args) {
        
        DateTimeFormatter dtf;
        DateTimeFormatter min;
        LocalDateTime now;
        ArrayList<String> messages = new ArrayList<String>();
        int mesIndex;
   
        String hr = JOptionPane.showInputDialog("Enter hours: ");
        String mins = JOptionPane.showInputDialog("Enter minutes: ");
        
        //scan in all quotes into ArrayLists
        try
        {
            
            Scanner fileScan = new Scanner( new File("data/messages.dat"));

            while( fileScan.hasNext() )
                messages.add(fileScan.nextLine());
            

        }
        catch(Exception e) {}
        
        //if before noon, display good morning message
        LocalDateTime initial = LocalDateTime.now();
        if(initial.isBefore(LocalDateTime.of(initial.getYear(),initial.getMonth() , initial.getDayOfMonth(), 22, 00)))
        {
            mesIndex = (int)(Math.random()*16);
            JOptionPane.showMessageDialog(null, messages.get(mesIndex), "Project Smile", JOptionPane.PLAIN_MESSAGE);
        }

        //display break message every hour and any set messages
        while(true)
        {
            dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            min = DateTimeFormatter.ofPattern("mm:ss");
            now = LocalDateTime.now();
            
            //set goodnight time and message
            if(dtf.format(now).equals(hr+":"+mins+":00"))
            {
                mesIndex = (int)(Math.random()*37+16);
                JOptionPane.showMessageDialog(null, messages.get(mesIndex), "Set a bedtime", JOptionPane.PLAIN_MESSAGE);
            }
            
            //take a break message
            if(dtf.format(now).equals("11:59:00"))
            {
                mesIndex = (int)(Math.random()*37+16);
                JOptionPane.showMessageDialog(null, messages.get(mesIndex), "Project Smile", JOptionPane.PLAIN_MESSAGE);
                sound();
            }
            
            
        }
    } 
    
    public static void sound()
    {
        InputStream music;
        
        int index = (int)(Math.random()*5+1);
        
        try
        {
            music = new FileInputStream(new File("instrumental/" + index + ".wav"));
            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);
        }
        catch(Exception e) {}
    }
}


