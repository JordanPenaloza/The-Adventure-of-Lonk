//Fianl Project Jordan Penaloza

package Main;

import java.awt.Color;
import java.util.ArrayList;

import Data.Vector2D;
import Data.boundingBox;
import Data.spriteInfo;


import logic.Control;


public class Main{
	
	//statc fields
	public static boundingBox lonkBox;
	public static boundingBox house = new boundingBox(190, 510, 380, 723);
	public static boundingBox cellar = new boundingBox(971, 1130, 250, 475); 
	
	public static int frames = 0;
	
	public static String trigger1 = "";	//toggles text
	public static String trigger2 = "";	//toggles text
	 	
	
	public static Vector2D currentVec = new Vector2D(100, 296); 	//current pos of player
	public static Vector2D lastVec = new Vector2D(0, 0); 			//previous pos of player
	
	

	
	public static spriteInfo lonk = new spriteInfo(currentVec, "lonk"+frames);
	public static spriteInfo lastPos = new spriteInfo (lastVec, lonk.getTag()); 
	
	//data structures for boxes and sprites
	public static ArrayList<boundingBox> boxes = new ArrayList<>(); 	
	public static ArrayList<spriteInfo> sprites = new ArrayList<>(); 	
	

	
	
	public static void main(String[] args) {
		Control ctrl = new Control();				/* Do NOT remove! */
		ctrl.gameLoop();
		/* Do NOT remove! */
	}
	
	
	
	public static void start() {
		
		//Bounding boxes for world
		boxes.add(new boundingBox(0, 60, 0, 1080));
		boxes.add(new boundingBox(60, 1880, 860, 1080));
		boxes.add(new boundingBox(1880, 1920, 0, 1080));
		boxes.add(new boundingBox(0, 1920, 0, 250));
		//Bounding boxes for world
		boxes.add(new boundingBox(240, 450, 420, 713));   
		boxes.add(new boundingBox(1000, 1090, 325, 450)); 
			
		
		//sprites
		sprites.add(new spriteInfo(new Vector2D(0, 0), "overworld")); 
		sprites.add(lonk);
		
	}
	
	
	
	public static void update(Control ctrl) {
		
		//player box
		lonkBox = new boundingBox(lonk, 30, 118, 98, 130);  	
		
		
		//checks for collision and bounces lonk back if it is detected
		for (int i = 0; i < boxes.size(); i++)
			if (checkCollision(lonkBox, boxes.get(i)))
				
				bounce(); 
		
		//draws sprites
		for (int i = 0; i < sprites.size(); i++)
			ctrl.addSpriteToFrontBuffer(sprites.get(i).getCoords().getX(), sprites.get(i).getCoords().getY(), 
					sprites.get(i).getTag());
		
		
		ctrl.drawString(415, 600, trigger1, Color.RED);
		ctrl.drawString(970, 435, trigger2, Color.RED);
	}
	
	

	
	//true = no collision     false = collision
	public static boolean checkCollision(boundingBox box1, boundingBox box2) {
		if (((box1.getX1() > box2.getX2()) || (box1.getX2() < box2.getX1()) || (box1.getY1() > box2.getY2())
				|| (box1.getY2() < box2.getY1())))
			return false;
		else
			return true;
	}

	//moves player back if collision detected
	public static void bounce() {
		if ((lonk.getCoords().getX() - lastPos.getCoords().getX()) != 0) {
			if ((lonk.getCoords().getX() - lastPos.getCoords().getX()) > 0) 
				lonk.getCoords().adjustX(-32);
			if ((lonk.getCoords().getX() - lastPos.getCoords().getX()) < 0)
				lonk.getCoords().adjustX(+32);
		}
		if ((lonk.getCoords().getY() - lastPos.getCoords().getY()) != 0) {
			if ((lonk.getCoords().getY() - lastPos.getCoords().getY()) > 0) 
				lonk.getCoords().adjustY(-32);
			if ((lonk.getCoords().getY() - lastPos.getCoords().getY()) < 0) 
				lonk.getCoords().adjustY(+32);
		}
	}
}
