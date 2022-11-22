package Main;


import java.awt.Color;
import java.awt.Graphics;

public class Tile {

        public int x,y;


        public Tile(int x, int y) {
                this.x = x;
                this.y = y;
        }

        public void tick() {
                if(x - Camera.x < -32) {
                        World.tiles.remove(this);
                        return;
                }
        }

        public void render(Graphics g) {

                g.drawImage(Game.floorSprite, x - Camera.x, y - Camera.y,World.blockSize,World.blockSize,null);

        }


}

