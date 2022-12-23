package lab2;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HopfieldGUI extends Application {

    private static final Integer PRIMARY_SCENE_WIDTH = 1200;
    private static final Integer PRIMARY_SCENE_HEIGHT = 950;

    private final HopfieldNetworkService hopfieldNetworkService = new HopfieldNetworkService();

    private Color checkedColor = Color.BLACK;
    private MyRectangle[][] inputLetterToRecognize = new MyRectangle[StandardImages.DIMENSION][StandardImages.DIMENSION];

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.setUpPrimaryStage(primaryStage);
        primaryStage.show();
        hopfieldNetworkService.trainNetwork();
    }

    private void setUpPrimaryStage(Stage primaryStage) {
        Text knownImages = new Text("Known letters:");
        knownImages.setFont(Font.font(26));
        Text inputImage = new Text("Input image to recognize");
        inputImage.setFont(Font.font(26));
        Text activeColor = new Text("Active color:");
        activeColor.setFont(Font.font(18));

        RadioButton black = new RadioButton("Black");
        RadioButton white = new RadioButton("White");
        ToggleGroup toggleGroup = new ToggleGroup();
        black.setToggleGroup(toggleGroup);
        white.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((ob, o, n) -> {
            RadioButton rb = (RadioButton)toggleGroup.getSelectedToggle();
            if (rb != null) {
                String s = rb.getText();
                checkedColor = s.contentEquals("Black") ? Color.BLACK : Color.WHITE;
            }
        });
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(500, 500);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(knownImages, 2, 0);
        gridPane.add(inputImage, 2, 3);
        gridPane.add(activeColor, 0, 4);
        gridPane.add(black, 1, 4);
        gridPane.add(white, 2, 4);
        gridPane.setStyle("-fx-background-color: #ABFCCD;");
        Scene scene = new Scene(gridPane, PRIMARY_SCENE_WIDTH, PRIMARY_SCENE_HEIGHT);
        primaryStage.setTitle("Lab 2 Hopfield network");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);

        gridPane.add(addLetters(this.colorBoxFromVector(StandardImages.standardImagesBits.get(0)), scene), 0, 1);
        gridPane.add(new Text("   "), 1, 1);
        gridPane.add(addLetters(this.colorBoxFromVector(StandardImages.standardImagesBits.get(1)), scene), 2, 1);
        gridPane.add(addLetters(this.colorBoxFromVector(StandardImages.standardImagesBits.get(2)), scene), 3, 1);
        gridPane.add(getInputLetterRectangle(scene), 2, 5);
        Button submitButton = new Button("Submit");
        gridPane.add(submitButton, 2, 6);

        submitButton.setOnAction(actionEvent -> {
            this.recognizeLetterStage().show();
        });

    }

    private Stage recognizeLetterStage() {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(500, 500);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        Stage recognizeStage = new Stage();
        Scene scene = new Scene(gridPane, 500, 400);
        recognizeStage.setTitle("Found letter");
        recognizeStage.setScene(scene);
        recognizeStage.initStyle(StageStyle.DECORATED);
        List<Integer> recognized = hopfieldNetworkService.recognizeLetter(this.fromRectangleToIntegers());
        if(recognized == null) {
            gridPane.add(new Text("Could not find correct letter"), 1, 1);
        } else {
            gridPane.add(this.addLetters(this.colorBoxFromVector(recognized), scene), 1, 1);
        }
        return recognizeStage;
    }

    private GridPane addLetters(Color[][] colorGrid, Scene scene) {
        GridPane mapPane = new GridPane();
        for (int i = 0; i < StandardImages.DIMENSION; i++) {
            for (int j = 0; j < StandardImages.DIMENSION; j++) {
                Rectangle rect = new Rectangle(3, 3);
                rect.widthProperty()
                        .bind(Bindings
                                .when(scene.widthProperty().lessThanOrEqualTo(scene.heightProperty().subtract(50)))
                                .then(scene.widthProperty().divide(50))
                                .otherwise(scene.heightProperty().subtract(50).divide(50)));
                rect.heightProperty().bind(rect.widthProperty());
                rect.setStyle("-fx-fill: " + toRGBCode(colorGrid[i][j]));
                mapPane.add(rect, j, i);
            }
        }
        return mapPane;
    }

    private GridPane getInputLetterRectangle(Scene scene) {
        GridPane mapPane = new GridPane();
        for (int i = 0; i < StandardImages.DIMENSION; i++) {
            for (int j = 0; j < StandardImages.DIMENSION; j++) {
                MyRectangle rect = new MyRectangle(3, 3);
                rect.widthProperty()
                        .bind(Bindings
                                .when(scene.widthProperty().lessThanOrEqualTo(scene.heightProperty().subtract(50)))
                                .then(scene.widthProperty().divide(50))
                                .otherwise(scene.heightProperty().subtract(50).divide(50)));
                rect.heightProperty().bind(rect.widthProperty());
                rect.setStyle("-fx-fill: " + toRGBCode(Color.WHITE) +"; -fx-border-color: " + Color.GRAY + "; -fx-border-width: 1");
                mapPane.add(rect, j, i);
                rect.setOnMouseClicked(mouseEvent -> {
                    rect.setStyle("-fx-fill: " + toRGBCode(checkedColor) +"; -fx-border-color: " + Color.GRAY + "; -fx-border-width: 1");
                    rect.setCurrentColor(checkedColor);
                });
                inputLetterToRecognize[i][j] = rect;
            }
        }
        return mapPane;
    }

    public static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    private List<Integer> fromRectangleToIntegers() {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < StandardImages.DIMENSION; i++) {
            for(int j = 0; j < StandardImages.DIMENSION; j++) {
                list.add(inputLetterToRecognize[i][j].getCurrentColor() == Color.BLACK ? 1 : -1);
            }
        }
        return list;
    }

    private Color[][] colorBoxFromVector(List<Integer> list) {
        int dimension = (int) Math.sqrt(list.size());
        Color[][] box = new Color[dimension][dimension];
        int listIndex = 0;
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                box[i][j] = list.get(listIndex) == -1 ? Color.WHITE : Color.BLACK;
                listIndex++;
            }
        }
        return box;
    }
}
