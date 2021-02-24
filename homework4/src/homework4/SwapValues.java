package homework4;

public class SwapValues {
	public static void ShowApp() {
		int numA = 56;
		int numB = 34;
		System.out.println("Системно въведени стойности: numA, numB = " + numA + ", " + + numB);
		
		numA = numA + numB;
		numB = numA - numB;
		numA = numA - numB;
		System.out.println("Размяна на стойността: numA, numB = " + numA + ", " + + numB);
		
		int choice = AppLogic.askTester();
		if(choice == 1) {
			ShowApp();			
		}else if(choice == 2) {
			AppLogic.showStart();
		}
	 }
}
