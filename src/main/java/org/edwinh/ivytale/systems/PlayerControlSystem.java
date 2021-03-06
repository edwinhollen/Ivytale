package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Config;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.*;
import org.edwinh.ivytale.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Fubar on 4/20/2015.
 */
public class PlayerControlSystem extends EntitySystem {
    public Action lastAction = Action.STAND_LEFT;

    public PlayerControlSystem(){
        this.acceptedComponents.addAll(Arrays.asList(
            PlayerControlComponent.class,
            PhysicsComponent.class,
            PositionComponent.class,
            PlayerStatsComponent.class
        ));
    }

    @Override
    public void update(ArrayList<Entity> entities, GameContainer gc, double dt) {
        for(Entity e : entities){
            PhysicsComponent phys = ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class));
            AnimationComponent anim = ((AnimationComponent) e.getComponentByClass(AnimationComponent.class));
            PlayerStatsComponent stats = ((PlayerStatsComponent) e.getComponentByClass(PlayerStatsComponent.class));

            double adjustedMoveSpeed = stats.moveSpeed * 0.065f;
            double adjustedJumpHeight = stats.jumpHeight * 0.15f;

            if(gc.getInput().isKeyDown(Config.active.jump) && phys.velocityY == 0){
                phys.velocityY -= (adjustedJumpHeight);
            }

            if(gc.getInput().isKeyDown(Config.active.moveRight)) {
                lastAction = Action.WALK_RIGHT;
                phys.velocityX = adjustedMoveSpeed;
                if(!anim.name.equals("character_walk_right")){
                    anim.change("character_walk_right");
                }
            }else if(gc.getInput().isKeyDown(Config.active.moveLeft)) {
                lastAction = Action.WALK_LEFT;
                phys.velocityX = adjustedMoveSpeed * -1;
                if(!anim.name.equals("character_walk_left")){
                    anim.change("character_walk_left");
                }
            }else{
                phys.velocityX = 0;
                if(lastAction == Action.WALK_LEFT){
                    lastAction = Action.STAND_LEFT;
                    if(!anim.name.equals("character_stand_left")){
                        anim.change("character_stand_left");
                    }
                }else if(lastAction == Action.WALK_RIGHT){
                    lastAction = Action.STAND_RIGHT;
                    if(!anim.name.equals("character_stand_right")){
                        anim.change("character_stand_right");
                    }
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
        ATTACK,
        STAND_LEFT,
        STAND_RIGHT,
        JUMP_LEFT,
        JUMP_RIGHT
    }
}
