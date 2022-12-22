package lab1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlgorithmParametersInput {

    private Integer iterationNumber;
    private Integer citiesNumber;
    private Double mutationPercent;

}
