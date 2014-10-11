package com.main;

import java.io.IOException;

/**
 * Created by crashh on 22-07-2014.
 */
public class Counter implements Runnable {
    private Thread counter;
    private boolean running;

    public Counter() {
        counter = new Thread(this);
    }

    public void start() {
        running = true;
        counter.start();
    }

    //Forces the run method to terminate, ensuring a proper cleanup.
    public void terminate() {
        running = false;
        GUI.hours=0;
        GUI.minutes=0;
        GUI.seconds=0;
    }

    public void run() {
        while (running) {
            while (GUI.hours >= 0) {
                while (GUI.minutes >= 0) {
                    while (GUI.seconds > 0) {
                        GUI.seconds--;
                        Main.window.updateTimer();
                        try {
                            counter.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    GUI.minutes--;
                    GUI.seconds=59;
                }
                GUI.hours--;
                GUI.minutes=59;
            }
            if(!running) //No systemcall if running == false for some reason.
                break;
            try {
                Process newChild = Runtime.getRuntime().exec("shutdown -s");
            } catch (IOException e) {
                e.printStackTrace();
            }
            running=false;
        }
        counter.interrupt();
        Main.window.dispose();
    }
}
