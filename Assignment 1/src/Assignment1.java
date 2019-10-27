import java.util.Random;
import java.util.Scanner;

public class Assignment1 {

    private static final int MAX_TERM_LENGTH_FACTOR = 1;
    private static final int LEVEL_SELECTION_DELAY = 5;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_ERASE_SCREEN = "\u001B[2J";
    public static final String ANSI_CROSSEDOUT = "\u001B[9m";
    public static final String ANSI_BOLD = "\u001B[1m";

    public static void main(String[] args) throws InterruptedException {
        int levelCounter = 1, maxLevelReached = 0, maxTermLength, correctCount, totalCorrectCount = 0,
                totalQuestionCount = 0, questionPerLevel = 4;
        float percentageToPass = 0.8f;
        boolean exitFlag = false;
        Scanner s = new Scanner(System.in);
        printEraseScreen();
        System.out.println(ANSI_GREEN
                + "THIS PROGRAM USES ANSI ESCAPE CHARACTERS, IF THIS TEXT IS NOT GREEN, YOU DON'T HAVE IT! PLEASE SWITCH TO ANOTHER IDE/COMMAND PROMPT THAT DOES SUPPORT ANSI ESACPE CHARACTERS"
                + ANSI_RESET);
        printHorizontalLine();
        System.out.println("This is a math game, just enter the correct answer and move on!");
        System.out.println(
                "The question are randomly generated and they will have the same number of operations as the level count!");
        System.out.println("That means they will get harder and harder!");
        System.out.println("There will be " + questionPerLevel + " levels per level, get "
                + (int) (percentageToPass * 100f) + "% of them correct to go to unlock the next one!");
        System.out.println(makeBoldString("PLEASE ENTER AN INTEGER WITH NO DECIMALS FOR ANSWERS"));

        printHorizontalLine();
        printMenuOption(true, "d", "for default to start with level 1!");
        printMenuOption(false, "x", "for settings");
        printMenuOption(false, "exit", "to quit");
        while (true) {
            switch (s.next()) {
            case "d":
            case "D":
                printChoiceNotice("chosen the default behaviour", "d");
                System.out.println("Entering level " + makeBoldString(Integer.toString(levelCounter)));
                break;
            case "s":
            case "S":
                printChoiceNotice("chosen to select level", "s");
                System.out.println("The highest level you have reached is level "
                        + makeBoldString(Integer.toString(levelCounter)));
                System.out.println("Please enter a number between 1 and " + levelCounter);
                levelCounter = getIntGuranteed(s, 1, maxLevelReached);
                System.out.println("Entering level " + makeBoldString(Integer.toString(levelCounter)));
                break;
            case "x":
            case "X":
                printChoiceNotice("chosen to open the setting menu", "x");
                printHorizontalLine();
                printMenuOption(true, "p", "to change the percentage needed to pass each level.");
                printMenuOption(false, "q", "to change the number of questions in each level.");
                switch (s.next()) {
                case "p":
                case "P":
                    printChoiceNotice("chosen to edit the percentage needed to pass each level", "p");
                    System.out.println(
                            "Please enter an integer between 1 and 100 for the new percentage to pass, it's currently: "
                                    + percentageToPass);
                    percentageToPass = ((float) getIntGuranteed(s, 1, 100)) / 100f;
                    System.out.println(
                            "Now you need to get " + (int) (percentageToPass * 100f) + "% to pass each level!");
                    break;
                case "q":
                case "Q":
                    printChoiceNotice("chosen to edit the percentage needed to pass each level", "q");
                    System.out.println("Please enter an integer between 1 and 100 for the new number, it's currently: "
                            + questionPerLevel);
                    questionPerLevel = getIntGuranteed(s, 1, 100);
                    System.out.println("Now there are " + questionPerLevel + " quetsions per level!");
                    break;
                default:
                    printEraseScreen();
                    printHorizontalLine();
                    System.out.println("Invalid input!");
                    break;
                }
                printHorizontalLine();
                System.out.println("continuing with default behaviour, continuing to level "
                        + makeBoldString(Integer.toString(levelCounter)));
                break;
            case "exit":
            case "Exit":
                printChoiceNotice("chosen to exit the game", "exit");
                System.out.println("Thanks for playing, good bye!");
                exitFlag = true;
                break;
            default:
                printHorizontalLine();
                System.out.println(
                        "The input is invalid, continuing to level " + makeBoldString(Integer.toString(levelCounter)));
                break;
            }
            if (exitFlag) {
                printHorizontalLine();
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
            printEraseScreen();
            System.out.println("Welcome to level " + levelCounter + "!");
            System.out.println("Get " + (int) (percentageToPass * 100f) + "% of the quetsions correct to pass!");

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
                System.out.print("Next question in ");
                for (int i = 2; i > 0; i--) {
                    System.out.print(i + "... ");
                    Thread.sleep(1000);
                }
                System.out.println();
            }

            // end of gameplay
            printEraseScreen();
            printHorizontalLine();
            if ((float) correctCount / (float) questionPerLevel >= percentageToPass) {
                System.out.println(ANSI_GREEN + "Nice! You got " + makeBoldString(Integer.toString(correctCount))
                        + ANSI_GREEN + " out of " + questionPerLevel + " right, that's "
                        + makePercentageString(correctCount, questionPerLevel) + " of the questions!" + ANSI_RESET);
                printCorrectPercentage(totalCorrectCount, totalQuestionCount);
                levelCounter++;
                System.out.print(ANSI_RESET);
                printHorizontalLine();
                printMenuOption(true, "d", "for default to continue to level " + levelCounter);
            } else {
                System.out.println(ANSI_RED + "Oof, you only got " + makeBoldString(Integer.toString(correctCount))
                        + ANSI_RED + " out of " + questionPerLevel + " right, that's only "
                        + makePercentageString(correctCount, questionPerLevel) + " of the questions.");
                printCorrectPercentage(totalCorrectCount, totalQuestionCount);
                System.out.print(ANSI_RESET);
                printHorizontalLine();
                printMenuOption(true, "d", "for default to retry level " + levelCounter + " again.");
            }
            maxLevelReached = levelCounter > maxLevelReached ? levelCounter : maxLevelReached;
            printMenuOption(true, "s", "for selecting levels");
            printMenuOption(false, "x", "for settings");
            printMenuOption(false, "exit", "to quit");
            printHorizontalLine();
        }
        s.close();
    }

    private static void printCorrectPercentage(int correct, int total) {
        System.out.println("Your total percentage of correct answers is " + makePercentageString(correct, total));
    }

    private static void printEraseScreen() {
        System.out.println(ANSI_ERASE_SCREEN);
    }

    private static void printMenuOption(boolean isFirst, String keyWord, String explanation) {
        if (isFirst)
            System.out.println("Enter " + makeBoldString(keyWord) + " " + explanation);
        else
            System.out.println("Or, enter " + makeBoldString(keyWord) + " " + explanation);
    }

    private static void printChoiceNotice(String action, String keyWord) {
        printEraseScreen();
        printHorizontalLine();
        System.out.println("You have " + action + " by entering " + makeBoldString(keyWord));
    }

    private static void printHorizontalLine() {
        System.out.println("__________________________________________________________");
    }

    private static String makePercentageString(int a, int b) {
        return Integer.toString((int) (Math.round((float) a / (float) b * 100))) + "%";
    }

    private static String makeBoldString(String text) {
        return ANSI_BOLD + text + ANSI_RESET;
    }

    private static int getIntGuranteed(Scanner s, int lowerLimit, int upperLimit) {
        int output = -1;

        while (true) {
            try {
                output = s.nextInt();
            } catch (java.util.InputMismatchException e) {
                s.nextLine();
            }
            if (output >= lowerLimit && output <= upperLimit) {
                return output;
            } else {
                System.out.print("That was an invalid input, please enter a single integer between 1 and " + upperLimit
                        + ", such as \"" + lowerLimit + "\" or \"" + upperLimit + "\"Try again now: ");
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