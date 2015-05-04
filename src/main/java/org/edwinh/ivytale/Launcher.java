package org.edwinh.ivytale;

import org.newdawn.slick.SlickException;

/**
 * Created by Fubar on 4/18/2015.
 */
public class Launcher {
    public static void main(String[] args) {
        Config.load();
        try {
            new Game();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
