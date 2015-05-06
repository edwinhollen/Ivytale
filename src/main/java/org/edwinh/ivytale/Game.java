package org.edwinh.ivytale;

import org.edwinh.ivytale.scenes.GameScene;
import org.newdawn.slick.*;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {
    private Scene currentScene;
    private AppGameContainer appgc;

    public Game() throws SlickException {
        super(Config.title);

        appgc = new AppGameContainer(new ScalableGame(this, Config.renderWidth, Config.renderHeight, false));
        appgc.setDisplayMode(Config.screenWidth, Config.screenHeight, false);
        appgc.setVSync(Config.vsync);
        appgc.setForceExit(false);
        appgc.setShowFPS(Config.show_fps);
        appgc.setUpdateOnlyWhenVisible(false);
        appgc.setMinimumLogicUpdateInterval(8);
        appgc.start();
    }


    @Override
    public void init(GameContainer gc) throws SlickException {
        currentScene = new GameScene();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        currentScene.render(gc, g);
    }

    @Override
    public boolean closeRequested() {
        return true;
    }

    @Override
    public String getTitle() {
        return String.format(
                "%s (%s)",
                Config.title,
                Config.version
        );
    }


    @Override
    public void update(GameContainer gc, int dt) throws SlickException {
        //System.out.println((double) dt);
        currentScene.update(gc, (double) dt);
    }

}
