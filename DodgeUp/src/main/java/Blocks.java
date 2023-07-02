import processing.core.PApplet;
import processing.core.PImage;

public class Blocks extends PApplet implements ObjectInterface {

    public int X;
    public int Y;
    private PImage image;

    private static PApplet p = Main.processing;

    public Blocks(int X, int Y, PImage image) {
        this.X = X;
        this.Y = Y;
        this.image = image;
    }

    public void makeBlocks(){
        for (int i=0;i<30;i++){

            if(i%6==0) {
                Main.blocks.add(new Blocks(X+60,Y-100,Main.blockImage));
                Y-=100;
            }
            else{
                Main.blocks.add(new Blocks(X+60,Y-100,Main.blockImage));
                Y-=100;
            }
            if (i%4==0){
                Main.blocks.add(new Blocks(X+205,Y-100,Main.blockImage));
                Y-=100;
            }
            else{
                Main.blocks.add(new Blocks(X+205,Y-100,Main.blockImage));
                Y-=100;
            }

            if(i%2==0)
                X+=70;
            else X-=70;
        }

    }

    public static void moveBlock ( int speed){
        for (Blocks block : Main.blocks) {
            block.setY(block.getY() + 2);
        }
    }

    public static  void removeBlock() {
        for (int i = 0; i < Main.blocks.size(); i++) {
            if (Main.blocks.get(i).Y > 690) {
                Main.removed++;
                Main.blocks.remove(i);
            }

        }
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        this.X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public PImage getImage() {
        return image;
    }

    public void setImage(PImage image) {
        this.image = image;
    }
}
