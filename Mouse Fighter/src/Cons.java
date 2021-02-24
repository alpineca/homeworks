import java.util.Scanner;

public class Cons {
	public static int GetInt() {
		Scanner input = new Scanner(System.in);
		return input.nextInt();
	}
	
	public static String GetStr() {
		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}
	
	public static void Echo(String message) {
		System.out.println(message);
	}
	
	public static void Echo(int message) {
		System.out.println(message);
	}
	
}
