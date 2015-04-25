package org.edwinh.ivytale;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created by Fubar on 4/18/2015.
 *
 */
public abstract class EntitySystem {
    public abstract void update(ArrayList<Entity> entities, GameContainer gc, int dt);
    public abstract void render(ArrayList<Entity> entities, GameContainer gc, Graphics g);
    public ArrayList<Class> acceptedComponents = new ArrayList<>();
}
