import java.util.Scanner;

public class ScoreFour_Assignment {
    public static void main(String[] args) {
        int gameBoardSize = createBoard();
        int[][][] gameBoard = new int[gameBoardSize][gameBoardSize][gameBoardSize];


        System.out.println(playerMove(gameBoard));
        System.out.println(computerMove(gameBoard));
    }

    public static String playerMove(int[][][] gameBoard) {
        Scanner input = new Scanner(System.in);
        String coordinates = "";

        System.out.println("Type the y, x and z coordinates of your move (with spaces in between):");

        while (coordinates.length() < 5) {
            int playerInput;

            //Change both player inputs to ask for string then convert to int so that the data is read into the buffer
            try {
                playerInput = input.nextInt();
                coordinates += Integer.toString(playerInput);
            } catch(Exception InputMismatchException) {
                System.out.println("Invalid position. Try again");
                playerInput = input.nextInt();
                coordinates += Integer.toString(playerInput);
            }


            if (coordinates.length() < 4) {
                coordinates += ",";
            }
        }
        input.nextLine();
        return coordinates;
    }

    public static String computerMove(int[][][] gameBoard) {
        Scanner input = new Scanner(System.in);
        String coordinates = "";

        for (int i = 0;i < 3;i++) {
            int random = (int)(Math.random() * gameBoard.length);
            coordinates += Integer.toString(random);

            if (coordinates.length() < 4) {
                coordinates += ",";
            }
        }
        return coordinates;
    }

    public static int createBoard() {
        Scanner input = new Scanner(System.in);

        System.out.println("Type the dimension you would like the size of the board to be:");
        try {
            return input.nextInt();

        } catch(Exception InputMismatchException) {
            System.out.println("Invalid size. Try again");
            return  input.nextInt();
        }
    }
}
