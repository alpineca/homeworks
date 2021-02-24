package homework4;

public class Separator {
	public static void ShowApp() {
		
		Cons.Say("Въведете четирицифрено число: ");
		int chislo = Cons.Get();
		int numLength = String.valueOf(chislo).length();
		
		if(numLength == 4) {
			int firstInt = (chislo % 10000) / 1000;
			int seccondInt = (chislo % 1000) / 100;
			int thirthInt = (chislo % 100) / 10;
			int fourthInt = chislo % 10;
			
			Cons.Say("Въведеното от вас число обърнато наобратно е: ");
			Cons.Say(fourthInt + "" + thirthInt + "" + seccondInt + "" + firstInt);
		}
		else {
			Cons.Say("Въведеното число не е четирицифрено!");
		}
		
		int choice = AppLogic.askTester();
		if(choice == 1) {
			ShowApp();			
		}else if(choice == 2) {
			AppLogic.showStart();
		}
		
	}

}
