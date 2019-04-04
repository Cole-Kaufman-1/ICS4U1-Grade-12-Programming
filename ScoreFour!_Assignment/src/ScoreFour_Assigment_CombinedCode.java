import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;




public class ScoreFour_Assigment_CombinedCode {
    public static void main (String[] args){
        Scanner keyboard = new Scanner(System.in);
        int boardGameSize = createBoard();
        int[][][] board = new int[boardGameSize][boardGameSize][boardGameSize];
        boolean threeInARow = false;
        int playerTurn = 1;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                Arrays.fill(board[i][j], 0);
            }
        }
        drawGameBoard(board);

        playerTurn = playerChoice();

        while (!threeInARow){
            int[] coordinates;
            System.out.println("It is player " + playerTurn + "'s turn\n");
            //Player 1 is user
            if (playerTurn == 1) {
                coordinates = playerMove(board);
            }
            else {
                coordinates = computerMove(board);
            }


            update(board, coordinates, playerTurn);
            drawGameBoard(board);


            if (computerTurn1(board, playerTurn) > 0){
                System.out.println("Four in a row for player " + playerTurn);
                threeInARow = true;
            }
            if (playerTurn == 2){
                playerTurn = 1;
                System.out.println("The computer placed a move at " + coordinates[0] + " " + coordinates[1] + " " + coordinates[2]);
            }
            else{
                playerTurn = 2;
                System.out.println("You placed a move at " + coordinates[0] + " " + coordinates[1] + " " + coordinates[2]);
            }
        }
        keyboard.close();
    }

    public static void drawGameBoard(int[][][] board){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                for (int k = 0; k < board[0][0].length; k++){
                    System.out.print(board[i][j][k]);
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void update(int[][][] board, int[] coordinates, int playerTurn){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                for (int k = 0; k < board[0][0].length; k++){
                    if (i == coordinates[0] && j == coordinates[1] && k == coordinates[2]){
                        if (playerTurn == 1){
                            board[i][j][k] = 1;
                        }
                        else{
                            board[i][j][k] = 2;
                        }
                    }
                }
            }
        }
    }

    public static int computerTurn1(int[][][] board, int playerTurn){
        int threeInARow = 0;
        int counter = 0;
        for (int i = 0; i < board.length && threeInARow == 0; i++){
            for (int j = 0; j < board[0].length && threeInARow == 0; j++){
                for (int k = 0; k < board[0][0].length && threeInARow == 0; k++){

                    //j and k are consistent - straight line
                    for (int a = 0; a < board.length; a++){
                        if (board[a][j][k] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 3){
                        threeInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }


                    //i and k are consistent - straight line
                    for (int a = 0; a < board.length; a++){
                        if (board[i][a][k] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 3){
                        threeInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }


                    //i and k are consistent - straight line
                    for (int a = 0; a < board.length; a++){
                        if (board[i][j][a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 3){
                        threeInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    //diagonal postitive slope - j consistent
                    for (int a = 0; a < board.length; a++){
                        if (board[a][j][a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 3){
                        threeInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }



                    //diagonal negative slope - j consistent
                    for (int a = 0; a < board.length; a++){
                        if (board[3 - a][j][a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 3){
                        threeInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }


                    //diagonal postitive slope - i consistent
                    for (int a = 0; a < board.length; a++){
                        if (board[i][a][a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 3){
                        threeInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }



                    //diagonal negative slope - i consistent
                    for (int a = 0; a < board.length; a++){
                        if (board[i][3 - a][a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 3){
                        threeInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }


                    //diagonal postitive slope - k consistent
                    for (int a = 0; a < board.length; a++){
                        if (board[a][a][k] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 3){
                        threeInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }


                    //diagonal negative slope - k consistent
                    for (int a = 0; a < board.length; a++){
                        if (board[a][j][a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 3){
                        threeInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }
                }
            }
        }
        return threeInARow;
    }

    public static int createBoard() {
        Scanner input = new Scanner(System.in);
        //Change both player inputs to ask for string then convert to int so that the data is read into the buffer
        System.out.println("Type the dimension you would like the size of the board to be:");
        try {
            return input.nextInt();

        } catch(Exception InputMismatchException) {
            System.out.println("Invalid size. Try again");
            return input.nextInt();
        }
    }

    public static int[] playerMove(int[][][] gameBoard) {
        Scanner input = new Scanner(System.in);
        int[] coordinates = new int[3];

        System.out.println("Type the y, x and z coordinates of your move (with spaces in between):");

        for (int i = 0;i < coordinates.length;i++) {
            coordinates[i] = input.nextInt();
        }

        input.nextLine();
        return coordinates;
    }

    public static int[] computerMove(int[][][] gameBoard) {
        Scanner input = new Scanner(System.in);
        int[] coordinates = new int[3];

        for (int i = 0;i < coordinates.length;i++) {
            int random = (int)(Math.random() * gameBoard.length);
            coordinates[i] = random;

        }

        return coordinates;
    }

    public static int playerChoice() {
        Scanner input  = new Scanner(System.in);
        int firstPlayer;
        System.out.println("Who would you like to start first?\n1 for player\n2 for computer");

        firstPlayer = input.nextInt();

        if(firstPlayer == 1) {
            return 1;
        }
        else {
            return 2;
        }
    }
}
