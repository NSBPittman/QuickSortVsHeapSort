/**
 * Nick Pittman
 * Quick Sort vs. Heap Sort
 * 2/22/2016
 * boo eclipse booo
 */
import java.util.*;

	public class disorder {
		/**
		 * display prints out passed in List of Integers with space between each entry
		 * @param listIn List to be printed
		 */
		private static void display(List<Integer> listIn){
			Iterator<Integer> itr = listIn.iterator();
			while(itr.hasNext()){
				System.out.print(itr.next() + " ");
			}
			System.out.println();
		}
		
		/**
		 * populate fills list with random numbers within specified range
		 * @param listIn list to be populated
		 * @param size number of numbers to put into the list
		 * @param randRange range for random, random numbers will be in range (0, randRange)
		 */
		private static void populate(List<Integer> listIn, int size, int randRange){
			Random r = new Random();
			while (size > 0){
				listIn.add(r.nextInt(randRange));
				size--;
			}
		}
		
		/**
		 * populateInOrder fills a list with numbers in ascending order
		 * @param listIn list to be populated
		 * @param size number of numbers to put into the list
		 */
		private static void populateInOrder(List<Integer> listIn, int size){
			for (int i = 0; i < size; i++){
				listIn.add(i+1);
			}
		}
		
		/**
		 * swap swaps numbers in list a percentage of the size times
		 * @param list list to be have Integers swapped
		 * @param swapPercent percentage to swap, number of swaps = swapPercent*list.size()
		 */
		private static void swap(List<Integer> list, double swapPercent){
			Random ugh = new Random();
			int numSwaps = (int) (list.size()*swapPercent);
			for (int i = 0; i < numSwaps; i++){
				int indOne = ugh.nextInt(list.size());
				int indTwo = ugh.nextInt(list.size());
				int numOne = list.get(indOne);
				int numTwo = list.get(indTwo);
				list.set(indTwo, numOne);
				list.set(indOne, numTwo);
			}
		}
		
		
		private static List<Integer> makeArray(int listSize){
			ArrayList<Integer> al = new ArrayList<Integer>();
			populateInOrder(al, listSize);	
			return al;
		}
		
		

		public static void main(String[] args){
			ArrayList<Integer> al = new ArrayList<Integer>();
			//LinkedList<Integer> ll = new LinkedList<Integer>();
			//int randRange = 100;
			int listSize = 10;
			int testsToRun = 10;
			
			double swapPercent = .50;
			
			for (int i = 1024; i < 65537; i = i*2){
				System.out.println("Making ArrayList of length: " + i);
				al = (ArrayList<Integer>) makeArray(i);
				
			}
			
//			populateInOrder(al, listSize);
//			populate(ll, listSize, randRange);
//			
//			System.out.println("ArrayList");
//			display(al);
//			System.out.println("LinkedList");
//			display(ll);
//			
//			swap(al, .10);
//			swap(ll, .10);
//			
//			System.out.println("ArrayList");
//			display(al);
//			System.out.println("LinkedList");
//			display(ll);
			
			
			
			
		}

	}


