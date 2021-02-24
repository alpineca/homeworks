import java.util.Random;

public class Movement {
	
	public static void move(int frontObject){
		
		if(frontObject == 1) 		moveLeftRight();
		else if(frontObject == 2)   moveJump();
		else if(frontObject == 3)   moveForward();
	}
	
	
	
	private static void moveLeftRight() {
		Random rand = new Random();
		int randomAction = rand.nextInt(2);
		randomAction += 1;
		
		if(randomAction == 1) {
			Cons.Echo("Mmmm, there is a wall front of me, im going left!");
			App.envoirment();
		}
		else if(randomAction == 2) {
			Cons.Echo("Mmmm, there is a wall front of me, im going right!");
			App.envoirment();
		}
	}
	
	private static void moveJump() {
		Cons.Echo("Im going to jump over this chair!");
		App.envoirment();
	}
	
	private static void moveForward() {
		Cons.Echo("Ok, lets go forward!");
		App.envoirment();
	}
}
