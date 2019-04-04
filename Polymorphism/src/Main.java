import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int animalType, action = 0;
        String name;

        BoardingHouse boarding = new BoardingHouse();
        Scanner input = new Scanner(System.in);

        while (true) {
            name = null;
            System.out.println("Would to you like to pickup or drop off?");
            System.out.println("1. Pickup\n2. Drop off\n3. Quit");
            action = input.nextInt();
            input.nextLine();

            if (action == 1) {
                System.out.println("Enter your pets name: ");
                name = input.nextLine();
                boarding.pickUp(name);
            }

            else if (action == 2) {
                System.out.println("Enter your pets name: ");
                name = input.nextLine();
                System.out.println("1. Dog\n2. Cat\n3. Bird\nEnter your pet type: ");
                animalType = input.nextInt();
                input.nextLine();
                boarding.dropOff(name,animalType);
            }

            else {
                System.out.println("GoodBye :)");
                break;
            }
        }
    }
}
