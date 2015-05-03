package org.edwinh.ivytale;

import org.edwinh.ivytale.scenes.GameScene;
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


    @Override
    public void init(GameContainer gc) throws SlickException {
        currentScene = new GameScene();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.scale((float) (gc.getWidth() / Config.renderWidth), (float) (gc.getHeight() / Config.renderHeight));
        currentScene.render(gc, g);

    }


    @Override
    public void update(GameContainer gc, int dt) throws SlickException {
        currentScene.update(gc, dt);
    }

}
