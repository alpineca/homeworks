
public class SerialNumberProcessor {
	final static int NORMAL_COEFICIENT_LOWER_BOUND = 99999;
	final static int NORMAL_COEFICIENT_UPPER_BOUND = 999999;
	final static int EA_COEFICIENT_LOWER_BOUND     = 99;
	final static int EXTRA_MEAL_COEFICIENT         = 3;

	public static boolean isNormal(int serialNumber) {
		return (serialNumber > NORMAL_COEFICIENT_LOWER_BOUND) && 
	            (serialNumber < NORMAL_COEFICIENT_UPPER_BOUND);
	}
	
	
	
	public static boolean isLagger(int serialNumber) {
		return (serialNumber > NORMAL_COEFICIENT_UPPER_BOUND);
	}

	
	
	
	public static boolean isEarlyAdopter(int serialNumber) {
		return (serialNumber > EA_COEFICIENT_LOWER_BOUND && 
	             !isNormal(serialNumber) && !isLagger(serialNumber));
	}
	
	
	
	
	public static boolean isVIP(int serialNumber) {
		return (serialNumber % 2 == 0) || 
				(serialNumber > 0 && serialNumber < 100);
	}

	
	
	
	public static boolean isSuperVip(int serialNumber) {
		
		return isVIP(serialNumber) && isApplicableForExtraMeal(serialNumber);
		
	}
	
	
	
	
	public static boolean isApplicableForExtraMeal(int serialNumber) {
		int getNextToLastDigit = (serialNumber % 100) / 10;
		return getNextToLastDigit > EXTRA_MEAL_COEFICIENT;
	}
	
	
	
	

}
