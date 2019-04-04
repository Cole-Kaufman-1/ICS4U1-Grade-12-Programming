public class QuickSort {

    public void quickSort(int[] numbers, int start, int end) {

        int pivot = numbers[(start + end) / 2];
        int i = start, j = end;


        while(i < j) {
            if (numbers[i] < pivot) {
                i++;
            }
            if (numbers[j] > pivot) {
                j--;
            }


            if (numbers[i]>=pivot && numbers[j]<=pivot) {
                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
                i++;
                j--;
            }
        }

        if (start < j) {
            quickSort(numbers, start, j);
        }
        if (i < end) {
            quickSort(numbers, i, end);
        }
    }
}
