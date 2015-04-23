package org.edwinh.ivytale;

import org.edwinh.ivytale.Component;

import java.util.ArrayList;

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
    public Component getComponentByClass(Class needle){
        for(Component c : components){
            if(needle.isInstance(c)){
                return c;
            }
        }
        return null;
    }
}
