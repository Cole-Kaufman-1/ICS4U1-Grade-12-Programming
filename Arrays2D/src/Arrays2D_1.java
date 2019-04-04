import java.util.Scanner;

public class Arrays2D_1 {
    public static void main(String[] args) {
        int[][] studentMarks = collectStudentData();

        for (int i = 0;i < studentMarks.length;i++){

            System.out.print("Student " + (i + 1) + ":" + " ");
            for (int j = 0;j < 4;j++) {
                System.out.print(studentMarks[i][j] + " " );
            }
            System.out.println(averageCalc(studentMarks,i));
        }
    }

    public static int averageCalc(int[][] studentMarks, int studentNum) {
        int average = 0;
        for (int i = 0;i < 4;i++) {
            average += studentMarks[studentNum][i];
        }
        average = average / 4;
        return average;
    }

    public static int[][] collectStudentData() {
        Scanner input  = new Scanner(System.in);
        int[][] studentMarks = new int[5][4];

        for (int i = 0;i < studentMarks.length;i++) {
            System.out.println("Type your marks (with spaces)");
            for (int j = 0;j < 4;j++) {
                studentMarks[i][j] = input.nextInt();
            }
        }
        return studentMarks;
    }
}
