package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;

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

    public PhysicsComponent(){

    }

    public PhysicsComponent(int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY) {
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
        this.hitboxOffsetX = hitboxOffsetX;
        this.hitboxOffsetY = hitboxOffsetY;
    }

    public PhysicsComponent(int hitboxWidth, int hitboxHeight) {
        this(hitboxWidth, hitboxHeight, 0, 0);
    }
}
