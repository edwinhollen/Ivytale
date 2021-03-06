package org.edwinh.ivytale;

import org.newdawn.slick.SlickException;

import java.util.Arrays;

/**
 * Created by Fubar on 4/18/2015.
 */
public class IvyTale {
    public static void main(String[] args) {
        if(Arrays.asList(args).contains("--clean-config")) {
            Config.clean();
        }
        Config.load();

        try {
            new Game();
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }
}
