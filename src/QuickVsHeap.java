/**
 * Nick Pittman
 * Quick Sort vs. Heap Sort
 * 2/22/2016
 * boo eclipse booo
 */
import java.io.BufferedWriter;
import java.io.File;
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
	 * populate fills list with random numbers within specified range
	 * 
	 * @param listIn
	 *            list to be populated
	 * @param size
	 *            number of numbers to put into the list
	 * @param randRange
	 *            range for random, random numbers will be in range (0,
	 *            randRange)
	 */
	private static void populate(List<Integer> listIn, int size, int randRange) {
		Random r = new Random();
		while (size > 0) {
			listIn.add(r.nextInt(randRange));
			size--;
		}
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

	/**
	 * swap swaps numbers in list a percentage of the size times
	 * 
	 * @param list
	 *            list to be have Integers swapped
	 * @param swapPercent
	 *            percentage to swap, number of swaps = swapPercent*list.size()
	 */
	private static void swap(List<Integer> list, double swapPercent) {
		Random ugh = new Random();
		int numSwaps = (int) (list.size() * swapPercent);
		for (int i = 0; i < numSwaps; i++) {
			int indOne = ugh.nextInt(list.size());
			int indTwo = ugh.nextInt(list.size());
			int numOne = list.get(indOne);
			int numTwo = list.get(indTwo);
			list.set(indTwo, numOne);
			list.set(indOne, numTwo);
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

	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		long testsToRun = (long) 100.0000;
		double maxSwapPercent = 1.05;
		int start = 1024;
		int end = 65536;
		long sTime = System.currentTimeMillis();
		
		//write to csv sorry Ali
		File file = new File("C:\\Users\\Nick\\Documents\\AlgoWorkSpace\\QuickSortVsHeapSort\\src\\data.txt");
        FileWriter fw = null;
        //ugh
        BufferedWriter writer = null;
		try {
			fw = new FileWriter(file);
			writer = new BufferedWriter( fw );
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		for (int i = start; i < end+1; i = i * 2) {
			System.out.println("Making ArrayList of length: " + i);
			al = (ArrayList<Integer>) makeArray(i);
			for (double swapAmt = .0; swapAmt < maxSwapPercent; swapAmt += .05) {
				long qTime = 0;
				long hTime = 0;
				for (int j = 0; j < testsToRun; j++) {
					ArrayList<Integer> qCopy = al;
					ArrayList<Integer> hCopy = al;
					swap(qCopy, swapAmt);
					swap(hCopy, swapAmt);
					Integer[] qArray = qCopy.toArray(new Integer[al.size()]);
					Integer[] hArray = hCopy.toArray(new Integer[al.size()]);
					
					//QUICK TIME
					long qStartTime = System.currentTimeMillis();
					quickTest(qArray);
					long qEndTime = System.currentTimeMillis();
					long qDuration = (qEndTime - qStartTime);
					qTime+=qDuration;
					
					//HEAP TIME
					long hStartTime = System.currentTimeMillis();
					heapTest(hArray);
					long hEndTime = System.currentTimeMillis();
					long hDuration = (hEndTime - hStartTime);
					hTime+=hDuration;
				}
				System.out.println("ArraySize: " + i + " SwapAmount: " + swapAmt + " QuickTime: " 
						+ qTime/testsToRun + " HeapTime: " + hTime/testsToRun);
				//write to file here
				try {
					writer.write(i + " " + swapAmt + " " + qTime/testsToRun + " " + hTime/testsToRun);
					writer.newLine();
				} catch (IOException e) {
					System.out.println("writer failed to write");
				}
			}
		}
		long eTime = System.currentTimeMillis();
		long totalTime = (eTime - sTime);
		System.out.println(totalTime/1000 + " in seconds");
		try {
			writer.flush();
			writer.close();
		    fw.close();
		} catch (IOException e) {
			System.out.println("no close");
		}
	}
}
