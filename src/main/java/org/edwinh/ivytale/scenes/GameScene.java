package org.edwinh.ivytale.scenes;

import org.edwinh.ivytale.Scene;
import org.edwinh.ivytale.components.*;
import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.systems.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Created by Fubar on 4/18/2015.
 */
public class GameScene extends Scene {

    public GameScene(){
        Entity playerCharacter = new Entity();
        playerCharacter.components.add(new PlayerStatsComponent());
        playerCharacter.components.add(new PlayerControlComponent());
        playerCharacter.components.add(new PhysicsComponent());
        playerCharacter.components.add(new ImageComponent("character-idle.png"));
        playerCharacter.components.add(new RecoloringComponent(new Color(0, 255, 0), new Color(255, 216, 191)));
        playerCharacter.components.add(new PositionComponent(20, 20));
        /*
        playerCharacter.components.add(new AnimationComponent(new AnimationFrame[]{
            new AnimationFrame("character-idle.png", 500),
            new AnimationFrame("character-idle.png", 2000),
            new AnimationFrame("character-idle.png", 2000),
            new AnimationFrame("character-idle.png", 500)
        }));
        */

        this.entities.add(playerCharacter);

        this.systems.add(new ImageSystem());
        this.systems.add(new PhysicsSystem());
        this.systems.add(new PlayerControlSystem());
        this.systems.add(new RecoloringSystem());
        this.systems.add(new AnimationSystem());
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
