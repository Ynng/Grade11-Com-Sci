import java.util.Random;

public class Assignment1 {

    final static int LEVEL_LIMIT = 10;
    final static int MAX_TERM_LENGTH_FACTOR = 1;
    final static int QUESTION_PER_LEVEL = 4;

    public static void main(String[] args) {
        int levelCounter = 1, nextDigit = 0, termAnswer, termPrevious, overallAnswer, maxTermLength, termOperationLimit,
                operationCounter;
        char operation = 'i', termOperation = 'i'; //i for initial
        String question;
        Random ran = new Random();

        System.out.println(
                "This is a math game, just enter the correct answer and move on!\nThe question are randomly generated and it will get harder and harder over time!\nThere are 4 levels per level, get all of them correct to go to the next level!");

        while (levelCounter < LEVEL_LIMIT) {
            printHorizontalLine();
            maxTermLength = (int) Math.ceil((double) levelCounter / (double) MAX_TERM_LENGTH_FACTOR);

            System.out.println("Welcome to level " + levelCounter + "!");
            for (int questionCounter = 1; questionCounter <= QUESTION_PER_LEVEL; questionCounter++) {
                printHorizontalLine();
                System.out.print("Question " + questionCounter + " :\t");

                // generating level
                question = "";
                operationCounter = -1;
                overallAnswer = 0;
                operation = 'i';
                do {
                    termAnswer = termPrevious = ran.nextInt(10);
                    if(operation=='i')operation='+';
                    else question += " " + operation;
                    question += " " + termPrevious;
                    operationCounter++;

                    termOperationLimit = ran.nextInt(Math.min(levelCounter - operationCounter, maxTermLength) + 1);
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
                    switch (operation) {
                    case '+':
                        overallAnswer += termAnswer;
                        break;
                    case '-':
                        overallAnswer -= termAnswer;
                        break;
                    }
                    switch (ran.nextInt(2)) { // randomly choose between addition or subtraction
                    case 0:// addition
                        operation = '+';
                        break;
                    case 1:// subtraction
                        operation = '-';
                        break;
                    }
                } while (operationCounter < levelCounter);
                System.out.println(question += " = " + overallAnswer);
            }
            levelCounter++;
        }
    }

    private static void printHorizontalLine() {
        System.out.println("__________________________________________________________");
    }
}