package homework4;

import java.util.Scanner;

public class Cons {
	public static void Say(int message) {
		System.out.println(message);
	}
	public static void Say(double message) {
		System.out.println(message);
	}
	public static void Say(String message) {
		System.out.println(message);
	}
	
	public static int Get() {
		Scanner input = new Scanner(System.in);
		return input.nextInt();
	}
	
	public static double GetDouble() {
		Scanner input = new Scanner(System.in);
		return input.nextDouble();
	}
	
}
