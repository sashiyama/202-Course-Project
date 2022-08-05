package ca.ciccc.transformation.company.main;

import ca.ciccc.transformation.company.solution.GameMaker;
import ca.ciccc.transformation.company.solution.Transformer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TransformationCompanyDriver {
    public static void run() {
        File inputFile = new File("transformers.txt");
        Scanner in;

        // error-handling or exception-handling
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist!");
            return;
        }

        ArrayList<Transformer> decepticons = new ArrayList<>();
        ArrayList<Transformer> autobots = new ArrayList<>();

        while (in.hasNextLine()) {
            String line = in.nextLine();

            if (line.equals("")) {
                GameMaker.start(decepticons, autobots);
                decepticons = new ArrayList<>();
                autobots = new ArrayList<>();
                continue;
            }

            String[] plainFighters = line.split("\\*");

            for (String plainFighter : plainFighters) {
                String[] fighter = plainFighter.split(",");
                if (fighter[1].equals("D")) {
                    decepticons.add(Transformer.createTransformer(fighter, Transformer.Allegiance.Decepticons));
                } else {
                    autobots.add(Transformer.createTransformer(fighter, Transformer.Allegiance.Autobots));
                }
            }
        }

        GameMaker.start(decepticons, autobots);

        in.close();
    }
}
