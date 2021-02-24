import java.util.Random;

public class ActionProcessor {
	public static void attack(int batteryLevel) {
		Random rand = new Random();
		int randomNum = rand.nextInt(10);
		boolean isHardHit = (randomNum == 5);
		
	}
	
	public static boolean checkForMouse() {
		Random rand = new Random();
		int randomPixels = rand.nextInt(9999);
		return (randomPixels % 2 == 0);
			
	}
	
	public static boolean isHardHit() {
		Random rand = new Random();
		int randomNum = rand.nextInt(10);
		return (randomNum == 5);
	}
}
