package ca.ciccc.transformation.company.solution;

public class Transformer implements ITransformer {
    public enum Allegiance {
        Autobots,
        Decepticons
    }

    private String name;
    private Allegiance allegiance;
    private Integer strength;
    private Integer intelligence;
    private Integer speed;
    private Integer endurance;
    private Integer rank;
    private Integer courage;
    private Integer firepower;
    private Integer skill;

    public Transformer(String name,
                       Allegiance allegiance,
                       Integer strength,
                       Integer intelligence,
                       Integer speed,
                       Integer endurance,
                       Integer rank,
                       Integer courage,
                       Integer firepower,
                       Integer skill) {
        this.name = name;
        this.allegiance = allegiance;
        this.strength = strength;
        this.intelligence = intelligence;
        this.speed = speed;
        this.endurance = endurance;
        this.rank = rank;
        this.courage = courage;
        this.firepower = firepower;
        this.skill = skill;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getOverall() {
        return this.strength + this.intelligence + this.speed + this.endurance + this.firepower;
    }

    @Override
    public Integer getRank() {
        return this.rank;
    }

    @Override
    public Integer getCourage() {
        return this.courage;
    }

    @Override
    public Integer getStrength() {
        return this.strength;
    }

    @Override
    public Integer getSkill() {
        return this.skill;
    }

    @Override
    public boolean isOptimusPrime() {
        return this.name.equals("Optimus Prime");
    }

    @Override
    public boolean isPredaking() {
        return this.name.equals("Predaking");
    }

    @Override
    public boolean isAutobot() {
        return this.allegiance.equals(Allegiance.Autobots);
    }
}
