package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.components.ImageComponent;
import org.edwinh.ivytale.components.RecoloringComponent;
import org.edwinh.ivytale.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by fubar on 4/21/15.
 */
public class RecoloringSystem extends EntitySystem {
    @Override
    public void update(Entity[] entities, GameContainer gc, int dt) {

    }

    @Override
    public void render(Entity[] entities, GameContainer gc, Graphics g) {
        for(Entity e : entities){
            if(!((RecoloringComponent) e.getComponentByClass(RecoloringComponent.class)).done){
                Image image = ((ImageComponent) e.getComponentByClass(ImageComponent.class)).image;
                
            }
            ((RecoloringComponent) e.getComponentByClass(RecoloringComponent.class)).done = true;
        }
    }

    @Override
    public Class[] getAcceptedComponents() {
        return new Class[]{
                ImageComponent.class,
                RecoloringComponent.class
        };
    }
}
