package com.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by crashh on 22-07-2014.
 */
public class GUI extends JFrame {
    private JPanel mainWindow;
    private JLabel title;
    private JButton incrementHour;
    private JButton incrementMinutes;
    private JButton reset;
    private JTextArea timer;
    private JButton begin;
    private JButton cancel;

    //should be private with getter/setters but im lazy atm.
    static public int seconds;
    static public int minutes;
    static public int hours;

    private Counter counter = new Counter();;

    public GUI() {
        minutes=0;
        hours=0;

        //needed swing stuff..
        setContentPane(mainWindow);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        //listeners..
        incrementHour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hours+=1;
                updateTimer();
            }
        });

        incrementMinutes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (minutes<=50){
                    minutes+=5;
                } else{
                    minutes=0;
                    hours+=1;
                }
                updateTimer();
            }
        });

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                minutes=0;
                hours=0;
                updateTimer();
            }
        });

        begin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                begin.setEnabled(false);
                //reset.setEnabled(false);
                cancel.setEnabled(true);
                counter.start();
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                counter.terminate();
            }
        });
    }

    public void updateTimer(){
        if (hours<10 && minutes<10 && seconds<10)
            timer.setText("0"+Integer.toString(hours)+":0"+Integer.toString(minutes)+":0"+Integer.toString(seconds));
        else if (hours<10 && minutes<10)
            timer.setText("0"+Integer.toString(hours)+":0"+Integer.toString(minutes)+":"+Integer.toString(seconds));
        else if (hours<10 && seconds<10)
            timer.setText("0"+Integer.toString(hours)+":"+Integer.toString(minutes)+":0"+Integer.toString(seconds));
        else if (minutes<10 && seconds<10)
            timer.setText(Integer.toString(hours)+":0"+Integer.toString(minutes)+":0"+Integer.toString(seconds));
        else if (hours<10)
            timer.setText("0"+Integer.toString(hours)+":"+Integer.toString(minutes)+":"+Integer.toString(seconds));
        else if (minutes<10)
            timer.setText(Integer.toString(hours)+":0"+Integer.toString(minutes)+":"+Integer.toString(seconds));
        else
            timer.setText(Integer.toString(hours)+":"+Integer.toString(minutes)+":"+Integer.toString(seconds));
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
