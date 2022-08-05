package ca.ciccc.transformation.company.solution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class GameMaker implements IGameMaker {
    public class Result {
        private Integer battleNum;
        private Integer autobotWins;
        private Integer decepticonWins;
        private ArrayList<Transformer> survivingAutobots;
        private ArrayList<Transformer> survivingdecepticons;
        private Transformer.Allegiance winningTeam;

        public Result() {
            this.battleNum = 0;
            this.autobotWins = 0;
            this.decepticonWins = 0;
            this.survivingAutobots = new ArrayList<>();
            this.survivingdecepticons = new ArrayList<>();
        }

        public void countUpBattleNum() {
            this.battleNum++;
        }

        public void countUpAutobotWins() {
            this.autobotWins++;
        }

        public void countUpDecepticonWins() {
            this.decepticonWins++;
        }

        public void addSurvivingAutobot(Transformer autobot) {
            this.survivingAutobots.add(autobot);
        }

        public void addSurvivingDecepticon(Transformer decepticon) {
            this.survivingdecepticons.add(decepticon);
        }

        public void destroyAllSurvivors() {
            this.survivingAutobots = new ArrayList<>();
            this.survivingdecepticons = new ArrayList<>();
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

        public ArrayList<String> getSurvivingDecepticonNames() {
            ArrayList<String> names = new ArrayList<>();
            for (Transformer survivingDecepticon : this.survivingdecepticons) {
                names.add(survivingDecepticon.getName());
            }
            return names;
        }
    }

    private ArrayList<Transformer> decepticons;
    private ArrayList<Transformer> autobots;

    public GameMaker(ArrayList<Transformer> decepticons, ArrayList<Transformer> autobots) {
        this.decepticons = decepticons;
        this.autobots = autobots;
    }

    @Override
    public Result fights() {
        Result gameResult = new Result();
        sortTransformersByRank();

        for (int i = 0; i < Math.max(this.decepticons.size(), this.autobots.size()); i++) {
            if (this.decepticons.size() > i && this.autobots.size() > i) {
                BattleMaker battleMaker = new BattleMaker(this.decepticons.get(i), this.autobots.get(i));
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
                        gameResult.countUpDecepticonWins();
                        gameResult.addSurvivingDecepticon(battleResult.getWinner());
                    }
                }
            } else if (this.decepticons.size() <= i) {
                gameResult.addSurvivingAutobot(this.autobots.get(i));
            } else {
                gameResult.addSurvivingDecepticon(this.decepticons.get(i));
            }
        }

        if (gameResult.autobotWins.equals(gameResult.decepticonWins)) return gameResult;
        if (gameResult.autobotWins > gameResult.decepticonWins) {
            gameResult.setWinningTeam(Transformer.Allegiance.Autobots);
        } else {
            gameResult.setWinningTeam(Transformer.Allegiance.Decepticons);
        }

        return gameResult;
    }

    private void sortTransformersByRank() {
        BiFunction<Transformer, Transformer, Integer> sortByRank = (a, b) -> a.getRank().compareTo(b.getRank()) * -1;

        this.decepticons.sort(sortByRank::apply);
        this.autobots.sort(sortByRank::apply);
    }
}
