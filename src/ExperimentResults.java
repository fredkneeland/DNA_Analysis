
import java.util.*;
public class ExperimentResults {
	public static void printBasicResults(Map<String, Integer> results) {
		int n = 5; // The number of top and bottom results displayed
	 	Set<String> set = results.keySet();
		String s[] = set.toArray(new String[0]);
		String top[] = getTopN(results, s, n); //Displays 5 by default, but can be changed
		String bottom[] = getBottomN(results, s, n);
		Double mean = getMean(results, s); //Gets the average
		Double sd = getStandardDeviation(results, s); //Gets the standard deviation
		Double median = getMedian(results, s); //Gets the median
		display(results, s, n, top, bottom, mean, sd, median); //Prints everything
	}

	private static void display(Map<String, Integer> m, String[] s, int n, String[] top, String[] bottom, Double mean, Double sd, Double median) {
		String toPrint = "";
		//Display top n
		toPrint += "The " + n +" most common strings are:\n";
		for (Integer i = 0; i < n; i++) {
			toPrint += "\t" + top[i] + " - " + m.get(top[i]) + "\n";
		}
		toPrint += "\n";
		//Display bottom n
		toPrint += "The " + n +" least common strings are:\n";
		for (Integer i = 0; i < n; i++) {
			toPrint += "\t" + bottom[i] + " - " + m.get(bottom[i]) + "\n";
		}
		toPrint += "\n";
		//Other data
		toPrint += "The mean is " + mean + "\n";
		toPrint += "The standard deviation is " + sd + "\n";
		toPrint += "The median is " + median + "\n";
		System.out.println(toPrint);
	}
	//Gets the n most common strings
	private static String[] getTopN(Map<String, Integer> m, String[] s, int n) {
		if(n > s.length) { //Error catching
			n = s.length;
		}
		String[] topNumbers = new String[n];
		for (Integer i = 0; i < s.length; i++) {
			Integer comp = m.get(s[i]);
			String next = null;
			for (Integer j = 0; j < topNumbers.length; j++) {
				if(topNumbers[j] == null || m.get(topNumbers[j]) < comp) {
					if(next == null) { //If it hasn't yet been placed
						next = topNumbers[j]; // Makes sure there are no repeats
						topNumbers[j] = s[i];
						if(next == null) {
							next = "null";
						}
					}
					else if (next == "null") {
						//So that the first number doesn't replace every null
					}
					else if (topNumbers[j] == null || m.get(topNumbers[j]) < m.get(next)) {
						//When a string takes's another's place, it pushes it out. This makes the pushed out one take the next available place
						String h = topNumbers[j];
						topNumbers[j] = next;
						next = h;
					}
				}
			}
		}
		return topNumbers;
	}

	//A reversed copy/pased of the top one
	private static String[] getBottomN(Map<String, Integer> m, String[] s, int n) {
		if(n > s.length) { //Error catching
			n = s.length;
		}
		String[] topNumbers = new String[n];
		for (Integer i = 0; i < s.length; i++) {
			Integer comp = m.get(s[i]);
			String next = null;
			for (Integer j = 0; j < topNumbers.length; j++) {
				if(topNumbers[j] == null || m.get(topNumbers[j]) > comp) {
					if(next == null) {
						next = topNumbers[j];
						topNumbers[j] = s[i];
						if(next == null) {
							next = "null";
						}
					}
					else if (next == "null") {
						//So that the first number doesn't replace every null
					}
					else if (topNumbers[j] == null || m.get(topNumbers[j]) > m.get(next)) {
						String h = topNumbers[j];
						topNumbers[j] = next;
						next = h;
					}
				}
			}
		}
		return topNumbers;
	}

	private static Double getMean(Map<String, Integer> m, String[] s) {
		//Adds everything up, then divides by the amount
		Double tot = 0.0;
		for (Integer i = 0; i < s.length; i++) {
			tot+=m.get(s[i]);
		}
		tot/=s.length;
		return tot;
	}

	private static Double getStandardDeviation(Map<String, Integer> m, String[] s) {
		//Follows standard deviation formula
		Double mean = getMean(m, s);
		Double tot = 0.0;
		for (Integer i = 0; i < s.length; i++) {
			tot+= Math.pow(m.get(s[i])-mean ,2);
		}
		tot/=s.length;
		tot = Math.sqrt(tot);
		return tot;
	}

	private static Double getMedian(Map<String, Integer> m, String[] s) {
		Integer l = s.length;
		//Load everything into an array
		Integer[] numbers = new Integer[l];
		for (Integer i = 0; i < l; i++) {
			numbers[i] = m.get(s[i]);
		}
		Arrays.sort(numbers); //Sort the array
		//Get the middle element
		Double median = -1.0;
		l-=1;
		if(l % 2 == 1) {
			median = ((double) (numbers[l/2] + numbers[l/2+1]))/2;
		}
		else {
			median = (double) numbers[l/2];
		}
		return median;
	}

	private static HashMap<String, Integer> createFakeValues(int NumValues) {
		//Sample data set for testing
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		for (Integer i = 0; i < NumValues; i++) {
			m.put(i.toString(), (int) (Math.random() * NumValues));
		}
		//System.out.println(m);
		return m;
	}

	public static void main(String[] args) {
		HashMap<String, Integer> m = createFakeValues(10);
		printBasicResults(m);
	}
}
