import java.io.*;

public class TotalAndAvg {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// Creates BufferReader to read input
		double sum = 0;
		// Sum variable to store the sum
		String inputNumberStrings[];
		// String array to store the input
		System.out.println(
				"This program returns the sum and the average of the numbers you will enter\nPlease enter numbers seperated by space:\n----------------------------------------");
		// Prompt the user to enter some numbers
		inputNumberStrings = br.readLine().split("\\s+");
		// Split the input by space into an array

		
		// Parses each individual item in the String array to int and prints it
		for (int i = inputNumberStrings.length - 1; i >= 0; i--) {
			sum += Double.parseDouble(inputNumberStrings[i]);
		}
		
		// Print a seperator for asethetic purpose
		System.out.println("----------------------------------------\nThe sum is: " + sum + "   |   The average is: " + sum/inputNumberStrings.length);
		
	}

}
