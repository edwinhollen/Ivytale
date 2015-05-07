package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Config;
import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.PlayerControlComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Fubar on 5/7/2015.
 */
public class CameraSystem extends EntitySystem{
    private static double fx = 0;
    private static double fy = 0;
    public static double x = fx;
    public static double y = fy;
    public static double speed = 0.035f;

    public CameraSystem(){
        this.acceptedComponents.addAll(Arrays.asList(
            PositionComponent.class,
            PlayerControlComponent.class
        ));
    }

    @Override
    public void update(ArrayList<Entity> entities, GameContainer gc, double dt) {
        for(Entity e : entities){
            PositionComponent pos = (PositionComponent) e.getComponentByClass(PositionComponent.class);

            int sourceX = (int) Math.round(fx);
            int sourceY = (int) Math.round(fy);
            int targetX = (int) Math.round(-pos.x+Config.renderWidth/2);
            int targetY = (int) Math.round(-pos.y+Config.renderHeight/2);

            double distance = Math.round(Math.sqrt(Math.pow((targetX-sourceX),2)+Math.pow(targetY-sourceY, 2)));

            fx += (Math.round(fx) == targetX) ? 0 : (Math.round(fx) < targetX) ? speed*distance: -speed*distance;
            fy += (Math.round(fy) == targetY) ? 0 : (Math.round(fy) < targetY) ? speed*distance : -speed*distance;

            x = Math.round(fx);
            y = Math.round(fy);
            break;
        }
    }

    @Override
    public void render(ArrayList<Entity> entities, GameContainer gc, Graphics g) {

    }
}
