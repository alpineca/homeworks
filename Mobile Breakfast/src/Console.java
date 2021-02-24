import java.util.Scanner;

public class Console {
	public static void log(String message) {
		System.out.println(message);
	}
	
	public static void log(int message) {
		System.out.println(message);
	}
	
	public static int promt(String message) {
		Scanner input = new Scanner(System.in);
		log(message);
		return input.nextInt();
		
	}

}
