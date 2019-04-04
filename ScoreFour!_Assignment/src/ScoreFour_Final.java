import java.util.Scanner;
import java.util.Arrays;
/** ScoreFourFinal
 * @author Kian Alipanah
 * @author Cole Kaufman
 */
public class ScoreFour_Final{
    public static void main (String[] args){
        Scanner keyboard = new Scanner(System.in);
        boolean playerTurnValid = false;
        int playerTurn = 0;

        //Asking who will go first and checks validity
        do{
            System.out.println("Type 1 if the player will go first and 2 if the computer will go first");
            String playerTurnStr = keyboard.next();
            if (playerTurnStr.equals("1")){
                playerTurn = 1;
                playerTurnValid = true;
            }
            else if (playerTurnStr.equals("2")){
                playerTurn = 2;
                playerTurnValid = true;
            }
            else{
                System.out.println("Invalid number, try again");
            }
        }while(!playerTurnValid);


        int boardGameSize = createBoard();
        int[][][] board = new int[boardGameSize][boardGameSize][boardGameSize];
        String[][][] map = new String[boardGameSize][boardGameSize][boardGameSize];
        DisplayGrid grid = new DisplayGrid(map);

        //Boolean to set the game loop
        boolean fourInARow = false;

        //Fills the board with 0 which means no piece
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                Arrays.fill(board[i][j], 0);
            }
        }
        drawGameBoard(board, map, grid);


        //Game loop
        while (!fourInARow){
            int[] coordinates = new int[3];
            boolean atBottom = false;
            System.out.println("It is player " + playerTurn + "'s turn");
            if (playerTurn == 1) {
                coordinates = playerMove(board);
            }
            else {
                coordinates = computerMove(board, playerTurn);
            }

            //Moves piece to the bottom
            for (int i = coordinates[2] + 1; i < board.length && !atBottom; i++){
                if (board[coordinates[0]][coordinates[1]][i] > 0){
                    coordinates[2] = i - 1;
                    atBottom = true;
                }
                else if (i == board.length - 1){
                    coordinates[2] = i;
                    atBottom = true;
                }
            }

            grid.refresh();
            update(board, coordinates, playerTurn, map, grid);
            drawGameBoard(board, map, grid);

            //Determines four in a row and ends the game if it is
            if (fourInARow(board, playerTurn) > 0){
                System.out.println("Four in a row for player " + playerTurn);
                fourInARow = true;
            }

            //Switches player turn
            if (playerTurn == 2){
                playerTurn = 1;
            }
            else{
                playerTurn = 2;
            }
        }
        keyboard.close();
    }




    /**
     * checkIfValid
     * Accepts coordinates and the board as parameters and checks if the piece can be placed at those coordinates.
     * The method returns a boolean that says whether or not the piece can be placed there
     * @param coordinates, A 1D integer array that contains x, y, and z coordinates
     * @param board, A 3D integer array that represents the board, 0 as no piece, 1 as player piece and 2 as computer piece
     * @return Boolean, true if the coordinates are valid, false if they are not
     * @author Kian Alipanah
     */
    public static boolean checkIfValid(int[] coordinates, int[][][] board){
        boolean invalid = false;
        for (int i = 0; i < coordinates.length; i++){
            if (coordinates[i] < 0 || coordinates[i] > board.length){
                invalid = true;
            }
        }
        if (!invalid){
            if (board[coordinates[0]][coordinates[1]][coordinates[2]] == 0 ){
                invalid = false;
            }
            else{
                invalid = true;
            }
        }
        return (!invalid);
    }



    /**
     * drawGameBoard
     * Accepts the integer board, the string map and the grid object as parameters and transfers the values from the board to the map and refreshes the grid
     * @param board, A 3D integer array that represents the board, 0 as no piece, 1 as player piece and 2 as computer piece
     * @param map, a 3D String array with the same values as the board but as a string that will be transfered to the DisplayGrid class
     * @param grid, an object in the DisplayGrid class which lets you refresh the display
     * @author Kian Alipanah
     */
    public static void drawGameBoard(int[][][] board, String[][][] map, DisplayGrid grid){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                for (int k = 0; k < board[0][0].length; k++){
                    if (board[i][j][k] == 0){
                        map[j][i][k] = "0";
                    }
                    else if (board[i][j][k] == 1){
                        map[j][i][k] = "1";
                    }
                    else{
                        map[j][i][k] = "2";
                    }
                }
            }
        }
        grid.refresh();
    }


    /**
     * update
     * Accepts the board, the coordinates, the player turn, the string map and the grid object as parameters and updates the board based on the inputted coordinates
     * and refreshes the grid
     * @param board, A 3D integer array that represents the board, 0 as no piece, 1 as player piece and 2 as computer piece
     * @param coordinates, A 1D integer array that contains x, y, and z coordinates
     * @param playerTurn, An integer where 1 represents the player's turn and 2 represents the computer's turn
     * @param map, a 3D String array with the same values as the board but as a string that will be transfered to the DisplayGrid class
     * @param grid, an object in the DisplayGrid class which lets you refresh the display
     * @author Kian Alipanah
     */
    public static void update(int[][][] board, int[] coordinates, int playerTurn, String[][][] map, DisplayGrid grid){
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
        grid.refresh();
    }



    /**
     * fourInARow
     * Accepts the board and the player turn as parameters and determines if there is a four in a row
     * @param board, A 3D integer array that represents the board, 0 as no piece, 1 as player piece and 2 as computer piece
     * @param playerTurn, An integer where 1 represents the player's turn and 2 represents the computer's turn
     * @return int, 0 if there is no four in a row, 1 if the player got a four in a row, 2 if the computer got a four in row
     * @author Kian Alipanah
     */
    public static int fourInARow(int[][][] board, int playerTurn){
        int fourInARow = 0;
        int counter = 0;
        for (int i = 0; i < board.length && fourInARow == 0; i++){
            for (int j = 0; j < board[0].length && fourInARow == 0; j++){
                for (int k = 0; k < board[0][0].length && fourInARow == 0; k++){




                    for (int b = 0; b < board.length - 3 || board.length == 4 && b == 0; b++){
                        //j and k are consistent - straight line
                        for (int a = 0; a < 4; a++){
                            if (board[a + b][j][k] == playerTurn){
                                counter++;
                            }
                        }
                        if (counter >= 4){
                            fourInARow = playerTurn;
                        }
                        else{
                            counter = 0;
                        }


                        //i and k are consistent - straight line
                        for (int a = 0; a < 4; a++){
                            if (board[i][a + b][k] == playerTurn){
                                counter++;
                            }
                        }
                        if (counter >= 4){
                            fourInARow = playerTurn;
                        }
                        else{
                            counter = 0;
                        }


                        //i and k are consistent - straight line
                        for (int a = 0; a < 4; a++){
                            if (board[i][j][a + b] == playerTurn){
                                counter++;
                            }
                        }
                        if (counter >= 4){
                            fourInARow = playerTurn;
                        }
                        else{
                            counter = 0;
                        }
                    }

                    //diagonal j consistent
                    for (int a = 0; a < 4 && i + a < board.length && k + a < board.length; a++){
                        if (board[i + a][j][k + a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }


                    for (int a = 0; a < 4 && k + a < board.length && i - a >= 0; a++){
                        if (board[i - a][j][k + a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    for (int a = 0; a < 4 && i + a < board.length && k - a >= 0; a++){
                        if (board[i + a][j][k - a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }


                    for (int a = 0; a < 4 && i - a >= 0 && k - a >= 0; a++){
                        if (board[i - a][j][k - a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }


                    //diagonal i consistent
                    for (int a = 0; a < 4 && j + a < board.length && k + a < board.length; a++){
                        if (board[i][j + a][k + a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }



                    for (int a = 0; a < 4 && k + a < board.length && j - a >= 0; a++){
                        if (board[i][j - a][k + a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    for (int a = 0; a < 4 && j + a < board.length && k - a >= 0; a++){
                        if (board[i][j + a][k - a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    for (int a = 0; a < 4 && j - a >= 0 && k - a >= 0; a++){
                        if (board[i][j - a][k - a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }


                    //diagonal k consistent
                    for (int a = 0; a < 4 && i + a < board.length && j + a < board.length; a++){
                        if (board[i + a][j + a][k] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    for (int a = 0; a < 4 && j + a < board.length && i - a >= 0; a++){
                        if (board[i - a][j + a][k] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    for (int a = 0; a < 4 && i + a < board.length && j - a >= 0; a++){
                        if (board[i + a][j - a][k] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    for (int a = 0; a < 4 && i - a >= 0 && j - a >= 0; a++){
                        if (board[i - a][j - a][k] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }



                    //3d diagonal
                    for (int a = 0; a < 4 && i + a < board.length && j + a < board.length && k + a < board.length; a++){
                        if (board[i + a][j + a][k + a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    for (int a = 0; a < 4 && i + a < board.length && k + a < board.length && j - a >= 0; a++){
                        if (board[i + a][j - a][k + a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    for (int a = 0; a < 4 && i + a < board.length && j + a < board.length && k - a >= 0; a++){
                        if (board[i + a][j + a][k - a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    for (int a = 0; a < 4 && i + a < board.length && j - a >= 0 && k - a >= 0; a++){
                        if (board[i + a][j - a][k - a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    for (int a = 0; a < 4 && j + a < board.length && k + a < board.length && i - a >= 0; a++){
                        if (board[i - a][j + a][k + a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    for (int a = 0; a < 4 && j + a < board.length && i - a >= 0 && k - a >= 0; a++){
                        if (board[i - a][j + a][k - a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    for (int a = 0; a < 4 && k + a < board.length && i - a >= 0 && j - a >= 0; a++){
                        if (board[i - a][j - a][k + a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                    for (int a = 0; a < 4 && i - a >= 0 && j - a >= 0 && k - a >= 0; a++){
                        if (board[i - a][j - a][k - a] == playerTurn){
                            counter++;
                        }
                    }
                    if (counter >= 4){
                        fourInARow = playerTurn;
                    }
                    else{
                        counter = 0;
                    }

                }
            }
        }
        return fourInARow;
    }


    /**
     * createBoard
     * Ask the player what size they would like the board to be and checks if it is valid
     * @return int, returns an int value for the board size
     * @author Cole Kaufman
     */
    public static int createBoard() {
        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        boolean boardSizeValid = false;
        String boardSizeStr;
        int boardSize = 0;
        //Change both player inputs to ask for string then convert to int so that the data is read into the buffer
        do{
            System.out.println("Type the dimension you would like the size of the board to be:");
            boardSizeStr = input.next();
            if (boardSizeStr.length() == 1 && boardSizeStr.charAt(0) >= '4' && boardSizeStr.charAt(0) <= '9'){
                boardSizeValid = true;
                boardSize = Integer.parseInt(boardSizeStr);
            }
            else{
                System.out.println("Invalid number, try again");
            }
        }while (!boardSizeValid);

        return boardSize;
    }

    /**
     * playerMove
     * Accepts the board as a parameter and then retrieves the player coordinates,
     * then calls the checkIfValid method to check if the move is available
     * @param gameBoard, A 3D integer array that represents the board, 0 as no piece, 1 as player piece and 2 as computer piece
     * @return int[], returns an array populated with the players coordinates
     * @author Cole Kaufman
     */
    public static int[] playerMove(int[][][] gameBoard) {
        Scanner input = new Scanner(System.in);
        boolean invalid = false;
        String coordinatesStr;
        boolean stringValid = false;
        int[] coordinates = new int[3];

        //retrieves the coordinates of the player as a string and converts to an int array, also checks if the coordinate is valid and if not loops through again
        do{
            System.out.println("Type the x and y coordinates of your move (with spaces in between):");
            invalid = false;
            for (int i = 0;i < coordinates.length - 1;i++) {
                do{
                    stringValid = false;
                    coordinatesStr = input.next();
                    if (coordinatesStr.length() == 1 && coordinatesStr.charAt(0) >= '0' && coordinatesStr.charAt(0) < Character.forDigit(gameBoard.length, 10)){
                        stringValid = true;
                        coordinates[i] = Integer.parseInt(coordinatesStr);
                    }
                    else{
                        System.out.println("Invalid input, try again.");
                    }
                }while(!stringValid);
            }
            coordinates[2] = 0;
            if (!checkIfValid(coordinates, gameBoard)){
                invalid = true;
                System.out.println("Invalid input, try again.");
            }
        }while(invalid);


        input.nextLine();
        return coordinates;
    }

    /**
     * computerMove
     * Accepts the board and playerTurn as parameters and checks the board to see if the opposing player has 3 in a row
     * if they do it will block them from winning, and otherwise it randomly places a piece.(Tried to get it to also attack
     * but it did not work on the tournament day so I scrapped it)
     * @param board, A 3D integer array that represents the board, 0 as no piece, 1 as player piece and 2 as computer piece
     * @param playerTurn An integer where 1 represents the player's turn and 2 represents the computer's turn
     * @return int[], returns an array populated with the players coordinates
     * @author Cole Kaufman
     */
    public static int[] computerMove(int[][][] board, int playerTurn) {
        boolean invalid = false;
        int threeInARow = 0;
        int counter = 0;
        int[] computerPlacement = new int[3];
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
                        computerPlacement[0] = i++;
                        computerPlacement[1] = j;
                        computerPlacement[2] = k;
                        return computerPlacement;
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
                        computerPlacement[0] = i;
                        computerPlacement[1] = j++;
                        computerPlacement[2] = k;
                        return computerPlacement;

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
                        computerPlacement[0] = i;
                        computerPlacement[1] = j;
                        computerPlacement[2] = 0;
                        return computerPlacement;
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
                        computerPlacement[0] = i++;
                        computerPlacement[1] = k;
                        computerPlacement[2] = k++;
                        return computerPlacement;
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
                        computerPlacement[0] = i--;
                        computerPlacement[1] = j;
                        computerPlacement[2] = k--;
                        return computerPlacement;
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
                        computerPlacement[0] = i++;
                        computerPlacement[1] = j;
                        computerPlacement[2] = k++;
                        return computerPlacement;
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
                        computerPlacement[0] = i--;
                        computerPlacement[1] = j--;
                        computerPlacement[2] = k;
                        return computerPlacement;
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
                        computerPlacement[0] = i++;
                        computerPlacement[1] = k++;
                        computerPlacement[2] = k;
                        return computerPlacement;
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
                        computerPlacement[0] = i--;
                        computerPlacement[1] = j--;
                        computerPlacement[2] = k;
                        return computerPlacement;
                    }
                    else{
                        counter = 0;
                    }
                }
            }
        }

        do{

            if (!checkIfValid(computerPlacement, board)){
                invalid = true;
            }
        }while(invalid);

        return computerPlacement;
    }
}
