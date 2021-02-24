package homework4;

public class ReverseInt {
	public static void ShowApp() {
		Cons.Say("Моля въведете четирицифрено число: ");
		final int numInput = Cons.Get();
		int num = numInput;
		int numLength = String.valueOf(num).length();
		int reversed = 0;
		int counter = 4;
		
		if(numLength == 4) {
			while(counter != 0) {
	            int digit = num % 10;
	            reversed = reversed * 10 + digit;
	            num /= 10;
	            counter -= 1;
	        }
			
			if(reversed > numInput) {
				Cons.Say(reversed);
			}else {
				Cons.Say(numInput);
			}
		}
		else {
			Cons.Say("Въведеното число не е четирицифрено");
		}
				
		int choice = AppLogic.askTester();
		if(choice == 1) {
			ShowApp();			
		}else if(choice == 2) {
			AppLogic.showStart();
		}
	}

}
