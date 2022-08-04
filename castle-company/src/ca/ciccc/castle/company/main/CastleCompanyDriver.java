package ca.ciccc.castle.company.main;

import ca.ciccc.castle.company.solution.CastleNumberCalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CastleCompanyDriver {
    public static void run() {
        File inputFile = new File("castles.txt");
        Scanner in;

        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist!");
            return;
        }

        while (in.hasNextLine()) {
            ArrayList<Integer> heights = new ArrayList<>();
            String line = in.nextLine();

            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) {
                    heights.add(Character.getNumericValue(line.charAt(i)));
                }
            }

            CastleNumberCalculator castleNumberCalculator = new CastleNumberCalculator(heights);
            System.out.print(heights);
            System.out.print(": ");
            System.out.println(castleNumberCalculator.getNumberOfCastles());
        }
    }
}
