import java.util.Random;

public class Battery {
	public static void checkLevel(int batteryLevel) {
		Cons.Echo("      *****   BATTERY LEVEL: " + batteryLevel + "/4   *******   ");
		batteryMenu();
	}
	
	public static void recharge() {
		Random rand = new Random();
		int randomNum1 = rand.nextInt(1000);
		int randomNum2 = rand.nextInt(1000);
		
		if(randomNum1 > randomNum2) {
			App.batteryLevel = 4;
			Cons.Echo("RECHARGE IS SUCCSESSFULL!");
			Show.attackMenu();
		}
		else if(randomNum1 < randomNum2) {
			Cons.Echo("THERE IS NO ELECTRICITY!");
			batteryMenu();
		}
		else {
			recharge();
		}
		
	}
	
	private static void batteryMenu() {
		Cons.Echo("PLEASE CHOOSE");
		Cons.Echo("  ****  ");
		Cons.Echo("[1] RECHARGE");
		Cons.Echo("[2] SEARCH FOR MOUSE");
		Cons.Echo("  ****  ");
		
		int menuIndex = Cons.GetInt();
		if(menuIndex == 1) recharge();
		else if (menuIndex == 2) Show.attackMenu();
	}
}
