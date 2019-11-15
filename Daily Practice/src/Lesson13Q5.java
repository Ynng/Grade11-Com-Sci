import java.io.*;
public class Lesson13Q5 {
	//Kevin Huang
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// Creates BufferReader to read input
		int lowest, temp;
		// sum int to temporarily store people's sums for calculating averages
		String inputString[];
		// String array to temporarily the input

		System.out.println("This programs finds the smallest number entered");
		//user friendly prompts

		while (true) {
			System.out.println("Please enter a list of numbers seperated by commas | or enter \"end\" to end the program :\n----------------------------------------");
			// Prompt the user to enter stuff
			inputString = br.readLine().split(",");
			// Split the input by comma
			if(inputString[0].equals("end"))break;

			lowest = Integer.parseInt(inputString[0]);
			// Parses each individual item in the String array to int and see find the smallest number
			for (int i = 1; i < inputString.length; i++) {
				temp = Integer.parseInt(inputString[i]);
				if(temp<lowest) lowest = temp;
			}

			System.out.println("The lowest number in there was " + lowest + "\n----------------------------------------");
		}
		
		System.out.println("thanks for using the program");
		
		// Print the sum
	}

}
