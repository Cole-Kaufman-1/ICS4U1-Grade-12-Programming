/**
 * ReviewExercise.java
 * Version 1.0
 * @Cole Kaufman
 * @05/02/2019
 * Program opens a text file and averages each individuals grade,
 * outputting their average and if they passed or failed.
 */

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

public class ReviewExercise {
    public static void main(String[] args) throws IOException {

        //Create File
        File file =  new File("C:\\School Work\\RHHS Grade 12\\ICS4U1\\Diagnostic\\src\\StudentGrades.txt");
        File outFile = new File("C:\\School Work\\RHHS Grade 12\\ICS4U1\\Diagnostic\\src\\out.txt");

        //Create Scanner
        Scanner newFile = new Scanner(file);

        //Create PrintWriter
        PrintWriter output = new PrintWriter(outFile);

        //Initialize variables for later use
        String line = "";
        String student = "";
        int numOfGrades = 0;
        int gradeSum = 0;
        double average = 0;

        //Read through file to check each line
        while (newFile.hasNext()){
             if (student.contains(line)){
                 line = newFile.next();
             }

            //Check if the first digit of the line string is a digit and if true add to the sum and number of grades
            if (Character.isDigit(line.charAt(0))) {

                while (Character.isDigit(line.charAt(0))) {
                    gradeSum += Integer.parseInt(line);
                    numOfGrades++;
                    if (newFile.hasNext()) {
                        line = newFile.next();
                    }
                    else {
                        break;
                    }

                }
                average = (double)gradeSum/numOfGrades;
                average = Math.round(average * 10.0) / 10.0;
                output(student,average,output);

                student = "";
                numOfGrades = 0;
                gradeSum = 0;
                average = 0;
            }

            //If the first digit of line is not a digit
            else {
                student += line + " ";
            }
        }
        output.close();
    }

    //Function to output the calculated values to the out.txt file
    public static void output(String student, double average, PrintWriter output) throws IOException {

        output.print(student + average);
        if (average >= 50) {
            output.print(" PASS\n");
        }
        else {
            output.print(" FAIL\n");
        }
    }
}
