package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.AnimationComponent;
import org.edwinh.ivytale.components.ImageComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by fubar on 4/23/15.
 */
public class AnimationSystem extends EntitySystem {
    public AnimationSystem(){
        this.acceptedComponents.add(ImageComponent.class);
        this.acceptedComponents.add(AnimationComponent.class);
    }

    @Override
    public void update(ArrayList<Entity> entities, GameContainer gc, int dt) {
        for(Entity e : entities){
            System.out.println("anim entity");
            long now = Instant.now().getEpochSecond();

        }
    }

    @Override
    public void render(ArrayList<Entity> entities, GameContainer gc, Graphics g) {

    }
}
