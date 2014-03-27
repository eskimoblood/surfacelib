import processing.core.*;
import processing.core.PShape;
import surface.*;
import processing.opengl.PShader;
import peasy.*;

/**
 * Created by andreaskoberle on 24.03.14.
 */
public class Test extends PApplet {

    private Surface s;
    private PShape t;
    private PShader colorShader;
    PeasyCam cam;

    public void setup() {
        size(800, 800, OPENGL);
        s = new Sphere(this, 20, 20);
        s.setScale(1);
        fill(color(255, 125, 0));
//        s.initColors(color(255, 125, 0));
        t = s.getSurface();
        t.scale(width/3);

//        cam = new PeasyCam(this, 20);
//        cam.setMinimumDistance(-500);
//        cam.setMaximumDistance(500);

        colorShader = loadShader("colorfrag.glsl", "colorvert.glsl");
    }

    public void draw() {
        background(255);
        lights();
        pointLight(255, 255, 255, width / 2, height, 200);
        pointLight(255, 255, 255, width / 2, height / 2, 500);
        translate(300, 250);
        rotateX(radians(frameCount));
//        colorShader.set("smX", mouseX / 100f);
//        colorShader.set("smY", mouseY / 100f);
//        shader(colorShader);

        shape(t);

    }
}
