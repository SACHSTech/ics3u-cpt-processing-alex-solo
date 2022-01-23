import processing.core.PApplet;

public class Sketch extends PApplet {

  boolean win = false;
  boolean lose = false;
  int speedAdd = 0;
  int speedMinus = 0;
  int intialSpeed = 5;
  int n = 0;
  int additional = 0;
  int shipX = 0;
  int shipY = 0;
  int lives = 3;
  float[] bulletsX = new float [500];
  float[] additionalLife = new float [3];
  float[] speedUp = new float [3];
  float [] speedDown = new float [3];
	
	
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

    for (int i = 0; i < bulletsX.length - 1; i++){
      bulletsX[i] = random(2000 - width) + width;
    }
    bulletsX[29] = 2100;

    for (int i = 0; i < additionalLife.length; i++){
      additionalLife[i] = random(2000 - width) + width;
      speedUp[i] = random(2000 - width) + width;
      speedDown[i] = random(2000 - width) + width;
    }


  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
    background(32);

    // Lives display
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

    // Wins and Loses Display
    if (win == true){
      rect(50, 50, 50, 50);
    }

    if (lose == true){
      ellipse(50, 50, 50, 50);
    }

    // Ship
    int yes = width/10;
    int no = height/20;
    rect(shipX, shipY, yes, no);

    // Movement of the ship
    if (keyPressed){
      if (key == 'w'){
        shipY -=intialSpeed + speedAdd - speedMinus;
      }
      if (key == 's'){
        shipY +=intialSpeed + speedAdd - speedMinus;
      }
      if (key == 'a'){
        shipX -=intialSpeed + speedAdd - speedMinus;
      }
      if (key == 'd'){
        shipX +=intialSpeed + speedAdd - speedMinus;
      }
      if (shipY < 0){
        shipY +=intialSpeed + speedAdd - speedMinus;
      }
      if (shipY + no > height){
        shipY -= intialSpeed + speedAdd - speedMinus;
      }
      if (shipX < 0){
        shipX += intialSpeed + speedAdd - speedMinus;
      }
      if (shipX + yes > width){
        shipX -= intialSpeed + speedAdd - speedMinus;
      }
      
    }

    // Additional Lives, speed up, and slow down
    for (int i = 0; i < additionalLife.length; i++){
      float circleY = height * i/additionalLife.length;
      float upY = height /5 * (i+2)/additionalLife.length;
      float downY = height/2 * (i+3)/additionalLife.length;
      ellipse(additionalLife[i],circleY, 20, 20);
      triangle(speedUp[i] - 15, upY, speedUp[i], upY - 25, speedUp[i] + 15, upY);
      triangle(speedDown[i] - 15, downY, speedDown[i], downY + 25, speedDown[i] + 15, downY);



      speedUp[n] --;
      speedDown[n]--;
      additionalLife[n] --;

      if (shipX < additionalLife[n] + 20 && shipX + yes > additionalLife[n] && shipY < circleY + 20 && shipY + no > circleY){
        additionalLife[n] = -20;
        if (lives < 3){
        lives ++; 
        }
      }
      if (shipX < speedUp[n] + 15 && shipX + yes > speedUp[n] && shipY < upY - 20 && shipY + no > upY){
        speedUp[n] = -15;
        speedAdd +=5;
      }
      if (shipX < speedDown[n] + 15 && shipX + yes > speedDown[n] && shipY < downY + 20 && shipY + no > downY){
        speedDown[n] = -15;
        if (speedMinus < speedAdd + intialSpeed){
        speedMinus +=5;
        }
      }
    }

    // Bullets
    for (int i = 0; i < 28; i++){
      float bulletsY = height * i/30;
      rect(bulletsX[i], bulletsY, 20, 5);

     
      rect(bulletsX[29], 50, 20, 5);

      bulletsX[29] -=0.12;
      bulletsX[i] -=5;
      
      if (bulletsX[29] < 0){
        win = true;
      }

      if (shipX < bulletsX[i] + 20 && shipX + yes > bulletsX[i] && shipY < bulletsY + 5 && shipY + no > bulletsY){
        bulletsX[i] = -20; 
        lives --;

        if (lives == 0){
          lose = true;
        }
      }
    }

  if (bulletsX[29] < 0){
    for (int i = 29; i < 60; i++){
      float bulletsY = height * i/45;
      rect(bulletsX[i], bulletsY, 20, 5);

     
      rect(bulletsX[29], 50, 20, 5);

      bulletsX[29] -=0.12;
      bulletsX[i] -=9;
      
      if (bulletsX[29] < 0){
        win = true;
      }

      if (shipX < bulletsX[i] + 20 && shipX + yes > bulletsX[i] && shipY < bulletsY + 5 && shipY + no > bulletsY){
        bulletsX[i] = -20; 
        lives --;

        if (lives == 0){
          lose = true;
        }
      }
    }
  }

  }
  
  // define other methods down here.
}