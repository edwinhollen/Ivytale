package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;
import org.newdawn.slick.Color;

/**
 * Created by Fubar on 4/20/2015.
 */
public class PlayerStatsComponent extends Component {
    public Color skinColor = new Color(255, 220, 184);
    public double moveSpeed = 1.0;
    public double jumpHeight = 1.0;
    public long lastJump = 0;
}
