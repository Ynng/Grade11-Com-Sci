import java.util.Scanner;

public class Lesson13Q4 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int input, numA, numB, divideCounter=0;
		System.out.println(
				"This program determines the number of times an integer can be divided by 2 with no remainder, please enter an integer:");
		numB = input = s.nextInt();
		numA = numB/2;
		while(numA*2==numB) {
			divideCounter++;
			numB = numA;
			numA = numB/2;
		}
		System.out.println("The number " + input + " was divided by 2 " + divideCounter + " times");
		s.close();
	}
}
