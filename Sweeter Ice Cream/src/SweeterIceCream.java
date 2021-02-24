import java.util.Scanner;

public class SweeterIceCream {
	public static void main(String[] args) {
		//Дефиниране на константи
		final int SHOPS_COUNT = 10;//Задача 3
		final int LAST_DAY_PRODUCTION = 3000;
		final int MINIMAL_DAY_PRODUCTION = 2000;//Задача 5
		
		
	/*
		1. Клиентите ни искат да имат възможност да интегрират система за конфигурация на
		технологичния процес, искат да могат да си въвеждат данни от командния ред и по този
		начин системата да научава повече за тях, да ги опознава и да прави работата им по
		приятна. Искаме като за начало да можем да въвеждаме името си от командния ред и да
		получаваме подходящо съобщение от системата. Като например здрасти и моето име.
	*/
		System.out.println("Въдедете своето име: ");
	
		Scanner reader = new Scanner(System.in);
		String myName = reader.nextLine();
		
		System.out.print("Здравей, " + myName);
	
	/*
		2. Продължаваме с конфигурационния процес, след като системата знае нашето име искаме
		да можем да въведем количеството сладоледи които трябва да се произведат за деня,
		количеството сладоледи винаги е цяло число.
	*/
		System.out.print("Въведете необходим брой произведени сладоледи за днес: ");
		int iceCreamsPerDay = reader.nextInt();
		
	/*	
		3. Сладоледите които произвеждаме се изпращат до 10 магазина в страната, за всеки
		магазин произвеждаме еднакво количество сладоледи, затова искаме да получим
		бройката на всички сладоледи които трябва да доставим след като въведем количеството
		което ни е необходимо за деня.
	*/
		int iceCreamsPerShop = iceCreamsPerDay / SHOPS_COUNT;
		System.out.print("Количество сладоледи за един магазин: " + iceCreamsPerShop);
		
	
	/*	
		4. Искаме да имаме възможност да въведем количеството сладоледи които сме произвели
		вчера. Ако количеството от вчера е по голямо от това днес искаме да изведем на екрана
		съобщение. Low capacity в противен случай трябва да изведем съобщения. Good job.
	*/
		String isProductionIsEnough = (iceCreamsPerDay < LAST_DAY_PRODUCTION)
				   											?"Low capacity!"
				   										    :"Good Job!";
		System.out.println(isProductionIsEnough);
	
	/*
    	5. Искаме да можем да дефинираме количество сладоледи под което да не можем да
		паднем в нито един момент когато правим производство на дневна база. 
	*/
		String isProductionNotMinimal = (iceCreamsPerDay < MINIMAL_DAY_PRODUCTION)
                										?"Дневното производство трябва да бъде над " + MINIMAL_DAY_PRODUCTION + " броя"
                										:"Количеството е ОК. Продължаваме напред!";
		System.out.println(isProductionNotMinimal);
	}

}
