public class BubbleSort {

    public int[] bubbleSort(int[] numbers) {
        boolean changed = false;

        for (int i = 0;i < numbers.length;i++) {
            changed = false;
            for (int k = 0;k < numbers.length - 1;k++) {
                if (numbers[k] > numbers[k + 1] && k + 1 < 10000)  {
                    int temp = numbers[k];
                    numbers[k] = numbers[k + 1];
                    numbers[k + 1] = temp;
                    changed =  true;
                }
                else if (changed = false && i == 10000) {
                    break;
                }
            }
        }
        return numbers;
    }
}
