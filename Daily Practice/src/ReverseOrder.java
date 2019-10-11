import java.io.*;

public class ReverseOrder {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//Creates BufferReader to read input
		String inputNumberStrings[];
		//String array to store the input
		System.out.println("This program returns the numbers you will enter in reverse order\nPlease enter numbers seperated by space:\n----------------------------------------");
		//Prompt the user to enter some numbers
		inputNumberStrings = br.readLine().split("\\s+");
		//Split the input by space into an array
		

		//Print a seperator for asethetic purpose
		System.out.println("----------------------------------------\n");

		//Parses each individual item in the String array to int and prints it
		for(int i = inputNumberStrings.length-1; i >= 0 ; i--){
			System.out.print(Double.parseDouble(inputNumberStrings[i]) + " ");
		}
	}

}
