
public class Comunicator {
	private static int numbers = 10;
	
	public static void comunicate() {
		checkNumbers();
		boolean isEven = (numbers % 2 == 0);
		
		if(isEven) {
			Cons.Echo("I am a Robotttt " + numbers);
			numbers--;
		}
		else numbers--;
	}
	
	private static void checkNumbers() {
		if(numbers == 0) {
			numbers = 10;
		}
	}
}
