import surface.*;

SuperShapes s;
Slider[] sliders;

void setup(){
  size(400,300,P3D);
  textFont(loadFont("dialog.vlw"));
  s=new SuperShapes(g, 20, 20);
  sliders=new Slider[6];
  sliders[0]=new Slider(20,50,100,10,20,4,55, "vertical Resolution");
  sliders[1]=new Slider(20,90,100,10,20,4,55, "horizontal Resolution");
  sliders[2]=new Slider(20,130,100,10,20,0,20, "n1");
  sliders[3]=new Slider(20,170,100,10,20,0,20, "n2");
  sliders[4]=new Slider(20,210,100,10,20,0,20, "n3");
  sliders[5]=new Slider(20,250,100,10,20,0,20, "m");
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
    float n1=sliders[2].getPos();
    float n2=sliders[3].getPos();
    float n3=sliders[4].getPos();
    float m=sliders[5].getPos();
    s=new SuperShapes(g, vResolution, hResolution);
    s.setParameter(n1,n2,n3,m,n1,n2,n3,m);
  }
  
  translate(250,150);
  rotateX(radians(frameCount));
  rotateY(radians(frameCount));
  rotateZ(radians(frameCount));
  s.setScale(40);
  
  noStroke();
  fill(#A6D3EA);
  
  s.draw();
}
