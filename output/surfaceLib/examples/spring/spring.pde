import processing.opengl.*;

import surface.*;

Spring s;
Slider[] sliders;

void setup(){
  size(400,300,OPENGL);
  textFont(loadFont("dialog.vlw"));
  s=new Spring(g, 20, 20);
  sliders=new Slider[5];
  sliders[0]=new Slider(20,50,100,10,20,4,55, "vertical Resolution");
  sliders[1]=new Slider(20,90,100,10,20,4,55, "horizontal Resolution");
  sliders[2]=new Slider(20,130,100,10,20,0,1, "Radius 1");
  sliders[3]=new Slider(20,170,100,10,20,0,1, "Radius 2");
  sliders[4]=new Slider(20,210,100,10,20,0,5, "Period Length");
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
    float radius1=sliders[2].getPos();
    float radius2=sliders[3].getPos();
    float periodLength=sliders[4].getPos();
    s=new Spring(g, vResolution, hResolution,  radius1, radius2, periodLength);
  }
  
  translate(250,150);
  rotateX(radians(frameCount));
  rotateY(radians(frameCount));
  rotateZ(radians(frameCount));
  s.setScale(20);
  
  noStroke();
  fill(#A6D3EA);
  
  s.draw();
}
