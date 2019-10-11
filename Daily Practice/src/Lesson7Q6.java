import java.io.*;
import java.util.*;

public class Lesson7Q6 {
	//Kevin Huang
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// Creates BufferReader to read input
		Map<String, Integer> averages = new HashMap<String, Integer>();
		// Map to store everyone's names and marks
		int sum;
		// sum int to temporarily store people's sums for calculating averages
		String inputString[];
		// String array to temporarily the input

		System.out.println("This programs calculated personal averages and class averages for any number of people.");
		//user friendly prompts

		while (true) {
			sum=0;
			System.out.println("Please enter a name then any number of marks seperated by commas | or enter \"end\" to end inputting and get a list of all averages and the class average :\n----------------------------------------");
			// Prompt the user to enter stuff
			inputString = br.readLine().split(",");
			// Split the input by comma
			if(inputString[0].equals("end"))break;

			// Parses each individual item in the String array to int and add it to the sum
			for (int i = 1; i < inputString.length; i++) {
				sum += Integer.parseInt(inputString[i].replaceAll("\\s+",""));
			}

			averages.put(inputString[0], sum / (inputString.length - 1));
			System.out.println(inputString[0] + "'s personal averages (without digits) is:" + sum / (inputString.length - 1) + "\n----------------------------------------");
		}
		
		System.out.println("----------------------------------------\nSummary view\n----------------------------------------");

		sum = 0;
		for (Map.Entry<String, Integer> entry : averages.entrySet()) {
			String name = entry.getKey();
			Integer average = entry.getValue();
			sum+=average;
			System.out.println("The average for \t\"" + name + "\"\t is \t" + average);
		}
		System.out.println("The class average is: " + sum/averages.entrySet().toArray().length);
		
		// Print the sum
	}

}
