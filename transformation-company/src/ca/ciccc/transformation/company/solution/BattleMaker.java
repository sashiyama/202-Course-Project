package ca.ciccc.transformation.company.solution;

import java.util.function.BiPredicate;

public class BattleMaker implements IBattleMaker {
    private Transformer deception;
    private Transformer autobot;

    public class Result {
        private Transformer winner;
        private Transformer loser;
        private boolean isForcedEnd;

        public Result(Transformer winner, Transformer loser) {
            this.winner = winner;
            this.loser = loser;
            this.isForcedEnd = false;
        }

        public Result() {
            this.isForcedEnd = true;
        }

        public Transformer getWinner() {
            return this.winner;
        }

        public Transformer getLoser() {
            return this.loser;
        }

        public boolean getForcedEnd() {
            return this.isForcedEnd;
        }
    }

    public BattleMaker(Transformer deception, Transformer autobot) {
        this.deception = deception;
        this.autobot = autobot;
    }

    @Override
    public Result battle() {
        if (this.autobot.isOptimusPrime() && this.deception.isPredaking()) {
            return new Result();
        }

        if (this.autobot.isOptimusPrime()) {
            return new Result(this.autobot, this.deception);
        }

        if (this.deception.isPredaking()) {
            return new Result(this.deception, this.autobot);
        }

        if (battleByCourageAndStrength() != null) {
            return battleByCourageAndStrength();
        }

        if (battleBySkill() != null) {
            return battleBySkill();
        }

        if (battleByOverall() != null) {
            return battleByOverall();
        }

        return null;
    }

    private Result battleByCourageAndStrength() {
        if (this.deception.getCourage().equals(this.autobot.getCourage())) return null;

        BiPredicate<Transformer, Transformer> isFighterWin = (fighter, opponent) -> (fighter.getCourage() - opponent.getCourage()) >= 4 && (fighter.getStrength() - opponent.getStrength()) >= 3;

        if (isFighterWin.test(this.deception, this.autobot)) {
            return new Result(this.deception, this.autobot);
        }

        if (isFighterWin.test(this.autobot, this.deception)) {
            return new Result(this.autobot, this.deception);
        }

        return null;
    }

    private Result battleBySkill() {
        if (this.deception.getSkill().equals(this.autobot.getSkill())) return null;

        BiPredicate<Transformer, Transformer> isFighterWin = (fighter, opponent) -> (fighter.getSkill() - opponent.getSkill()) >= 3;

        if (isFighterWin.test(this.deception, this.autobot)) {
            return new Result(this.deception, this.autobot);
        }

        if (isFighterWin.test(this.autobot, this.deception)) {
            return new Result(this.autobot, this.deception);
        }

        return null;
    }

    private Result battleByOverall() {
        if (this.deception.getOverall().equals(this.autobot.getOverall())) return null;

        if (this.deception.getOverall() > this.autobot.getOverall()) return new Result(this.deception, this.autobot);

        return new Result(this.autobot, this.deception);
    }
}
