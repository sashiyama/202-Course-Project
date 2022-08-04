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

        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist!");
            return;
        }

        ArrayList<Transformer> deceptions = new ArrayList<>();
        ArrayList<Transformer> autobots = new ArrayList<>();

        while (in.hasNextLine()) {
            String line = in.nextLine();

            if (line.equals("")) {
                startGame(deceptions, autobots);
                deceptions = new ArrayList<>();
                autobots = new ArrayList<>();
                continue;
            }

            String[] plainFighters = line.split("\\*");

            for (String plainFighter : plainFighters) {
                String[] fighter = plainFighter.split(",");
                if (fighter[1].equals("D")) {
                    Transformer deception = new Transformer(
                            fighter[0],
                            Transformer.Allegiance.Decepticons,
                            Integer.parseInt(fighter[2]),
                            Integer.parseInt(fighter[3]),
                            Integer.parseInt(fighter[4]),
                            Integer.parseInt(fighter[5]),
                            Integer.parseInt(fighter[6]),
                            Integer.parseInt(fighter[7]),
                            Integer.parseInt(fighter[8]),
                            Integer.parseInt(fighter[9])
                    );
                    deceptions.add(deception);
                } else {
                    Transformer autobot = new Transformer(
                            fighter[0],
                            Transformer.Allegiance.Autobots,
                            Integer.parseInt(fighter[2]),
                            Integer.parseInt(fighter[3]),
                            Integer.parseInt(fighter[4]),
                            Integer.parseInt(fighter[5]),
                            Integer.parseInt(fighter[6]),
                            Integer.parseInt(fighter[7]),
                            Integer.parseInt(fighter[8]),
                            Integer.parseInt(fighter[9])
                    );
                    autobots.add(autobot);
                }
            }
        }

        startGame(deceptions, autobots);

        in.close();
    }

    private static void startGame(ArrayList<Transformer> deceptions, ArrayList<Transformer> autobots) {
        GameMaker gameMaker = new GameMaker(deceptions, autobots);
        GameMaker.Result result = gameMaker.fight();

        System.out.println();
        System.out.printf("The number of battles: %d\n", result.getBattleNum());

        if (result.getWinningTeam() == null) {
            System.out.println("Draw!");
            return;
        }

        if (result.getWinningTeam() == Transformer.Allegiance.Autobots) {
            System.out.printf("The winning team: (%s): %s\n", result.getWinningTeam(), result.getSurvivingAutobotNames());
            System.out.printf("The surviving members of the losing team: (%s): %s\n", Transformer.Allegiance.Decepticons, result.getSurvivingDeceptionNames());
        } else {
            System.out.printf("The winning team: (%s): %s\n", result.getWinningTeam(), result.getSurvivingDeceptionNames());
            System.out.printf("The surviving members of the losing team: (%s): %s\n", Transformer.Allegiance.Autobots, result.getSurvivingAutobotNames());
        }
    }
}
