import processing.opengl.*;

import surface.*;

SuperTorus s;
Slider[] sliders;

void setup(){
  size(600,600,OPENGL);
  textFont(loadFont("dialog.vlw"));
  s=new SuperTorus(g, 20, 20);
 
  sliders=new Slider[12];
  sliders[0]=new Slider(20,50,100,10,20,4,55, "vertical Resolution");//Slider of vertical Resolution
  sliders[1]=new Slider(20,90,100,10,20,4,55, "horizontal Resolution");//Slider of horizontal Resolution
  
  sliders[2]=new Slider(20,140,100,10,20,0,10, "r1m");
  sliders[3]=new Slider(20,180,100,10,20,0,10, "rn1");
  sliders[4]=new Slider(20,220,100,10,20,0,10, "rn2");
  sliders[5]=new Slider(20,260,100,10,20,0,10, "rn3");
  
  sliders[6]=new Slider(20,300,100,10,20,0,10, "r2m");
  sliders[7]=new Slider(20,340,100,10,20,0,10, "r2n1");
  sliders[8]=new Slider(20,380,100,10,20,0,10, "r2n2");
  sliders[9]=new Slider(20,420,100,10,20,0,10, "r2n3");
  
  
  sliders[10]=new Slider(20,380,100,10,20,0,10, "c");
  sliders[11]=new Slider(20,420,100,10,20,0,10, "t");
  
  
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
    
    float r1m=(sliders[2].getPos());
    float r1n1=(sliders[3].getPos());
    float r1n2=(sliders[4].getPos());
    float r1n3=(sliders[5].getPos());
    float r2m=(sliders[6].getPos());
    float r2n1=(sliders[7].getPos());
    float r2n2=(sliders[8].getPos());
    float r2n3=(sliders[9].getPos());
    
    float c=(sliders[10].getPos());
    float t=(sliders[1].getPos());

    s=new SuperTorus(g, vResolution, hResolution,  r1m, r1n1, r1n2, r1n3, r2m, r2n1, r2n2, r2n3, c,t);
 
    
  }
  
  translate(400,250);
 
  noStroke();
  fill(#A6D3EA);
  rotateX(radians(frameCount));
  rotateY(radians(frameCount));
  rotateZ(radians(frameCount));
   s.setScale(mouseX/10f);
  s.draw();
}
