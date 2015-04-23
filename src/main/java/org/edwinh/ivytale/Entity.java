package org.edwinh.ivytale;

import org.edwinh.ivytale.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Fubar on 4/19/2015.
 */
public class Entity {
    public ArrayList<Component> components = new ArrayList<>();
    public ArrayList<Class> getComponentsAsClasses(){
        ArrayList<Class> classes = new ArrayList<>();
        for(Component c : components){
            classes.add(c.getClass());
        }
        return classes;
    }
    public Component getComponentByClass(Class needle){
        for(Component c : components){
            if(needle.isInstance(c)){
                return c;
            }
        }
        return null;
    }
    public void removeComponentByClass(Class needle){
        for(Iterator<Component> iterator = components.iterator(); iterator.hasNext();){
            Component c = iterator.next();
            if(needle.isInstance(c)){
                iterator.remove();
                break;
            }
        }
    }

    public void addComponent(Component component){
        this.components.add(component);
    }
}
