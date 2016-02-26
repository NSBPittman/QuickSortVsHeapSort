/**
 * Nick Pittman
 * Quick Sort vs. Heap Sort
 * 2/22/2016
 * boo eclipse booo
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Heap;

public class QuickVsHeap {
	/**
	 * display prints out passed in List of Integers with space between each
	 * entry
	 * @param listIn
	 * List to be printed
	 */
	private static void display(List<Integer> listIn) {
		Iterator<Integer> itr = listIn.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next() + " ");
		}
		System.out.println();
	}
	
	private static void displayArr(Integer[] arrIn){
		for (Integer i : arrIn)
			System.out.print(i + " ");
		System.out.println();
	}


	/**
	 * populateInOrder fills a list with numbers in ascending order
	 * 
	 * @param listIn
	 *            list to be populated
	 * @param size
	 *            number of numbers to put into the list
	 */
	private static void populateInOrder(List<Integer> listIn, int size) {
		for (int i = 0; i < size; i++) {
			listIn.add(i + 1);
		}
	}


	
	private static void swapArr(Integer[] arr, double swapPercent){
		Random ugh = new Random();
		int numSwaps = (int) (arr.length * swapPercent);
		for (int i = 0; i < numSwaps; i++){
			int indOne = ugh.nextInt(arr.length);
			int indTwo = ugh.nextInt(arr.length);
			int numOne = arr[indOne];
			int numTwo = arr[indTwo];
			arr[indOne] = numTwo;
			arr[indTwo] = numOne;
		}
	}

	private static List<Integer> makeArray(int listSize) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		populateInOrder(al, listSize);
		return al;
	}
	
	private static void quickTest(Integer[] qArray){
		Quick.sort(qArray);

	}
	
	private static void heapTest(Integer[] hArray){
		Heap.sort(hArray);
	}
	
	private static void writeToFile(String filePath, String toWrite){
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\Documents\\1School\\SeniorYear\\Algorithms\\SORTING_NICK_PITTMAN\\src\\data.csv", true));
			bw.write(toWrite);
			bw.newLine();
			bw.flush();
			bw.close();
		} catch (IOException e) {
			System.out.print("UGH");
			e.printStackTrace();
		}
	}
	

	private static void test(ArrayList<Integer> qal, ArrayList<Integer> hal, double testsToRun, 
			double maxSwapPercent, int start, int end, String filePath, Map<Integer, Map<Double, Double>> outer, Map<Double, Double> inner){
		
		for (int i = start; i < end+1; i = i * 2) {
			System.out.println("Making ArrayList of length: " + i);
			qal = (ArrayList<Integer>) makeArray(i);
			hal = (ArrayList<Integer>) makeArray(i);
			
			for (double swapAmt = .0; swapAmt < maxSwapPercent; swapAmt += .05) {
				long qTime = 0;
				long hTime = 0;
				
				for (int j = 0; j < testsToRun; j++) {
					Integer[] qArr = qal.toArray(new Integer[qal.size()]);
					Integer[] hArr = hal.toArray(new Integer[hal.size()]);
					
					//Swap numbers around in arrays
					swapArr(qArr, swapAmt);
					swapArr(hArr, swapAmt);
										
					//QUICK TIME
					long qStartTime = System.currentTimeMillis();
					quickTest(qArr);
					long qEndTime = System.currentTimeMillis();
					long qDuration = (qEndTime - qStartTime);
					//System.out.println("quick: " + qDuration);
					qTime+=qDuration;
					
					//HEAP TIME						
					long hStartTime = System.currentTimeMillis();
					heapTest(hArr);
					long hEndTime = System.currentTimeMillis();
					long hDuration = (hEndTime - hStartTime);
					hTime+=hDuration;
				}
				System.out.println("ArraySize: " + i + " SwapAmount: " + swapAmt + " QuickTime: " 
						+ qTime/testsToRun + " HeapTime: " + hTime/testsToRun);
				
				inner.put((Double)swapAmt, (Double)(qTime/testsToRun));
				outer.put((Integer) i, inner);
				
				String toWrite = String.valueOf(i) + "/" + String.valueOf(swapAmt) + ":" + 
						String.valueOf(qTime/testsToRun) + "|" + String.valueOf(hTime/testsToRun);
						
				//writeToFile(filePath, toWrite);
				
				
			}
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> qal = new ArrayList<Integer>();
		ArrayList<Integer> hal = new ArrayList<Integer>();
		Map<Integer, Map<Double, Double>> outer = new HashMap<Integer, Map<Double, Double>>();
		Map<Double, Double> inner = new HashMap<Double, Double>();
		long testsToRun = (long) 100.0000;
		double maxSwapPercent = 1.05;
		int start = 1024;
		int end = 2048;//65536;
		
		
		//write to csv sorry Ali
		//PrinterWriter file = new PrinterWriter("E:\\Documents\\1School\\SeniorYear\\Algorithms\\SORTING_NICK_PITTMAN\\src\\data");
		String filePath = "E:\\Documents\\1School\\SeniorYear\\Algorithms\\SORTING_NICK_PITTMAN\\src\\data.csv";

		//test1(qal, testsToRun, maxSwapPercent, start, end, filePath);
		test(qal, hal, testsToRun, maxSwapPercent, start, end, filePath, outer, inner);
		
		//access nested hashmap
		System.out.println(inner.get(.3));

	}
}
