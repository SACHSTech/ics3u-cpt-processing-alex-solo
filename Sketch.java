import processing.core.PApplet;

public class Sketch extends PApplet {

  int shipX = 0;
  int shipY = 0;
  int lives = 3;
  float[] bulletsX = new float [30];
	
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(800, 800);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);

    for (int i = 0; i < bulletsX.length; i++){
      bulletsX[i] = random(2000 - width) + width;
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
    background(32);

    if (lives == 3){
      ellipse(700, 20, 25, 25);
      ellipse(625, 20, 25, 25);
      ellipse(550, 20, 25, 25);
    }
    if (lives == 2){
      ellipse(700, 20, 25, 25);
      ellipse(625, 20, 25, 25);
    }
    if (lives == 1){
      ellipse(700, 20, 25, 25);
    }
    int yes = width/10;
    int no = height/20;
    rect(shipX, shipY, yes, no);

    if (keyPressed){
      if (key == 'w'){
        shipY -=5;
      }
      if (key == 's'){
        shipY +=5;
      }
      if (key == 'a'){
        shipX -=5;
      }
      if (key == 'd'){
        shipX +=5;
      }
    }

    for (int i = 0; i < bulletsX.length; i++){
      float bulletsY = height * i/bulletsX.length;
      rect(bulletsX[i], bulletsY, 20, 5);

      bulletsX[i] --;
      
      
      if (shipX < bulletsX[i] + 20 && shipX + yes > bulletsX[i] && shipY < bulletsY + 5 && shipY + no > bulletsY){
        bulletsX[i] = -20; 
        lives --;
      }
         
        
        
    
     
    }


  }
  
  // define other methods down here.
}