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
        super(Config.TITLE);

        appgc = new AppGameContainer(new ScalableGame(this, Config.RENDER_WIDTH, Config.RENDER_HEIGHT, true));
        appgc.setDisplayMode(Config.active.resolutionX, Config.active.resolutionY, false);
        appgc.setVSync(Config.active.vsync);
        appgc.setForceExit(false);
        appgc.setShowFPS(false);
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
        if(Config.active.showFPS){
            g.setColor(Color.black);
            g.drawString(String.format("%s fps", gc.getFPS()), 20, 21);
            g.setColor(Color.white);
            g.drawString(String.format("%s fps", gc.getFPS()), 20, 20);
        }
    }

    @Override
    public boolean closeRequested() {
        return true;
    }

    @Override
    public String getTitle() {
        return String.format(
                "%s (%s)",
                Config.TITLE,
                Config.VERSION
        );
    }


    @Override
    public void update(GameContainer gc, int dt) throws SlickException {
        currentScene.update(gc, (double) dt);
    }

}
