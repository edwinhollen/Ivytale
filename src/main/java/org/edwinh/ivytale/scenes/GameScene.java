package org.edwinh.ivytale.scenes;

import org.edwinh.ivytale.Component;
import org.edwinh.ivytale.components.*;
import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.systems.ImageSystem;
import org.edwinh.ivytale.systems.PhysicsSystem;
import org.edwinh.ivytale.systems.PlayerControlSystem;
import org.edwinh.ivytale.systems.RecoloringSystem;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Fubar on 4/18/2015.
 */
public class GameScene extends Scene {

    public GameScene(){
        Entity playerCharacter = new Entity();
        playerCharacter.components.add(new PlayerStatsComponent());
        playerCharacter.components.add(new PlayerControlComponent());
        playerCharacter.components.add(new PhysicsComponent());
        playerCharacter.components.add(new ImageComponent());
        playerCharacter.components.add(new AnimationComponent(new AnimationFrame[]{
            new AnimationFrame("character-idle.png", 500),
            new AnimationFrame("character-idle.png", 2000),
            new AnimationFrame("character-idle.png", 2000),
            new AnimationFrame("character-idle.png", 500)
        }));
        playerCharacter.components.add(new RecoloringComponent(new Color(0, 255, 0), new Color(255, 216, 191)));
        playerCharacter.components.add(new PositionComponent(20, 20));

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
