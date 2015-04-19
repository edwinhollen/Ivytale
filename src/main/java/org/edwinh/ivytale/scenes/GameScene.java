package org.edwinh.ivytale.scenes;

import org.edwinh.ivytale.components.Component;
import org.edwinh.ivytale.components.ImageComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.edwinh.ivytale.entities.Entity;
import org.edwinh.ivytale.systems.ImageSystem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by Fubar on 4/18/2015.
 */
public class GameScene extends Scene {

    public GameScene(){
        for(int i = 0; i < 20; i++){
            Entity imgEntity = new Entity();
            imgEntity.components = new Component[]{new ImageComponent("test.png"), new PositionComponent(100*i, 100*i)};
            entities.add(imgEntity);
        }
        for(int i = 0; i < 20; i++){
            Entity imgEntity = new Entity();
            imgEntity.components = new Component[]{new PositionComponent(100*i, 100*i)};
            entities.add(imgEntity);
        }
        this.systems.add(new ImageSystem());
    }
    @Override
    public void update(GameContainer gc, int dt) {
        super.update(gc, dt);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
    }
}
