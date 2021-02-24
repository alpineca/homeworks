
public class MenuProcessor {

	private static final int TASK_ORDER__NEW_ITEM = 1;
	private static final int TASK_CANCEL__ORDER_REQUEST = 2;
	private static final int TASK_PAY__ORDER_REQUEST = 3;
	private static final int TASK_INFO__ORDER_REQUEST = 5;
	private static final int TASK_INFO__ORDER_LIST = 7;

	private static int finalPrice = 0;

	private static int orderID = 0;
	private static int[] orderCollection = new int[5];

	public static void orderMenuItem(int serialNumber) {
		displayMenu();

		int menuChoiceIndex = Console.promt("Please choose: ");

		if (menuChoiceIndex == TASK_ORDER__NEW_ITEM) {
			taskOrderNewItem(serialNumber);
		}
		if (menuChoiceIndex == TASK_CANCEL__ORDER_REQUEST) {
			taskCancelOrderRequest();
		}
		if (menuChoiceIndex == TASK_PAY__ORDER_REQUEST) {
			taskPayOrderRequest(serialNumber);
		}
		if (menuChoiceIndex == TASK_INFO__ORDER_REQUEST) {
			taskInfoOrderRequest(serialNumber);
		}
		if (menuChoiceIndex == TASK_INFO__ORDER_LIST) {
			taskInfoOrderList(serialNumber);
		}
	}

	private static int getMenuItemPrice(int menuItemIndex) {
		if (menuItemIndex == 1)
			return 5;
		if (menuItemIndex == 2)
			return 7;
		if (menuItemIndex == 3)
			return 9;
		return 0;
	}

	private static String getMenuItem(int menuItemIndex) {
		if (menuItemIndex == 1)
			return "Meat";
		if (menuItemIndex == 2)
			return "Salad";
		if (menuItemIndex == 3)
			return "Desert";
		return null;
	}

	private static void displayMenu() {

		Console.log("** MENU **");
		Console.log("[1] Meat");
		Console.log("[2] Salad");
		Console.log("[3] Desert");

		if (finalPrice == 0) {
			Console.log("Make your choice:");
			Console.log("{1} Order item");
			Console.log("{2} Cancel order request");
		}
		if (finalPrice > 0) {
			Console.log("Make your choice:");
			Console.log("{1} Order item");
			Console.log("{2} Cancel order request");
			Console.log("{3} Pay the order(Finish)");
			Console.log("{4} Remove item from my order request");
			Console.log("{5} Give information regarding my order");
			Console.log("{6} Give information regarding ingridients of item");
			Console.log("{7} Give list of items");
		}

	}

	private static void changeID(int menuItemIndex) {
		 
		if (orderCollection.length > orderID) {
			orderCollection[orderID] = menuItemIndex;
			orderID += 1;
		}
	}

	private static void taskOrderNewItem(int serialNumber) {
		int menuItemIndex = Console.promt("Please choose menu item: ");
		finalPrice += getMenuItemPrice(menuItemIndex);
		changeID(menuItemIndex);
		orderMenuItem(serialNumber);
	}

	private static void taskInfoOrderRequest(int serialNumber) {
		Console.log("Your order request price until now is: ");
		Console.log("Final price: " + finalPrice);
		Console.log("Final price + discount: " + PaymentProcessor.getPrice(serialNumber, finalPrice));
		orderMenuItem(serialNumber);
	}

	private static void taskPayOrderRequest(int serialNumber) {
		Console.log("Final price: " + PaymentProcessor.getPrice(serialNumber, finalPrice));
		Console.log("Have a nice day");
	}

	private static void taskCancelOrderRequest() {
		Console.log("Have nice day");
	}

	private static void taskInfoOrderList(int serialNumber) {

		for (int i = 0; i < orderCollection.length; i++) {
			
			if(orderCollection[i] != 0) Console.log("  ***  " + getMenuItem(orderCollection[i]) + "  ***  ");

		}

		
		
//		  if(orderCollection[0] != 0) Console.log(getMenuItem(orderCollection[0]));
//		  if(orderCollection[1] != 0) Console.log(getMenuItem(orderCollection[1]));
//		  if(orderCollection[2] != 0) Console.log(getMenuItem(orderCollection[2]));
//		  if(orderCollection[3] != 0) Console.log(getMenuItem(orderCollection[3]));
//		  if(orderCollection[4] != 0) Console.log(getMenuItem(orderCollection[4]));
		 

	}

}
