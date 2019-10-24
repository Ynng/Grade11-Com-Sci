import java.util.Random;
import java.util.Scanner;

public class Assignment1 {

    private static final int MAX_TERM_LENGTH_FACTOR = 1;
    private static final int LEVEL_SELECTION_DELAY = 4;
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_EREASE_SCREEN = "\u001B[2J";
    public static final String ANSI_CROSSEDOUT = "\u001B[9m";
    public static final String ANSI_BOLD = "\u001B[1m";

    public static void main(String[] args) throws InterruptedException {
        int levelCounter = 1, maxLevelReached = 0, maxTermLength, correctCount, totalCorrectCount = 0,
                totalQuestionCount = 0, questionPerLevel = 4;
        float percentageToPass = 0.8f;
        boolean exitFlag = false;
        Scanner s = new Scanner(System.in);
        System.out.println(
                "This is a math game, just enter the correct answer and move on!\nThe question are randomly generated and they will have the same number of operations as the level count!\nThat means they will get harder and harder!\nThere will be "
                        + questionPerLevel + " levels per level, get " + (int) (percentageToPass * 100f)
                        + "% of them correct to go to unlock the next one!");
        printHorizontalLine();
        printMenuOption(true, "d", "for default to start with level 1!");
        printMenuOption(false, "x", "for settings");
        printMenuOption(false, "exit", "to quit");
        while (true) {

            switch (s.next()) {
            case "d":
            case "D":
                printHorizontalLine();
                System.out.println("You have chosen the default behaviour by entering " + ANSI_BOLD + "d" + ANSI_RESET
                        + ", entering level " + levelCounter);
                break;
            case "s":
            case "S":
                printHorizontalLine();
                System.out.println("You have chosen to select level by entering " + ANSI_BOLD + "s" + ANSI_RESET);
                System.out.println(
                        "The highest level you have reached is level " + ANSI_BOLD + levelCounter + ANSI_RESET);
                System.out.println("Please enter a number between 1 and " + levelCounter);
                levelCounter = getIntGuranteed(s, maxLevelReached);
                System.out.println("Entering level " + levelCounter);
                break;
            case "x":
            case "X":
                printHorizontalLine();
                System.out.println(
                        "You have chosen to open the setting menu by entering " + ANSI_BOLD + "x" + ANSI_RESET);
                printHorizontalLine();
                System.out.println("Enter " + ANSI_BOLD + "p" + ANSI_RESET
                        + " to change the percentage needed to pass each level.");
                System.out.println("Or, Enter " + ANSI_BOLD + "q" + ANSI_RESET
                        + " to change the number of questions in each level");
                switch (s.next()) {
                case "p":
                case "P":
                    printHorizontalLine();
                    System.out.println("You have chosen to edit the percentage needed to pass each level by pressing "
                            + ANSI_BOLD + "p" + ANSI_RESET);
                    System.out.println(
                            "Please enter an integer between 1 and 100 for the new percentage to pass, it's currently: "
                                    + percentageToPass);
                    percentageToPass = ((float) getIntGuranteed(s, 100)) / 100f;
                    System.out.println(
                            "Now you need to get " + (int) (percentageToPass * 100f) + "% to pass each level!");
                    break;
                case "q":
                case "Q":
                    printHorizontalLine();
                    System.out.println("You have chosen to edit number of quetsions in each level by pressing "
                            + ANSI_BOLD + "q" + ANSI_RESET);
                    System.out.println("Please enter an integer between 1 and 100 for the new number, it's currently: "
                            + questionPerLevel);
                    questionPerLevel = getIntGuranteed(s, 100);
                    System.out.println("Now there are " + questionPerLevel + " quetsions per level!");
                    break;
                default:
                    printHorizontalLine();
                    System.out.println("Invalid input!!");
                    break;
                }
            case "exit":
            case "Exit":
                printHorizontalLine();
                System.out.println("You have chosen to exit the game by entering " + ANSI_BOLD + "exit" + ANSI_RESET);
                System.out.println("Thanks for playing, good bye!");
                exitFlag = true;
                break;
            default:
                printHorizontalLine();
                System.out.println("The input is invalid, continueing to level " + levelCounter);
                break;
            }
            if (exitFlag) {
                System.out.print("exiting the game in ");
                for (int i = LEVEL_SELECTION_DELAY; i > 0; i--) {
                    System.out.print(i + "... ");
                    Thread.sleep(1000);
                }
                break;
            }
            System.out.print("in ");
            for (int i = LEVEL_SELECTION_DELAY; i > 0; i--) {
                System.out.print(i + "... ");
                Thread.sleep(1000);
            }

            printHorizontalLine();
            maxTermLength = (int) Math.ceil((double) levelCounter / (double) MAX_TERM_LENGTH_FACTOR);
            correctCount = 0;
            System.out.println(ANSI_EREASE_SCREEN + "Welcome to level " + levelCounter + "!");
            // gameplay
            for (int questionCounter = 1; questionCounter <= questionPerLevel; questionCounter++) {
                printHorizontalLine();
                System.out.print("Question " + questionCounter + "/" + questionPerLevel + " :\t");
                if (Integer.toString(generateQuestion(levelCounter, maxTermLength)).equals(s.next())) {
                    printHorizontalLine();
                    System.out.println(ANSI_GREEN + "Nice! you got question " + questionCounter + " right!");
                    totalCorrectCount++;
                    correctCount++;
                } else {
                    printHorizontalLine();
                    System.out.println(ANSI_RED + "Oof, you got question " + questionCounter + " wrong.");
                }
                totalQuestionCount++;
                System.out.print(ANSI_RESET);
            }
            // end of gameplay
            System.out.println(ANSI_EREASE_SCREEN);
            printHorizontalLine();
            if (correctCount == questionPerLevel) {
                System.out.println(ANSI_GREEN + "Nice! You finished level " + ANSI_BOLD + levelCounter + ANSI_RESET
                        + ANSI_GREEN + " perfectly!");
                System.out.println("Your total percentage of correct answers is "
                        + makePercentageString(totalCorrectCount, totalQuestionCount));
                levelCounter++;
                System.out.print(ANSI_RESET);
                printHorizontalLine();
                printMenuOption(true, "d", "for default to continue to level " + levelCounter);
            } else {
                System.out.println(ANSI_RED + "Oof, you only got " + ANSI_BOLD + correctCount + ANSI_RESET + ANSI_RED
                        + " out of " + questionPerLevel + " right, that's only "
                        + makePercentageString(correctCount, questionPerLevel) + " of the questions.");
                System.out.println("Your total percentage of correct answers is "
                        + makePercentageString(totalCorrectCount, totalQuestionCount));
                System.out.print(ANSI_RESET);
                printHorizontalLine();
                printMenuOption(true, "d", "for default to retry level " + levelCounter + " again.");
            }
            maxLevelReached = levelCounter > maxLevelReached ? levelCounter : maxLevelReached;
            printMenuOption(true, "x", "for selecting levels");
            printMenuOption(false, "x", "for settings");
            printMenuOption(false, "exit", "to quit");
            printHorizontalLine();
        }
        s.close();
    }

    private static void printMenuOption(boolean isFirst, String option, String explanation) {
        if (isFirst)
            System.out.println("Enter " + ANSI_BOLD + option + ANSI_RESET + " " + explanation);
        else
            System.out.println("Or, enter " + ANSI_BOLD + option + ANSI_RESET + " " + explanation);
    }

    private static void printHorizontalLine() {
        System.out.println("__________________________________________________________");
    }

    private static String makePercentageString(int a, int b) {
        return Integer.toString((int) (Math.round((float) a / (float) b * 100))) + "%";
    }

    private static int getIntGuranteed(Scanner s, int upperLimit) {
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