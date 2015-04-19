package org.edwinh.ivytale.entities;

import org.edwinh.ivytale.components.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Fubar on 4/19/2015.
 */
public class Entity {
    public Component[] components = new Component[]{};
    public Class[] getComponentsAsClasses(){
        ArrayList<Class> classes = new ArrayList<>();
        for(Component c : components){
            classes.add(c.getClass());
        }
        return classes.toArray(new Class[classes.size()]);
    }
}
