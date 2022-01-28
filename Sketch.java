import processing.core.PApplet;
import processing.core.PFont;

public class Sketch extends PApplet {

  // Variables for the game
  PFont roundNumber;
  PFont colourChoice;
  PFont t;
  PFont secretFont;
  String name = " ";
  boolean nameEnter = false;
  boolean secretEnding = false;
  boolean pause = false;
  boolean red = false;
  boolean blue = false;
  boolean green = false;
  boolean yellow = false;
  boolean shipColour = false; 
  boolean win = false;
  boolean lose = false;
  int decorationX = 25;
  int decorationY = 25;
  int speedAdd = 0;
  int speedMinus = 0;
  int intialSpeed = 5;
  float bulletSpeed = 2;
  float lastBullet = (float) 0.04;
  int n = 0;
  int shipX = 50;
  int shipY = 50;
  int lives = 3;
  float secretBulletX = 2100;
  float secretBulletY = 0;
  float[] rainY = new float [100];
  float[] fireworksX = new float [129];
  float[] fireworksY = new float [129];
  float[] bulletsX = new float [51];
  float[] circleY = new float [20];
  float[] additionalLife = new float [20];
  float[] upY = new float[20];
  float[] speedUp = new float [20];
  float[] downY = new float [20];
  float [] speedDown = new float [20];
	
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(700, 700);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(0, 0, 30);

    // Giving positions for the bullets
    for (int i = 0; i < 50; i++){
      bulletsX[i] = random(2000 - width) + width;
  }   
    bulletsX[50] = 2400;

    // Giving positions for the other objects
    for (int i = 0; i < additionalLife.length; i++){
      additionalLife[i] = random(2200 - width) + width + 25;
      speedUp[i] = random(2200 - width) + width + 25;
      speedDown[i] = random(2200 - width) + width + 25;
      upY[i] = random(height);
      downY[i] = random(height);
      circleY[i] = random(height);

    }

    // Positions for the circles in the winning screen
    for (int i = 0; i < 32; i++){
      fireworksX[i] = decorationX;
      fireworksY[i] = 0;

      decorationX += 25;
    }
    for (int i = 32; i < 64; i++){
      fireworksX[i] = width;
      fireworksY[i] = decorationY;

      decorationY += 25;
    }

    for (int i = 64; i < 96; i++){
      decorationX -= 25;
      fireworksX[i] = decorationX;
      fireworksY[i] = height;

      
    }
    for (int i = 96; i < 129; i++){
      decorationY -= 25;
      fireworksX[i] = 0;
      fireworksY[i] = decorationY;

      
    }

    // Position for the rain in the losing screen
    for (int i = 0; i < rainY.length; i++){
      rainY[i] = random(height) + 100;
    }

    secretBulletY = random(height);

    

    t = createFont("Arial", 40, true);
    roundNumber = createFont("Arial", 20, true);
    colourChoice = createFont("Arial", 30, true);
    secretFont = createFont("Serif", 25, true);


  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    if (pause == false){
      background(0, 0, 30);

    // Colours that can be used for the ship
      if(shipColour == false){
        fill(255, 255, 255);
        textFont(colourChoice);
        text("Click the colour you want for your ship", width/2 - 250, height/4);

        fill(255, 0, 0);
        rect(30, height/2, 50, 20);

        fill(0, 255, 0);
        rect(180, height/2, 50, 20);

        fill(0, 0, 255);
        rect(330, height/2, 50, 20);

        fill(255, 255, 0);
        rect(480, height/2, 50, 20);

      }

      // Lives display
      fill (255,192,203);
      if (lives == 3){
        ellipse(625, 20, 25, 25);
        ellipse(550, 20, 25, 25);
        ellipse(475, 20, 25, 25);
      }
      if (lives == 2){
        ellipse(625, 20, 25, 25);
        ellipse(550, 20, 25, 25);
      }
      if (lives == 1){
        ellipse(625, 20, 25, 25);
      }
      
      // Indication that user got the secret 
      if (secretEnding == true){
        fill(255,215,0);
        rect(380, 10, 20, 5);
      }

      // Wins and Loses Display
      if (lose == true){
        fill(0,0,0);
        rect(0,0,width,height);
        fill(128, 128, 128);
        textFont(t);
        text("You LOSE!!",width/2 - 100, height/3);
        for(int i = 0; i <rainY.length; i++){
          int rainX = height * i/rainY.length;
          fill(0, 0, 255);
          rect(rainX, rainY[i], 3, 10);

          rainY[i] ++;

          if (rainY[i] > height){
            rainY[i] = 0;
          }
        }
      }
      // Secret Ending 
      else if(win == true && secretEnding == true){
        fill(255, 255, 255);
        rect(0,0,width,height);
        
        if (nameEnter == false){
          fill(255, 255, 0);
          textFont(secretFont);
          text("Type your name and enter",width/2 - 150, height/3);
          text(name, width/2 - 100, height/2);
        }
        for(int i = 0; i < fireworksX.length; i++){
          float r = random(255);
          float g = random(255);
          float b = random(255);
          fill(r,g,b);
          ellipse(fireworksX[i], fireworksY[i], 25, 25);
          if (nameEnter == true){
            textFont(secretFont);
            text(name +" you are the champion!!", width/2 - 200, height/3);
            text("Congratulations for beating the game!", width/2 - 200, height/3 + 100);
          }
        }
      }
      else if (win == true){
        fill(0,0,0);
        rect(0,0,width,height);
        fill(255, 0, 0);
        textFont(t);
        text("You WIN!!",width/2 - 100, height/3);
        for(int i = 0; i < fireworksX.length; i++){
          float r = random(255);
          float g = random(255);
          float b = random(255);
          fill(r,g,b);
          ellipse(fireworksX[i], fireworksY[i], 25, 25);
        }
      }

      // Allows user to click the rectangles to access the colour
      if (30 < mouseX && 80 > mouseX && height/2 < mouseY && height/2 + 50 > mouseY && shipColour == false){
        if(mousePressed){
          red = true;
          shipColour = true;
      }
    }
      if (180 < mouseX && 230 > mouseX && height/2 < mouseY && height/2 + 50 > mouseY && shipColour == false){
        if(mousePressed){
          green = true;
          shipColour = true;
      }
    }
      if (330 < mouseX && 380 > mouseX && height/2 < mouseY && height/2 + 50 > mouseY && shipColour == false){
        if(mousePressed){
          blue = true;
          shipColour = true;
      }
    }
      if (480 < mouseX && 530 > mouseX && height/2 < mouseY && height/2 + 50 > mouseY && shipColour == false){
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
    // Ship user will control
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
          shipY = 0;
        }
        if (shipY + no > height){
          shipY = height - no;
        }
        if (shipX < 0){
          shipX = 0;
        }
        if (shipX + yes > width){
          shipX = width - yes;
        }
        
      }
    
      // Display of the round numbers
      if (bulletsX[50] > 0 && shipColour == true && lives > 0){
        fill(255, 255, 255);
        textFont(roundNumber);
        text("Round " +(n + 1), 20, 30);
      }


      // Additional Lives, speed up, and slow down 
    if (win == false){
      if (lose == false){
        if (shipColour == true){
          for (int i = 0; i < additionalLife.length; i++){
        
            fill(255,192,203);
            ellipse(additionalLife[i],circleY[i], 20, 20);
            fill(0,255,0);
            triangle(speedUp[i] - 15, upY[i], speedUp[i], upY[i] - 25, speedUp[i] + 15, upY[i]);
            fill(255, 0, 0);
            triangle(speedDown[i] - 15, downY[i], speedDown[i], downY[i] + 25, speedDown[i] + 15, downY[i]);

            
            speedUp[n] -=0.1 * (n+1);
            speedDown[n]-=0.1 * (n+1);
            additionalLife[n] -=0.1 * (n+1);

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
        }
      }
    
    // Generation of the secret bullet
    if (win == false){
      if (lose == false){
        if (shipColour == true && n == 9){
          fill(255,215,0);
          rect(secretBulletX, secretBulletY, 20, 5);

          secretBulletX -=8;

          if (shipX < secretBulletX + 20 && shipX + yes > secretBulletX && shipY < secretBulletY + 5 && shipY + no > secretBulletY){
            secretEnding = true;
            secretBulletX = -20;
          }
        }
      }
    }

      // Bullets user will have to dodge
    if(win == false){
      if(lose == false){
        if(shipColour == true && lives > 0){
          for(int i = 0; i < 50; i++){
            
            float bulletsY = height * i/50;
            fill(255, 255, 255);
            rect(bulletsX[i], bulletsY, 20, 5);
        
            rect(bulletsX[50], 50, 20, 5);
          
            if (bulletsX[50] > -20){
              bulletsX[i] -= bulletSpeed;
              bulletsX[50] -= lastBullet;
        
              if (shipX < bulletsX[i] + 20 && shipX + yes > bulletsX[i] && shipY < bulletsY + 5 && shipY + no > bulletsY){
                bulletsX[i] = -20; 
                lives --;
              }
              if (lives == 0){
                lose = true;
              }
              
              if (shipX < bulletsX[50] + 20 && shipX + yes > bulletsX[50] && shipY < 50 + 5 && shipY + no > 50){
                bulletsX[50] = -21; 
                lives --;
              }
              if (lives == 0){
                lose = true;
                }
              }
            }
          if(bulletsX[50] < -20 && lives > 0){
            for(int j = 0; j < 50; j++){
              bulletsX[j] = random(2000 - width) + width;
            }
            bulletsX[50] = 2400;
            n++;
            bulletSpeed += 0.5;
            lastBullet += 0.0115;
            if (n == 20){
              win = true;
            }
          }
        }
      }
    }
    
  }
  // Pause message
  if (pause == true){
    textFont(t);
    text("Paused", width/2 - 100, width/3);
  }

}
  
   /**
   * Checks to see if the user has pressed something on the keyboard
   */
  public void keyPressed(){
    if (keyCode == CONTROL && pause == true){
      pause = false;
    }
    else if (keyCode == CONTROL){
      pause = true;
    }
    else if (keyCode == ENTER && win == true && secretEnding == true){
      nameEnter = true;
    }
    else if (keyCode == BACKSPACE && win == true && secretEnding == true && nameEnter == false){
      name = name.substring(0, name.length() - 1);
    }
  }
  /**
   * Checks to see if the user has typed something on the keyboard
   */
  public void keyTyped(){
    if(win == true && nameEnter == false){
      if (key != BACKSPACE){
        name += key;
      }
     
      
    } 
  }
}