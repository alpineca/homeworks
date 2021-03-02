public class MenuProcessor {
	
	public static String[] opperationOptions = {
							"[1] Сортиране на въведените числа във възходящ ред",
							"[2] Сортиране на въведените числа във низходящ ред",
							"[3] Търсене на позиция на конкретно число",
							"[4] Разбъркване на числата",
							"[5] Изчисляване на сбора на всички числа",
							"[6] Намиране на най-голямото число",
							"[7] Намиране на най-малкото число",
							"[8] Намиране на средно-аритметично на числата",
							"[9] Проверка за симетричност на масива от числа",
							"[10] Обръщане на масива от числа",
							"[11] Визуализирай въведените числа\r",
							"[12] Изход от програмата",
							};
//	"[3] Търсене на позиция на конкретно число",
//	"[9] Проверка за симетричност на масива от числа",
	public static void opperationNavigator(int choice, int collection[]) {
		if(choice == 1) {
			Algorithm.bubbleSortAscend(collection, "sort"); 
			askAgain(collection);
		}
		else if(choice == 2) {
			Algorithm.bubbleSortDescend(collection, "sort"); 
			askAgain(collection);
		}
		else if(choice == 3) {
			Console.Log("Моля въведете числото което искате да намерите в масива:");
			int element = Console.Get();
			Algorithm.binarySearch(collection, element); 
			askAgain(collection);
		}
		else if(choice == 4) {
			Algorithm.randomizer(collection); 
			askAgain(collection);
		}
		else if(choice == 5) {
			Algorithm.sumArray(collection, "sum"); 
			askAgain(collection);
		}
		else if(choice == 6) {
			Algorithm.bubbleSortDescend(collection, "maximalValue"); 
			askAgain(collection);
		}
		else if(choice == 7) {
			Algorithm.bubbleSortAscend(collection, "minimalValue"); 
			askAgain(collection);
		}
		else if(choice == 8) {
			Algorithm.sumArray(collection, "average"); 
			askAgain(collection);
		}
		else if(choice == 9) {
			Algorithm.checkSimetry(collection); 
			askAgain(collection);
		}
		else if(choice == 10) {
			Algorithm.invert(collection); 
			askAgain(collection);
		}
		else if(choice == 11) {
			Algorithm.displayArray(collection); 
			askAgain(collection);
		}
		else if(choice == 12) System.exit(0);
	}
	
	public static void printMenu(String[] options) {
		Console.Log("*** МОЛЯ ИЗБЕРЕТЕ ***\r");
		for(int i = 0; i < options.length; i++) {
			Console.Log(options[i]);
		}
	}
	
	private static void askAgain(int collection[]) {
		MenuProcessor.printMenu(MenuProcessor.opperationOptions);
		int menuChoice = Console.Get();
		MenuProcessor.opperationNavigator(menuChoice, collection);
	}
	
}
