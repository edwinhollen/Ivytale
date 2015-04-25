package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.ImageComponent;
import org.edwinh.ivytale.components.RecoloringComponent;
import org.edwinh.ivytale.Entity;
import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by fubar on 4/21/15.
 */
public class RecoloringSystem extends EntitySystem {
    public RecoloringSystem(){
        this.acceptedComponents.addAll(Arrays.asList(
            ImageComponent.class,
            RecoloringComponent.class
        ));
    }

    @Override
    public void update(ArrayList<Entity> entities, GameContainer gc, int dt) {

    }

    @Override
    public void render(ArrayList<Entity> entities, GameContainer gc, Graphics g) {
        for(Entity e : entities){
            RecoloringComponent recoloringComponent = ((RecoloringComponent) e.getComponentByClass(RecoloringComponent.class));
            ImageComponent imageComponent = ((ImageComponent) e.getComponentByClass(ImageComponent.class));
            if(!recoloringComponent.done){
                for(int x = 0; x < imageComponent.image.getWidth(); x++){
                    for(int y = 0; y < imageComponent.image.getHeight(); y++){
                        Color pixel = imageComponent.image.getColor(x, y);
                        if(pixel.equals(recoloringComponent.keyColor)){
                            try {
                                imageComponent.image.getGraphics().setColor(recoloringComponent.fillColor);
                                imageComponent.image.getGraphics().fillRect((float) x, (float) y, 1, 1);
                            } catch (SlickException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
                try {
                    imageComponent.image.getGraphics().flush();
                } catch (SlickException e1) {
                    e1.printStackTrace();
                }
            }
            ((RecoloringComponent) e.getComponentByClass(RecoloringComponent.class)).done = true;
        }
    }
}
