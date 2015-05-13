package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Config;
import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.UIComponent;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by fubar on 5/13/15.
 */
public class UserInterfaceSystem extends EntitySystem{
    TrueTypeFont font_regular;
    public UserInterfaceSystem(){
        this.acceptedComponents.addAll(Arrays.asList(
                UIComponent.class
        ));
        // load fonts
        try {
            font_regular = new TrueTypeFont(Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("OpenSans-Regular.ttf")).deriveFont(12f), false);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ArrayList<Entity> entities, GameContainer gc, double dt) {
        
    }

    @Override
    public void render(ArrayList<Entity> entities, GameContainer gc, Graphics g) {
        if(Config.active.debug){
            g.setColor(new Color(0, 0, 0, 0.5f));
            g.fillRect(20, 20, 120, 120);
            font_regular.drawString(24, 24, String.format("%s fps", gc.getFPS()), Color.white);
        }
    }
}
