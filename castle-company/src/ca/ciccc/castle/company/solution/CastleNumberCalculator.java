package ca.ciccc.castle.company.solution;

import java.util.ArrayList;

public class CastleNumberCalculator implements ICastleNumberCalculator {
    private final Detector.PeakDetector peakDetector;
    private final Detector.ValleyDetector valleyDetector;
    private final ArrayList<Integer> heights;

    public CastleNumberCalculator(ArrayList<Integer> heights) {
        this.heights = heights;

        Detector detector = new Detector(heights);
        this.peakDetector = detector.new PeakDetector();
        this.valleyDetector = detector.new ValleyDetector();
    }

    @Override
    public int getNumberOfCastles() {
        if (this.heights.isEmpty()) {
            return 0;
        } else {
            // You can always build a castle at the start of the array, provided it is non-empty.
            return 1 + this.peakDetector.getNumberOfPeaks() + this.valleyDetector.getNumberOfValleys();
        }
    }
}
