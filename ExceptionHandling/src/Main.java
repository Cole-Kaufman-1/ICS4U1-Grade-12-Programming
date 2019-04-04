import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter two x-values and two y-values.");
        System.out.println("First x-value: ");
        int x1 = input.nextInt();
        input.nextLine();
        System.out.println("First y-value: ");
        int y1 = input.nextInt();
        input.nextLine();
        System.out.println("Second x-value: ");
        int x2 = input.nextInt();
        input.nextLine();
        System.out.println("Second y-value: ");
        int y2 = input.nextInt();
        input.nextLine();

        SlopeCalculator slope = new SlopeCalculator(x1,y1,x2,y2);

        try {
            System.out.println("Slope is: " + slope.calcSlope());
        }
        catch (VerticalLineException e) {
            System.out.println("The slope is undefined as both x-values are zero.");
        }
        finally {
            System.out.println("leaving try-catch block");
        }

    }
}
