import processing.opengl.*;

import surface.*;

SuperDuperShape s;
Slider[] sliders;

void setup(){
  size(800,800,OPENGL);
  textFont(loadFont("dialog.vlw"));
  s=new SuperDuperShape(g, 20, 20, 1, 0,0,0, 1,0,0,0, 1,1,0,0,0,0,0);
 
  sliders=new Slider[17];
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
  
  
  sliders[10]=new Slider(20,460,100,10,20,0,10, "c1");
  sliders[11]=new Slider(20,500,100,10,20,0,10, "c2");
  sliders[12]=new Slider(20,540,100,10,20,0,10, "c3");
  
  sliders[13]=new Slider(20,580,100,10,20,0,10, "t1");
  sliders[14]=new Slider(20,620,100,10,20,0,10, "t2");
  
  sliders[15]=new Slider(20,660,100,10,20,0,10, "d1");
  sliders[16]=new Slider(20,700,100,10,20,0,10, "d2");
  
  
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
    
    float c1=(sliders[10].getPos());
    float c2=(sliders[11].getPos());
    float c3=(sliders[12].getPos());
    
    float t1=(sliders[13].getPos());
    float t2=(sliders[14].getPos());
    
    float d1=(sliders[15].getPos());
    float d2=(sliders[16].getPos());

    s=new SuperDuperShape(g, vResolution, hResolution,  r1n1, r1n2, r1n3,r1m,  r2n1, r2n2, r2n3, r2m, c1, c2,c3 ,t1, t2, d1, d2);
 
    
  }
  
  translate(400,400);
 
  noStroke();
  fill(#A6D3EA);
  rotateX(radians(frameCount));
  rotateY(radians(frameCount));
  rotateZ(radians(frameCount));
   s.setScale(mouseX/10f);
  s.draw();
}
