
import java.io.*;//import the code contained in the java io (input/output) library
//we need this to get keyboard input

public class InputExample1 {
	public static void main(String[] args) throws IOException {
		BufferedReader nameReader = new BufferedReader(new InputStreamReader(System.in));
		String name;
		System.out.println("What's your name? enter now");
		name = nameReader.readLine();
		System.out.println("Hi " + name);
	}
}
