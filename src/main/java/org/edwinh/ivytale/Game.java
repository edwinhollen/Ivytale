package org.edwinh.ivytale;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {
    private AppGameContainer appgc;

    public Game() throws SlickException {
        super("A Slick2d game");
        appgc = new AppGameContainer(this);
        appgc.setDisplayMode(800, 600, false);
        appgc.setForceExit(false);
        appgc.start();
    }

    public void render(GameContainer container, Graphics g) throws SlickException {


    }

    @Override
    public void init(GameContainer container) throws SlickException {

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {

    }

}
