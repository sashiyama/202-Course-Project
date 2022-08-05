package ca.ciccc.transformation.company.solution;

public class BattleMaker implements IBattleMaker {
    private Transformer decepticon;
    private Transformer autobot;
    private BattleRule.BasicRule basicRule;
    private BattleRule.SpecialRule specialRule;

    public static class Result {
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

    public BattleMaker(Transformer decepticon, Transformer autobot) {
        this.decepticon = decepticon;
        this.autobot = autobot;

        BattleRule battleRule = new BattleRule(this.decepticon, this.autobot);
        this.basicRule = battleRule.new BasicRule();
        this.specialRule = battleRule.new SpecialRule();
    }

    @Override
    public Result battle() {
        Result specialRuleResult = this.specialRule.judge();

        if (specialRuleResult != null) {
            return specialRuleResult;
        }

        return this.basicRule.judge();
    }
}
