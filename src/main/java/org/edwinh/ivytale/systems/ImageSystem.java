package org.edwinh.ivytale.systems;

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
    public void update(ArrayList<Entity> entities, GameContainer gc, double dt) {

    }

    @Override
    public void render(ArrayList<Entity> entities, GameContainer gc, Graphics g) {
        for(Entity e : entities){
            PositionComponent pos = (PositionComponent) e.getComponentByClass(PositionComponent.class);
            ImageComponent img = (ImageComponent) e.getComponentByClass(ImageComponent.class);
            g.drawImage(
                img.image,
                (float) (img.locked ? 0 : CameraSystem.x + pos.x),
                (float) (img.locked ? 0 : CameraSystem.y + pos.y)
            );
        }
    }
}
