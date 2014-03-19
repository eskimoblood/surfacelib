class Slider
{
  int swidth, sheight;    // width and height of bar
  int xpos, ypos;         // x and y position of bar
  float spos, newspos;    // x position of slider
  int sposMin, sposMax;   // max and min values of slider
  int loose;              // how loose/heavy
  boolean over;           // is the mouse over the slider?
  boolean locked;
  float ratio;
  boolean moved;
  float sLength,vLength, vMin, vMax;
  String label;

  Slider (int xp, int yp, int sw, int sh, int l, float mn, float mx, String lb) {
    swidth = sw;
    sheight = sh;
    int widthtoheight = sw - sh;
    ratio = (float)sw / (float)widthtoheight;
    xpos = xp;
    ypos = yp-sheight/2;
    spos = xpos + swidth/2 - sheight/2;
    newspos = spos;
    sposMin = xpos;
    sposMax = xpos + swidth - sheight;
    loose = l;
    vMin=mn;
    vMax=mx;
    vLength=vMax-vMin;
    sLength=swidth-sheight;
    label=lb;
  }

  void update() {
    if(over()) {
      over = true;
    } 
    else {
      over = false;
    }
    if(mousePressed && over) {
      locked = true;
    }
    if(!mousePressed) {
      locked = false;
    }
    if(locked) {
      newspos = constrain(mouseX-sheight/2, sposMin, sposMax);
    }
    if(abs(newspos - spos) > 1) {
      spos = spos + (newspos-spos)/loose;
      moved=true;
    }
    else{
      moved=false;
    }
  }

  int constrain(int val, int minv, int maxv) {
    return min(max(val, minv), maxv);
  }

  boolean over() {
    if(mouseX > xpos && mouseX < xpos+swidth &&
      mouseY > ypos && mouseY < ypos+sheight) {
      return true;
    } 
    else {
      return false;
    }
  }

  void draw() {
    rectMode(CORNER);
    fill(255);
    rect(xpos, ypos, swidth, sheight);
    if(over || locked) {
      fill(240, 200, 0);
    } 
    else {
      fill(102, 102, 102);
    }
    rect(spos, ypos, sheight, sheight);
    fill(50);
    text(label,xpos,ypos-10);
  }
  void setPos(float p){
spos=xpos+(p*sLength)/vLength;
newspos=spos;
  }
  float getPos() {
    return   max(min((vMin+((spos-xpos)*vLength)/sLength),vMax),vMin) ;
  }
}
