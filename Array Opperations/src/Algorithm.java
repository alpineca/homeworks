//import java.util.Arrays;
import java.util.Random;

public class Algorithm {
	public static void displayArray(int[] collection) {
		System.out.print("\r\r{ ");
		for(int i = 0; i < collection.length; i++) {
			System.out.print(collection[i] + ", ");
		}
		System.out.println("}\r\r");
	}
	
	public static void sumArray(int[] collection, String operation) {
		int arrayLength = collection.length;
		int sum = 0;
		
		for(int i = 0; i < arrayLength; i++) {
			sum = sum + collection[i];
		}
		
		double average = sum / arrayLength;
		
		if(operation == "sum") {
			Console.Log("Общият сбор на въведените числа в системата е: " + sum);			
		}
		else if(operation == "average") {
			Console.Log("Средно-аритметично на въведените числа в системата е: " + average);
		}
		
	}
	
	public static void randomizer(int[] collection) {
		
		Random randomIndex = new Random(); 
        int arrayLength = collection.length;
        for (int i = (arrayLength-1); i > 0; i--) { 
               
            int j = randomIndex.nextInt(i+1); 
              
            int temp = collection[i]; 
            collection[i] = collection[j]; 
            collection[j] = temp; 
        }
        
        displayArray(collection);
		
	}
	
	public static void invert(int[] collection) {
		
		int arrayLength = collection.length;
		int tempArrayIndex = collection.length - 1;
		int[] invertedArray = new int[collection.length];
		
		for(int i = 0; i < arrayLength; i++) {
			invertedArray[tempArrayIndex] = collection[i];
			tempArrayIndex = tempArrayIndex - 1;
		}
		
		displayArray(invertedArray);
		
	}
	
	public static void bubbleSortAscend(int[] collection, String opperation) {
		
		boolean hasSwap = false;
		
		//Outer loop
		for(int outerIndex = 0; outerIndex < (collection.length - 1); outerIndex++) {
			
			int innerLoopLimit = (collection.length - outerIndex - 1);
			hasSwap = false;
			
			for(int innerIndex = 0; innerIndex < innerLoopLimit; innerIndex++) {
				
				int firstElement    = collection[innerIndex];
				int seccondElement  = collection[innerIndex + 1];
				
				if(firstElement > seccondElement) {
					
					//int swapValueContainer = firstElement;
					collection[innerIndex] = seccondElement;
					collection[innerIndex + 1] = firstElement;
					hasSwap = true;
					
				}
			}
			
			if(hasSwap == false) break;
			
		}
		
		if(opperation == "sort") {
			displayArray(collection);
		}
		else if(opperation == "minimalValue") {
			Console.Log("Най-малкото число в масива е: " + collection[0]);
		}
		
	}
	
	public static void bubbleSortDescend(int[] collection, String opperation) {
		
		boolean hasSwap = false;
		
		//Outer loop
		for(int outerIndex = 0; outerIndex < (collection.length - 1); outerIndex++) {
			
			int innerLoopLimit = (collection.length - outerIndex - 1);
			hasSwap = false;
			
			for(int innerIndex = 0; innerIndex < innerLoopLimit; innerIndex++) {
				
				int firstElement    = collection[innerIndex];
				int seccondElement  = collection[innerIndex + 1];
				
				if(firstElement < seccondElement) {
					
					//int swapValueContainer = firstElement;
					collection[innerIndex] = seccondElement;
					collection[innerIndex + 1] = firstElement;
					hasSwap = true;
					
				}
			}
			
			if(hasSwap == false) break;
			
		}
		
		if(opperation == "sort") {
			displayArray(collection);
		}
		else if(opperation == "maximalValue") {
			Console.Log("Най-голямото число в масива е: " + collection[0]);
		}
		
	}
	
	public static void binarySearch(int[] collection, int element) {
		
		int leftSideIndex = 0;
		int rightSideIndex = collection.length - 1;
		
		while(leftSideIndex <= rightSideIndex) {
			
			int middleIndex = (leftSideIndex + rightSideIndex) / 2;
			int pivotElement = collection[middleIndex];
			
			
			if(pivotElement == element) {
				Console.Log("Числото " + element + " е намерено в index " + middleIndex);
			}
			if(pivotElement < element) {
				leftSideIndex = middleIndex + 1;
			}
			else {
				rightSideIndex = middleIndex - 1;
			}
			
		}
		
		Console.Log("Числото " + element + "не е намерено в масива!");
	
	}
	
	public static void checkSimetry(int[] collection) {

	    int arrayLength = collection.length;
	    int i = 0;
	    
	    while (i<arrayLength/2&& collection[i] == collection[arrayLength-1-i]){
	    	i++;
	    }

	    if(i==arrayLength/2){
	        System.out.println("Този масив е симетричен!");
	        displayArray(collection);
	    }
	    else{
	    	System.out.println("Този масив НЕ Е симетричен!");
	    	displayArray(collection);
	    }
	   
	}
	
}
