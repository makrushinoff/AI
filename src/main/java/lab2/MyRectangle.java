package lab2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MyRectangle extends Rectangle {

    private Color currentColor;

    public MyRectangle(double width, double height) {
        super(width, height);
    }
}
