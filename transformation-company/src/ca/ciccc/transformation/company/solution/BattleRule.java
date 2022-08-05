package ca.ciccc.transformation.company.solution;

import java.util.function.BiPredicate;

public class BattleRule {
    private Transformer decepticon;
    private Transformer autobot;

    public BattleRule(Transformer decepticon, Transformer autobot) {
        this.decepticon = decepticon;
        this.autobot = autobot;
    }

    public class BasicRule implements IBattleRule {
        public BattleMaker.Result judge() {
            if (battleByCourageAndStrength() != null) {
                return battleByCourageAndStrength();
            }

            if (battleBySkill() != null) {
                return battleBySkill();
            }

            if (battleByOverall() != null) {
                return battleByOverall();
            }

            // draw!
            return null;
        }

        private BattleMaker.Result battleByCourageAndStrength() {
            // draw!
            if (decepticon.getCourage().equals(autobot.getCourage())) return null;

            BiPredicate<Transformer, Transformer> isFighterWin = (fighter, opponent) -> (fighter.getCourage() - opponent.getCourage()) >= 4 && (fighter.getStrength() - opponent.getStrength()) >= 3;

            if (isFighterWin.test(decepticon, autobot)) {
                return new BattleMaker.Result(decepticon, autobot);
            }

            if (isFighterWin.test(autobot, decepticon)) {
                return new BattleMaker.Result(autobot, decepticon);
            }

            return null;
        }

        private BattleMaker.Result battleBySkill() {
            if (decepticon.getSkill().equals(autobot.getSkill())) return null;

            BiPredicate<Transformer, Transformer> isFighterWin = (fighter, opponent) -> (fighter.getSkill() - opponent.getSkill()) >= 3;

            if (isFighterWin.test(decepticon, autobot)) {
                return new BattleMaker.Result(decepticon, autobot);
            }

            if (isFighterWin.test(autobot, decepticon)) {
                return new BattleMaker.Result(autobot, decepticon);
            }

            return null;
        }

        private BattleMaker.Result battleByOverall() {
            if (decepticon.getOverall().equals(autobot.getOverall())) return null;

            if (decepticon.getOverall() > autobot.getOverall()) return new BattleMaker.Result(decepticon, autobot);

            return new BattleMaker.Result(autobot, decepticon);
        }
    }

    public class SpecialRule implements IBattleRule {
        public BattleMaker.Result judge() {
            if (autobot.isOptimusPrime() && decepticon.isPredaking()) {
                return new BattleMaker.Result();
            }

            if (autobot.isOptimusPrime()) {
                return new BattleMaker.Result(autobot, decepticon);
            }

            if (decepticon.isPredaking()) {
                return new BattleMaker.Result(decepticon, autobot);
            }

            return null;
        }
    }
}
