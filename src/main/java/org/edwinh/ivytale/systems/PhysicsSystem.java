package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Config;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.PhysicsComponent;
import org.edwinh.ivytale.components.PositionComponent;
import org.edwinh.ivytale.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.GeomUtil;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Fubar on 4/20/2015.
 */
public class PhysicsSystem extends EntitySystem {
    public double gravity = 1.0;
    public Quadtree quadtree = new Quadtree(0, new Rectangle(0, 0, Config.renderWidth, Config.renderHeight));

    public PhysicsSystem(){
        this(1.0);
    }

    public PhysicsSystem(double gravity){
        this.gravity = gravity;
        this.acceptedComponents.add(PositionComponent.class);
        this.acceptedComponents.add(PhysicsComponent.class);
    }

    @Override
    public void update(ArrayList<Entity> entities, GameContainer gc, int dt) {

        // add items to quadtree
        //quadtree.clear();
        for(Entity e : entities){
            PositionComponent pos = (PositionComponent) e.getComponentByClass(PositionComponent.class);
            PhysicsComponent phys = ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class));

            // apply gravity
            if(!phys.fixed){
                phys.velocityY += (this.gravity / 100) * dt;
            }

            Rectangle eCorrectedHitbox = new Rectangle(
                (float) (pos.x + phys.hitbox.getX() + phys.velocityX),
                (float) (pos.y + phys.hitbox.getY() + phys.velocityY),
                phys.hitbox.getWidth(),
                phys.hitbox.getHeight()
            );



            for(Entity otherEntity : entities){
                if(otherEntity == e) continue;
                PositionComponent otherPos = (PositionComponent) otherEntity.getComponentByClass(PositionComponent.class);
                PhysicsComponent otherPhys = ((PhysicsComponent) otherEntity.getComponentByClass(PhysicsComponent.class));
                if(!otherPhys.fixed) continue;
                Rectangle otherCorrectedHitbox = new Rectangle(
                        (float) (otherPos.x + otherPhys.hitbox.getX() + otherPhys.velocityX),
                        (float) (otherPos.y + otherPhys.hitbox.getY() + otherPhys.velocityY),
                        otherPhys.hitbox.getWidth(),
                        otherPhys.hitbox.getHeight()
                );
                if(eCorrectedHitbox.intersects(otherCorrectedHitbox)){
                    if(eCorrectedHitbox.getCenterY() < otherCorrectedHitbox.getCenterY()){
                        phys.velocityY = 0;
                    }
                }
            }

            pos.x += phys.velocityX;
            pos.y += phys.velocityY;

            /*
            quadtree.insert(new Rectangle(
                    (float) pos.x + phys.hitboxOffsetX,
                    (float) pos.y + phys.hitboxOffsetY,
                    phys.hitboxWidth,
                    phys.hitboxHeight
            ));
            */
        }
        /*
        List<Rectangle> returnObjects = new ArrayList<>();
        for(Entity e : entities){
            PositionComponent pos = (PositionComponent) e.getComponentByClass(PositionComponent.class);
            PhysicsComponent phys = ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class));
            returnObjects.clear();
            quadtree.retrieve(returnObjects, new Rectangle(
                    (float) pos.x + phys.hitboxOffsetX,
                    (float) pos.y + phys.hitboxOffsetY,
                    phys.hitboxWidth,
                    phys.hitboxHeight
            ));
        }
        */
    }

    @Override
    public void render(ArrayList<Entity> entities, GameContainer gc, Graphics g) {
        for(Entity e : entities){
            PositionComponent pos = (PositionComponent) e.getComponentByClass(PositionComponent.class);
            PhysicsComponent phys = ((PhysicsComponent) e.getComponentByClass(PhysicsComponent.class));
            g.setColor(Color.red);
            g.fillRect((int) pos.x + phys.hitbox.getX(), (int) pos.y + phys.hitbox.getY(), phys.hitbox.getWidth(), phys.hitbox.getHeight());
        }
    }

    public class Quadtree{
        /*
        http://gamedevelopment.tutsplus.com/tutorials/quick-tip-use-quadtrees-to-detect-likely-collisions-in-2d-space--gamedev-374
         */
        private static final int MAX_OBJECTS = 10;
        private static final int MAX_LEVELS = 5;

        private int level;
        private List<Rectangle> objects;
        private Rectangle bounds;
        private Quadtree[] nodes;

        public Quadtree(int pLevel, Rectangle pBounds){
            level = pLevel;
            objects = new ArrayList<Rectangle>();
            bounds = pBounds;
            nodes = new Quadtree[4];
        }

        public void clear(){
            objects.clear();

            for(int i = 0; i < nodes.length; i++){
                if(nodes[i] == null) continue;
                nodes[i].clear();
                nodes[i] = null;
            }
        }

        public void split(){
            int subWidth = (int) (bounds.getWidth() / 2);
            int subHeight = (int) (bounds.getHeight() / 2);
            int x = (int) bounds.getX();
            int y = (int) bounds.getY();

            nodes[0] = new Quadtree(level+1, new Rectangle(x + subWidth, y, subWidth, subHeight));
            nodes[1] = new Quadtree(level+1, new Rectangle(x, y, subWidth, subHeight));
            nodes[2] = new Quadtree(level+1, new Rectangle(x, y + subHeight, subWidth, subHeight));
            nodes[3] = new Quadtree(level+1, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));
        }

        private int getIndex(Rectangle pRect){
            int index = -1;
            double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
            double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);

            // obj can completely fit in top quadrants
            boolean topQuadrant = (pRect.getY() < horizontalMidpoint && pRect.getY() + pRect.getHeight() < horizontalMidpoint);
            // obj can completely fit in bottom quadrants
            boolean bottomQuadrant = (pRect.getY() > horizontalMidpoint);

            if (pRect.getX() < verticalMidpoint && pRect.getX() + pRect.getWidth() < verticalMidpoint) {
                // obj can completely fit in left quadrants
                if (topQuadrant) {
                    index = 1;
                }
                else if (bottomQuadrant) {
                    index = 2;
                }
            }else if(pRect.getX() > verticalMidpoint){
                // obj can completely fit in right quadrants
                if(topQuadrant){
                    index = 0;
                }else if(bottomQuadrant){
                    index = 3;
                }
            }
            return index;
        }
        public void insert(Rectangle pRect){
            if(nodes[0] != null){
                int index = getIndex(pRect);
                if(index != -1){
                    nodes[index].insert(pRect);
                    return;
                }
            }

            objects.add(pRect);

            if(objects.size() > MAX_OBJECTS && level < MAX_LEVELS){
                if(nodes[0] == null) split();

                int i = 0;
                while(i < objects.size()){
                    int index = getIndex(objects.get(i));
                    if(index != -1){
                        nodes[index].insert(objects.remove(i));
                    }else{
                        i++;
                    }
                }
            }
        }
        public List retrieve(List<Rectangle> returnObjects, Rectangle pRect){
            int index = getIndex(pRect);
            if(index != -1 && nodes[0] != null){
                nodes[index].retrieve(returnObjects, pRect);
            }

            returnObjects.addAll(objects);
            return returnObjects;
        }
    }

}
