package homework4;

import java.util.Scanner;

public class app {

	public static void main(String[] args) {
		AppLogic.showStart();
		int choice = Cons.Get();
		AppLogic.appLoader(choice);

}
}
