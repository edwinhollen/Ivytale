package org.edwinh.ivytale;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Created by Fubar on 4/20/2015.
 */
public class Config {
    public static final String CONFIG_FILE_LOCATION = System.getProperty("user.home")+"/.ivytale/config.txt";
    public Config(){
        load();
    }
    public static void parse(String str){

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
            File defaultConfig = new File(ClassLoader.class.getResource("/default_config.txt").getFile());
            System.out.println(defaultConfig.getAbsolutePath());
            try {
                System.out.println("Copying default config file...");
                Files.copy(defaultConfig.toPath(), new File(CONFIG_FILE_LOCATION).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
