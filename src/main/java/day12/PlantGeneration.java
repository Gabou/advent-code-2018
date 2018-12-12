package day12;

import reader.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlantGeneration {
    private final List<Plant> initial;
    private final List<PropagationRule> propagationRules;

    public PlantGeneration(List<Plant> initial, List<PropagationRule> propagationRules) {

        this.initial = initial;
        this.propagationRules = propagationRules;
    }

    public int growTo(int generationNumber) {

        List<Plant> finalGeneration = new ArrayList<>(initial);

        for (int generation = 0; generation < generationNumber; generation++) {
            List<Plant> plants = new ArrayList<>(finalGeneration);
            finalGeneration = new ArrayList<>();

            addLeftExtremityPlants(plants);
            addRightExtremityPlants(plants);

            plants.sort(Comparator.comparing(Plant::potNumber));

            for (int i = 2; i < plants.size() - 2; i++) {
                boolean patternMatched = true;
                Plant plant = plants.get(i);
                Plant newPlant = new Plant(plant.potNumber(), new PlantState('.'));
                for (PropagationRule propagationRule : propagationRules) {

                    patternMatched = true;
                    List<PlantState> pattern = propagationRule.rule().pattern();
                    for (int j = 0; j < pattern.size(); j++) {
                        PlantState plantState = pattern.get(j);
                        if (!plants.get(i - 2 + j).plantState().equals(plantState)) {
                            patternMatched = false;
                            break;
                        }
                    }
                    if(patternMatched) {
                        newPlant = new Plant(plant.potNumber(), propagationRule.result());
                        break;
                    }

                }

                //if (patternMatched) {
                    finalGeneration.add(newPlant);
                /*} else {
                    finalGeneration.add(plant);
                }*/
            }
        }

        int result = 0;
        for (Plant plant : finalGeneration) {
            if(plant.plantState().equals(new PlantState('#'))) {
                result += plant.potNumber().value();
            }
        }

        return result;
    }

    private void addRightExtremityPlants(List<Plant> plants) {
        int lastPotNumber = plants.stream()
                .max(Comparator.comparing(Plant::potNumber))
                .get()
                .potNumber()
                .value();

        for (int potNumber = lastPotNumber + 1; potNumber <= (lastPotNumber + 5); potNumber++) {
            plants.add(new Plant(new PotNumber(potNumber), new PlantState('.')));
        }
    }

    private void addLeftExtremityPlants(List<Plant> plants) {
        int firstPotNumber = plants.stream()
                .min(Comparator.comparing(Plant::potNumber))
                .get()
                .potNumber()
                .value();

        for (int potNumber = firstPotNumber - 1; potNumber >= (firstPotNumber - 5); potNumber--) {
            plants.add(new Plant(new PotNumber(potNumber), new PlantState('.')));
        }
    }

    public static void main(String[] args) throws IOException {
        PlantGeneration plantGeneration = plantGeneration("/inputDay12");
        System.out.println(plantGeneration.growTo(20));
    }

    public static PlantGeneration plantGeneration(String filename) throws IOException {
        List<String> input = InputReader.input(filename);
        List<PropagationRule> propagationRules = new ArrayList<>();
        String initialState = "";

        for (String line : input) {
            if (line.isEmpty()) {
                continue;
            }

            if (line.contains("initial state")) {
                initialState = line.substring(Math.min(line.indexOf('.'),line.indexOf('#')));
                continue;
            }

            Rule rule = new Rule(line.substring(0,line.indexOf(" =>")));
            PlantState result = new PlantState(line.substring(line.indexOf("=> ") + 3).charAt(0));

            PropagationRule propagationRule = new PropagationRule(rule, result);

            propagationRules.add(propagationRule);
        }

        List<Plant> plants = new ArrayList<>();

        char[] charArray = initialState.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char plantState = charArray[i];
            plants.add(new Plant(new PotNumber(i), new PlantState(plantState)));
        }

        return new PlantGeneration(plants, propagationRules);
    }


}
