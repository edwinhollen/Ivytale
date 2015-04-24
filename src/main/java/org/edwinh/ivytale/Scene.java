package org.edwinh.ivytale;

import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.EntitySystem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Fubar on 4/18/2015.
 */
public abstract class Scene {
    public ArrayList<EntitySystem> systems = new ArrayList<>();
    public ArrayList<Entity> entities = new ArrayList<>();
    public void update(GameContainer gc, int dt){
        for(EntitySystem s : systems){
            ArrayList<Entity> relevantEntities = new ArrayList<>();
            for(Entity e : entities){
                if(e.getComponentsAsClasses().containsAll(s.acceptedComponents)){
                    relevantEntities.add(e);
                }
            }
            s.update(relevantEntities.toArray(new Entity[relevantEntities.size()]), gc, dt);
        }

    }
    public void render(GameContainer gc, Graphics g){
        for(EntitySystem s : systems){
            ArrayList<Entity> relevantEntities = new ArrayList<>();
            for(Entity e : entities){
                if(e.getComponentsAsClasses().containsAll(s.acceptedComponents)){
                    relevantEntities.add(e);
                }
            }
            s.render(relevantEntities.toArray(new Entity[relevantEntities.size()]), gc, g);
        }

    }
}