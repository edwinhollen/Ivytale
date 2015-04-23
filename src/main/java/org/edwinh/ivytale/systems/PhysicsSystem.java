package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.PhysicsComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.edwinh.ivytale.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by Fubar on 4/20/2015.
 */
public class PhysicsSystem extends EntitySystem {
    @Override
    public void update(Entity[] entities, GameContainer gc, int dt) {
        for(Entity e : entities){
            ((PositionComponent) e.getComponentByClass(PositionComponent.class)).x += ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class)).velocityX * dt;
            ((PositionComponent) e.getComponentByClass(PositionComponent.class)).y += ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class)).velocityY * dt;
        }
    }

    @Override
    public void render(Entity[] entities, GameContainer gc, Graphics g) {

    }

    @Override
    public Class[] getAcceptedComponents() {
        return new Class[]{
                PositionComponent.class,
                PhysicsComponent.class
        };
    }
}
