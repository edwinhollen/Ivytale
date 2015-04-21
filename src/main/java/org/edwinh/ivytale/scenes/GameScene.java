package org.edwinh.ivytale.scenes;

import org.edwinh.ivytale.components.*;
import org.edwinh.ivytale.entities.Entity;
import org.edwinh.ivytale.systems.ImageSystem;
import org.edwinh.ivytale.systems.PhysicsSystem;
import org.edwinh.ivytale.systems.PlayerControlSystem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by Fubar on 4/18/2015.
 */
public class GameScene extends Scene {

    public GameScene(){
        for(int i = 0; i < 20; i++){
            Entity imgEntity = new Entity();
            imgEntity.components = new Component[]{
                    new ImageComponent("test.png"),
                    new PositionComponent(100*i, 100*i),
                    new PhysicsComponent(),
                    new PlayerControlComponent(),
                    new PlayerStatsComponent()
            };
            entities.add(imgEntity);
        }
        this.systems.add(new ImageSystem());
        this.systems.add(new PhysicsSystem());
        this.systems.add(new PlayerControlSystem());
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
