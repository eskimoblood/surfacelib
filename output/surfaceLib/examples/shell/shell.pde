import processing.opengl.*;

import surface.*;

Surface s;
Slider[] sliders;

void setup(){
  size(400,300,OPENGL);
  textFont(loadFont("dialog.vlw"));
  s=new Shell(g, 20, 20);
  sliders=new Slider[6];
  sliders[0]=new Slider(20,50,100,10,20,4,55, "vertical Resolution");
  sliders[1]=new Slider(20,90,100,10,20,4,55, "horizontal Resolution");
  sliders[2]=new Slider(20,130,100,10,20,0,10, "Number of spirals");
  sliders[3]=new Slider(20,170,100,10,20,0,1, "Width");
  sliders[4]=new Slider(20,210,100,10,20,0,10, "Height");
  sliders[5]=new Slider(20,250,100,10,20,0,10, "Inner radius");
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
    int numberOfSpirals=floor(sliders[2].getPos());
    float  finalRadius=sliders[3].getPos();
    float  height=sliders[4].getPos();
    float  innerRadius=sliders[5].getPos();
    s=new Shell(g, vResolution, hResolution,  numberOfSpirals, finalRadius, height, innerRadius);
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
