import java.util.*;
public class Lesson13Q1 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int input;
		System.out.println("Please enter a batter's average in integers to decide where we'll send him/her");
		input = s.nextInt();
		if(input>=300) {
			System.out.println("send to Toronto");
		}else if(input>=275) {
			System.out.println("send to Dunedin");
		}else {
			System.out.println("release him");
		}
	}
}
