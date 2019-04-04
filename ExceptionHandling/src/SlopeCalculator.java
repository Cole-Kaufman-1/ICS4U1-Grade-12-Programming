public class SlopeCalculator {
    int x1, y1, x2, y2;

    SlopeCalculator(int x1,int y1,int x2,int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public double calcSlope() throws VerticalLineException {
        double slope;

        if (x2 == 0 && x1 == 0) {
            throw new VerticalLineException();
        }

        slope = (y2 - y1) / (x2 - x1);

        return slope;
    }
}
