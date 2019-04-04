import java.util.Scanner;

public class Classes_and_Objects {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean quit = false;
        DataBase data = new DataBase();

        while(!quit) {
            System.out.println("1. Add a Person\n2. Remove a Person\n3. List all People\n4. Quit\n 5. Sort by last name");
            String choice = validChoice(input.nextLine());
            if (choice.equals("4")) {
                quit = true;
            }

            else if(choice.equals("1")) {
                System.out.println("Type your first and last name:");
                String name = input.nextLine();
                System.out.println("Type your street:");
                String street = input.nextLine();
                System.out.println("Type your postcode:");
                String postCode = input.nextLine();
                System.out.println("Type your phone number:");
                String phoneNum = input.nextLine();
                System.out.println("Type your email:");
                String email = input.nextLine();
                Person person = new Person(name,street,postCode,phoneNum,email);
                data.addPerson(person);
            }
            else if(choice.equals("2")) {
                System.out.println("Type the name you would like removed:");
                String name = input.nextLine();
                data.removePerson(name);
            }
            else if (choice.equals("3")) {
               data.List();
            }
            else if (choice.equals("5")) {
                data.sortListLastNames();
            }
        }
    }

    public static String validChoice(String choice) {
        if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
            return choice;
        }
        else {
            System.out.println("Invalid choice try again");
            return "5";
        }
    }

}
