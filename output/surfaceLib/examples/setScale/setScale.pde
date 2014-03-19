import processing.opengl.*;

import surface.*;

Sphere s;
Slider[] sliders;

void setup(){
  size(400,300,OPENGL);
  textFont(loadFont("dialog.vlw"));
  s=new Sphere(g, 40, 40);
  s.setScale(50);
  
  sliders=new Slider[3];
  sliders[0]=new Slider(20,50,100,10,20,1,100, "Xscale");
  sliders[1]=new Slider(20,90,100,10,20,1,100, "Yscale");
  sliders[2]=new Slider(20,130,100,10,20,1,100, "Zscale");
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
    float xScale=sliders[0].getPos();
    float yScale=sliders[1].getPos();
    float zScale=sliders[2].getPos();
    s.setScale(xScale,yScale,zScale);
  }
  translate(250,150);
  noStroke();
fill(#A6D3EA);
  rotateX(radians(frameCount));
  rotateY(radians(frameCount));
  rotateZ(radians(frameCount));
  s.draw();
}
