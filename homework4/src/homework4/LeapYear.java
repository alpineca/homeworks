package homework4;

public class LeapYear {
	public static void ShowApp() {
		Cons.Say("Въведете година за проверка: ");
		int year = Cons.Get();
		
		boolean isLeapYear = ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
		
		if(isLeapYear) {
			Cons.Say(year + " година е високостна");
		}else {
			Cons.Say(year + " година НЕ Е високостна");
		}
		
		int choice = AppLogic.askTester();
		if(choice == 1) {
			ShowApp();			
		}else if(choice == 2) {
			AppLogic.showStart();
		}
	}
	
}
