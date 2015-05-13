package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Component;
import org.edwinh.ivytale.Config;
import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.AnimationComponent;
import org.edwinh.ivytale.components.ImageComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.json.JSONException;
import org.json.JSONObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.ResourceLoader;

import java.time.Instant;
import java.util.*;

/**
 * Created by fubar on 4/23/15.
 */
public class AnimationSystem extends EntitySystem {
    private Map<String, Animation> animations = new HashMap<>();

    public AnimationSystem(){
        this.acceptedComponents.addAll(Arrays.asList(
            AnimationComponent.class,
            PositionComponent.class
        ));
        // load all animations
        JSONObject animationsJson = new JSONObject(new Scanner(ResourceLoader.getResourceAsStream("animations.json"), "UTF-8").useDelimiter("\\A").next());
        for(Iterator<String> iterator = animationsJson.keys(); iterator.hasNext();){
            try {
                String name = iterator.next();
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
                        ic = new ImageComponent(animationObject.getJSONArray("frames").getString(i), keyColor, fillColor, animationObject.optBoolean("shading", false));
                    }catch(JSONException e){
                        ic = new ImageComponent(animationObject.getJSONArray("frames").getString(i));
                    }
                    ic.flipHorizontal = animationObject.optBoolean("flip_horizontal", false);
                    ic.flipVertical = animationObject.optBoolean("flip_vertical", false);
                    frames.add(ic);
                }
                this.animations.put(name, new Animation(frames, animationObject.optInt("delay", 120), animationObject.optBoolean("loop", true)));
            }catch(JSONException e){
                e.printStackTrace();
                System.out.println("Couldn't add animation");
            }
        }

    }

    @Override
    public void update(ArrayList<Entity> entities, GameContainer gc, double dt) {
        for(Entity e : entities){
            long now = Instant.now().toEpochMilli();
            AnimationComponent ac = ((AnimationComponent) e.getComponentByClass(AnimationComponent.class));
            Animation a = this.animations.get(ac.name);
            if(now >= ac.lastFrame + a.delay){
                ac.currentFrame++;
                if(ac.currentFrame >= a.frames.size()){
                    ac.currentFrame = 0;
                }
                ac.lastFrame = now;
            }
        }
    }

    @Override
    public void render(ArrayList<Entity> entities, GameContainer gc, Graphics g) {
        g.scale(Config.active.resolutionX/Config.RENDER_WIDTH, Config.active.resolutionY/Config.RENDER_HEIGHT);
        for(Entity e : entities){
            PositionComponent pc = ((PositionComponent) e.getComponentByClass(PositionComponent.class));
            AnimationComponent ac = ((AnimationComponent) e.getComponentByClass(AnimationComponent.class));
            Animation a = this.animations.get(ac.name);
            a.frames.get(ac.currentFrame).getImage().draw((float) ((ac.locked ? 0 : CameraSystem.x) + pc.x), (float) ((ac.locked ? 0 : CameraSystem.y) + pc.y));
        }
    }

    public static class Animation extends Component {
        public int delay = 100;
        public boolean looping = true;
        public List<ImageComponent> frames = new ArrayList<>();
        public Animation(List<ImageComponent> frames, int delay, boolean looping){
            this.frames = frames;
            this.delay = delay;
            this.looping = looping;
        }
    }
}
