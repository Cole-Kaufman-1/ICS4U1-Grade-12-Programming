import java.util.Scanner;

public class Sorting_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String postalCodes[] = new String[10];


        while (true) {
            String userPostCode = null;
            System.out.println("Type one of the three options:");
            System.out.println("1. Add\n2. Remove\n3. Edit");
            String keyword = input.nextLine();

            if (keyword.equalsIgnoreCase("Add")) {
                System.out.println("Type your postal code to add it to the database:");
                userPostCode = input.nextLine();
                menuDirectory(postalCodes,keyword,userPostCode);
            }
            else if (keyword.equalsIgnoreCase("Remove")) {
                System.out.println("Type your postal code to remove it from the database:");
                userPostCode = input.nextLine();
                menuDirectory(postalCodes,keyword,userPostCode);
            }
            else if (keyword.equalsIgnoreCase("Edit")) {
                System.out.println("Type your existing postal code to edit it:");
                userPostCode = input.nextLine();
                menuDirectory(postalCodes,keyword,userPostCode);
            }
            else {
                System.out.println("That is not an existing command, try again.");
            }
            sortPostCodes(postalCodes);
        }
    }

    public static String[] addPostCode(String[] postalCodes, String newPostCode) {
        for (int i = 0;i < postalCodes.length; i++) {
            if (postalCodes[i] == null) {
                postalCodes[i] = newPostCode;
                break;
            }
            else if (postalCodes[i].equals(newPostCode)){
                System.out.println("This address is already in the database.");
                break;
            }
            else if (i == 9 && postalCodes[i] != null){
                System.out.println("The database is full.");
                break;
            }
        }
        return postalCodes;
    }

    public static String[] removePostCode(String[] postalCodes, String removedPostCode) {

        for (int i = 0; i < postalCodes.length; i++) {
            if (postalCodes[i].equals(removedPostCode)) {
                postalCodes[i] = null;
                break;
            }
            else {
                System.out.println("This address is not in the database.");
                break;
            }
        }

        return postalCodes;
    }

    public static String[] editPostCode(String[] postalCodes,String userPostCode) {

        Scanner input = new Scanner(System.in);

        for (int i = 0;i < postalCodes.length; i++) {
            if (postalCodes[i].equals(userPostCode)) {
                System.out.println("Please type the new postal code that replaces the old one.");
                String newPostalCode = input.nextLine();
                char [] postCode = newPostalCode.toCharArray();
                if (Character.isLetter(postCode[0]) && Character.isDigit(postCode[1]) &&
                        Character.isLetter(postCode[2]) && Character.isWhitespace(postCode[3]) &&
                        Character.isDigit(postCode[4]) && Character.isLetter(postCode[5]) &&
                        Character.isDigit(postCode[6])) {
                    postalCodes[i] = newPostalCode;
                    break;
                }
                else{
                    System.out.println("That is not a valid postal code.");
                    break;
                }
            }
            else {
                System.out.println("This postal code is not in the database.");
                break;
            }
        }
        return postalCodes;
    }

    public static void menuDirectory(String[] postalCodes,String keyword, String userPostCode) {
        char[] postCode = userPostCode.toCharArray();

        if (Character.isLetter(postCode[0]) && Character.isDigit(postCode[1]) &&
        Character.isLetter(postCode[2]) && Character.isWhitespace(postCode[3]) &&
        Character.isDigit(postCode[4]) && Character.isLetter(postCode[5]) &&
        Character.isDigit(postCode[6])) {

            if (keyword.equalsIgnoreCase("Add")) {
                addPostCode(postalCodes,userPostCode);
            }
            else if(keyword.equalsIgnoreCase("Remove")) {
                removePostCode(postalCodes,userPostCode);
            }
            else if (keyword.equalsIgnoreCase("Edit")) {
                editPostCode(postalCodes,userPostCode);
            }
        }
        else {
            System.out.println("That is not a valid postal code.");
        }
    }

    public static String[] sortPostCodes (String[] postalCodes) {

        boolean swapped = false;

        for (int i = 0;i < postalCodes.length; i++) {
            swapped = false;
            for (int j = 0;j < postalCodes.length-i-1; j++) {
                if (postalCodes[j+ 1] != null && postalCodes[j + 1].compareTo(postalCodes[j]) < 0) {
                    swapped = true;

                    String temp = postalCodes[j];
                    postalCodes[j] = postalCodes[j + 1];
                    postalCodes[j + 1] = temp;
                }
            }
        }
        if (swapped == false) {
            return postalCodes;
        }

        return postalCodes;
    }
}
