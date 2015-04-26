package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.AnimationComponent;
import org.edwinh.ivytale.components.ImageComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    public static AnimationComponent loadAnimation(String name){
        JSONObject animationsJson = null;
        animationsJson = new JSONObject(new Scanner(ClassLoader.class.getResourceAsStream("/animations.json"), "UTF-8").useDelimiter("\\A").next());
        try {
            JSONObject animationObject = animationsJson.getJSONObject(name);
            List<ImageComponent> frames = new ArrayList<>();
            for(int i = 0; i < animationObject.getJSONArray("frames").length(); i++){
                ImageComponent ic;
                try{
                    Color keyColor = new Color(
                            animationObject.getJSONArray("keyColor").getInt(0),
                            animationObject.getJSONArray("keyColor").getInt(1),
                            animationObject.getJSONArray("keyColor").getInt(2)
                    );
                    Color fillColor = new Color(
                        animationObject.getJSONArray("fillColor").getInt(0),
                        animationObject.getJSONArray("fillColor").getInt(1),
                        animationObject.getJSONArray("fillColor").getInt(2)
                    );
                    ic = new ImageComponent(animationObject.getJSONArray("frames").getString(i), keyColor, fillColor);
                }catch(JSONException e){
                    ic = new ImageComponent(animationObject.getJSONArray("frames").getString(i));
                }
                frames.add(ic);
            }
            return new AnimationComponent(frames, animationObject.getInt("delay"), animationObject.optBoolean("delay", true));
        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
