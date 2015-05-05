package org.edwinh.ivytale.components;
import org.edwinh.ivytale.Component;
import org.edwinh.ivytale.Entity;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fubar on 4/20/2015.
 */
public class PhysicsComponent extends Component {
    public double velocityX = 0;
    public double velocityY = 0;
    public boolean fixed = false;
    public Rectangle hitbox;

    public PhysicsComponent(Rectangle hitbox, boolean fixed){
        this.hitbox = hitbox;
        this.fixed = fixed;
    }

    public PhysicsComponent(Rectangle hitbox) {
        this(hitbox, false);
    }
}
