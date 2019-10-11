//=================================================================
// Lesson 11 More looping questions #1
// Kevin Huang
// Oct 8th 2019
// Java 11
//=================================================================
// Problem Definition 	– determine the sum of all numbers between 91 and 989 that are divisible by 13.
//I - no user input required
//O - outputs the sum of all numbers between 91 and 989 that are divisible by 13.
//P - determine the sum of all numbers between 91 and 989 that are divisible by 13. 
//=================================================================
/*List of Variables 	- let sum represent the sum of all numbers between 91 and 989 that are divisible by 13– type integer
			            - let i represent the current number we are on when looping through 91 to 989 – type integer
*/ 

public class L11Q1 {//Start of class L11Q1
    public static void main(String[] args) {//Main method
        int sum = 0, i = 91;//Declare and initialize variables, sum is set to 0 as no numbers have been added yet while i is set to 91 because we are starting from 91 ending at 989.
        while (i % 13 != 0)//conditional loop to find the first number from 91, which is what i was, that is divisible by 13.
            i++;//increment the value of i if i is not divisible by 13 so hopefully the next number will be
        while(i <= 989){//conditional loop to increment i by 13 until 989 , i is guranteed to be divisible by 13 because 13x + 13 = 13(x+1) which is still divisible by 13
            sum+=i;//add i, which is divisible by 13 to sum to sum up all the numbers between 91 and 989 that are divisible by 13
            i+=13; //increment i, which is divisible by 13, by 13, which is still divisible by 13
        }//end of conditional loop
        System.out.println(sum); //Outputs the sum of all numbers between 91 and 989 that are divisible by 13
    }//End of main method
}//End of class L11Q1