import java.util.Scanner;

public class Lesson13Q3 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int input, tCounter = 0, dCounter = 0, rCounter = 0;

		while (true) {
			System.out.println(
					"This program decides the fate of players, please enter the type of player, either \"pitcher\" or \"batter\". Or, you can enter \"end\" to stop the program and get the total number of players that ended up in each place");
			switch (s.next()) {
			case "pitcher":
				System.out.println(
						"Please enter a pither's fast ball speed in integers in mph, for example, 18mph would be \"18\"");
				input = s.nextInt();
				if (input >= 87) {
					System.out.println("send to Toronto");
					tCounter++;
				} else if (input >= 82) {
					System.out.println("send to Dunedin");
					dCounter++;
				} else {
					System.out.println("release him");
					rCounter++;
				}
				break;
			case "batter":
				System.out.println("Please enter a batter's average in integers");
				input = s.nextInt();
				if (input >= 300) {
					System.out.println("send to Toronto");
					tCounter++;
				} else if (input >= 275) {
					System.out.println("send to Dunedin");
					dCounter++;
				} else {
					System.out.println("release him");
					rCounter++;
				}
				break;
			case "end":
				System.out.println(tCounter + " players were sent to Toronto\n" + dCounter
						+ " players were sent to Dunedin\n" + rCounter + " players were released");
				s.close();
				return;
			default:
				System.out.println("idk what that was, try again");
			}
		}
		// s.close();
	}
}
