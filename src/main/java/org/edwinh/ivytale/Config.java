package org.edwinh.ivytale;

import org.newdawn.slick.Input;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Created by Fubar on 4/20/2015.
 */
public class Config {
    public static final String CONFIG_FILE_LOCATION = System.getProperty("user.home")+"/.ivytale/config.txt";

    public int screenWidth, screenHeight;
    public int control_walkLeft, control_walkRight, control_attack;

    public Config(){
        load();
    }

    public void load(){
        File f = new File(CONFIG_FILE_LOCATION);
        if(!f.exists()){
            // see if ~/.ivytale/ exists
            if(!new File(System.getProperty("user.home")+"/.ivytale/").exists()){
                try {
                    Files.createDirectory(new File(System.getProperty("user.home")+"/.ivytale/").toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // copy the default config
            File defaultConfig = new File(ClassLoader.class.getResource("/default_config.txt").getFile());
            System.out.println(defaultConfig.getAbsolutePath());
            try {
                System.out.println("Copying default config file...");
                Files.copy(defaultConfig.toPath(), new File(CONFIG_FILE_LOCATION).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        parse(f);
    }

    public void parse(File f){
        try {
            for(String l : Files.readAllLines(f.toPath())){
                String line = l.trim().toUpperCase();
                if(!(line.startsWith("#") || line.isEmpty())){
                    String key = line.split("=")[0];
                    String val = line.split("=")[1];
                    switch(key){
                        case "WIDTH":
                            screenWidth = Integer.parseInt(val);
                            break;
                        case "HEIGHT":
                            screenHeight = Integer.parseInt(val);
                            break;
                        case "WALK_LEFT":
                            control_walkLeft = getKeyCode(val);
                            break;
                        case "WALK_RIGHT":
                            control_walkRight = getKeyCode(val);
                            break;
                        case "ATTACK":
                            control_attack = getKeyCode(val);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getKeyCode(String keyName){
        try {
            return Integer.parseInt(Input.class.getField(keyName).get(Input.class).toString());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
