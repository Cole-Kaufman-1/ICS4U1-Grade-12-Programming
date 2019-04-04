public class Sorting_2 {
    public static void main(String[] args) {
        String data[] = new String[] {"dog","cat","bird","plane","space","woof","ice","snow","sun","zebra"};
        selectionSortArray(data);
    }

    public static String[] selectionSortArray(String[] data) {

        int min;

        for (int i = 0;i < data.length;i++) {
            min = i;
            for (int j = i+1;j < data.length;j++) {
                if (data[min].compareTo(data[j]) > 0){
                    min = j;
                }
            }
            String temp = data[min];
            data[min] = data[i];
            data[i] = temp;
        }
        return data;
    }
}
