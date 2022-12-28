package lab3.dao;

import java.util.ArrayList;
import java.util.List;
import lab3.model.City;
import lab3.model.CostLevel;
import lab3.model.MajorityScience;
import lab3.model.University;

public class UniversityDaoFiller {

    public static List<University> fillUniversityBase() {
        List<University> universityList = new ArrayList<>();
        universityList.addAll(fillKyivUniversities());
        universityList.addAll(fillKharkivUniversities());
        universityList.addAll(fillLvivUniversities());
        return universityList;
    }

    public static List<University> fillLvivUniversities() {
        List<University> universityList = new ArrayList<>();
        universityList.add(University.builder()
                .name("Львівський національний університет імені Івана Франка")
                .stateUniversity(true)
                .majorityScience(MajorityScience.UNIVERSAL)
                .complexity(8)
                .costLevel(CostLevel.MEDIUM)
                .city(City.LVIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Національний університет \"Львівська політехніка\"")
                .stateUniversity(true)
                .majorityScience(MajorityScience.TECHNICAL)
                .complexity(8)
                .costLevel(CostLevel.MEDIUM)
                .city(City.LVIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Український католицький університет")
                .stateUniversity(false)
                .majorityScience(MajorityScience.HUMANITARIAN)
                .complexity(8)
                .costLevel(CostLevel.HIGH)
                .city(City.LVIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Львівський національний медичний університет імені Данила Галицького")
                .stateUniversity(true)
                .majorityScience(MajorityScience.MEDICAL)
                .complexity(6)
                .costLevel(CostLevel.MEDIUM)
                .city(City.LVIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Університет банківської справи")
                .stateUniversity(true)
                .majorityScience(MajorityScience.ECONOMIC)
                .complexity(4)
                .costLevel(CostLevel.LOW)
                .city(City.LVIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Національний лісотехнічний університет України")
                .stateUniversity(true)
                .majorityScience(MajorityScience.TECHNICAL)
                .complexity(3)
                .costLevel(CostLevel.LOW)
                .city(City.LVIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Львівський торговельно-економічний університет")
                .stateUniversity(true)
                .majorityScience(MajorityScience.ECONOMIC)
                .complexity(5)
                .costLevel(CostLevel.LOW)
                .city(City.LVIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Львівський державний університет безпеки життєдіяльності")
                .stateUniversity(true)
                .majorityScience(MajorityScience.HUMANITARIAN)
                .complexity(3)
                .costLevel(CostLevel.LOW)
                .city(City.LVIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Дрогобицький державний педагогічний університет імені Івана Франка")
                .stateUniversity(true)
                .majorityScience(MajorityScience.UNIVERSAL)
                .complexity(4)
                .costLevel(CostLevel.LOW)
                .city(City.LVIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Українська академія друкарства")
                .stateUniversity(true)
                .majorityScience(MajorityScience.HUMANITARIAN)
                .complexity(3)
                .costLevel(CostLevel.LOW)
                .city(City.LVIV)
                .build()
        );
        return universityList;
    }

    public static List<University> fillKharkivUniversities() {
        List<University> universityList = new ArrayList<>();
        universityList.add(University.builder()
                .name("Харківський національний університет імені В.Н. Каразіна")
                .stateUniversity(true)
                .majorityScience(MajorityScience.UNIVERSAL)
                .complexity(7)
                .costLevel(CostLevel.HIGH)
                .city(City.KHARKIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Харківський національний університет радіоелектроніки")
                .stateUniversity(true)
                .majorityScience(MajorityScience.TECHNICAL)
                .complexity(7)
                .costLevel(CostLevel.MEDIUM)
                .city(City.KHARKIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Національний юридичний університет імені Ярослава Мудрого")
                .stateUniversity(true)
                .majorityScience(MajorityScience.HUMANITARIAN)
                .complexity(7)
                .costLevel(CostLevel.MEDIUM)
                .city(City.KHARKIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Харківський національний медичний університет")
                .stateUniversity(true)
                .majorityScience(MajorityScience.MEDICAL)
                .complexity(8)
                .costLevel(CostLevel.HIGH)
                .city(City.KHARKIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Харківський національний економічний університет імені Семена Кузнеця")
                .stateUniversity(true)
                .majorityScience(MajorityScience.ECONOMIC)
                .complexity(4)
                .costLevel(CostLevel.LOW)
                .city(City.KHARKIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Національний аерокосмічний університет ім. М. Є. Жуковського \"Харківський авіаційний інститут\"")
                .stateUniversity(true)
                .majorityScience(MajorityScience.TECHNICAL)
                .complexity(4)
                .costLevel(CostLevel.LOW)
                .city(City.KHARKIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Харківський національний університет міського господарства імені О.М. Бекетова")
                .stateUniversity(true)
                .majorityScience(MajorityScience.ECONOMIC)
                .complexity(6)
                .costLevel(CostLevel.LOW)
                .city(City.KHARKIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Харківський гуманітарний університет «Народна українська академія»")
                .stateUniversity(false)
                .majorityScience(MajorityScience.HUMANITARIAN)
                .complexity(5)
                .costLevel(CostLevel.MEDIUM)
                .city(City.KHARKIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Харківський національний педагогічний університет імені Г.С. Сковороди")
                .stateUniversity(true)
                .majorityScience(MajorityScience.UNIVERSAL)
                .complexity(4)
                .costLevel(CostLevel.LOW)
                .city(City.KHARKIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Національний технічний університет \"Харківський політехнічний інститут\"")
                .stateUniversity(true)
                .majorityScience(MajorityScience.TECHNICAL)
                .complexity(6)
                .costLevel(CostLevel.MEDIUM)
                .city(City.KHARKIV)
                .build()
        );
        return universityList;
    }

    private static List<University> fillKyivUniversities() {
        List<University> universityList = new ArrayList<>();
        universityList.add(University.builder()
                .name("Київський національний університет імені Тараса Шевченка")
                .stateUniversity(true)
                .majorityScience(MajorityScience.UNIVERSAL)
                .complexity(9)
                .costLevel(CostLevel.HIGH)
                .city(City.KYIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Національний технічний університет України \"Київський політехнічний інститут імені Ігоря Сікорського\"")
                .stateUniversity(true)
                .majorityScience(MajorityScience.TECHNICAL)
                .complexity(10)
                .costLevel(CostLevel.HIGH)
                .city(City.KYIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Національний університет \"Києво-Могилянська академія\"")
                .stateUniversity(true)
                .majorityScience(MajorityScience.HUMANITARIAN)
                .complexity(9)
                .costLevel(CostLevel.HIGH)
                .city(City.KYIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Національний медичний університет імені О.О. Богомольця")
                .stateUniversity(true)
                .majorityScience(MajorityScience.MEDICAL)
                .complexity(7)
                .costLevel(CostLevel.MEDIUM)
                .city(City.KYIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Київський національний економічний університет імені Вадима Гетьмана")
                .stateUniversity(true)
                .majorityScience(MajorityScience.ECONOMIC)
                .complexity(6)
                .costLevel(CostLevel.MEDIUM)
                .city(City.KYIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Національний авіаційний університет")
                .stateUniversity(true)
                .majorityScience(MajorityScience.TECHNICAL)
                .complexity(5)
                .costLevel(CostLevel.MEDIUM)
                .city(City.KYIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Київська школа економіки")
                .stateUniversity(false)
                .majorityScience(MajorityScience.ECONOMIC)
                .complexity(6)
                .costLevel(CostLevel.HIGH)
                .city(City.KYIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Київський університет імені Бориса Грінченка")
                .stateUniversity(true)
                .majorityScience(MajorityScience.HUMANITARIAN)
                .complexity(5)
                .costLevel(CostLevel.LOW)
                .city(City.KYIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Національний педагогічний університет імені М.П. Драгоманова")
                .stateUniversity(true)
                .majorityScience(MajorityScience.UNIVERSAL)
                .complexity(5)
                .costLevel(CostLevel.LOW)
                .city(City.KYIV)
                .build()
        );
        universityList.add(University.builder()
                .name("Національний університет біоресурсів і природокористування України")
                .stateUniversity(true)
                .majorityScience(MajorityScience.TECHNICAL)
                .complexity(4)
                .costLevel(CostLevel.MEDIUM)
                .city(City.KYIV)
                .build()
        );
        return universityList;
    }

}
