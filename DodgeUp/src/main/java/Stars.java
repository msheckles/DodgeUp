import processing.core.PApplet;
import processing.core.PImage;

public class Stars extends PApplet implements ObjectInterface{
    public int X;
    public int Y;
    private PImage image;

    private static PApplet p = Main.processing;

    public Stars(int X, int Y, PImage image) {
        this.X = X;
        this.Y = Y;
        this.image = image;
    }

    public void makeStars(){
        for (int i=0;i<20;i++){
            Main.stars.add(new Stars(X+120,Y-100,Main.point));
            Y-=300;
            Main.stars.add(new Stars(X+200,Y-100,Main.point));
            Y-=150;
            Main.stars.add(new Stars(X+400,Y-100,Main.point));
            Y-=400;
        }
    }

    public static  void removeStars() {
        for (int i = 0; i < Main.blocks.size(); i++) {
            if (Main.blocks.get(i).Y > 690) {
                Main.removed++;
                Main.blocks.remove(i);
            }
        }
    }
    public static void moveStar (){
        for (Stars star : Main.stars) {
            star.setY(star.getY() + 2);
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
