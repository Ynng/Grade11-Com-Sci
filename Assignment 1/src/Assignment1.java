
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
    }//end of main method

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
    
    private static void printChoiceNotice(String action, String keyWord) {//start of printChoiceNotice function, which takes in the keyword the user has entered, and a description of the action the user has caused
        printEraseScreen(); //erases the screen, so the user gets a fresh new screen with no previous text making it look messy.inuing
        printHorizontalLine(); //prints a horizontal line to seperate the screen visualy, so it looks better
        System.out.println("You have " + action + " by entering " + makeBoldString(keyWord));//user friendly output telling the user about what the user has entered that triggered the action, and what action the program is going to execute
    }//end of printChoiceNotice function

    private static void printHorizontalLine() {//start of printHorizontalLine function, which prints a horizontal line, taking in no input and returns nothing, therefore void
        System.out.println("__________________________________________________________"); //print a horizontal line to the screen, so the console will look nicer
    }//end of printHorizontalLine function

    private static String makePercentageString(int a, int b) {//start of makePercentageString function, which takes in 2 numbers, finds the percentage by dividing the two numbesr and prints it out in a neet way
        return Integer.toString((int) (Math.round((float) a / (float) b * 100))) + "%"; //returns the percentage made by dividing the two numbers, with the percentage sign behind it
    }//end of makePercentageString function

    private static String makeBoldString(String text) {//start of makeBoldString function, which takes in a string, and returns that same string, with the ansi escape characters that makes it bold before it, and the ansi escape characters that resets styling afterwards
        return ANSI_BOLD + text + ANSI_RESET; //returns the string passed in but with the ansi escape characters that makes it bold before it, and the ansi escape characters that resets styling afterwards
    }//end of makeBoldString function

    private static int getIntGuranteed(Scanner s, int lowerLimit, int upperLimit) {//start of getIntGuranteed function, which takes in the scanner, a lower limit and an upper limit. It handles all the try catch, error proofing and user prompting for getting an integer from the user within a certain range. Therefore, int *guranteed*, because all the errors are handled by itself and won't be thrown out to the main method
        int output = lowerLimit-1;//initalizes and declares an integer called output to 1 lower then the lower limit, so that even if the user didn't enter an integer and an error occurred, "output" would've still be initialized and won't cause any additional errors

        while (true) {//start of an inifinite loop that the user has to enter an acceptable integer to get out from
            try {//start of try catch block
                output = s.nextInt();//tries to get an integer from the user, user friendly output should be printed before calling "getIntGuranteed"
            } catch (java.util.InputMismatchException e) {//catches the input mismatch exception, which nextInt() might throw if the didn't enter an integer'
                s.nextLine();//throws away the invalid input by reading it with nextLine, which gurantees nothing (most of the time) can go wrong 
            }//end of try catch block
            if (output >= lowerLimit && output <= upperLimit) {//start of if statement, checks if the integer is within the range of acceptable inputs
                return output;//if the integer is within the range of acceptable inputs, return it
            } else {//if the integer is outside the range of acceptable inputs
                System.out.print("That was an invalid input, please enter a single integer between 1 and " + upperLimit + ", such as \"" + lowerLimit + "\" or \"" + upperLimit + "\"Try again now: ");//user friendly output retelling the user about the range of acceptable inputs, and prompts the user to try again
            }//end of if statement
        }// end of while loop
    }//end of function getIntGuranteed

    private static int generateQuestion(int overallOperationLimit, int maxTermLength) {//start of generateQuestion function, which is the center part of the program. It generates a random equation, along with its answer and prints it to the console for the user to answer
        // generating Question
        //term: one term of a polynomial, made up of only multiplication and/or division
        int termAnswer,//declares an integer variable called termAnswer, which helps the program to temporarily store the answer of a term while generating it sequentially
            termPrevious,//declares an integer variable called termPrevious, which helps the program to temporarily store the "previous" answer of a term while generating it
            termOperationLimit,//declares an integer variable called termOperationLimit, which helps the program to temporarily store the number of operations the program chose to have in a given term
            nextDigit = 0,//declares and initializes an integer variable called nextDigit to 0, which helps the program to temporarily store the next digit of a term while generating it, so the program can multiply/divide it by the "previous" answer and finds the overall answer. It is initialized to 0 to prevent a "variable might not be initialized" error, as the variable is only initialized in a switch statement without a default case, powered by a random number generator. Despite being logically impossible that the statement using this variable would have run without the variable being initialized, the compiler doesnt know that, therefore I'll just initialize it here to some random value, I chose 0, but it doesnt actually matter 
            operationCounter = -1, //declares an integer variable called operationCounter, which helps the program to cound the number of operations in the equation, it is limited to the level number, so that the equation can get progressively harder 
            overallAnswer = 0; //declares and initialize an integer variable called overallAnswer, which helps the program to store the overall answer of the equation while generating it.
        char overallOperation = 'i', termOperation = 'i'; // declares and initialize the character variable called overallOperation and termOperation, which temporarily stores the sign of the operation, so that the program can run a switch statement on it and figure out what operation it needs to perform between the "previous" number/term and the "next"number/term to find the overall/term answer 
        String question = "";//declares and initialize a string variable called question, which stores the quetsion, with the digits and operators in string format, for printing to the console for the user to read 
        Random ran = new Random();//declares and initializes a Random object called ran, which is the main way the program randomizes its equation

        do {//start of main loop, which generates a term and adds it to the rest of the equation, which will be generated from the previous iterations of the loop
            termAnswer = termPrevious = ran.nextInt(10);//randomly generates the first digit of the term and sets the value of both termAnswer and termPrevious to it.
            if (overallOperation == 'i')//start of if statement, checks if this is the first digit of the equation
                overallOperation = '+';//if so, set overallOperation to + and don't put it in the string question, which will be printed to the user. Because despite technically, the program is going through 0 + [the first digit] and therefore wants to print that, this simply doesnt make sense, simply [the first digit] is enough.
            else//if this is not the first digit of the equation, therefore there are numbesr and equations in the string question before this already
                question += " " + overallOperation;//add a space followed by the equation, which will be generated at the end of the loop 
            question += " " + termPrevious;//adds a space followed by the first digit of the term
            operationCounter++;//since we just added a digit and an operation to our quetsion, increment the operation counter 

            termOperationLimit = ran.nextInt(Math.min(overallOperationLimit - operationCounter, maxTermLength) + 1);//randomly generates the number of operations our current term will have, the number is limited by the remaining number of operations our question can still have and the max number of operations our term can have, it is incremented by 1 because the Random class's nextInt method outputs maximum ("your input" -1), when we actually want it to output maximum ("your input"), so we increment our input to the nextInt method by 1 to cancel it out and achieve our desired range of random numbesr
            for (int termOperationCount = 0; termOperationCount < termOperationLimit; termOperationCount++) {//start of for loop that generates a term in our polynomial equation, each iteration through this loop will add an operation and a digit to our quetsion  
                switch (ran.nextInt(2)) { // start of switch statement that randomly choose between 0 and 1, which mathces up to multiplication and subtraction
                case 0:// if 0 is chosen
                    nextDigit = ran.nextInt(10); // sets the value of nextDigit to a random digit, from 0 to 9.
                    termOperation = '×'; //sets the value of termOperation to the multiplication sign, which will be added to the quetsion string, which will only be used as a user friendly prompt  
                    termAnswer = termPrevious * nextDigit; //finds the total value of the term, as it currently is, so it can be accumulated through the loop and so we can arrive at the true total value of the term later on
                    break;//end of the case if 0 is chosen, or, multiplication is chosen
                case 1:// if 1 is chosen by the random number generator, therefore we go with division
                    termOperation = '÷';//sets the value of termOperation to the division sign, which will be added to the quetsion string, which will only be used as a user friendly prompt
                    do {//start of do while loop that doesnt stop until the answer of the term is an integer, which is what we want. Our quetsions shouldnt involve any digits at all in any step
                        nextDigit = ran.nextInt(9)+1; // sets the value of nextDigit to a random digit, from 1 to 9, the random number generator will generate an integer from 0 to 8, and then it will be incremented by 1, to 1 to 9. This digit can not be 0 as divisionby 0 is impossible 
                        termAnswer = termPrevious / nextDigit;// finds the total value of the term, as it currently is, so it can be accumulated through the loop and so we can arrive at the true total value of the term later on 
                    } while (termAnswer * nextDigit != termPrevious);//end and condition statement of the do while loop. The loop doesnt end until the answer of the term is an integer
                    break;//end of the case if 1 is chosen, or, division is chosen 
                }//end of random term and digit generation
                termPrevious = termAnswer;//sets the value of termPrevious to the current term answer, as the current total value of the term will be the "previous" value of the term in the next iteration of the for loop  
                question += " " + termOperation + " " + nextDigit;//adds a space followed by the new digit we just generated  
                operationCounter++;//since we just added a digit and an operation to our questioe, increment the operation counter   
            }//end of term generation loop
            switch (overallOperation) {//start of a switch block that either add or divide the total value of the term to the total value of the whole equation, depending on the operation generated at the end of the loop in the last iteration, or "+" set at the start manually if this is the first iteration 
            case '+'://if the value of overallOperation is +
                overallAnswer += termAnswer;//adds the total value of the term to the total value of the whole equation
                break;//end of the case if the value of overallOperation is +
            case '-'://if the value of overallOperation is -
                overallAnswer -= termAnswer;//subtracts the total value of the term from the total value of the whole equation
                break;//end of the case if the value of overallOperation is -
            }//end of the switch block that either add or divide the total value of the term to the total value of the whole equation
            switch (ran.nextInt(2)) { //start of a switch block that either randomly decides the value of overallOperation, it is powerd by a random number generator that outputs either 0 or 1
            case 0:// if the output of the random number generator is 0, we do addition 
                overallOperation = '+';//sets the value of overallOperation to the plus sign
                break;//end of the case if the output of the random number generator is 0
            case 1:// if the output of the random number generator is 1, we do subtraction
                overallOperation = '-';//sets the value of overallOperation to the minus sign
                break;//end of the case if the output of the random number generator is 1
            }//end of the switch block that decides the value of overallOperation
        } while (operationCounter < overallOperationLimit);//end of the main loop that genearte and adds/subtracts the term from the equation.
        System.out.print(question += " = ");//prints the string quetsion, which is filled up with a mathematical expression in the while loop above, followed by an equal sign surounded by spaces, to prompt the user to answer the question   
        return overallAnswer;//returns the answer, which is the total value of the whole expression, so the logic in the main method can handle user input and determine if the user got the quetsion right
    }//end of the generateQuestion method, a quetsion has been geneated and printed, an answer has been returned
}//end of the Assignment1 class! yay I finally finished commenting all this