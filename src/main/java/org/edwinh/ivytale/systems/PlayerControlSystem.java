package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Config;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.*;
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
            PhysicsComponent phys = ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class));
            AnimationComponent anim = ((AnimationComponent) e.getComponentByClass(AnimationComponent.class));


            double adjustedMoveSpeed = (((PlayerStatsComponent) e.getComponentByClass(PlayerStatsComponent.class)).moveSpeed) / 10;
            if(gc.getInput().isKeyDown(Config.control_walkRight)) {
                phys.velocityX = adjustedMoveSpeed;
                if(!anim.name.equals("character_walk")){
                    anim.change("character_walk");
                }
            }else if(gc.getInput().isKeyDown(Config.control_walkLeft)) {
                phys.velocityX = adjustedMoveSpeed * -1;
                if(!anim.name.equals("character_walk")){
                    anim.change("character_walk");
                }
            }else{
                phys.velocityX = 0;
                if(!anim.name.equals("character_stand")){
                    anim.change("character_stand");
                }
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
