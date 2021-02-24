package homework4;

public class CheckTriangle {
	public static void ShowApp() {
		Cons.Say("Моля въведете дължина на страните на триъгълника:");
		
		Cons.Say("Страна A");
		double sideA = Cons.GetDouble();
		
		Cons.Say("Страна B");
		double sideB = Cons.GetDouble();
		
		Cons.Say("Страна C");
		double sideC = Cons.GetDouble();
		
		boolean isTriangle = (((sideA + sideB) > sideC) &&
							  ((sideA + sideC) > sideB) &&
							  ((sideB + sideC) > sideA));
		
		System.out.println(isTriangle);
		
		int choice = AppLogic.askTester();
		if(choice == 1) {
			ShowApp();			
		}else if(choice == 2) {
			AppLogic.showStart();
		}
	}
}
