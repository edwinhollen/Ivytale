package org.edwinh.ivytale;

import org.newdawn.slick.SlickException;

import java.util.Arrays;

/**
 * Created by Fubar on 4/18/2015.
 */
public class Launcher {
    public static void main(String[] args) {
        if(Arrays.asList(args).contains("--clean-config")) {
            System.out.println("Cleaning config...");
            Config.load(true);
        }else{
            Config.load();
        }
        try {
            new Game();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
