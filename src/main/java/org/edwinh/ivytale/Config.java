package org.edwinh.ivytale;

import org.json.JSONObject;
import org.newdawn.slick.Input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

/**
 * Created by Fubar on 4/20/2015.
 */
public class Config {
    public static final String title = "Ivytale";
    public static final String version = "0.1";

    public static final String CONFIG_FILE_LOCATION = System.getProperty("user.home")+"/.ivytale/config.json";

    public static final int renderWidth = 400;
    public static final int renderHeight = 300;
    public static int screenWidth = 800;
    public static int screenHeight = 600;
    public static boolean vsync = true;
    public static boolean show_fps = false;
    public static int control_walkLeft = Input.KEY_LEFT;
    public static int control_walkRight = Input.KEY_RIGHT;
    public static int control_attack = Input.KEY_LCONTROL;

    public Config(){
        load();
    }

    public static void load(){
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
            File defaultConfig = new File(ClassLoader.class.getResource("/default_config.json").getFile());
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

    public static void parse(File f){
        try {
            JSONObject configObject = new JSONObject(new Scanner(f).useDelimiter("\\A").next());
            screenWidth = configObject.getInt("width");
            screenHeight = configObject.getInt("height");
            vsync = configObject.getBoolean("vsync");
            show_fps = configObject.getBoolean("show_fps");
            control_attack = getKeyCode(configObject.getString("attack"));
            control_walkLeft = getKeyCode(configObject.getString("walk_left"));
            control_walkRight = getKeyCode(configObject.getString("walk_right"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int getKeyCode(String keyName){
        try {
            return Integer.parseInt(Input.class.getField(keyName.toUpperCase()).get(Input.class).toString());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
