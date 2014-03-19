import processing.opengl.*;

import surface.*;

Surface s;
int resolution=50;
int cnt;
boolean useVertexNormals = false;

void setup(){
  size(400,300,OPENGL);
  s=new DoubleCone(g,resolution,resolution);
  noStroke();
  smooth();
}

void draw(){
  background(#FFF9D1);
  fill(#A6D3EA);
  lights();

  translate(width/2,height/2);
  rotateX(radians(frameCount));
  rotateY(radians(frameCount));
  rotateZ(radians(frameCount));
  s.setScale((mouseX));
  
  s.draw();
}



void keyPressed(){
 if(key=='v'){
 useVertexNormals = !useVertexNormals;

  s.useVertexNormals(useVertexNormals); 

 }else{
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
}

