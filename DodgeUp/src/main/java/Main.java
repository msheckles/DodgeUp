import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;

public class Main extends PApplet {
    public static PApplet processing;
    public static PApplet pApplet;

    public static int scoreNumber;

    private PImage mainBackground;
    private PImage gameBackground1;
    private PImage gameBackground2;
    private PImage gameBackground3;
    public static PImage blockImage;
    public static PImage point;
    public static PImage alien;

    static int heart;
    static int removed =0;

    Color color = new Color(255, 255, 255, 255);

    public static ArrayList<Blocks> blocks = new ArrayList<>();
    public static ArrayList<Stars> stars = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("Main",args);
    }

    @Override
    public void setup() {
        processing = this;
        pApplet = this;

        mainBackground = loadImage("mainBackground.jpg");
        gameBackground1 = loadImage("gameBackground1.jpg");
        gameBackground2 = loadImage("gameBackground2.jpg");
        gameBackground3 = loadImage("gameBackground3.jpg");
        blockImage = loadImage("block.png");
        point = loadImage("point.png");
        alien = loadImage("alien.png");

        Blocks block = new Blocks(0,0,null);
        block.makeBlocks();

        Stars star = new Stars(0,0,null);
        star.makeStars();

        heart = 3;
        scoreNumber = 0;
    }

    @Override
    public void settings() {
        size(400,700);
    }

    boolean start = true;
    boolean game = false;
    boolean won = false;
    boolean lost = false;
    boolean setting = false;
    boolean help = false;
    boolean exit = false;
    boolean wallPaper = true;

    @Override
    public void draw() {
        if (start) {
            background(gameBackground3);

            textSize(60);
            text("Start",140,220);
            if (mouseX>95 && mouseX<320 && mouseY>310 && mouseY<350) {
                fill(0,0,255);
            } else {
                fill(255);
            }
            text("exit",500,300);

            textSize(60);
            text("Setting",115,370);
            if (mouseX>135 && mouseX<260 && mouseY>470 && mouseY<530) {
                fill(0,0,255);
            } else {
                fill(255);
            }
            text("exit",500,300);

            textSize(60);
            text("Help",145,520);
            if (mouseX>135 && mouseX<275 && mouseY>170 && mouseY<230) {
                fill(0,0,255);
            } else {
                fill(255);
            }
            text("exit",500,300);

            if (mousePressed) {
                if (mouseX>135 && mouseX<275 && mouseY>170 && mouseY<230) {
                    start = false;
                    game = true;
                }
                if (mouseX>95 && mouseX<320 && mouseY>310 && mouseY<350) {
                    start = false;
                    setting = true;
                }
                if (mouseX>135 && mouseX<260 && mouseY>470 && mouseY<530) {
                    start = false;
                    help = true;
                }
            }
        }

        if (game) {
            if (wallPaper) {
                background(mainBackground);
            } else {
                background(gameBackground2);
            }

            fill(225,225,225);
            textSize(50);

            if (blocks.size()==0) {
                game = false;
                won = true;
            }

            for (Blocks block : blocks) {
                showBlocks(block.getImage(), block.getX(), block.getY());
            }
            Stars.moveStar();
            for (Stars star : stars) {
                showStars(star.getImage(), star.getX() , star.getY());
            }

//            Blocks.showBlock(blocks);
            Blocks.moveBlock(5);
            Blocks.removeBlock();

            int alienX = mouseX;
            image(alien,alienX,630);

            crashBlocks();
            crashStars();
        }

        if (lost) {
            background(gameBackground1);

            textSize(50);
            text("> GAME OVER <",45,210);

            textSize(40);
            text("score : "+ scoreNumber,130,310);

            text("replay",140,400);

            text("exit",160,500);

            if (mousePressed) {
                if (mouseX > 120 && mouseX < 230 && mouseY > 370 && mouseY < 450) {
                    blocks.clear();
                    stars.clear();
                    setup();
                    lost = false;
                    game = true;
                }
                if (mouseX > 150 && mouseX < 220 && mouseY > 480 && mouseY < 530) {
                    setup();
                    lost = false;
                    exit = true;
                }
            }
        }

        if (won) {
            background(gameBackground1);

            textSize(50);
            text(">> YOU WIN <<",45,210);

            textSize(40);
            text("score : "+ scoreNumber,130,310);

            text("replay",140,400);

            text("exit",160,500);

            if (mousePressed) {
                if (mouseX > 120 && mouseX < 230 && mouseY > 390 && mouseY < 450) {
                    setup();
                    won = false;
                    game = true;
                }
                if (mouseX > 150 && mouseX < 220 && mouseY > 480 && mouseY < 530) {
                    setup();
                    won = false;
                    exit = true;
                }
            }
        }

        if (setting) {
            if (wallPaper) {
                background(mainBackground);
            } else {
                background(gameBackground2);
            }

            fill(225);
            textSize(40);

            if (wallPaper) {
                fill(246, 141, 131);
                text( "Background 1",80,260);
                fill(225);
            } else {
                text( "Background 1",80,260);
                fill(246, 141, 131);
            }
            text( "Background 2",80,400);

            fill(0, 118, 168);
            textSize(30);
            text("back",175,600);

            if (mousePressed) {
                if (mouseX>65 && mouseX<350 && mouseY>230 && mouseY<300) {
                    wallPaper = true;
                }
                if (mouseX>65 && mouseX<350 && mouseY>370 && mouseY<440){
                    wallPaper = false;
                }
                if (mouseX>165 && mouseX<250 && mouseY>580 && mouseY<640) {
                    start = true;
                    setting = false;
                }
            }
        }

        if (help) {
            background(gameBackground2);

            fill(225);
            textSize(40);
            text("All you have to",80,150);
            text("do is to avoid",85,200);
            text("blocks and try",85,250);
            text("to collect stars",80,300);

            text("You got 3 chances",55,400);
            text("Good luck!",110,450);

            fill(0, 118, 168);
            textSize(30);
            text("back",175,600);
            if (mousePressed) {
                if (mouseX > 160 && mouseX < 270 && mouseY > 585 && mouseY < 620) {
                    help = false;
                    start = true;
                }
            }
        }

        if (exit) {
            exit();
        }
    }

    public void showBlocks(PImage image, int X, int Y) {
        image(image, X, Y);
    }

    public void showStars(PImage image, int X , int Y) {
        image(point,X, Y);
    }

    public void crashBlocks() {
        for (int i=0 ; i < blocks.size() ; i++) {
            if (mouseX - blocks.get(i).X < 15 && blocks.get(i).X - mouseX < 15 && blocks.get(i).Y > 600 && blocks.get(i).Y < 720) {
                blocks.remove(blocks.get(i));
                if (heart == 0 ) {
                    game = false;
                    lost = true;
                } else {
                    heart--;
                }
            }
        }
    }

    public void crashStars() {
        for (int i=0 ; i < blocks.size() ; i++) {
            if (mouseX - stars.get(i).X < 25 && stars.get(i).X - mouseX < 25 && stars.get(i).Y > 600 && stars.get(i).Y < 720) {
                stars.remove(stars.get(i));
                scoreNumber += 5;
            }
        }
    }
}