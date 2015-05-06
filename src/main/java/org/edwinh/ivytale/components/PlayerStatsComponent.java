package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;
import org.newdawn.slick.Color;

/**
 * Created by Fubar on 4/20/2015.
 */
public class PlayerStatsComponent extends Component {
    public Color skinColor = new Color(255, 220, 184);
    public double moveSpeed = 8;
    public double jumpHeight = 20;
    public double jumpCooldown = 650;
    public long lastJump = 0;
}
