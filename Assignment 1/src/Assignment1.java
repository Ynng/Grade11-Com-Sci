import java.util.Random;
import java.util.Scanner;

public class Assignment1 {

    private static final int LEVEL_LIMIT = 100;
    private static final int MAX_TERM_LENGTH_FACTOR = 1;
    private static final int LEVEL_SELECTION_DELAY = 4;
    private static final int QUESTION_PER_LEVEL = 4;
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_EREASE_SCREEN = "\u001B[2J";
    public static final String ANSI_CROSSEDOUT = "\u001B[9m";
    public static final String ANSI_BOLD = "\u001B[1m";

    public static void main(String[] args) throws InterruptedException {
        int levelCounter = 1, maxLevelReached = 0, maxTermLength, correctCount;
        Scanner s = new Scanner(System.in);
        System.out.println(
                "This is a math game, just enter the correct answer and move on!\nThe question are randomly generated and it will get harder and harder over time!\nThere are 4 levels per level, get all of them correct to go to the next level!");

        while (levelCounter <= LEVEL_LIMIT) {
            printHorizontalLine();
            maxTermLength = (int) Math.ceil((double) levelCounter / (double) MAX_TERM_LENGTH_FACTOR);
            correctCount = 0;
            System.out.println(ANSI_EREASE_SCREEN + "Welcome to level " + levelCounter + "!");
            // gameplay
            for (int questionCounter = 1; questionCounter <= QUESTION_PER_LEVEL; questionCounter++) {
                printHorizontalLine();
                System.out.print("Question " + questionCounter + "/" + QUESTION_PER_LEVEL + " :\t");
                if (Integer.toString(generateQuestion(levelCounter, maxTermLength)).equals(s.next())) {
                    printHorizontalLine();
                    System.out.println(ANSI_GREEN + "Nice! you got question " + questionCounter + " right!");
                    correctCount++;
                } else {
                    printHorizontalLine();
                    System.out.println(ANSI_RED + "Oof, you got question " + questionCounter + " wrong.");
                }
                System.out.print(ANSI_RESET);
            }
            // end of gameplay
            System.out.println(ANSI_EREASE_SCREEN);
            printHorizontalLine();
            if (correctCount == QUESTION_PER_LEVEL) {
                System.out.println(ANSI_GREEN + "Nice! You finished level " + ANSI_BOLD + levelCounter + ANSI_RESET + ANSI_GREEN + " perfectly!");
                levelCounter++;
                System.out.print(ANSI_RESET);
                printHorizontalLine();
                System.out.println("Choose " + ANSI_BOLD + "d" + ANSI_RESET + "efault to continue to level " + levelCounter);
                printHorizontalLine();
            } else {
                System.out.println(ANSI_RED + "Oof, you got " + ANSI_BOLD + (QUESTION_PER_LEVEL - correctCount)
                        + ANSI_RESET + ANSI_RED + " out of " + QUESTION_PER_LEVEL + " wrong, try again!");
                System.out.print(ANSI_RESET);
                printHorizontalLine();
                System.out.println("Choose " + ANSI_BOLD + "d" + ANSI_RESET + "efault to retru level " + levelCounter + " again.");
                printHorizontalLine();
            }
            maxLevelReached = levelCounter > maxLevelReached ? levelCounter : maxLevelReached;
            System.out.println("Enter " + ANSI_BOLD + "d" + ANSI_RESET + " for default");
            System.out.println("Enter " + ANSI_BOLD + "s" + ANSI_RESET + " for selecting levels");
            printHorizontalLine();

            switch (s.next()) {
            case "d":
            case "D":
                printHorizontalLine();
                System.out.println("You have chosen the default behaviour by pressing " + ANSI_BOLD + "d" + ANSI_RESET
                        + ", entering level " + levelCounter);
                break;
            case "s":
            case "S":
                printHorizontalLine();
                System.out.println("You have chosen to select level by pressing " + ANSI_BOLD + "s" + ANSI_RESET);
                System.out.println(
                        "The highest level you have reached is level " + ANSI_BOLD + levelCounter + ANSI_RESET);
                System.out.println("Please enter a number between 1 and " + levelCounter);
                levelCounter = getLevelSelection(s, maxLevelReached);
                System.out.println("Entering level " + levelCounter);

                break;
            default:
                printHorizontalLine();
                System.out.println("The input is invalid, continueing to level " + levelCounter);
                break;
            }
            System.out.print("in ");
            for (int i = LEVEL_SELECTION_DELAY; i > 0; i--) {
                System.out.print(i + "... ");
                Thread.sleep(1000);
            }

        }
        s.close();
    }

    private static void printHorizontalLine() {
        System.out.println("__________________________________________________________");
    }

    private static int getLevelSelection(Scanner s, int upperLimit) {
        int output = -1;

        while (true) {
            try {
                output = s.nextInt();
            } catch (java.util.InputMismatchException e) {
                s.nextLine();
            }
            if (output >= 1 && output <= upperLimit) {
                return output;
            } else {
                System.out.print("That was an invalid input, please enter a single integer between 1 and " + upperLimit
                        + ", such as \"1\" or \"" + upperLimit + "\"Try again now: ");
            }
        }
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
                    termOperation = '×';
                    termAnswer = termPrevious * nextDigit;
                    break;
                case 1:// division
                    termOperation = '÷';
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