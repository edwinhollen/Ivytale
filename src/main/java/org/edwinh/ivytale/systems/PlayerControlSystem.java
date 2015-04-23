package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Config;
import org.edwinh.ivytale.components.PhysicsComponent;
import org.edwinh.ivytale.components.PlayerControlComponent;
import org.edwinh.ivytale.components.PlayerStatsComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.edwinh.ivytale.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by Fubar on 4/20/2015.
 */
public class PlayerControlSystem extends EntitySystem {
    @Override
    public void update(Entity[] entities, GameContainer gc, int dt) {
        for(Entity e : entities){
            if(gc.getInput().isKeyDown(Config.control_walkLeft)){
                ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class)).velocityX = ((PlayerStatsComponent) e.getComponentByClass(PlayerStatsComponent.class)).moveSpeed * -1;
            }else if(gc.getInput().isKeyDown(Config.control_walkRight)){
                ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class)).velocityX = ((PlayerStatsComponent) e.getComponentByClass(PlayerStatsComponent.class)).moveSpeed;
            }else{
                ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class)).velocityX = 0;
            }
        }
    }

    @Override
    public void render(Entity[] entities, GameContainer gc, Graphics g) {

    }

    @Override
    public Class[] getAcceptedComponents() {
        return new Class[]{
                PlayerControlComponent.class,
                PhysicsComponent.class,
                PositionComponent.class,
                PlayerStatsComponent.class
        };
    }

    public enum Action{
        WALK_LEFT,
        WALK_RIGHT,
        ATTACK
    }
}
