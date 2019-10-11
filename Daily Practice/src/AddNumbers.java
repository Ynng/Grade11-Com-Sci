import java.io.*;

public class AddNumbers {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//Creates BufferReader to read input
		double sum = 0;
		//Sum variable to store the sum
		String inputNumberStrings[];
		//String array to store the input
		System.out.println("This programs adds numbers together \nPlease enter numbers seperated by space:\n----------------------------------------");
		//Prompt the user to enter
		inputNumberStrings = br.readLine().split("\\s+");
		//Split the input by space
		
		//Parses each individual item in the String array to int and add it to the sum
		for(int i = 0; i < inputNumberStrings.length; i++){
			sum+=Double.parseDouble(inputNumberStrings[i]);
		}
	
		//Print the sum
		System.out.println("----------------------------------------\nThe sum is " + sum);
	}

}
