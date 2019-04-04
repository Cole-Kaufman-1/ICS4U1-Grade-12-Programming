import java.util.Scanner;

public class Array2D_2 {
    public static void main(String[] args) {
        char board[][] = {
                {'*','*','*','*','*'},
                {'*','*','*','*','*'},
                {'*','*','*','*','*'},
                {'*','*','*','*','*'}};
        boardDisplay(board);

        while (checkBoard(board) == true) {
            checkBoard(board);
            boardChange(board);
            boardDisplay(board);
        }
        System.out.println("Exit");
    }

    public static boolean checkBoard(char[][] board) {
        char testChar;

        for(int i = 0;i < board.length;i++) {
            for (int j = 0;j < 5;j++) {
                testChar = board[i][j];
                if (testChar == '*') {
                    return true;
                }
            }
        }
        return false;
    }

    public static void boardDisplay(char[][] board) {
        for(int i = 0;i < board.length;i++) {
            for (int j = 0;j < 5;j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }

    public static void boardChange(char[][] board) {
        Scanner input = new Scanner(System.in);
        System.out.print("\nType a coordinate of the board:");
        int coordinate1 = input.nextInt();
        int coordinate2 = input.nextInt();

        board[coordinate1][coordinate2] = 'x';
    }
}
