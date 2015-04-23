package org.edwinh.ivytale.scenes;

import org.edwinh.ivytale.components.*;
import org.edwinh.ivytale.entities.Entity;
import org.edwinh.ivytale.systems.ImageSystem;
import org.edwinh.ivytale.systems.PhysicsSystem;
import org.edwinh.ivytale.systems.PlayerControlSystem;
import org.edwinh.ivytale.systems.RecoloringSystem;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by Fubar on 4/18/2015.
 */
public class GameScene extends Scene {

    public GameScene(){
        Entity playerCharacter = new Entity();
        playerCharacter.components = new Component[]{
                new PlayerStatsComponent(),
                new PlayerControlComponent(),
                new PhysicsComponent(),
                new ImageComponent("character-idle.png"),
                new RecoloringComponent(new Color(0, 255, 0), new Color(255, 216, 191)),
                new PositionComponent(20, 20)
        };

        this.entities.add(playerCharacter);

        this.systems.add(new ImageSystem());
        this.systems.add(new PhysicsSystem());
        this.systems.add(new PlayerControlSystem());
        this.systems.add(new RecoloringSystem());
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
