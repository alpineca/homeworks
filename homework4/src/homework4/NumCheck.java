package homework4;

public class NumCheck {
	public static void ShowApp() {
		Cons.Say("Моля въведете едно число: ");
		double chislo = Cons.GetDouble();
		
		if(chislo > 0) {
			chislo *= 10;
		}else if(chislo == 0) {
			chislo -= 10;
		}else if(chislo < 0) {
			chislo /= 10;
		}
		
		Cons.Say(chislo);
		
		int choice = AppLogic.askTester();
		if(choice == 1) {
			ShowApp();			
		}else if(choice == 2) {
			AppLogic.showStart();
		}
	}
}
