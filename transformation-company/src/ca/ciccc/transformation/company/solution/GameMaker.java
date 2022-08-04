package ca.ciccc.transformation.company.solution;

import java.util.ArrayList;
import java.util.Comparator;

public class GameMaker implements IGameMaker {
    public class Result {
        private Integer battleNum;
        private Integer autobotWins;
        private Integer deceptionWins;
        private ArrayList<Transformer> survivingAutobots;
        private ArrayList<Transformer> survivingDeceptions;
        private Transformer.Allegiance winningTeam;

        public Result() {
            this.battleNum = 0;
            this.autobotWins = 0;
            this.deceptionWins = 0;
            this.survivingAutobots = new ArrayList<>();
            this.survivingDeceptions = new ArrayList<>();
        }

        public void countUpBattleNum() {
            this.battleNum++;
        }

        public void countUpAutobotWins() {
            this.autobotWins++;
        }

        public void countUpDeceptionWins() {
            this.deceptionWins++;
        }

        public void addSurvivingAutobot(Transformer autobot) {
            this.survivingAutobots.add(autobot);
        }

        public void addSurvivingDeception(Transformer deception) {
            this.survivingDeceptions.add(deception);
        }

        public void destroyAllSurvivors() {
            this.survivingAutobots = new ArrayList<>();
            this.survivingDeceptions = new ArrayList<>();
        }

        public void setWinningTeam(Transformer.Allegiance allegiance) {
            this.winningTeam = allegiance;
        }

        public Integer getBattleNum() {
            return this.battleNum;
        }

        public Transformer.Allegiance getWinningTeam() {
            return this.winningTeam;
        }

        public ArrayList<String> getSurvivingAutobotNames() {
            ArrayList<String> names = new ArrayList<>();
            for (Transformer survivingAutobot : this.survivingAutobots) {
                names.add(survivingAutobot.getName());
            }
            return names;
        }

        public ArrayList<String> getSurvivingDeceptionNames() {
            ArrayList<String> names = new ArrayList<>();
            for (Transformer survivingDeception : this.survivingDeceptions) {
                names.add(survivingDeception.getName());
            }
            return names;
        }
    }

    private ArrayList<Transformer> deceptions;
    private ArrayList<Transformer> autobots;

    public GameMaker(ArrayList<Transformer> deceptions, ArrayList<Transformer> autobots) {
        this.deceptions = deceptions;
        this.autobots = autobots;
    }

    @Override
    public Result fight() {
        Result gameResult = new Result();
        sortTransformersByRank();

        for (int i = 0; i < Math.max(this.deceptions.size(), this.autobots.size()); i++) {
            if (this.deceptions.size() > i && this.autobots.size() > i) {
                BattleMaker battleMaker = new BattleMaker(this.deceptions.get(i), this.autobots.get(i));
                BattleMaker.Result battleResult = battleMaker.battle();

                gameResult.countUpBattleNum();

                if (battleResult != null) {
                    if (battleResult.getForcedEnd()) {
                        gameResult.destroyAllSurvivors();
                        break;
                    }

                    if (battleResult.getWinner().isAutobot()) {
                        gameResult.countUpAutobotWins();
                        gameResult.addSurvivingAutobot(battleResult.getWinner());
                    } else {
                        gameResult.countUpDeceptionWins();
                        gameResult.addSurvivingDeception(battleResult.getWinner());
                    }
                }
            } else if (this.deceptions.size() <= i) {
                gameResult.addSurvivingAutobot(this.autobots.get(i));
            } else {
                gameResult.addSurvivingDeception(this.deceptions.get(i));
            }
        }

        if (gameResult.autobotWins.equals(gameResult.deceptionWins)) return gameResult;
        if (gameResult.autobotWins > gameResult.deceptionWins) {
            gameResult.setWinningTeam(Transformer.Allegiance.Autobots);
        } else {
            gameResult.setWinningTeam(Transformer.Allegiance.Decepticons);
        }

        return gameResult;
    }

    private void sortTransformersByRank() {
        this.deceptions.sort((a, b) -> a.getRank().compareTo(b.getRank()) * -1);
        this.autobots.sort((a, b) -> a.getRank().compareTo(b.getRank()) * -1);
    }
}
