/**
 * ReviewExercise.java
 * Version 1.0
 * @Cole Kaufman
 * @07/02/2019
 * Program opens a text file and averages each individuals grade,
 * outputting their average and if they passed or failed.
 */

import java.util.Scanner;
import java.util.Random;

public class ReviewExercise_2 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        char gameBoard[][] = initializeBoard();
        displayBlankBoard(gameBoard);

        while (true) {
            String playerInput = playerGuess(gameBoard);
            boardGuess(gameBoard, playerInput);
        }
    }

    public static void displayBlankBoard(char gameBoard[][]){
        System.out.println("Hello welcome to Concentration Game 1.0\n");
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static String playerGuess(char gameBoard[][]) {
        Scanner input = new Scanner(System.in);

        System.out.print("Type the coordinates of your guess with a '-' in between the digits: ");
        String guessIndex = input.nextLine();
        System.out.println("");

        return guessIndex;
    }

    public static void boardGuess(char[][] gameBoard, String playerInput) {
       String[] playerInputSplit = playerInput.split("-");

       String indexString1 = playerInputSplit[0];
       String indexString2 = playerInputSplit[1];
       int index1 = Integer.parseInt(indexString1);
       int index2 = Integer.parseInt(indexString2);

       displayBoardProgress(gameBoard, index1, index2);
    }

    public static void displayBoardProgress(char[][] gameBoard, int index1, int index2) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (i == index1 && j ==index2) {
                    System.out.print(gameBoard[i][j]);
                }
                else{
                    System.out.print("*");
                }
            }
            System.out.print("\n");
        }
    }

    public static char[][] initializeBoard() {
        Random random = new Random();

        char[][] unshuffledBoard;
        unshuffledBoard = new char[][] {
                {'a','a','b','b'},
                {'c','c','d','d'},
                {'e','e','f','f'},
                {'g','g','h','h'}};

        //Fisher Yates Shuffle
        for (int i = 0; i < unshuffledBoard.length; i++) {
            for (int j = 0; j < unshuffledBoard.length; j++) {
                int randNum1 = random.nextInt(i +  1);
                int randNum2 = random.nextInt(j + 1);

                char tempBoardIndex = unshuffledBoard[i][j];
                unshuffledBoard[i][j] = unshuffledBoard[randNum1][randNum2];
                unshuffledBoard[randNum1][randNum2] = tempBoardIndex;
            }
        }
        char[][] shuffledBoard = unshuffledBoard;

        return shuffledBoard;
    }
}
