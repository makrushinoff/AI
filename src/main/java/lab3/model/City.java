package lab3.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum City {
    KYIV("Київ"),
    KHARKIV("Харків"),
    LVIV("Львів");

    private final String value;

    City(String value) {
        this.value = value;
    }

    public static List<String> getAvailableCitiesValues() {
        return Arrays.stream(values())
                .map(City::getValue)
                .collect(Collectors.toList());
    }

    public static City getFromValue(String value) {
        return Arrays.stream(values())
                .filter(city -> city.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    public String getValue() {
        return value;
    }
}
