package org.edwinh.ivytale;

import org.edwinh.ivytale.scenes.GameScene;
import org.edwinh.ivytale.scenes.Scene;
import org.newdawn.slick.*;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {
    public Config config;

    private Scene currentScene;

    public Game() throws SlickException {
        super("Ivytale");
        Config.load();
        AppGameContainer appgc = new AppGameContainer(this);
        appgc.setDisplayMode(Config.screenWidth, Config.screenHeight, false);
        appgc.setVSync(Config.vsync);
        appgc.setForceExit(false);
        appgc.setShowFPS(Config.show_fps);
        appgc.start();


    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.scale((float) (Config.screenWidth / Config.renderWidth), (float) (Config.screenHeight / Config.renderHeight));
        currentScene.render(gc, g);

    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        currentScene = new GameScene();
    }

    @Override
    public void update(GameContainer gc, int dt) throws SlickException {
        currentScene.update(gc, dt);
    }

}
