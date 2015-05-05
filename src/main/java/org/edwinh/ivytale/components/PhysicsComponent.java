package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;
import org.edwinh.ivytale.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fubar on 4/20/2015.
 */
public class PhysicsComponent extends Component {
    public double velocityX = 0;
    public double velocityY = 0;
    public int hitboxWidth = 0;
    public int hitboxHeight = 0;
    public int hitboxOffsetX = 0;
    public int hitboxOffsetY = 0;
    public boolean fixed = false;
    public List<Entity> collisions = new ArrayList<>();

    public PhysicsComponent(int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, boolean fixed){
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
        this.hitboxOffsetX = hitboxOffsetX;
        this.hitboxOffsetY = hitboxOffsetY;
        this.fixed = fixed;
    }

    public PhysicsComponent(int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY) {
        this(hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, false);
    }

    public PhysicsComponent(int hitboxWidth, int hitboxHeight) {
        this(hitboxWidth, hitboxHeight, 0, 0);
    }
}
