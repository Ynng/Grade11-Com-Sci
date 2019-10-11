import java.util.*;


public class Mode {
    public static void main(String[] args) throws Exception {
        //Kevin Huang
        Scanner sc = new Scanner(System.in);
        String inputString[];
        int count, number, maxCount, maxNumber;
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        System.out.println(
                "This program finds the mode of the numbers you enter\nPlease enter some comma seperated numbers");
        inputString = sc.next().split(",");
        // Split the input by comma

        // Parses each individual item in the String array to int and add it to the sum
        for (int i = 0; i < inputString.length; i++) {
            number = Integer.parseInt(inputString[i].replaceAll("\\s+", ""));
            count = freq.containsKey(number) ? freq.get(number) : 0;
            freq.put(number, count + 1);
        }

        maxCount = maxNumber = -1;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            number = entry.getKey();
            count  = entry.getValue();
            if(count > maxCount){
                maxCount = count;
                maxNumber = number;
            }
        }
        System.out.println("The number " + maxNumber + " appeared the most frequent, it appeared " + maxCount + " times!");
        sc.close();
    }
}