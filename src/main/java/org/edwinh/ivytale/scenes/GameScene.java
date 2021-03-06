package org.edwinh.ivytale.scenes;

import org.edwinh.ivytale.Level;
import org.edwinh.ivytale.Scene;
import org.edwinh.ivytale.components.*;
import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.systems.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Fubar on 4/18/2015.
 */
public class GameScene extends Scene {

    public GameScene(){
        Entity playerCharacter = new Entity();
        playerCharacter.components.add(new PlayerStatsComponent());
        playerCharacter.components.add(new PlayerControlComponent());
        playerCharacter.components.add(new PhysicsComponent(new Rectangle(0, 0, 22, 37)));
        playerCharacter.components.add(new PositionComponent(20, 0));
        playerCharacter.components.add(new AnimationComponent("character_stand_left"));
        //playerCharacter.components.add(new ImageComponent("character-idle-shaded.png", new Color(0, 255, 0), new Color(255, 230, 209), true));

        Level level = new Level("test");
        this.entities.addAll(level.entities);

        this.entities.add(playerCharacter);

        this.systems.add(new PhysicsSystem(level.gravity));
        this.systems.add(new PlayerControlSystem());
        this.systems.add(new CameraSystem());
        this.systems.add(new UserInterfaceSystem());
        this.systems.add(new AnimationSystem());

    }
    @Override
    public void update(GameContainer gc, double dt) {
        super.update(gc, dt);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
    }
}
