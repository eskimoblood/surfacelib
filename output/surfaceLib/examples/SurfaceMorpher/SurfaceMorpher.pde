import processing.opengl.*;
import surface.*;


Surface s;
SurfaceMorpher sm;

int resolution = 80;
int cnt;
float morphStep = 0;

void setup() {
  size(400, 300, OPENGL);
  smooth();
  s = new DoubleCone(g, resolution, resolution);
  sm = new SurfaceMorpher(s,s);
  noStroke();
}

void draw() {
  background(255);
  lights();

  translate(width / 2, height / 2);
  rotateX(radians(frameCount));
  rotateY(radians(frameCount));
  rotateZ(radians(frameCount));

  morphStep += 0.01f;
  morphStep = min(1, morphStep);
  if (morphStep <= 1) {
    println(morphStep);
    sm.morph(morphStep);
  }
  sm.setScale((mouseX));
  sm.draw();
}

void keyPressed() {
  println(cnt);
  cnt++;
  if (cnt > 14)
    cnt = 0;
  switch (cnt) {
  case 0:
    s = new DoubleCone(g, resolution, resolution);
    break;
  case 1:
    s = new EnnepersSurface(g, resolution, resolution);
    break;
  case 2:
    s = new FishSurface(g, resolution, resolution);
    break;
  case 3:
    s = new Horn(g, resolution, resolution);
    break;
  case 4:
    s = new JetSurface(g, resolution, resolution);
    break;
  case 5:
    s = new MoebiusStrip(g, resolution, resolution);
    break;
  case 6:
    s = new Pillow(g, resolution, resolution);
    break;
  case 7:
    s = new Shell(g, resolution, resolution);
    break;
  case 8:
    s = new SnailSurface(g, resolution, resolution);
    break;
  case 9:
    s = new Sphere(g, resolution, resolution);
    break;
  case 10:
    s = new Spring(g, resolution, resolution);
    break;
  case 11:
    s = new SuperShape(g, resolution, resolution);
    break;
  case 12:
    s = new TearDrop(g, resolution, resolution);
    break;
  case 13:
    s = new TetrahedralEllipse(g, resolution, resolution);
    break;
  case 14:
    s = new WhitneyUmbrella(g, resolution, resolution);
    break;
  }
  println(s);
  morphStep = 0;

  sm.morphTo(s);
}


