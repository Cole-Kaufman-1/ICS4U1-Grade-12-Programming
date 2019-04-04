public class CompareRuntime {
    long startTime;
    long endTime;

    public long StartCompare() {
        this.startTime = System.nanoTime();

        return startTime;
    }

    public long EndCompare() {
        this.endTime = System.nanoTime();

        return endTime;
    }
}
