package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fubar on 4/25/2015.
 */
public class AnimationCollectionComponent extends Component {
    public int currentAnimation = 0;
    public Map<String, AnimationComponent> animations = new HashMap<>();
    public AnimationCollectionComponent(HashMap<String, AnimationComponent> animations){
        this.animations = animations;
    }
    public AnimationCollectionComponent add(String name, AnimationComponent animation){
        this.animations.put(name, animation);
        return this;
    }
}
