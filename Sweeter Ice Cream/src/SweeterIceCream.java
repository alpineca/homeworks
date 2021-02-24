import java.util.Scanner;

public class SweeterIceCream {
	public static void main(String[] args) {
		//Дефиниране на константи
		final int SHOPS_COUNT = 10;//Задача 3
		final int LAST_DAY_PRODUCTION = 3000;
		final int MINIMAL_DAY_PRODUCTION = 2000;//Задача 5
	
		System.out.println("Въдедете своето име: ");
	
		Scanner reader = new Scanner(System.in);
		String myName = reader.nextLine();
		
		System.out.print("Здравей, " + myName);
	
		System.out.print("Въведете необходим брой произведени сладоледи за днес: ");
		int iceCreamsPerDay = reader.nextInt();
		
		int iceCreamsPerShop = iceCreamsPerDay / SHOPS_COUNT;
		System.out.print("Количество сладоледи за един магазин: " + iceCreamsPerShop);
		
		String isProductionIsEnough = (iceCreamsPerDay < LAST_DAY_PRODUCTION)		   											?"Low capacity!"
				   										    :"Good Job!";
		System.out.println(isProductionIsEnough);

		String isProductionNotMinimal = (iceCreamsPerDay < MINIMAL_DAY_PRODUCTION)
                										?"Дневното производство трябва да бъде над " + MINIMAL_DAY_PRODUCTION + " броя"
                										:"Количеството е ОК. Продължаваме напред!";
		System.out.println(isProductionNotMinimal);
	}

}
