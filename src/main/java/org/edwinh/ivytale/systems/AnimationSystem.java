package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.AnimationComponent;
import org.edwinh.ivytale.components.ImageComponent;
import org.edwinh.ivytale.components.PositionComponent;
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
        this.acceptedComponents.addAll(Arrays.asList(
            AnimationComponent.class,
            PositionComponent.class
        ));
    }

    @Override
    public void update(ArrayList<Entity> entities, GameContainer gc, int dt) {
        for(Entity e : entities){
            long now = Instant.now().toEpochMilli();
            AnimationComponent ac = ((AnimationComponent) e.getComponentByClass(AnimationComponent.class));
            if((ac.looping || ac.currentFrame >= 0) && now >= ac.lastFrame + ac.delay){
                ac.currentFrame++;
                if(ac.currentFrame >= ac.frames.size()){
                    ac.currentFrame = 0;
                }
                ac.lastFrame = now;
            }
        }
    }

    @Override
    public void render(ArrayList<Entity> entities, GameContainer gc, Graphics g) {
        for(Entity e : entities){
            AnimationComponent ac = ((AnimationComponent) e.getComponentByClass(AnimationComponent.class));
            PositionComponent pc = ((PositionComponent) e.getComponentByClass(PositionComponent.class));
            g.drawImage(ac.frames.get(ac.currentFrame).image, (float) pc.x, (float) pc.y);
        }
    }
}
