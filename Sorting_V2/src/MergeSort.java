public class MergeSort {

    public void mergeSort(int[] numbers, int arrayLength) {
        if (arrayLength < 2) {
            return;
        }

        int mid = arrayLength / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[arrayLength - mid];

        for (int i = 0; i < mid; i++) {
            leftArray[i] = numbers[i];
        }

        for (int i = mid; i < arrayLength; i++) {
            rightArray[i - mid] = numbers[i];
        }
        mergeSort(leftArray, mid);
        mergeSort(rightArray, arrayLength - mid);

        merge(numbers, leftArray, rightArray, mid, arrayLength - mid);

    }

    public static void merge(int[] numbers, int[] leftArray, int[] rightArray, int leftValue, int rightValue) {
        //create merge function
        //leftValue is left arrays last index
        //rightValue is left arrays last index

        int i = 0, j = 0, k = 0;

        while (i < leftValue && j < rightValue) {
            if (leftArray[i] <= rightArray[j]) {
                numbers[k++] = leftArray[i++];
            }
            else {
                numbers[k++] = rightArray[j++];
            }
        }
    }
}
