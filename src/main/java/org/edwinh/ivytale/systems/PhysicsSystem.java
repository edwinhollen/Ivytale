package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.PhysicsComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.awt.*;

import java.awt.geom.Rectangle2D;
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
    public void update(ArrayList<Entity> entities, GameContainer gc, double dt) {
        //for(int i = 0; i < dt; i++){
        dt = dt * 1.5f;
            for(Entity e : entities){
                PhysicsComponent phys = ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class));
                PositionComponent pos = ((PositionComponent) e.getComponentByClass(PositionComponent.class));

                if(phys.fixed) continue;

                Point.Double current = new Point.Double(
                        Math.round(pos.x),
                        Math.round(pos.y)
                );
                Point.Double destination = new Point.Double(
                        Math.round(current.getX() + (phys.velocityX * dt)),
                        Math.round(current.getY() + (phys.velocityY * dt))
                );

                boolean applyGravity = true;
                while(!(current.getX() == destination.getX() && current.getY() == destination.getY())){
                    Point.Double potentialX = new Point.Double(
                            current.getX() + (current.getX() == destination.getX() ? 0 : (current.getX() < destination.getX() ? 1 : -1)),
                            current.getY()
                    );
                    Rectangle2D.Double potentialXRect = new Rectangle2D.Double(
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
                        Rectangle2D.Double otherRect = new Rectangle2D.Double(
                                otherPos.x+otherPhys.hitbox.getX(),
                                otherPos.y+otherPhys.hitbox.getY(),
                                otherPhys.hitbox.getWidth(),
                                otherPhys.hitbox.getHeight()
                        );
                        if(potentialXRect.intersects(otherRect)){
                            potentialX = current;
                            destination = new Point.Double(potentialX.getX(), destination.getY());
                            phys.velocityX = 0;
                            break;
                        }
                    }
                    current = new Point.Double(potentialX.getX(), current.getY());

                    Point.Double potentialY = new Point.Double(
                            current.getX(),
                            current.getY() + (current.getY() == destination.getY() ? 0 : (current.getY() < destination.getY() ? 1 : -1))
                    );
                    Rectangle2D.Double potentialYRect = new Rectangle2D.Double(
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
                        Rectangle2D.Double otherRect = new Rectangle2D.Double(
                                otherPos.x+otherPhys.hitbox.getX(),
                                otherPos.y+otherPhys.hitbox.getY(),
                                otherPhys.hitbox.getWidth(),
                                otherPhys.hitbox.getHeight()
                        );
                        if(potentialYRect.intersects(otherRect)){
                            potentialY = current;
                            destination = new Point.Double(destination.getX(), potentialY.getY());
                            phys.velocityY = 0;
                            applyGravity = false;
                            break;
                        }
                    }
                    current = new Point.Double(potentialX.getX(), potentialY.getY());
                }

                pos.x = current.getX();
                pos.y = current.getY();

                if(applyGravity) phys.velocityY += (gravity * dt * 0.0005f);
            }
        //}
    }

    @Override
    public void render(ArrayList<Entity> entities, GameContainer gc, Graphics g) {

    }
}
