package homework4;

public class TempConvert {
	public static void ShowApp() {
		double convertedTemp = 0;
		Cons.Say("Моля изберете:");
		Cons.Say("[1] От Целзий към Фаренхайт");
		Cons.Say("[2] От Фаренхайт към Целзий");
		
		int type = Cons.Get();
		
		Cons.Say("Въведете температура:");
		double temp = Cons.GetDouble();
		
		if(type == 1) {
			convertedTemp = (temp * 1.8) + 32;
		}else if(type == 2) {
			convertedTemp = (temp - 32) / 1.8;
		}
		
		double finalTemp = Math.round(convertedTemp * 100.0) / 100.0;
		
		Cons.Say(finalTemp);
		
		int choice = AppLogic.askTester();
		if(choice == 1) {
			ShowApp();			
		}else if(choice == 2) {
			AppLogic.showStart();
		}
	}
}
