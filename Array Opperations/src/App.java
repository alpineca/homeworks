public class App {

	public static void main(String[] args) {
		Console.Log("Моля въведете колко числа ще съдържа вашият запис в системата:");
		int collectionCount = Console.Get();
		
		Console.Log("Изберете как ще въведете числата в системата:\r");
		Console.Log("[1] Едно по едно");
		Console.Log("[2] Всички заедно (Например: 1, 12, 5, 40)");
		int inputCollectionType = Console.Get();
		
		int[] collection = new int[collectionCount];
		
		if(inputCollectionType == 1) {
			for(int i = 0; i < collectionCount; i++) {
				int numIndex = i + 1;
				Console.Log("Моля въведете число номер " + numIndex);
				int numInput = Console.Get();
 				collection[i] = numInput;
			}
			
			Console.Log("Въведохте всички числа!\r");
			MenuProcessor.printMenu(MenuProcessor.opperationOptions);
			int menuChoice = Console.Get();
			MenuProcessor.opperationNavigator(menuChoice, collection);
		}
		else if(inputCollectionType == 2) {
			Console.Log("Моля въведете всички числа едно след друго: ");
			String allNumsInput = Console.GetStr();
			String[] stringIntArray = allNumsInput.split(",");
			
			if(stringIntArray.length == collectionCount) {
				
				for(int i = 0; i < collectionCount; i++) {
					collection[i] = Integer.parseInt(stringIntArray[i]);
				}
				Console.Log("Въведохте всички числа!\r");
				MenuProcessor.printMenu(MenuProcessor.opperationOptions);
				int menuChoice = Console.Get();
				MenuProcessor.opperationNavigator(menuChoice, collection);
				
			}
			else if(stringIntArray.length > collectionCount){
				Console.Log("Въвели сте повече числа отколкото трябва да въведете!");
				main(null);
			}
			else if(stringIntArray.length < collectionCount){
				Console.Log("Въвели сте по-малко числа отколкото трябва да въведете!");
				main(null);
			}
			
		}
	}

}
