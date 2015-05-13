package org.edwinh.ivytale;

import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.EntitySystem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RunnableFuture;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Created by Fubar on 4/18/2015.
 */
public abstract class Scene {
    public ArrayList<EntitySystem> systems = new ArrayList<>();
    public ArrayList<Entity> entities = new ArrayList<>();
    public HashMap<EntitySystem, ArrayList<Entity>> organizedEntities = new HashMap<>();

    public void update(GameContainer gc, double dt){
        // organize entities
        organizedEntities.clear();
        for(EntitySystem s : systems){
            organizedEntities.put(s, entities.stream().filter(e -> e.getComponentsAsClasses().containsAll(s.acceptedComponents)).collect(Collectors.toCollection(ArrayList::new)));
        }

        // update
        organizedEntities.forEach((entitySystem, entities) -> entitySystem.update(entities, gc, dt));

    }
    public void render(GameContainer gc, Graphics g){
        // render
        organizedEntities.forEach((entitySystem, entities) -> entitySystem.render(entities, gc, g));
    }
}
