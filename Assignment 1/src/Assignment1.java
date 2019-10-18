import java.util.Random;
import java.util.Scanner;

public class Assignment1 {

    private static final int LEVEL_LIMIT = 10;
    private static final int MAX_TERM_LENGTH_FACTOR = 1;
    private static final int QUESTION_PER_LEVEL = 4;
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        int levelCounter = 1, maxTermLength;
        Scanner s = new Scanner(System.in);
        System.out.println(
                "This is a math game, just enter the correct answer and move on!\nThe question are randomly generated and it will get harder and harder over time!\nThere are 4 levels per level, get all of them correct to go to the next level!");

        while (levelCounter <= LEVEL_LIMIT) {
            printHorizontalLine();
            maxTermLength = (int) Math.ceil((double) levelCounter / (double) MAX_TERM_LENGTH_FACTOR);

            System.out.println("Welcome to level " + levelCounter + "!");
            for (int questionCounter = 1; questionCounter <= QUESTION_PER_LEVEL; questionCounter++) {
                printHorizontalLine();
                System.out.print("Question " + questionCounter + " :\t");
                if (Integer.toString(generateQuestion(levelCounter, maxTermLength)).equals(s.next())) {
                    printHorizontalLine();
                    System.out.println(ANSI_GREEN + "Nice! you got it right!");
                } else {
                    printHorizontalLine();
                    System.out.println(ANSI_RED + "Oof you got it wrong!");
                }
                System.out.print(ANSI_RESET);
            }
            levelCounter++;
        }
        s.close();
    }

    private static void printHorizontalLine() {
        System.out.println("__________________________________________________________");
    }

    private static int generateQuestion(int overallOperationLimit, int maxTermLength) {
        // generating Question
        int termAnswer, termPrevious, termOperationLimit, nextDigit = 0, operationCounter = -1, overallAnswer = 0;
        char overallOperation = 'i', termOperation = 'i'; // i for initial
        String question = "";
        Random ran = new Random();

        do {
            termAnswer = termPrevious = ran.nextInt(10);
            if (overallOperation == 'i')
                overallOperation = '+';
            else
                question += " " + overallOperation;
            question += " " + termPrevious;
            operationCounter++;

            termOperationLimit = ran.nextInt(Math.min(overallOperationLimit - operationCounter, maxTermLength) + 1);
            for (int termOperationCount = 0; termOperationCount < termOperationLimit; termOperationCount++) {
                switch (ran.nextInt(2)) { // randomly choose between multiplication and subtraction, therefore a
                                          // single term in a polynomial
                case 0:// multiplication
                    nextDigit = ran.nextInt(10); // generates the next digit in the question
                    termOperation = '*';
                    termAnswer = termPrevious * nextDigit;
                    break;
                case 1:// division
                    termOperation = '/';
                    do {
                        do {
                            nextDigit = ran.nextInt(10); // generates the next digit in the question
                        } while (nextDigit == 0); // to make sure the next digit is not 0 because
                        termAnswer = termPrevious / nextDigit;
                    } while (termAnswer * nextDigit != termPrevious);
                    break;
                }
                termPrevious = termAnswer;
                question += " " + termOperation + " " + nextDigit;
                operationCounter++;
            }
            switch (overallOperation) {
            case '+':
                overallAnswer += termAnswer;
                break;
            case '-':
                overallAnswer -= termAnswer;
                break;
            }
            switch (ran.nextInt(2)) { // randomly choose between addition or subtraction
            case 0:// addition
                overallOperation = '+';
                break;
            case 1:// subtraction
                overallOperation = '-';
                break;
            }
        } while (operationCounter < overallOperationLimit);
        // System.out.println(question += " = " + overallAnswer);
        System.out.print(question += " = ");
        return overallAnswer;
    }
}