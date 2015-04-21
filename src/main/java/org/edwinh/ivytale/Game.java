package org.edwinh.ivytale;

import org.edwinh.ivytale.scenes.GameScene;
import org.edwinh.ivytale.scenes.MenuScene;
import org.edwinh.ivytale.scenes.Scene;
import org.edwinh.ivytale.systems.EntitySystem;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.io.File;
import java.util.ArrayList;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {
    public Config config;

    private AppGameContainer appgc;
    private ArrayList<EntitySystem> systems;
    private Scene currentScene;

    public Game() throws SlickException {
        super("Ivytale");
        config = new Config();
        appgc = new AppGameContainer(this);
        appgc.setDisplayMode(config.screenWidth, config.screenHeight, false);
        appgc.setVSync(config.vsync);
        appgc.setForceExit(false);
        appgc.start();
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
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
