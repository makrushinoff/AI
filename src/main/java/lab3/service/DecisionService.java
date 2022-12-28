package lab3.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lab3.dao.UniversityDao;
import lab3.model.City;
import lab3.model.CostLevel;
import lab3.model.DecisionDto;
import lab3.model.MajorityScience;
import lab3.model.Subject;
import lab3.model.University;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecisionService {

    private final UniversityDao universityDao;

    public List<University> findRelevantUniversities(DecisionDto decisionDto) {
        Predicate<University> restrictions = (university) -> {
            Boolean needStateUniversity = defineNeedStateUniversity(decisionDto);
            boolean restriction = defineComplexityRange(decisionDto).contains(university.getComplexity()) &&
                    defineCity(decisionDto).contains(university.getCity()) &&
                    defineCostLevel(decisionDto).contains(university.getCostLevel()) &&
                    defineMajoritySciences(decisionDto).contains(university.getMajorityScience());
            if (!needStateUniversity) {
                return restriction;
            }
            return restriction && needStateUniversity;
        };
        return universityDao.findByPredicate(restrictions);
    }

    private List<Integer> defineComplexityRange(DecisionDto decisionDto) {
        Double averageMark = decisionDto.getAverageMark();
        Integer likeWorkRate = decisionDto.getLikeWorkRate();
        Integer targetForcedRate = decisionDto.getTargetForcedRate();
        Double normedAverageMark = (averageMark / 12) * 10;

        double averagePoints = (likeWorkRate + targetForcedRate + normedAverageMark) / 3;
        int point = (int) Math.floor(averagePoints);
        if (point == 10) {
            return List.of(8, 9, 10);
        }
        return List.of(point - 1, point, point + 1);
    }

    private Boolean defineNeedStateUniversity(DecisionDto decisionDto) {
        return !Boolean.TRUE.equals(decisionDto.getCanPayContract());
    }

    private List<CostLevel> defineCostLevel(DecisionDto decisionDto) {
        if (!Boolean.TRUE.equals(decisionDto.getCanPayContract())) {
            return Collections.singletonList(CostLevel.LOW);
        }
        return Arrays.stream(CostLevel.values()).collect(Collectors.toList());
    }

    private Set<MajorityScience> defineMajoritySciences(DecisionDto decisionDto) {
        Subject favouriteSubject = decisionDto.getFavouriteSubject();
        List<MajorityScience> subjectMajoritySciences = favouriteSubject.getMajoritySciences();
        if (!Boolean.TRUE.equals(decisionDto.getLikeAnalysis()) && !Boolean.TRUE.equals(decisionDto.getLikeCreation())
                && !Boolean.TRUE.equals(decisionDto.getLikeHelpPeople()) && !Boolean.TRUE.equals(decisionDto.getLikeMath())) {
            return new HashSet<>(subjectMajoritySciences);
        }
        Set<MajorityScience> majoritySciences = new HashSet<>();
        if (Boolean.TRUE.equals(decisionDto.getLikeHelpPeople())) {
            majoritySciences.addAll(List.of(MajorityScience.MEDICAL, MajorityScience.HUMANITARIAN));
        }
        if (Boolean.TRUE.equals(decisionDto.getLikeCreation()) && Boolean.TRUE.equals(decisionDto.getLikeAnalysis())) {
            majoritySciences.add(MajorityScience.TECHNICAL);
        }
        if (!Boolean.TRUE.equals(decisionDto.getLikeCreation()) && Boolean.TRUE.equals(decisionDto.getLikeAnalysis())) {
            majoritySciences.add(MajorityScience.ECONOMIC);
        }
        if (!Boolean.TRUE.equals(decisionDto.getWorkWithPeople())) {
            majoritySciences.add(MajorityScience.TECHNICAL);
        } else {
            majoritySciences.add(MajorityScience.HUMANITARIAN);
        }
        if ((decisionDto.getLikeMath())) {
            majoritySciences.addAll(Set.of(MajorityScience.ECONOMIC, MajorityScience.TECHNICAL));
        }
        majoritySciences.addAll(subjectMajoritySciences);
        return majoritySciences;
    }

    private List<City> defineCity(DecisionDto decisionDto) {
        if (!Boolean.TRUE.equals(decisionDto.getCityMatters())) {
            return Arrays.stream(City.values()).collect(Collectors.toList());
        }
        return Collections.singletonList(decisionDto.getCity());
    }
}
