package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.components.ImageComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.edwinh.ivytale.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created by Fubar on 4/19/2015.
 */
public class ImageSystem extends EntitySystem {


    @Override
    public void update(Entity[] entities, GameContainer gc, int dt) {

    }

    @Override
    public void render(Entity[] entities, GameContainer gc, Graphics g) {

    }

    @Override
    public Class[] getAcceptedComponents() {
        return new Class[]{
                ImageComponent.class,
                PositionComponent.class
        };
    }
}
