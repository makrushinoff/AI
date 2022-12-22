package lab1.service;

import lab1.model.City;
import lab1.model.Solution;

import java.util.*;

public class GeneticAlgorithmService {

    private static final Integer DEFAULT_POPULATION_SIZE = 50;

    public List<Solution> generatePopulation(List<City> cities) {
        List<Solution> population = new ArrayList<>();

        for(int i = 1; i <= DEFAULT_POPULATION_SIZE; i++) {
            population.add(generateSolution(cities));
        }
        return population;
    }

    public Solution generateSolution(List<City> cities) {
        Solution solution = new Solution();
        List<City> solutionCitySequence = new ArrayList<>();
        List<City> sample = new LinkedList<>(cities);
        double generalPathLength = 0.0;
        int previousCityIndex = 0;
        while(!sample.isEmpty()) {
            City city1 = sample.remove(this.randomIndex(sample.size()));
            solutionCitySequence.add(city1);
            if(solutionCitySequence.size() > 1) {
                City city2 = solutionCitySequence.get(previousCityIndex);
                generalPathLength += calculateDistance(city1, city2);
            }
            previousCityIndex++;
        }
        City startCity = solutionCitySequence.get(0);
        City lastCity = solutionCitySequence.get(solutionCitySequence.size() - 1);
        generalPathLength += calculateDistance(startCity, lastCity);
        solutionCitySequence.add(startCity);
        solution.setCities(solutionCitySequence);
        solution.setPathLength(generalPathLength);
        return solution;
    }

    public List<Solution> evolvePopulation(List<Solution> parentPopulation, Double mutationPercent) {
        List<Solution> newPopulation = new ArrayList<>();
        List<Solution> parentPopulationCopy = new ArrayList<>(parentPopulation);
        while(parentPopulationCopy.size() > 0) {
            Solution randomParent = parentPopulationCopy.get(this.randomIndex(parentPopulationCopy.size()));
            parentPopulationCopy.sort(Comparator.comparingDouble(Solution::getPathLength));
            Solution leftFarthest = parentPopulationCopy.get(0);
            Solution rightFarthest = parentPopulationCopy.get(parentPopulationCopy.size() - 1);
            Solution secondParent = Math.abs(leftFarthest.getPathLength() - randomParent.getPathLength()) > Math.abs(rightFarthest.getPathLength() - randomParent.getPathLength())
                    ? leftFarthest
                    : rightFarthest;
            parentPopulationCopy.remove(randomParent);
            parentPopulationCopy.remove(secondParent);
            newPopulation.addAll(this.generateChildren(randomParent, secondParent, mutationPercent));
        }
        newPopulation.sort(Comparator.comparingDouble(Solution::getPathLength));
        return newPopulation;
    }

    private List<Solution> generateChildren(Solution firstParent, Solution secondParent, Double mutationPercent) {
        int firstPivot = randomIndex(firstParent.getCities().size() / 2);
        int secondPivot = randomIndex(firstParent.getCities().size());
        if(secondPivot <= firstPivot) {
            secondPivot += firstPivot;
            if(secondPivot >= firstParent.getCities().size()) {
                secondPivot = firstParent.getCities().size() - 1;
            }
        }
        firstParent.getCities().remove(firstParent.getCities().size() - 1);
        secondParent.getCities().remove(secondParent.getCities().size() - 1);
        Solution firstChild = new Solution();
        firstChild.setCities(new ArrayList<>());
        Solution secondChild = new Solution();
        secondChild.setCities(new ArrayList<>());
        for(int i = 0; i < firstParent.getCities().size(); i++) {
            if(i <= firstPivot) {
                firstChild.getCities().add(firstParent.getCities().get(i));
                secondChild.getCities().add(secondParent.getCities().get(i));
            } else if (i <= secondPivot) {
                this.setCitiesForChildrenAfterFirstPivot(firstChild, secondChild, firstParent, secondParent, i);
            } else {
                this.setCitiesForChildrenAfterSecondPivot(firstChild, secondChild, firstParent, secondParent, i);
            }
        }
        firstChild.getCities().add(firstChild.getCities().get(0));
        secondChild.getCities().add(secondChild.getCities().get(0));
        this.randomlyMutateChild(firstChild, mutationPercent);
        this.randomlyMutateChild(secondChild, mutationPercent);
        firstChild.setPathLength(this.calculatePathLength(firstChild.getCities()));
        secondChild.setPathLength(this.calculatePathLength(secondChild.getCities()));
        return List.of(firstChild, secondChild);
    }

    private void setCitiesForChildrenAfterFirstPivot(Solution firstChild, Solution secondChild, Solution firstParent, Solution secondParent, int i) {
        City cityFromFirstParent = firstParent.getCities().get(i);
        City cityFromSecondParent = secondParent.getCities().get(i);
        if(firstChild.getCities().contains(cityFromSecondParent)) {
            if(firstChild.getCities().contains(cityFromFirstParent)) {
                Optional<City> optionalCity = secondParent.getCities().stream()
                        .filter(city -> !firstChild.getCities().contains(city))
                        .findFirst();
                if(optionalCity.isPresent()) {
                    firstChild.getCities().add(optionalCity.get());
                } else {
                    firstChild.getCities().add(firstParent.getCities().stream()
                            .filter(city -> !firstChild.getCities().contains(city))
                            .findFirst()
                            .get());
                }
            } else {
                firstChild.getCities().add(cityFromFirstParent);
            }
        } else {
            firstChild.getCities().add(cityFromSecondParent);
        }
        if(secondChild.getCities().contains(cityFromFirstParent)) {
            if(secondChild.getCities().contains(cityFromSecondParent)) {
                Optional<City> optionalCity = firstParent.getCities().stream()
                        .filter(city -> !secondChild.getCities().contains(city))
                        .findFirst();
                if(optionalCity.isPresent()) {
                    secondChild.getCities().add(optionalCity.get());
                } else {
                    secondChild.getCities().add(secondParent.getCities().stream()
                            .filter(city -> !secondChild.getCities().contains(city))
                            .findFirst()
                            .get());
                }
            } else {
                secondChild.getCities().add(cityFromSecondParent);
            }
        } else {
            secondChild.getCities().add(cityFromFirstParent);
        }
    }

    private void setCitiesForChildrenAfterSecondPivot(Solution firstChild, Solution secondChild, Solution firstParent, Solution secondParent, int i) {
        City cityFromFirstParent = firstParent.getCities().get(i);
        City cityFromSecondParent = secondParent.getCities().get(i);
        if(firstChild.getCities().contains(cityFromFirstParent)) {
            if(firstChild.getCities().contains(cityFromSecondParent)) {
                Optional<City> optionalCity = firstParent.getCities().stream()
                        .filter(city -> !firstChild.getCities().contains(city))
                        .findFirst();
                if(optionalCity.isPresent()) {
                    firstChild.getCities().add(optionalCity.get());
                } else {
                    firstChild.getCities().add(secondParent.getCities().stream()
                            .filter(city -> !firstChild.getCities().contains(city))
                            .findFirst()
                            .get());
                }
            } else {
                firstChild.getCities().add(cityFromSecondParent);
            }
        } else {
            firstChild.getCities().add(cityFromFirstParent);
        }

        if(secondChild.getCities().contains(cityFromSecondParent)) {
            if(secondChild.getCities().contains(cityFromFirstParent)) {
                Optional<City> optionalCity = secondParent.getCities().stream()
                        .filter(city -> !secondChild.getCities().contains(city))
                        .findFirst();
                if(optionalCity.isPresent()) {
                    secondChild.getCities().add(optionalCity.get());
                } else {
                    secondChild.getCities().add(firstParent.getCities().stream()
                            .filter(city -> !secondChild.getCities().contains(city))
                            .findFirst()
                            .get());
                }
            } else {
                secondChild.getCities().add(cityFromFirstParent);
            }
        } else {
            secondChild.getCities().add(cityFromSecondParent);
        }
    }

    private void randomlyMutateChild(Solution child, Double mutationPercent) {
        if(this.toMutate(mutationPercent)) {
            int middleIndex = child.getCities().size() / 2;
            City removedFromMiddleIndex = child.getCities().remove(middleIndex);
            child.getCities().add(middleIndex + 1, removedFromMiddleIndex);
        }
    }

    private double calculatePathLength(List<City> cities) {
        double result = 0.0;
        for(int i = 0; i < cities.size(); i++) {
            if (i != cities.size() - 1) {
                result += calculateDistance(cities.get(i), cities.get(i + 1));
            }
        }
        return result;
    }

    private double calculateDistance(City city1, City city2) {
        return Math.sqrt(
                Math.pow(city1.getHorizontalLocation() - city2.getHorizontalLocation(), 2) + Math.pow(city1.getVerticalLocation() - city2.getVerticalLocation(), 2)
        );
    }

    private int randomIndex(int listSize) {
        return new Random().nextInt(listSize);
    }

    private boolean toMutate(Double mutationPercent) {
        final double randomValue = Math.random() * 100;
        return randomValue <= mutationPercent;
    }

}
