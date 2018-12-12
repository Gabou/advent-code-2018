package day12;

public class PropagationRule {
    private final Rule rule;
    private final PlantState result;

    public PropagationRule(Rule rule, PlantState result) {

        this.rule = rule;
        this.result = result;
    }

    public Rule rule() {
        return rule;
    }

    public PlantState result() {
        return result;
    }
}
