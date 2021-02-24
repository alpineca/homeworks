public class App {
	public static int frontObject;
	public static int batteryLevel = 0;
	public static String moveCommand;
	
	public static void main(String[] args) {
				
		if(frontObject == 0) {
			Cons.Echo("  ***  MOUSE FIGHTER ***  ");
			Cons.Echo("Lets fight some mouses!");
			envoirment();
		}
		else {
			envoirment();
		}
		
	}
	
	public static void envoirment() {
		Show.attackMenu();
		frontObject = Cons.GetInt();
		Movement.move(frontObject);
	}

}
