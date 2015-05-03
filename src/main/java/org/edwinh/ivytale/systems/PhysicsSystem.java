package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.PhysicsComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.edwinh.ivytale.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created by Fubar on 4/20/2015.
 */
public class PhysicsSystem extends EntitySystem {
    public double gravity = 1.0;

    public PhysicsSystem(){
        this(1.0);
    }

    public PhysicsSystem(double gravity){
        this.gravity = gravity;
        this.acceptedComponents.add(PositionComponent.class);
        this.acceptedComponents.add(PhysicsComponent.class);
    }

    @Override
    public void update(ArrayList<Entity> entities, GameContainer gc, int dt) {
        for(Entity e : entities){
            ((PositionComponent) e.getComponentByClass(PositionComponent.class)).x += ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class)).velocityX * dt;
            ((PositionComponent) e.getComponentByClass(PositionComponent.class)).y += ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class)).velocityY * dt;
        }
    }

    @Override
    public void render(ArrayList<Entity> entities, GameContainer gc, Graphics g) {

    }

}
