import java.util.Scanner;

public class Sorting_1 {
    public static void main(String[] args) {
        int[] data = new int[] {6,3,1,45,92,34,12,9,11,65};
        bubbleSortArray(data);
    }

    //fix return if swapped = false
    public static int[] bubbleSortArray(int[] data) {

        int item;
        boolean swapped = false;

        for (int i = 0;i < 10;i++) {
            swapped = false;
            for (int j = 0;j < data.length-1;j++) {
                if (data[j+1] < data[j]) {
                    item = data[j];
                    data[j] = data[j + 1];
                    data[j+1] = item;
                    swapped = true;

                }
            }
        }
        if (swapped == false) {
            return data;
        }
        return data;
    }
}
