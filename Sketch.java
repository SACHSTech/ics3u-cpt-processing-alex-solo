import processing.core.PApplet;
import processing.core.PFont;

public class Sketch extends PApplet {

  PFont t;
  boolean red= false;
  boolean blue = false;
  boolean green = false;
  boolean yellow = false;
  boolean shipColour = false; 
  boolean win = false;
  boolean lose = false;
  int speedAdd = 0;
  int speedMinus = 0;
  int intialSpeed = 5;
  int a = 1;
  int n = 0;
  int additional = 0;
  int shipX = 50;
  int shipY = 50;
  int lives = 3;
  float[] bulletsX = new float [500];
  float[] circleY = new float [10];
  float[] additionalLife = new float [10];
  float[] upY = new float[10];
  float[] speedUp = new float [10];
  float[] downY = new float [10];
  float [] speedDown = new float [10];
	
	
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
      if (i % 50 > 0){
        bulletsX[i] = random(2000 - width) + width;
    }
      if (i % 50 == 0){
        bulletsX[i] = 2100;
    }
  }   

    for (int i = 0; i < additionalLife.length; i++){
      additionalLife[i] = random(2100 - width) + width;
      speedUp[i] = random(2100 - width) + width;
      speedDown[i] = random(2100 - width) + width;
      upY[i] = height /5 * (i+2)/additionalLife.length;
      downY[i] = height/2 * (i+3)/additionalLife.length;
      circleY[i] = height * (i + 1)/additionalLife.length;

    }

    t = createFont("Arial", 40, true);


  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
    background(32);

    if(shipColour == false){
    fill(255,0,0);
    rect(30, height/2, 50, 20);

    fill(0,255,0);
    rect(180, height/2, 50, 20);

    fill(0,0,255);
    rect(330, height/2, 50, 20);

    fill(255,255,0);
    rect(480, height/2, 50, 20);

    }

    // Lives display
    fill (255,192,203);
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
    if (lose == true){
      fill(0,0,0);
      rect(0,0,width,height);
      fill(255, 255, 255);
      textFont(t);
      text("You LOSE!!",300, 200);
    }
    else if (win == true){
      fill(0,0,0);
      rect(0,0,width,height);
      for(int i = 0; i < 4; i++){
        if (i == 1){
          fill(255, 0, 0);
        }
        else if (i == 2){
          fill(0, 255, 0);
        }
        else if (i == 3){
          fill(0, 0 ,255);
        }
      }
      textFont(t);
      text("You WIN!!",300, 200);;
    }

    // Ship
    if (30 < mouseX && 80 > mouseX && height/2 < mouseY && height/2 + 50 > mouseY){
      if(mousePressed){
      red = true;
      shipColour = true;
    }
  }
    if (180 < mouseX && 230 > mouseX && height/2 < mouseY && height/2 + 50 > mouseY){
      if(mousePressed){
      green = true;
      shipColour = true;
    }
  }
    if (330 < mouseX && 380 > mouseX && height/2 < mouseY && height/2 + 50 > mouseY){
      if(mousePressed){
      blue = true;
      shipColour = true;
    }
  }
    if (480 < mouseX && 530 > mouseX && height/2 < mouseY && height/2 + 50 > mouseY){
      if(mousePressed){
      yellow = true;
      shipColour = true;
    }
  }
  if (red == true){
    fill(255, 0 , 0);
  }
  if (blue == true){
    fill(0, 0, 255);
  }
  if (green == true){
    fill(0, 255, 0);
  }
  if (yellow == true){
    fill(255, 255, 0); 
  }
  if (shipColour == false){
    fill(255, 255, 255);
  }
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

      // Boundary of the map
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
    
    if (bulletsX[50 * a] < -20 && lives > 0){
      n++;
      a++;
    }

    // Additional Lives, speed up, and slow down
  if (shipColour == true){
    for (int i = 0; i < additionalLife.length; i++){
      
      fill(255,192,203);
      ellipse(additionalLife[i],circleY[i], 20, 20);
      fill(0,255,0);
      triangle(speedUp[i] - 15, upY[i], speedUp[i], upY[i] - 25, speedUp[i] + 15, upY[i]);
      fill(255, 0, 0);
      triangle(speedDown[i] - 15, downY[i], speedDown[i], downY[i] + 25, speedDown[i] + 15, downY[i]);



      speedUp[n] -=0.3 * (n+1);
      speedDown[n]-=0.3 * (n+1);
      additionalLife[n] -=0.3 * (n+1);

      if (shipX < additionalLife[n] + 10 && shipX + yes > additionalLife[n] && shipY < circleY[n] + 10 && shipY + no > circleY[n]){
        additionalLife[n] = -20;
        if (lives < 3){
        lives ++; 
        }
      }
      if (shipX < speedUp[n] + 15 && shipX + yes > speedUp[n] && shipY < upY[n] && shipY + no > upY[n] - 25){
        speedUp[n] = -15;
        speedAdd +=2;
      }
      if (shipX < speedDown[n] + 15 && shipX + yes > speedDown[n] && shipY < downY[n] + 25 && shipY + no > downY[n]){
        speedDown[n] = -15;
        if (speedMinus + 2 < speedAdd + intialSpeed){
        speedMinus +=2;
        }
      }
    }
  }

    // Bullets
   if(shipColour == true){
    for (int i = 0; i < 49; i++){
      float bulletsY = height * i/50;
      fill(255, 255, 255);
      rect(bulletsX[i], bulletsY, 20, 5);

     
      rect(bulletsX[50], 50, 20, 5);

      bulletsX[50] -=0.05;
      bulletsX[i] -=2;

      if (shipX < bulletsX[i] + 20 && shipX + yes > bulletsX[i] && shipY < bulletsY + 5 && shipY + no > bulletsY){
        bulletsX[i] = -20; 
        lives --;
      }
      if (lives == 0){
        lose = true;
       }
      
      if (shipX < bulletsX[50] + 20 && shipX + yes > bulletsX[50] && shipY < 50 + 5 && shipY + no > 50){
        bulletsX[50] = -20; 
        lives --;
      }
       if (lives == 0){
        lose = true;
        }
      }
     }
    
    

  if (bulletsX[50] < -20 && lives > 0){
    for (int i = 50; i < 99; i++){
      float bulletsY = height * (i-50)/50;
      rect(bulletsX[i], bulletsY, 20, 5);

     
      rect(bulletsX[100], 50, 20, 5);

      bulletsX[100] -=0.055;
      bulletsX[i] -=3;

      if (shipX < bulletsX[i] + 20 && shipX + yes > bulletsX[i] && shipY < bulletsY + 5 && shipY + no > bulletsY){
        bulletsX[i] = -20; 
        lives --;

        if (lives == 0){
          lose = true;
        }
      }
      if (shipX < bulletsX[100] + 20 && shipX + yes > bulletsX[100] && shipY < 50 + 5 && shipY + no > 50){
        bulletsX[100] = -20; 
        lives --;

        if (lives == 0){
          lose = true;
        }
      }
    }
  }
  if (bulletsX[100] < -20 && lives > 0){
    for (int i = 100; i < 149; i++){
      float bulletsY = height * (i-100)/50;
      rect(bulletsX[i], bulletsY, 20, 5);

     
      rect(bulletsX[150], 50, 20, 5);

      bulletsX[150] -=0.06;
      bulletsX[i] -=4;

      if (shipX < bulletsX[i] + 20 && shipX + yes > bulletsX[i] && shipY < bulletsY + 5 && shipY + no > bulletsY){
        bulletsX[i] = -20; 
        lives --;

        if (lives == 0){
          lose = true;
        }
      }
      if (shipX < bulletsX[150] + 20 && shipX + yes > bulletsX[150] && shipY < 50 + 5 && shipY + no > 50){
        bulletsX[150] = -20; 
        lives --;

        if (lives == 0){
          lose = true;
        }
      }
    }
  }
  if (bulletsX[150] < -20 && lives > 0){
    for (int i = 150; i < 199; i++){
      float bulletsY = height * (i-150)/50;
      rect(bulletsX[i], bulletsY, 20, 5);

     
      rect(bulletsX[200], 50, 20, 5);

      bulletsX[200] -=0.055;
      bulletsX[i] -=5;

      if (bulletsX[200] < 0){
        win = true;
      }

      if (shipX < bulletsX[i] + 20 && shipX + yes > bulletsX[i] && shipY < bulletsY + 5 && shipY + no > bulletsY){
        bulletsX[i] = -20; 
        lives --;

        if (lives == 0){
          lose = true;
        }
      }
      if (shipX < bulletsX[200] + 20 && shipX + yes > bulletsX[200] && shipY < 50 + 5 && shipY + no > 50){
        bulletsX[200] = -20; 
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