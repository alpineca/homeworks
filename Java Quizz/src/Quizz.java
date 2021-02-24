import java.util.Scanner;

class Quizz {

	public static void main(String[] args) {
		
		int numberOfRounds = 4;
		int numberOfQuestionInRound1 = 10;
		int numberOfQuestionInRound2 = 7;
		int numberOfQuestionInRound3 = 5;
		
		int playerScore = 0;
		System.out.println(playerScore);
		
		
		String mainTitle = "Quizz Show";
		String subTitle = "Hello World!";
		String titleOfTheShow = mainTitle + subTitle;
		
		System.out.print(titleOfTheShow);
		
		int isApplauseSignOnInNumber = 1;
		boolean isApplauseSignOnBoolean = true;
		
		String checkId = "П" + 45678;
		System.out.println(checkId);
		
		double netPrice = 10.5;
		
		//Matrix
		Scanner reader = new Scanner(System.in);
		String myName = reader.nextLine();
		
		System.out.println("Здравей, " + myName);
		
		//QuizzLogic.getNumberOfQuestionsForToday(1);
		
		int numberOfQuestionsForToday = QuizzLogic.getNumberOfQuestionsForToday(1);
		System.out.println(numberOfQuestionsForToday);
		
		
	}
}
