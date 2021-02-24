import java.util.Scanner;

public class Application {
	
	static int mealMenuIndex;
	
	public static void displayMenu(int cardSerialNumber) {
		
		int mealIndex = Console.promt("Make your choice:");
		
		
		if(mealIndex == 1 || mealIndex == 2 || mealIndex == 3 || mealIndex == 4) {
			mealMenuIndex = mealIndex - 1;
			processMenu(mealMenuIndex, cardSerialNumber);
		}
		else {
			Console.log("You choose incorrect menu index!");
			displayMenu(cardSerialNumber);
		}
		
		
	}
	
	public static void processMenu(int mealMenuIndex, int cardSerialNumber) {
		
		int priceCollection[] = {10, 7, 4, 1};
		int mealPrice = priceCollection[mealMenuIndex];
		
	}

	public static void main(String[] args) {
		
		int serialNumber = Console.promt("Моля въведете серийния номер на вашата клиентска карта: ");
		MenuProcessor.orderMenuItem(serialNumber);
		
		
		
		//displayMenu(serialNumber);
				

	}

}
