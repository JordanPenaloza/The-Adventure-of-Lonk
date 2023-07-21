/* This will handle the "Hot Key" system. */

package Main;


import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';	
	private static stopWatchX sw = new stopWatchX(100); 	// Debouncing Timer
	private static stopWatchX timer = new stopWatchX(1); 	// Timer for movement
	private static int i, j; 
	
	// Static Method(s)
	public static void processKey(char key){
		if(key == ' ')				return;
		 //Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here! */
		switch(key){
		case '%':								// ESC key
			if(key == ' ')				return;
			// Debounce routine below...
			if(key == last)
				
			System.exit(0);
			break;
		
			//walk up
		case 'w':
			Main.trigger1 = "";
			Main.trigger2 = "";
			if (timer.isTimeUp()){
				Main.lastPos.setCoords(Main.lonk.getCoords().getX(), Main.lonk.getCoords().getY());
				Main.lonk.getCoords().adjustY(-32);
				Main.lonk.setTag("lonkk"+j); 
				j++;
				if (j >= 4){
					j = 0;
				}
				timer.resetWatch();
			}
			
			break;
			//walk left
		case 'a':
			Main.trigger1 = "";
			Main.trigger2 = "";										// Toggles off dialogue text					// Toggles off dialogue background
			if (timer.isTimeUp()){									
				Main.lastPos.setCoords(Main.lonk.getCoords().getX(), Main.lonk.getCoords().getY());
				Main.lonk.getCoords().adjustX(-32);
				Main.lonk.setTag("lonkL"+i);
				i++;
				if (i >= 4){
					i = 0;
				}
				timer.resetWatch();
			}
			break;
			//walk back
		case 's':
			Main.trigger1 = "";
			Main.trigger2 = "";
			if (timer.isTimeUp()){
				Main.lastPos.setCoords(Main.lonk.getCoords().getX(), Main.lonk.getCoords().getY());
				Main.lonk.getCoords().adjustY(32);
				Main.lonk.setTag("lonk"+j);
				j++;
				if (j >= 4){
					j = 0;
				}
				timer.resetWatch();
			}
			break;
			//walk right
		case 'd':
			Main.trigger1 = "";
			Main.trigger2 = "";
			if (timer.isTimeUp()){
				Main.lastPos.setCoords(Main.lonk.getCoords().getX(), Main.lonk.getCoords().getY());
				Main.lonk.getCoords().adjustX(32);
				Main.lonk.setTag("lonkR"+i);
				i++;
				if (i >= 4){
					i = 0;
				}
				timer.resetWatch();
			}
			break;
			//object interaction
		  case '$':
		        if(Main.checkCollision(Main.lonkBox, Main.house)){
		            Main.trigger1 = "my silly little house";
		        }
		        
		        if(Main.checkCollision(Main.lonkBox, Main.cellar)){
		            Main.trigger2 = "oh naur don't go down there";
		        }

		        break;
		
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		}
	}
}