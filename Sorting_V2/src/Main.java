import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        runArraysSort();
        runBubbleSort();
        runInsertionSort();
        runQuickSort();
        runMergeSort();
    }

    public static void runMergeSort() {
        CompareRuntime compare = new CompareRuntime();
        MergeSort merge = new MergeSort();

        int[] numbers = shuffleNumbers();

        long startTime = compare.StartCompare();
        merge.mergeSort(numbers,numbers.length);
        long endTime = compare.EndCompare();

        for (int x : numbers) {
            System.out.println(x);
        }

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("The merge sort took: " + time);
    }


    public static void runQuickSort() {
        CompareRuntime compare = new CompareRuntime();
        QuickSort quick = new QuickSort();

        int[] numbers = shuffleNumbers();

        long startTime = compare.StartCompare();
        quick.quickSort(numbers,0,9999);
        long endTime = compare.EndCompare();

        for (int x : numbers) {
            System.out.println(x);
        }

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("The quick sort took: " + time);
    }

    public static void runArraysSort() {
        CompareRuntime compare = new CompareRuntime();

        int[] numbers = shuffleNumbers();

        long startTime = compare.StartCompare();
        Arrays.sort(numbers);
        long endTime = compare.EndCompare();

        for (int x : numbers) {
            System.out.println(x);
        }

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("The Arrays sort took: " + time);
    }

    public static void runBubbleSort() {
        CompareRuntime compare = new CompareRuntime();
        BubbleSort bubble = new BubbleSort();

        int[] numbers = shuffleNumbers();

        long startTime = compare.StartCompare();
        bubble.bubbleSort(numbers);
        long endTime = compare.EndCompare();

        for (int x : numbers) {
            System.out.println(x);
        }

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("The bubble sort took: " + time);
    }

    public static void runInsertionSort() {
        InsertionSort insertion = new InsertionSort();
        CompareRuntime compare = new CompareRuntime();

        int[] numbers = shuffleNumbers();

        long startTime = compare.StartCompare();
        insertion.instertionSort(numbers);
        long endTime = compare.EndCompare();

        for (int x : numbers) {
            System.out.println(x);
        }

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("The insertion sort took: " + time);
    }

    public static int[] shuffleNumbers() {
        Random rand = new Random();
        int[] numbers = new int[10000];
        int n;
        boolean contains = false;

        for (int i = 0;i < numbers.length;i++) {
            n = rand.nextInt(10001);
            for (int k = 0;k < numbers.length;k++) {
                if (numbers[k] == n) {
                    contains = true;
                    break;
                }
                else {
                    contains = false;
                }
            }
            if (!contains) {
                numbers[i] = n;
            }
            else {
                n = rand.nextInt(10001);
                i--;
            }
        }
        return numbers;
    }
}
