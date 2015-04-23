package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;
import org.newdawn.slick.Color;

/**
 * Created by fubar on 4/21/15.
 */
public class RecoloringComponent extends Component {
    public Color keyColor = new Color(0, 255, 0);
    public Color fillColor = new Color(0, 0, 255);
    public boolean shading = true;
    public boolean done = false;
    public RecoloringComponent(Color keyColor, Color fillColor){
        this.keyColor = keyColor;
        this.fillColor = fillColor;
    }
    public RecoloringComponent(){}
}
