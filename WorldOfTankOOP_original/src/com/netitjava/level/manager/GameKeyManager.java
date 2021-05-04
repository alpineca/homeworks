package com.netitjava.level.manager;

public class GameKeyManager {
	
	public static final String FORWARD 	= "w";
	public static final String BACKWARD 	= "s";
	public static final String LEFT 		= "a";
	public static final String RIGHT 		= "d";
	public static final String ACTION_FIRE 				= "f";
	
	public static boolean isDirectionKeyValid(String keyCodeParameter) {
		
		String keyCode = keyCodeParameter.toLowerCase();
		
		return 	process(keyCode).equals(FORWARD) 	||
				process(keyCode).equals(LEFT) 		||
				process(keyCode).equals(BACKWARD) 	||
				process(keyCode).equals(RIGHT);
	}
	
	public static boolean isFireKey(String keyCodeParameter) {
		
		return 	process(keyCodeParameter).equals(ACTION_FIRE);
	}
	
	private static String process(String keyCodeParameter) {
		return keyCodeParameter.toLowerCase();
	}

}
