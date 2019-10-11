import java.text.DecimalFormat;
import java.util.*;

public class Circle	{
	public static void main(String[] args) {
		final double PI = 3.141592654;
		DecimalFormat  twoDigits = new DecimalFormat ("0.00");
		Scanner in = new Scanner(System.in);
		System.out.println(
				"This program returns the perimeter and area of a circle given a radius\nPlease enter the radius of a circle:\n----------------------------------------");
		double radius = in.nextDouble();
		//Prints the perimeter and area of the circle in 2 decimal
		System.out.println("----------------------------------------\nPerimeter of the circle: "
				+ String.format("%.2f", radius * 2 * PI) + "   |   Area of the circle: "
				+ twoDigits.format(radius * radius * PI));
		in.close();
	}
}
