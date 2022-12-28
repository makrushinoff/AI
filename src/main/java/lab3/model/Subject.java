package lab3.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Subject {
    MATH(List.of(MajorityScience.TECHNICAL, MajorityScience.ECONOMIC, MajorityScience.UNIVERSAL), "Математика"),
    UKRAINIAN(List.of(MajorityScience.HUMANITARIAN, MajorityScience.UNIVERSAL), "Українська мова"),
    LITERATURE(List.of(MajorityScience.HUMANITARIAN, MajorityScience.UNIVERSAL), "Література"),
    ENGLISH(List.of(MajorityScience.HUMANITARIAN, MajorityScience.UNIVERSAL), "Англійська"),
    PHYSICS(List.of(MajorityScience.TECHNICAL, MajorityScience.UNIVERSAL), "Фізика"),
    CHEMISTRY(List.of(MajorityScience.TECHNICAL, MajorityScience.UNIVERSAL), "Хімія"),
    BIOLOGY(List.of(MajorityScience.TECHNICAL, MajorityScience.UNIVERSAL, MajorityScience.MEDICAL), "Біологія"),
    GEOGRAPHY(List.of(MajorityScience.TECHNICAL, MajorityScience.ECONOMIC, MajorityScience.UNIVERSAL, MajorityScience.HUMANITARIAN), "Географія"),
    ECONOMICS(List.of(MajorityScience.TECHNICAL, MajorityScience.ECONOMIC, MajorityScience.UNIVERSAL), "Економіка"),
    SAFE_LIFEHOOD(List.of(MajorityScience.HUMANITARIAN, MajorityScience.UNIVERSAL), "Ознови здоров'я"),
    INFORMATICS(List.of(MajorityScience.TECHNICAL, MajorityScience.UNIVERSAL), "Інформатика");

    private final List<MajorityScience> majoritySciences;
    private final String value;

    Subject(List<MajorityScience> majoritySciences, String value) {
        this.majoritySciences = majoritySciences;
        this.value = value;
    }

    public static List<String> getAvailableSubjectsValues() {
        return Arrays.stream(values())
                .map(Subject::getValue)
                .collect(Collectors.toList());
    }

    public static Subject getFromValue(String value) {
        return Arrays.stream(values())
                .filter(subject -> subject.getValue().contentEquals(value))
                .findFirst()
                .orElse(null);
    }

    public List<MajorityScience> getMajoritySciences() {
        return majoritySciences;
    }

    public String getValue() {
        return value;
    }
}
