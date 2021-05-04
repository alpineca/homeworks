package com.netitjava.util;

import java.util.Random;
import java.util.Scanner;

public final class Console {
	
	private Console() {
		
	}
	
	public static void log(String message) {
		System.out.println(message);
	}
	public static void log(int message) {
		System.out.println(message);
	}

	public static String promtString() {
		
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}	
	
	public static String promtString(String message) {
		
		log(message);
		return promtString();
	}
	
	public static int promtInt() {
		
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}	
	
	public static int promtInt(String message) {
		
		log(message);
		return promtInt();
	}

	public static void clear(int linesCount) {
		for(int i = 0; i < linesCount; i++) {			
			System.out.println("");
		}
		
	}
	public static int randomNumber(int boundMin, int boundMax) {
		Random rand = new Random();
		int randomNumber = rand.nextInt(boundMax);
		
		while(randomNumber < boundMin) {
			randomNumber = rand.nextInt(boundMax);
		}
		return randomNumber;
	}
	
}