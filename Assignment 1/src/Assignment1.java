import java.util.*;

public class Assignment1 {

    final static int levelLimit = 10;
    final static int maxTermLengthFactor = 4;
    final static int questionPerLevel = 4;

    public static void main(String[] args) {
        int levelCounter = 1, previousNumber, nextDigit = 0, termAnswer, termPrevious, answer, maxTermLength,
                operationCounter;
        char operation = 'e';
        String question;
        Random ran = new Random();

        System.out.println(
                "This is a math game, just enter the correct answer and move on!\nThe question are randomly generated and it will get harder and harder over time!\nThere are 4 levels per level, get all of them correct to go to the next level!");

        while (levelCounter < levelLimit) {
            printHorizontalLine();
            maxTermLength = (int) Math.ceil((double) levelCounter / (double) maxTermLengthFactor);

            System.out.println("Welcome to level " + levelCounter + "!");
            for (int questionCounter = 1; questionCounter <= questionPerLevel; questionCounter++) {
                printHorizontalLine();
                System.out.print("Question " + questionCounter + " :\t");

                // generating level
                question = "";
                operationCounter = 0;
                answer = 0;
                previousNumber = ran.nextInt(10);// generates the first digit
                question += previousNumber;
                while (operationCounter < levelCounter) {
                    termAnswer = 0;
                    termPrevious = previousNumber;
                    for (int termOperationCount = ran.nextInt(Math.min(levelCounter - operationCounter + 1,
                            maxTermLength + 1)); termOperationCount > 0; termOperationCount--) {
                        operationCounter++;
                        switch (ran.nextInt(2)) { // randomly choose between multiplication and subtraction, therefore a
                                                  // single term in a polynomial
                        case 0:// multiplication
                            nextDigit = ran.nextInt(10); // generates the next digit in the question
                            operation = '*';
                            termAnswer = termPrevious * nextDigit;
                            break;
                        case 1:// division
                            operation = '/';
                            do {
                                do {
                                    nextDigit = ran.nextInt(10); // generates the next digit in the question
                                } while (nextDigit == 0); // to make sure the next digit is not 0 because
                                termAnswer = termPrevious / nextDigit;
                            } while (termAnswer * nextDigit != termPrevious);
                            break;
                        }
                        termPrevious = termAnswer;
                        question +=  " " + operation + " " + nextDigit;
                        previousNumber = termAnswer;
                    }

                    if (operationCounter >= levelCounter)
                        break;
                    else {
                        operationCounter++;
                        switch (ran.nextInt(2)) { // randomly choose between addition or subtraction
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
                        }
                        previousNumber = answer;
                        question += " " + operation + " " + nextDigit;
                    }

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