package homework4;

import java.text.DecimalFormat;

public class Rounder {
	public static void ShowApp() {
		double number = 112.45822134;
		Cons.Say("Системно въведено число: " + number);

		DecimalFormat df = new DecimalFormat("###.#####");
		Cons.Say("Числото след форматирането: " + df.format(number));
		
		int choice = AppLogic.askTester();
		if(choice == 1) {
			ShowApp();			
		}else if(choice == 2) {
			AppLogic.showStart();
		}
	}
}
