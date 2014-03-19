import processing.opengl.*;

import surface.*;


Surface s;
int[]  colors= { -23142912, -18415604, -18088052, -16813052, -16859606, -24200991, -19600897, -17435660};
int resolution=80;
int cnt;

void setup(){
  size(400,300,OPENGL);
  s=new DoubleCone(g,resolution,resolution);
  noStroke();
  smooth();
}

void draw(){
  background(#FFF9D1);
  lights();

  translate(width/2,height/2);
  rotateX(radians(frameCount));
  rotateY(radians(frameCount));
  rotateZ(radians(frameCount));
  s.setScale((mouseX));
  
  for(int i=0;i<resolution;i+=2){
    stroke(colors[(i/2)%colors.length]);
    s.drawVerticalLineStrip(i);
  }
}


void keyPressed(){
  cnt++;
  if(cnt>14)cnt=0;
  switch (cnt){
  case 0 : 
    s= new DoubleCone(g,resolution,resolution); 
    break;
  case 1 : 
    s= new EnnepersSurface(g,resolution,resolution); 
    break;
  case 2 : 
    s= new FishSurface(g,resolution,resolution); 
    break;
  case 3 : 
    s= new Horn(g,resolution,resolution); 
    break;
  case 4 : 
    s= new JetSurface(g,resolution,resolution); 
    break;
  case 5 : 
    s= new MoebiusStrip(g,resolution,resolution); 
    break;
  case 6 : 
    s= new Pillow(g,resolution,resolution); 
    break;
  case 7 : 
    s= new Shell(g,resolution,resolution); 
    break;
  case 8 : 
    s= new SnailSurface(g,resolution,resolution); 
    break;
  case 9 : 
    s= new Sphere(g,resolution,resolution); 
    break;
  case 10 : 
    s= new Spring(g,resolution,resolution); 
    break;
  case 11 : 
    s= new SuperShape(g,resolution,resolution); 
    break;
  case 12 : 
    s= new TearDrop(g,resolution,resolution); 
    break;
  case 13 : 
    s= new TetrahedralEllipse(g,resolution,resolution); 
    break;
  case 14 : 
    s= new WhitneyUmbrella(g,resolution,resolution); 
    break;
  }
}

