public class InsertionSort {

    public int[] instertionSort(int[] numbers){
        int index;
        int item;

        for (int i = 1;i < numbers.length;i++) {
            item = numbers[i];
            index = i - 1;

            while(index >= 0 && numbers[index] > item) {
                numbers[index + 1] = numbers[index];
                index--;
            }
            numbers[index + 1] = item;
        }

        return numbers;
    }
}
