package org.edwinh.ivytale;

import org.edwinh.ivytale.scenes.GameScene;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.awt.Font;
import java.io.IOException;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {
    private Scene currentScene;
    private AppGameContainer appgc;

    private TrueTypeFont font_regular;

    public Game() throws SlickException {
        super(Config.TITLE);

        appgc = new AppGameContainer(this);
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
