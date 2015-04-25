package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Component;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.ImageComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.edwinh.ivytale.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Fubar on 4/19/2015.
 */
public class ImageSystem extends EntitySystem {
    public ImageSystem(){
        this.acceptedComponents.addAll(Arrays.asList(
            ImageComponent.class,
            PositionComponent.class
        ));
    }


    @Override
    public void update(ArrayList<Entity> entities, GameContainer gc, int dt) {

    }

    @Override
    public void render(ArrayList<Entity> entities, GameContainer gc, Graphics g) {
        for(Entity e : entities){
            g.drawImage(
                    ((ImageComponent) e.getComponentByClass(ImageComponent.class)).image,
                    (int) ((PositionComponent) e.getComponentByClass(PositionComponent.class)).x,
                    (int) ((PositionComponent) e.getComponentByClass(PositionComponent.class)).y
            );
        }
    }
}
