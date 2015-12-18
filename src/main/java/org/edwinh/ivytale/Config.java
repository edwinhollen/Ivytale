package org.edwinh.ivytale;

import com.google.gson.Gson;

import java.io.*;

/**
 * Created by fubar on 5/12/15.
 */
public class Config {
    public static final int RENDER_WIDTH = 400;
    public static final int RENDER_HEIGHT = 300;
    public static final String TITLE = "Ivytale";
    public static final String VERSION = "0.0";
    public static Settings active;

    public static void clean(){
        File f = getConfigFile();
        if(f.exists()){
            try{
                boolean deleted = f.delete();
                if (deleted) System.out.println("Cleaned up config file");
                if (!deleted) System.out.print("Failed to clean up user config file... ");
            }catch(SecurityException e){
                System.out.print("is it read-only?");
            }
        }else{
            System.out.println("User config file not found, continuing...");
        }
        System.out.println();
    }
    public static void load(){
        File f = getConfigFile();
        if(!f.exists()) makeNewConfigFile();

        // read config
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            active = (new Gson()).fromJson(reader, Settings.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static File getConfigFile(){
        return new File(System.getProperty("user.home") + "/.ivytale/config.json");
    }
    public static void makeNewConfigFile(){
        System.out.println("Making a fresh new config file...");
        getConfigFile().getParentFile().mkdirs();
        try {
            FileWriter writer = new FileWriter(getConfigFile());
            writer.write((new Gson()).toJson(new Settings()));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Settings{
        public int resolutionX = 800;
        public int resolutionY = 600;
        public boolean vsync = false;
        public boolean debug = true;
        public boolean fullscreen = false;
        public int moveLeft = 203;
        public int moveRight = 205;
        public int jump = 56;
        public int attack = 29;
    }
}
