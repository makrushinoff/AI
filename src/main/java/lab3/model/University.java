package lab3.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class University {

    private String name;
    private City city;
    private boolean stateUniversity;
    private int complexity;
    private MajorityScience majorityScience;
    private CostLevel costLevel;

}
