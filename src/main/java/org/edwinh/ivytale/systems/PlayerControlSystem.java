package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Config;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.PhysicsComponent;
import org.edwinh.ivytale.components.PlayerControlComponent;
import org.edwinh.ivytale.components.PlayerStatsComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.edwinh.ivytale.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Fubar on 4/20/2015.
 */
public class PlayerControlSystem extends EntitySystem {
    public PlayerControlSystem(){
        this.acceptedComponents.addAll(Arrays.asList(
            PlayerControlComponent.class,
            PhysicsComponent.class,
            PositionComponent.class,
            PlayerStatsComponent.class
        ));
    }

    @Override
    public void update(ArrayList<Entity> entities, GameContainer gc, int dt) {
        for(Entity e : entities){
            double adjustedMoveSpeed = (((PlayerStatsComponent) e.getComponentByClass(PlayerStatsComponent.class)).moveSpeed) / 10;
            if(gc.getInput().isKeyDown(Config.control_walkRight)) {
                ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class)).velocityX = adjustedMoveSpeed;
            }else if(gc.getInput().isKeyDown(Config.control_walkLeft)) {
                ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class)).velocityX = adjustedMoveSpeed * -1;
            }else{
                ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class)).velocityX = 0;
            }
        }
    }

    @Override
    public void render(ArrayList<Entity> entities, GameContainer gc, Graphics g) {

    }

    public enum Action{
        WALK_LEFT,
        WALK_RIGHT,
        ATTACK
    }
}
