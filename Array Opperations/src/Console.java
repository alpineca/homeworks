import java.util.Scanner;

public class Console {
	public static void Log(int message) {
		System.out.println(message);
	}
	public static void Log(double message) {
		System.out.println(message);
	}
	public static void Log(String message) {
		System.out.println(message);
	}
	
	public static int Get() {
		Scanner input = new Scanner(System.in);
		return input.nextInt();
	}
	
	public static String GetStr() {
		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}
	
	public static double GetDouble() {
		Scanner input = new Scanner(System.in);
		return input.nextDouble();
	}
	
}
