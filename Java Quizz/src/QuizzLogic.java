import java.util.Scanner;
public class QuizzLogic {
	
	public static int getNumberOfQuestionsForToday(int dayIndex, int numberOfVisitors) {
		boolean isRatingLow = numberOfVisitors < 500;
		boolean isRatingMedium = (numberOfVisitors >= 500) && (numberOfVisitors < 1000);
		boolean isRatingHigh = numberOfVisitors > 1000;
		
		//boolean isSpecialPriceAvailable = isRatingMedium || isRatingHigh;
		
		//boolean isSpecialPriceAvailable = (isRatingLow == false);
		boolean isSpecialPriceAvailable = !isRatingLow;
		
		boolean isMonday       = dayIndex == 0;
		boolean isQuestionEasy = isRatingLow && isMonday;
		
		boolean isQuestionHard = isSpecialPriceAvailable;
		
		
	}
	
	/*
	public static void int getDayOfTheWeek(int dayIndex) {
		return 0;
	}*/
	
	public static void displayNumberOfQuestionsForToday(int dayIndex, int numberOfVisitors) {
		
		int numberOfQuestions = getNumberOfQuestionsForToday(dayIndex);
		System.out.println(numberOfQuestionsForToday);
		
	}
	
	//Functionality
	// -> procedure
	public static void getNumberOfQuestionsForTodayByInput(int indexOfTheDay) {
		
		int indexOffset = indexOfTheDay - 1;
		
		String[] daysOfTheWeek = {
				"Monday",
				"Tuesday",
				"Wednesday",
				"Thursday",
				"Friday"
		};
		
		
		String todayIdentificator = daysOfTheWeek[indexOffset];	
		
		
		System.out.print("Enter number of questions for today ? : ");
		Scanner scanner = new Scanner(System.in);
		int numberOfQuestions = scanner.nextInt();
		System.out.println("We have " + numberOfQuestions + " questions"
								      + "for day " + todayIdentificator);
		
	}
	

}
