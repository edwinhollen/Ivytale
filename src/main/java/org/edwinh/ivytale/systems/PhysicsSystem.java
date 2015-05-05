package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.PhysicsComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Fubar on 5/5/2015.
 */
public class PhysicsSystem extends EntitySystem {
    public double gravity = 1.0;

    public PhysicsSystem(double gravity){
        this.gravity = gravity;
        this.acceptedComponents.addAll(Arrays.asList(
            PhysicsComponent.class,
            PositionComponent.class
        ));
    }

    @Override
    public void update(ArrayList<Entity> entities, GameContainer gc, int dt) {
        for(Entity e : entities){
            PhysicsComponent phys = ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class));
            PositionComponent pos = ((PositionComponent) e.getComponentByClass(PositionComponent.class));

            if(phys.fixed) continue;

            Point current = new Point(
                (float) pos.x,
                (float) pos.y
            );
            Point destination = new Point(
                (float) Math.round((pos.x + phys.velocityX)),
                (float) Math.round((pos.y + phys.velocityY))
            );
            while(!(current.getX() == destination.getX() && current.getY() == destination.getY())){
                Point potentialX = new Point(current.getX() + (current.getX() == destination.getX() ? 0 : (current.getX() < destination.getX() ? 1 : -1)), current.getY());
                Rectangle potentialXRect = new Rectangle(
                    potentialX.getX()+phys.hitbox.getX(),
                    potentialX.getY()+phys.hitbox.getY(),
                    phys.hitbox.getWidth(),
                    phys.hitbox.getHeight()
                );
                // check for collisions with new x
                for(Entity otherEntity : entities){
                    if(otherEntity.id.equals(e.id)) continue;
                    PhysicsComponent otherPhys = ((PhysicsComponent) otherEntity.getComponentByClass(PhysicsComponent.class));
                    PositionComponent otherPos = ((PositionComponent) otherEntity.getComponentByClass(PositionComponent.class));
                    Rectangle otherRect = new Rectangle(
                        (float) (otherPos.x+otherPhys.hitbox.getX()),
                        (float) (otherPos.y+otherPhys.hitbox.getY()),
                        otherPhys.hitbox.getWidth(),
                        otherPhys.hitbox.getHeight()
                    );
                    if(potentialXRect.intersects(otherRect)){
                        potentialX = current;
                        destination.setX(potentialX.getX());
                        phys.velocityX = 0;
                        break;
                    }
                }
                current.setX(potentialX.getX());

                Point potentialY = new Point(current.getX(), current.getY() + (current.getY() == destination.getY() ? 0 : (current.getY() < destination.getY() ? 1 : -1)));
                Rectangle potentialYRect = new Rectangle(
                        potentialY.getX()+phys.hitbox.getX(),
                        potentialY.getY()+phys.hitbox.getY(),
                        phys.hitbox.getWidth(),
                        phys.hitbox.getHeight()
                );
                // check for collisions with new y
                for(Entity otherEntity : entities){
                    if(otherEntity.id.equals(e.id)) continue;
                    PhysicsComponent otherPhys = ((PhysicsComponent) otherEntity.getComponentByClass(PhysicsComponent.class));
                    PositionComponent otherPos = ((PositionComponent) otherEntity.getComponentByClass(PositionComponent.class));
                    Rectangle otherRect = new Rectangle(
                            (float) (otherPos.x+otherPhys.hitbox.getX()),
                            (float) (otherPos.y+otherPhys.hitbox.getY()),
                            otherPhys.hitbox.getWidth(),
                            otherPhys.hitbox.getHeight()
                    );
                    if(potentialYRect.intersects(otherRect)){
                        potentialY = current;
                        destination.setY(potentialY.getY());
                        phys.velocityY = 0;
                        break;
                    }
                }
                current.setX(potentialX.getX());
                current.setY(potentialY.getY());
            }

            pos.x = current.getX();
            pos.y = current.getY();

            phys.velocityY += ((gravity / 100) * dt);
        }
    }

    @Override
    public void render(ArrayList<Entity> entities, GameContainer gc, Graphics g) {

    }
}
