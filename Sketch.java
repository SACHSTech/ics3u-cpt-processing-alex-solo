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

    rect(shipX, shipY, width/10, height/20);

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

      for (int j = 0; j < bulletsX.length; j++){
        if (bulletsX[j] < shipX + width/10 && bulletsY > shipY && bulletsY + 5 < shipY + height/20 ){
          lives --;
        }
      }
     
    }


  }
  
  // define other methods down here.
}