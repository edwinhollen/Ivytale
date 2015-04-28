package org.edwinh.ivytale;

import org.edwinh.ivytale.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * Created by Fubar on 4/19/2015.
 */
public class Entity {
    public ArrayList<Component> components = new ArrayList<>();
    public ArrayList<Class> getComponentsAsClasses(){
        return components.stream().map(Component::getClass).collect(Collectors.toCollection(ArrayList::new));
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
