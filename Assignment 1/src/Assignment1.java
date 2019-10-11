import java.util.Random;

public class Assignment1 {
    
    final static int levelLimit = 10;
    public static void main(String[] args) {
        int levelCounter = 1, previousNumber, nextDigit = 0, answer;
        char operation = 'e';
        String question;
        Random ran = new Random();
        System.out.println(
                "This is a math game, just enter the correct answer and move on!\nThe question are randomly generated and it will get harder and harder over time!\nThere are 4 levels per level, get all of them correct to go to the next level!");
        while (levelCounter < levelLimit) {
            printHorizontalLine();
            System.out.println("Welcome to level " + levelCounter + "!");
            for (int questionCounter = 1; questionCounter <= 4; questionCounter++) {
                System.out.print("Question " + questionCounter + " :\t");

                // generating level
                question = "";
                answer = 0;
                previousNumber = ran.nextInt(10);// generates the first digit
                question += previousNumber; // adds the first digit to the question String

                for (int i = 0; i < levelCounter; i++) {// generates the operations applied to the first digit and the
                                                        // operations in String format to display for the user

                    switch (ran.nextInt(4)) { // randomly choose an operation to apply between the 2 digits
                    case 0:// addition
                        nextDigit = ran.nextInt(10); // generates the next digit in the question
                        operation = '+';
                        answer = previousNumber + nextDigit;
                        break;
                    case 1:// subtraction
                        nextDigit = ran.nextInt(10); // generates the next digit in the question
                        operation = '-';
                        answer = previousNumber - nextDigit;
                        break;
                    case 2:// multiplication
                        nextDigit = ran.nextInt(10); // generates the next digit in the question
                        operation = '*';
                        answer = previousNumber * nextDigit;
                        break;
                    case 3:// division
                        operation = '/';
                        do {
                            do {
                                nextDigit = ran.nextInt(10); // generates the next digit in the question
                            } while (nextDigit == 0); // to make sure the next digit is not 0 because 
                            answer = previousNumber / nextDigit;
                        } while (answer * nextDigit != previousNumber);// to make sure the answer is an integer, if the
                                                                       // answer is not, digits will be cut off, making
                                                                       // this euqaion false.
                                                                       // We repeatedly try to generate the next integer
                                                                       // until the answer is a whole number
                        break;
                    }
                    
                    previousNumber = answer;
                    question += " " + operation + " " + nextDigit;
                }
                System.out.println(question += " = " + answer);
            }
            levelCounter++;
        }
    }

    private static void printHorizontalLine() {
        System.out.println("__________________________________________________________");
    }
}