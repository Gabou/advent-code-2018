package day12;

import java.util.ArrayList;
import java.util.List;

public class Rule {

    private List<PlantState> pattern = new ArrayList<>();

    public Rule(String rulePattern) {
        char[] chars = rulePattern.toCharArray();
        for (char state : chars) {
            pattern.add(new PlantState(state));
        }

    }

    public List<PlantState> pattern() {
        return pattern;
    }
}
