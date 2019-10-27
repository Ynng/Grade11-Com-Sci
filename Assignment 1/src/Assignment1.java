
//=================================================================
// Assignment 1 : Looping/Selection Assignment
// Kevin Huang
// Oct 28th 2019
// Java 11
//=================================================================
// Program Definition – a math game that randomly generates a simple one digit equation where the user must respond with the correct answer
//I - User interacts by entering text and numbers
//O - The program outputs through the console, ansi esacpe characters are being used for better formatting
//P - a math game that randomly generates a simple one digit equation where the user must respond with the correct answer
//=================================================================
/*List of Variables     - let MAX_TERM_LENGTH_FACTOR be the total numbesr of operations in a polynomial divided by the max number of operations in one term of the polynomial 
.                       - let LEVEL_SELECTION_DELAY be the number of seconds the program delays before continuing execution after the user has finished their stay in the menu, so the user can have time to read all the text Outputs
                        - let ANSI_RED be the unicode character that is esacepe followed by the ansi esacape character that makes text red
                        - let ANSI_GREEN be the unicode character that is esacepe followed by the ansi esacape character that makes text green
                        - let ANSI_RESET be the unicode character that is esacepe followed by the ansi esacape character that resets text formatting
                        - let ANSI_ERASE_SCREEN be the unicode character that is esacepe followed by the ansi esacape character that erase the screen
                        - let ANSI_BOLD be the unicode character that is esacepe followed by the ansi esacape character that makes text bold
*/
import java.util.Random; //imports the Random class to aid in generating random numbers 
import java.util.Scanner; //imports the Scanner class to aid in taking user input from console

public class Assignment1 {//Start of Assignment1 Class

    private static final int MAX_TERM_LENGTH_FACTOR = 1;//total number operations in one term of the polynomial divided by the max number of operations in one term of the polynomial
    private static final int LEVEL_SELECTION_DELAY = 5; //number of seconds the program delays before continuing executing after the user has finished their stay in the menu, so the user can have time to read all the text Outputs
    public static final String ANSI_RED = "\u001B[31m"; //the unicode character that is esacepe followed by the ansi esacape character that makes text red
    public static final String ANSI_GREEN = "\u001B[32m"; //the unicode character that is esacepe followed by the ansi esacape character that makes text green
    public static final String ANSI_RESET = "\u001B[0m"; //the unicode character that is esacepe followed by the ansi esacape character that resets text formatting
    public static final String ANSI_ERASE_SCREEN = "\u001B[2J"; //the unicode character that is esacepe followed by the ansi esacape character that erase the screen
    public static final String ANSI_BOLD = "\u001B[1m"; //the unicode character that is esacepe followed by the ansi esacape character that makes text bold

    public static void main(String[] args) throws InterruptedException {//Start of main method
        int levelCounter = 1, //declares and innitialize the int levelCounter, which stores the current level the user is on
            maxLevelReached = 1, //declares and innitialize the int maxLevelReached, which stores the max level the user has reached. It is used to limit the player from choosing future levels in the level selection screen
            maxTermLength, //declares the int maxTermLength, which temporarily stores the max number of operations a term can have for a specific level. Used to prevent the unlikely senerio where the entire eqaution is made up of one term, which is not fun
            correctCount, //declares the int correctCount, which temporarily stores the number of quetsions in a specific level a user has gotten correct
            totalCorrectCount = 0, //declares and initialize the int totalCorrectCount, which stores the total number of questions the user has gotten correct
            totalQuestionCount = 0, //declares and initialize the int totalQuestionCount, which stores the total number of quetsions the user has answered
            questionPerLevel = 4; //declares and initialize the int questionPerLevel, which stores the number of questions a level have. It can be changed by the user in settings
        float percentageToPass = 0.8f; //declares and initialize the float percentageToPass, which stores the percentage needed to pass each level. It can be changed by the user in settings
        boolean exitFlag = false; //declares and initialize the boolean exitFlag, which helps the program know when to getout of the main infinit loop.
        Scanner s = new Scanner(System.in); //declares and initialize the Scanner s, which is the main way the program takes input from the user
        printEraseScreen(); //erases the screen, so the user gets a fresh new screen with no previous text making it look messy.inuing
        System.out.println(ANSI_GREEN
                + "THIS PROGRAM USES ANSI ESCAPE CHARACTERS, IF THIS TEXT IS NOT GREEN, YOU DON'T HAVE IT! PLEASE SWITCH TO ANOTHER IDE/COMMAND PROMPT THAT DOES SUPPORT ANSI ESACPE CHARACTERS"
                + ANSI_RESET);//prints user friendly oputput 
        printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
        System.out.println("This is a math game, just enter the correct answer and move on!");//prints user friendly oputput
        System.out.println(
                "The question are randomly generated and they will have the same number of operations as the level count!");//prints user friendly oputput
        System.out.println("That means they will get harder and harder!");//prints user friendly oputput
        System.out.println("There will be " + questionPerLevel + " levels per level, get "
                + (int) (percentageToPass * 100f) + "% of them correct to go to unlock the next one!");//prints user friendly oputput that tells the user about the default settings of the game
        System.out.println(makeBoldString("PLEASE ENTER AN INTEGER WITH NO DECIMALS FOR ANSWERS"));//prints user friendly oputput so the user knows what to enter

        printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
        printMenuOption(true, "d", "for default to start with level 1!");//prints a menu option with the keyword bolded, and a description of what this option does, so the user knows what to enter later on
        printMenuOption(false, "x", "for settings");//prints a menu option with the keyword bolded, and a description of what this option does, so the user knows what to enter later on
        printMenuOption(false, "exit", "to quit");//prints a menu option with the keyword bolded, and a description of what this option does, so the user knows what to enter later on
        while (true) {//start of the main inifinite loop
            switch (s.next()) {//gets user input using the Scanner s and matches it up with a bunch of menu options
            case "d"://if the user enters d
            case "D"://if the user enters D
                printChoiceNotice("chosen the default behaviour", "d");//prints a notice telling the user which part of the menu the user has entered and what the user did that caused it.
                System.out.println("Entering level " + makeBoldString(Integer.toString(levelCounter)));//user friendly output telling the user which level the program is entering
                break;//ends of the switch statement if the user enterd d or D
            case "s"://if the user enters s
            case "S"://if the user enters S
                printChoiceNotice("chosen to select level", "s");//prints a notice telling the user which part of the menu the user has entered and what the user did that caused it.
                System.out.println("The highest level you have reached is level " + makeBoldString(Integer.toString(levelCounter)));//user friendly output telling the user what the highest level the user can select
                System.out.println("Please enter a number between 1 and " + levelCounter); //user friendly output telling the user what to enter
                levelCounter = getIntGuranteed(s, 1, maxLevelReached); //gets a level choice from the user using the getIntGuranteed method, so that it's guranteed to get an int between 1 and maxLevelReached with no hassle
                System.out.println("Entering level " + makeBoldString(Integer.toString(levelCounter)));//user friendly output telling the user which level the program is entering
                break;//ends of the switch statement if the user enterd s or S
            case "x"://if the user enters x
            case "X"://if the user enters X
                printChoiceNotice("chosen to open the setting menu", "x");//prints a notice telling the user which part of the menu the user has entered and what the user did that caused it.
                printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
                printMenuOption(true, "p", "to change the percentage needed to pass each level.");//prints a menu option with the keyword bolded, and a description of what this option does, so the user knows what to enter later on
                printMenuOption(false, "q", "to change the number of questions in each level.");//prints a menu option with the keyword bolded, and a description of what this option does, so the user knows what to enter later on
                switch (s.next()) {//get user input using the Scanner s and matches it up with a bunch of options menu options
                case "p"://if the user enters p
                case "P"://if the user enters P
                    printChoiceNotice("chosen to edit the percentage needed to pass each level", "p");//prints a notice telling the user which part of the menu the user has entered and what the user did that caused it.
                    System.out.println( "Please enter an integer between 1 and 100 for the new percentage to pass, it's currently: " + percentageToPass);//prints the limits to the user's input so the user can better respond
                    percentageToPass = ((float) getIntGuranteed(s, 1, 100)) / 100f;//gets an integer between 1 and 100, cast it to float then divide it by 100 to get a percentage, and set it as the new percentageToPass
                    System.out.println("Now you need to get " + (int) (percentageToPass * 100f) + "% to pass each level!");//user friendly output telling the user the new settings
                    break;//end of the switch statement if the user enterd p;
                case "q"://if the user enters q
                case "Q"://if the user enters Q
                    printChoiceNotice("chosen to edit the percentage needed to pass each level", "q");//prints a notice telling the user which part of the menu the user has entered and what the user did that caused it.
                    System.out.println("Please enter an integer between 1 and 100 for the new number, it's currently: " + questionPerLevel);//prints the limits to the user's input so the user can better respond
                    questionPerLevel = getIntGuranteed(s, 1, 100);//gets an integer between 1 and 100 and set it as the new questionPerLevel
                    System.out.println("Now there are " + questionPerLevel + " quetsions per level!");//user friendly output telling the user the new settings
                    break;//end of the switch statement if the user enters q
                default://if the user entered gibeerish
                    printEraseScreen(); //erases the screen, so the user gets a fresh new screen with no previous text making it look messy.inuing
                    printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
                    System.out.println("Invalid input!");//userfriendly output telling the user that its input is invalid
                    break;//end of the switch statement if the user entered gibeerish
                }//end of the switch statement of the options menu
                printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
                System.out.println("continuing with default behaviour, continuing to level " + makeBoldString(Integer.toString(levelCounter)));//user friendly output telling the user which level the program is entering
                break;//end of the case switch statement if the user x or X
            case "exit"://if the user entered exit
            case "Exit"://if the user enters Exit
                printChoiceNotice("chosen to exit the game", "exit");//prints a notice telling the user which part of the menu the user has entered and what the user did that caused it.
                System.out.println("Thanks for playing, good bye!");//user friendly output telling the user good bye
                exitFlag = true;//sets the boolean exitFlag to true, so that an if statement in the loop can end the loop
                break;//end of the case switch statement if the user entered exit or Exit
            default://if the user entered Gibeerish
                printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
                System.out.println("The input is invalid, continuing to level " + makeBoldString(Integer.toString(levelCounter)));//user friendly output telling the user which level the program is entering
                break;//end of the case switch statement if the user entered gibberish
            }//end of the switch statement of the menu
            if (exitFlag) {//if the user wants to exit the game
                printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
                System.out.print("exiting the game in ");//user friendly output telling the user that the game is quiting
                for (int i = LEVEL_SELECTION_DELAY; i > 0; i--) {//loops i from the level selection delay time set ealier to 0, delaying 1 second in each loop, counting down to the game's exit
                    System.out.print(i + "... ");//user friendly output telling the user where the timeout is at
                    Thread.sleep(1000);//waits for 1 second
                }//end of the loop of game quiting timeout
                break;//breaks the main infinite loop, ending the program
            }//end of if statement that exits the game
            System.out.print("in ");//user friendly output
            for (int i = LEVEL_SELECTION_DELAY; i > 0; i--) {//loops i from the level selection delay time set ealier to 0, delaying 1 second in each loop, counting down to the level's start
                System.out.print(i + "... ");//user friendly output telling the user where the timeout is at
                Thread.sleep(1000);//waits for 1 second
            }//end of the loop before starting of the level

            printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
            maxTermLength = (int) Math.ceil((double) levelCounter / (double) MAX_TERM_LENGTH_FACTOR); //calculate/update the maxTermLength int by dividing levelCounter by MAX_TERM_LENGTH_FACTOR then ceiling it, so it's guranteed to be at least 1
            correctCount = 0;//reset the correct count for the level;
            printEraseScreen(); //erases the screen, so the user gets a fresh new screen with no previous text making it look messy.inuing
            System.out.println("Welcome to level " + levelCounter + "!");//user friendly output telling the user which level its entering
            System.out.println("Get " + (int) (percentageToPass * 100f) + "% of the quetsions correct to pass!");//user friendly output telling the user the percentage needed to pass the level

            // gameplay
            for (int questionCounter = 1; questionCounter <= questionPerLevel; questionCounter++) {//start of loop of actual gameplay, loops through all "quetsion per level" questions
                printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
                System.out.print("Question " + questionCounter + "/" + questionPerLevel + " :\t");//user friendly output telling the user the quetsion numbre and the total number of questions
                if (Integer.toString(generateQuestion(levelCounter, maxTermLength)).equals(s.next())) {//large if statement, first, the program calls generateQuestion() to generate and print out a quetsion, then it turns the answer to a string and compares it with the user input
                    printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
                    System.out.println(ANSI_GREEN + "Nice! you got question " + questionCounter + " right!");//user friendly output telling the user that they got a question right
                    totalCorrectCount++;//increment the total number of questions correct
                    correctCount++;//increment the total number of questions correct per level
                } else {//if the user's answer does not match the correct answer, therefore, the user is wrong
                    printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
                    System.out.println(ANSI_RED + "Oof, you got question " + questionCounter + " wrong.");//user friendly output telling the user that they got a question wrong
                }//end of level generation and correct/wrong decision
                totalQuestionCount++;//increment the total number of questions the user has answered
                System.out.print(ANSI_RESET);//resets the color of the text for future text, as it was set to either red or green
                System.out.print("Next question in ");
                for (int i = 2; i > 0; i--) {//loops i from the 2 to 0, delaying 1 second in each loop, counting down to the next question's appearance so the user can have time to read the outputs
                    System.out.print(i + "... ");//user friendly output telling the user where the timeout is at
                    Thread.sleep(1000);//waits for 1 second
                }//end of countdown loop
                System.out.println();//prints a \n, to switch to a new line, for better readability
            }//end of the main gameplay loop

            // end of gameplay
            printEraseScreen(); //erases the screen, so the user gets a fresh new screen with no previous text making it look messy.inuing
            printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
            if ((float) correctCount / (float) questionPerLevel >= percentageToPass) {//if the percentage of quetsions the user has got right is higher then the percentage needed to pass, therfore the user passed
                System.out.println(ANSI_GREEN + "Nice! You got " + makeBoldString(Integer.toString(correctCount)) + ANSI_GREEN + " out of " + questionPerLevel + " right, that's " + makePercentageString(correctCount, questionPerLevel) + " of the questions!" + ANSI_RESET); // Prints some fancy text telling the user that he has passed and the user how many quetsions the user has gotten correct in the quetsion, and the percentage version of that 
                printCorrectPercentage(totalCorrectCount, totalQuestionCount);//prints the total percentage of correct questions the user has answered compared to the total number of questions the user has answered
                levelCounter++;//increment the level counter
                System.out.print(ANSI_RESET);//resets the fancy text formatting
                printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
                printMenuOption(true, "d", "for default to continue to level " + levelCounter);//user friendly output telling the user what the default option will do
            } else {//if the user didn't pass
                System.out.println(ANSI_RED + "Oof, you only got " + makeBoldString(Integer.toString(correctCount)) + ANSI_RED + " out of " + questionPerLevel + " right, that's only " + makePercentageString(correctCount, questionPerLevel) + " of the questions.");// Prints some fancy text telling the user that he has not passed and the user how many quetsions the user has gotten correct in the quetsion, and the percentage version of that 
                printCorrectPercentage(totalCorrectCount, totalQuestionCount);//prints the total percentage of correct questions the user has answered compared to the total number of questions the user has answered
                System.out.print(ANSI_RESET);//resets the fancy text formatting
                printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
                printMenuOption(true, "d", "for default to retry level " + levelCounter + " again.");//user friendly output telling the user what the default option will do
            }//end of "level ending" if statement
            maxLevelReached = levelCounter > maxLevelReached ? levelCounter : maxLevelReached;//increase the max level reached int if the user has reached an higher level then before
            printMenuOption(true, "s", "for selecting levels");//prints a menu option with the keyword bolded, and a description of what this option does, so the user knows what to enter later on
            printMenuOption(false, "x", "for settings");//prints a menu option with the keyword bolded, and a description of what this option does, so the user knows what to enter later on
            printMenuOption(false, "exit", "to quit");//prints a menu option with the keyword bolded, and a description of what this option does, so the user knows what to enter later on
            printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
        }//end of main inifinite loop
        s.close();//closes the scanner to prevent resource leak at the very end of the program
    }

    private static void printCorrectPercentage(int correct, int total) {//start of printCorrectPercentage function, which takes in the correct number of questions the user has answered and the total number of questions the user has answered and prints out the percentage of correct answers the user has given
        System.out.println("Your total percentage of correct answers is " + makePercentageString(correct, total));//user friendly output
    }//end of printCorrectPercentage function

    private static void printEraseScreen() {//start of printEraseScreen function, which doesnt take in or return anything
        System.out.println(ANSI_ERASE_SCREEN);//literally just prints the ANSI erase screen string
    }//end of printEraseScreen function

    private static void printMenuOption(boolean isFirst, String keyWord, String explanation) {//start of printMenuOption function, which takes in a boolean which decides if it's gonna say "Enter" or "or, enter", takes in the keyword which will be bolded and the explanation string of what the option will do
        if (isFirst)//if first is true
            System.out.println("Enter " + makeBoldString(keyWord) + " " + explanation);//user friendly starting with "enter", with the keyword bolded and the explanation after it
        else//if first is false
            System.out.println("Or, enter " + makeBoldString(keyWord) + " " + explanation);//user friendly starting with "or, enter", with the keyword bolded and the explanation after it
    }//end of printMenuOption function
    
    private static void printChoiceNotice(String action, String keyWord) {
        printEraseScreen(); //erases the screen, so the user gets a fresh new screen with no previous text making it look messy.inuing
        printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
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