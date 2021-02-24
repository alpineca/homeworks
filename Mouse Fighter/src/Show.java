
public class Show {
	public static void attackMenu() {
		if(ActionProcessor.checkForMouse() == true) {
			Cons.Echo("THERE IS MOUSE AHEAD!");
			Cons.Echo("Please choose");
			Cons.Echo("  ****  ");
			Cons.Echo("[1] ATTACK THE MOUSE");
			Cons.Echo("[2] CHECK BATTERY");
			Cons.Echo("  ****  ");
			int attackIndex = Cons.GetInt();
			
			if((attackIndex == 1) && (App.batteryLevel >= 1)) {
				Cons.Echo("MOUSE IS UNDER ATTACK");
				if(ActionProcessor.isHardHit()) {
					Cons.Echo("*** HARD HIT ***");
					Cons.Echo("MOUSE IS ESCAPE, YOUR FURNITURE IS DEAD!");
					Comunicator.comunicate();
				}else {
					Cons.Echo("*** GOOD HIT ***");
					Cons.Echo("RIP MOUSE!");
					Comunicator.comunicate();
				}
				App.batteryLevel -= 1;
				objectsMenu();
			}
			else if(attackIndex == 2) {
				Battery.checkLevel(App.batteryLevel);
			}
			else if((attackIndex == 1) && (App.batteryLevel == 0)) {
				Battery.checkLevel(App.batteryLevel);
			}
		}
		else {
			objectsMenu();
		}
		
	}
	public static void objectsMenu() {
		Cons.Echo("What object is front of me?");
		Cons.Echo("  ****  ");
		Cons.Echo("[1] WALL");
		Cons.Echo("[2] CHAIR");
		Cons.Echo("[3] IT`S CLEAR");
		Cons.Echo("  ****  ");
	}
}
