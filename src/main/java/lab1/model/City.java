package lab1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private Integer number;
    private Integer horizontalLocation;
    private Integer verticalLocation;

}
