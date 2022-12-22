package lab1.service;

import lab1.model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapService {

    private Integer generateRandomInt(Integer min, Integer max) {
        return new Random().nextInt(max - min) + min;
    }

    public List<City> generateCitiesList(Integer citiesNumber, Integer sceneWidth, Integer sceneHeight) {
        Integer minWidth = 10;
        Integer maxWidth = sceneWidth + 10;
        Integer minHeight = 10;
        Integer maxHeight = sceneHeight - 10;

        List<City> cities = new ArrayList<>();
        for(int i = 1; i <= citiesNumber; i++) {
            cities.add(new City(i, generateRandomInt(minWidth, maxWidth), generateRandomInt(minHeight, maxHeight)));
        }
        return cities;
    }

}
