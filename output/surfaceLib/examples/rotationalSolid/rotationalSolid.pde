import processing.opengl.*;

import surface.*;

RotationalSolid s;
Slider[] sliders;

void setup(){
  size(600,600,OPENGL);
  textFont(loadFont("dialog.vlw"));
  float[] points = new float[20];
  for(int i=0; i<20; i++){
    points[i] = noise(i, frameCount);
  }
  s=new RotationalSolid(g, 20, points);
 
  sliders=new Slider[2];
  sliders[0]=new Slider(20,50,100,10,20,4,55, "vertical Resolution");//Slider of vertical Resolution
  sliders[1]=new Slider(20,90,100,10,20,4,55, "horizontal Resolution");//Slider of horizontal Resolution
  
  
}


void draw(){
   background(255);
  
  lights();
  boolean moved=false;
  for(int i=0;i<sliders.length;i++){
    fill(200);
    stroke(100);
    sliders[i].update();
    sliders[i].draw();
    if(sliders[i].moved)moved=true;
  }
  if(moved){
    int vResolution=floor(sliders[0].getPos());
    int hResolution=floor(sliders[1].getPos());
    
    float[] points = new float[hResolution];
    for(int i=0; i<hResolution; i++){
      points[i] = noise(i/10f, frameCount);
    }
    s=new RotationalSolid(g, vResolution,points);
  }
  
  translate(400,250);
 
  noStroke();
  fill(#A6D3EA);
  rotateX(radians(frameCount));
  rotateY(radians(frameCount));
  rotateZ(radians(frameCount));
   s.setScale(mouseX);
  s.draw();
}
