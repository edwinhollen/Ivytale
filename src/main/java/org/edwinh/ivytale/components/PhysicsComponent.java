package org.edwinh.ivytale.components;

/**
 * Created by Fubar on 4/20/2015.
 */
public class PhysicsComponent extends Component{
    public double velocityX = 0;
    public double velocityY = 0;
    public Hitbox hitbox = new Hitbox();

    public static class Hitbox{
        public int width = 0;
        public int height = 0;
        public int offsetX = 0;
        public int offsetY = 0;
        public Hitbox(){};
        public Hitbox(int width, int height){
            this.width = width;
            this.height = height;
        }
        public Hitbox(int width, int height, int offsetX, int offsetY){
            this(width, height);
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }
    }
}
