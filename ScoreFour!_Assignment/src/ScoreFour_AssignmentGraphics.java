import java.util.Scanner;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ScoreFour_AssignmentGraphics{
    public static void main (String[] args){
        Scanner keyboard = new Scanner(System.in);
        boolean playerTurnValid = false;
        int playerTurn = 0;
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
        boolean fourInARow = false;

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                Arrays.fill(board[i][j], 0);
            }
        }
        drawGameBoard(board, map, grid);

        while (!fourInARow){
            int[] coordinates = new int[3];
            boolean atBottom = false;
            System.out.println("It is player " + playerTurn + "'s turn");
            if (playerTurn == 1) {
                coordinates = playerMove(board);
            }
            else {
                coordinates = computerMove1(board, playerTurn);
            }

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
            if (fourInARow(board, playerTurn) > 0){
                System.out.println("Four in a row for player " + playerTurn);
                fourInARow = true;
            }
            if (playerTurn == 2){
                playerTurn = 1;
            }
            else{
                playerTurn = 2;
            }
        }
        keyboard.close();
    }


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

    public static int[] playerMove(int[][][] gameBoard) {
        Scanner input = new Scanner(System.in);
        boolean invalid = false;
        String coordinatesStr;
        boolean stringValid = false;
        int[] coordinates = new int[3];

        do{
            System.out.println("Type the x and y coordinates of your move (press enter in between numbers):");
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
                        System.out.println("Invalid input, try again. 1");
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

    public static int[] computerMove1(int[][][] board, int playerTurn) {
            boolean invalid = false;
            int threeInARow = 0;
            int counter = 0;
            int[] computerPlacment = new int[3];
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
                            computerPlacment[0] = i++;
                            computerPlacment[1] = j;
                            computerPlacment[2] = k;
                            return computerPlacment;
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
                            computerPlacment[0] = i;
                            computerPlacment[1] = j++;
                            computerPlacment[2] = k;
                            return computerPlacment;

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
                            computerPlacment[0] = i;
                            computerPlacment[1] = j;
                            computerPlacment[2] = 0;
                            return computerPlacment;
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
                            computerPlacment[0] = i++;
                            computerPlacment[1] = k;
                            computerPlacment[2] = k++;
                            return computerPlacment;
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
                            computerPlacment[0] = i--;
                            computerPlacment[1] = j;
                            computerPlacment[2] = k--;
                            return computerPlacment;
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
                            computerPlacment[0] = i++;
                            computerPlacment[1] = j;
                            computerPlacment[2] = k++;
                            return computerPlacment;
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
                            computerPlacment[0] = i--;
                            computerPlacment[1] = j--;
                            computerPlacment[2] = k;
                            return computerPlacment;
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
                            computerPlacment[0] = i++;
                            computerPlacment[1] = k++;
                            computerPlacment[2] = k;
                            return computerPlacment;
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
                            computerPlacment[0] = i--;
                            computerPlacment[1] = j--;
                            computerPlacment[2] = k;
                            return computerPlacment;
                        }
                        else{
                            counter = 0;
                        }
                    }
                }
            }

        do{

            if (!checkIfValid(computerPlacment, board)){
                invalid = true;
            }
        }while(invalid);

        return computerPlacment;
    }
}

class DisplayGrid {

    private JFrame frame;
    private MyKeyListener keyListener = new MyKeyListener();
    private int maxX,maxY, GridToScreenRatio, layer;
    private String[][][] world;

    DisplayGrid(String[][][] w) {
        this.world = w;

        maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
        maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
        GridToScreenRatio = maxY / (world.length+1);  //ratio to fit in screen as square map

        GridToScreenRatio = 50;

        System.out.println("Map size: "+world.length+" by "+world[0].length + " by " + world[0][0].length + "\nScreen size: "+ maxX +"x"+maxY+ " Ratio: " + GridToScreenRatio);

        this.frame = new JFrame("Score Four");

        GridAreaPanel worldPanel = new GridAreaPanel();

        frame.getContentPane().add(BorderLayout.CENTER, worldPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setVisible(true);
        frame.addKeyListener(keyListener);
    }


    public void refresh() {
        frame.repaint();
    }



    class GridAreaPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.repaint();

            setDoubleBuffered(true);
            g.setColor(Color.BLACK);
            for(int i = 0; i<world.length;i=i+1)
            {
                for(int j = 0; j<world[0].length;j=j+1)
                {

                    if (world[i][j][layer].equals("1"))    //This block can be changed to match character-color pairs
                        g.setColor(Color.RED);
                    else if (world[i][j][layer].equals("2"))
                        g.setColor(Color.BLUE);
                    else
                        g.setColor(Color.WHITE);

                    g.fillRect(j*GridToScreenRatio, i*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
                    g.setColor(Color.BLACK);
                    g.drawRect(j*GridToScreenRatio, i*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
                }
            }
        }
    }//end of GridAreaPanel


    class MyKeyListener implements KeyListener{
        // method to process key pressed events (when a key goes down, i.e. immediately)
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_1){
                layer = 0;
            }
            else if (key == KeyEvent.VK_2){
                layer = 1;
            }
            else if (key == KeyEvent.VK_3){
                layer = 2;
            }
            else if (key == KeyEvent.VK_4){
                layer = 3;
            }
            else if (key == KeyEvent.VK_5){
                layer = 4;
            }
            else if (key == KeyEvent.VK_6){
                layer = 5;
            }
            else if (key == KeyEvent.VK_7){
                layer = 6;
            }
            else if (key == KeyEvent.VK_8){
                layer = 7;
            }
            else if (key == KeyEvent.VK_9){
                layer = 8;
            }
            else if (key == KeyEvent.VK_0){
                layer = 9;
            }
            if (layer >= world.length){
                layer = world.length - 1;
            }
            System.out.println("Layer: " + (layer + 1));
        }
        public void keyReleased(KeyEvent e){
        }
        public void keyTyped(KeyEvent e){
        }
    }

}





