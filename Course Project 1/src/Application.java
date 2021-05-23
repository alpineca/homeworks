import java.util.concurrent.TimeUnit;

import GUI.GUI;

public class Application {
    public static void main(String[] args)  {
//        new GUI(); 
        boolean rotate = false;
        while(rotate) {
        	new GUI();
        	try {
    			TimeUnit.MILLISECONDS.sleep(600);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        new GUI();
    }
}
