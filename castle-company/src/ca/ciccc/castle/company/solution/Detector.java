package ca.ciccc.castle.company.solution;

import java.util.ArrayList;

public class Detector {
    private ArrayList<Integer> heights;

    public Detector(ArrayList<Integer> heights) {
        this.heights = heights;
    }

    public class PeakDetector {
        public int getNumberOfPeaks() {
            int num = 0;
            boolean maybePeak = false;

            for (int i = 0; i < heights.size() - 1; i++) {
                if (heights.get(i).equals(heights.get(i + 1))) continue;

                if (heights.get(i) < heights.get(i + 1)) {
                    maybePeak = true;
                } else if (heights.get(i) > heights.get(i + 1)) {
                    if (maybePeak) {
                        num++;
                        maybePeak = false;
                    }
                }
            }

            return num;
        }
    }

    public class ValleyDetector {
        public int getNumberOfValleys() {
            int num = 0;
            boolean maybeValley = false;

            for (int i = 0; i < heights.size() - 1; i++) {
                if (heights.get(i).equals(heights.get(i + 1))) continue;

                if (heights.get(i) > heights.get(i + 1)) {
                    maybeValley = true;
                } else if (heights.get(i) < heights.get(i + 1)) {
                    if (maybeValley) {
                        num++;
                        maybeValley = false;
                    }
                }
            }

            return num;
        }
    }
}
