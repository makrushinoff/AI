package lab3.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lab3.model.University;
import lombok.Data;

@Data
public class UniversityDao {

    private final List<University> knowledgeBase = UniversityDaoFiller.fillUniversityBase();

    public List<University> findByPredicate(Predicate<University> predicate) {;
        return knowledgeBase.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

}
