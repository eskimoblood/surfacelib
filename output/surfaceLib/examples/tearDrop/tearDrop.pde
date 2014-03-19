import processing.opengl.*;

import surface.*;

TearDrop s;
Slider[] sliders;

void setup(){
  size(400,300,OPENGL);
  textFont(loadFont("dialog.vlw"));
  s=new TearDrop(g, 20, 20);
  sliders=new Slider[2];
  sliders[0]=new Slider(20,50,100,10,20,4,55, "vertical Resolution");
  sliders[1]=new Slider(20,90,100,10,20,4,55, "horizontal Resolution");
  smooth();
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
    s=new TearDrop(g, vResolution, hResolution);
  }
  
  translate(250,150);
  rotateX(radians(frameCount));
  rotateY(radians(frameCount));
  rotateZ(radians(frameCount));
  s.setScale(90);
  
  noStroke();
  fill(#A6D3EA);
  
  s.draw();
}
