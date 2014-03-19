import processing.opengl.*;

import surface.*;

Surface s;
Slider[] sliders;
int cnt;
int resolution=40;

void setup(){
  size(400,300,OPENGL);
  textFont(loadFont("dialog.vlw"));
  smooth();

  s=new DoubleCone(g, resolution, resolution);
  sliders=new Slider[6];
  sliders[0]=new Slider(20,50,100,10,20,0,resolution, "horizontal startpoint");
  sliders[0].setPos(0);
  sliders[1]=new Slider(20,90,100,10,20,1,resolution, "horizontal endpoint");
  sliders[1].setPos(resolution);
  sliders[2]=new Slider(20,130,100,10,20,1,resolution, "horizontal stepsize");
  sliders[2].setPos(1);
  sliders[3]=new Slider(20,170,100,10,20,1,resolution, "vertical startpoint");
  sliders[3].setPos(0);
  sliders[4]=new Slider(20,210,100,10,20,1,resolution, "vertical endpoint");
  sliders[4].setPos(resolution);
  sliders[5]=new Slider(20,250,100,10,20,1,resolution, "vertical stepsize");
  sliders[5].setPos(1);
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
  
  translate(250,150);
  rotateX(radians(frameCount));
  rotateY(radians(frameCount));
  rotateZ(radians(frameCount));
  s.setScale(mouseX);

  int startHor=floor(sliders[0].getPos());
  int endHor=floor(sliders[1].getPos());
  int stepHor=floor(sliders[2].getPos());
  int startVert=floor(sliders[3].getPos());
  int endVert=floor(sliders[4].getPos());
  int stepVert=floor(sliders[5].getPos());
 
  noStroke();
  fill(#A6D3EA);
  
  s.drawPart(startHor,endHor,startVert,endVert,stepHor,stepVert);
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
