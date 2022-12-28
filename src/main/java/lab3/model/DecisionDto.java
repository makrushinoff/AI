package lab3.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DecisionDto {

    private Subject favouriteSubject;
    private Double averageMark;
    private Boolean likeMath;
    private Boolean workWithPeople;
    private Boolean likeAnalysis;
    private Boolean likeCreation;
    private Integer likeWorkRate;
    private Integer targetForcedRate;
    private Boolean likeHelpPeople;
    private Boolean canPayContract;
    private Boolean cityMatters;
    private City city;

}
