package org.edwinh.ivytale;

import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.EntitySystem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Fubar on 4/18/2015.
 */
public abstract class Scene {
    public ArrayList<EntitySystem> systems = new ArrayList<>();
    public ArrayList<Entity> entities = new ArrayList<>();
    public void update(GameContainer gc, int dt){
        for(EntitySystem s : systems){
            ArrayList<Entity> relevantEntities = entities.stream().filter(e -> e.getComponentsAsClasses().containsAll(s.acceptedComponents)).collect(Collectors.toCollection(ArrayList::new));
            s.update(relevantEntities, gc, dt);
        }

    }
    public void render(GameContainer gc, Graphics g){
        for(EntitySystem s : systems){
            ArrayList<Entity> relevantEntities = entities.stream().filter(e -> e.getComponentsAsClasses().containsAll(s.acceptedComponents)).collect(Collectors.toCollection(ArrayList::new));
            s.render(relevantEntities, gc, g);
        }

    }
}
