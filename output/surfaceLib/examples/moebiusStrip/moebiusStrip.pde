import processing.opengl.*;

import surface.*;

Surface s;
Slider[] sliders;

void setup(){
  size(400,300,OPENGL);
  textFont(loadFont("dialog.vlw"));
  s=new MoebiusStrip(g, 20, 20);
  sliders=new Slider[3];
  sliders[0]=new Slider(20,50,100,10,20,4,55, "vertical Resolution");//Slider of vertical Resolution
  sliders[1]=new Slider(20,90,100,10,20,4,55, "horizontal Resolution");//Slider of horizontal Resolution
  sliders[2]=new Slider(20,130,100,10,20,0,10, "Inner radius");//Slider of horizontal Resolution
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
    float innerRadius=sliders[2].getPos();
    s=new MoebiusStrip(g, vResolution, hResolution, innerRadius);

  }
  translate(250,150);
  s.setScale(20);
  noStroke();
fill(#A6D3EA);
  rotateX(radians(frameCount));
  rotateY(radians(frameCount));
  rotateZ(radians(frameCount));
  s.draw();
}
