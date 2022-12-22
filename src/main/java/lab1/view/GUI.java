package lab1.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lab1.model.AlgorithmParametersInput;
import lab1.model.City;
import lab1.model.Solution;
import lab1.service.GeneticAlgorithmService;
import lab1.service.MapService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class GUI extends Application {

    private static final Integer PRIMARY_SCENE_WIDTH = 1200;
    private static final Integer PRIMARY_SCENE_HEIGHT = 950;

    private final GeneticAlgorithmService geneticAlgorithmService = new GeneticAlgorithmService();
    private final MapService mapService = new MapService();

    private List<Solution> population = new ArrayList<>();
    private List<City> cities;
    private int currentIteration = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage startStage = this.setUpInputFieldsStage(primaryStage);
        startStage.show();
        primaryStage.setTitle("Genetic Algorithm");
        startStage.setOnHidden(windowEvent -> {
            primaryStage.show();
        });
    }

    private Stage setUpInputFieldsStage(Stage primaryStage) {
        Text iterationsNumberLabel = new Text("Iterations number:");
        TextField iterationsNumber = new TextField();
        Text citiesNumberLabel = new Text("Cities number:");
        TextField citiesNumber = new TextField();
        Text mutationPercentLabel = new Text("Mutation percent:");
        TextField mutationPercent = new TextField();

        Button submitButton = new Button("Start");
        submitButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(500, 500);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(iterationsNumberLabel, 0, 2);
        gridPane.add(iterationsNumber, 1, 2);
        gridPane.add(citiesNumberLabel, 0, 3);
        gridPane.add(citiesNumber, 1, 3);
        gridPane.add(mutationPercentLabel, 0, 4);
        gridPane.add(mutationPercent, 1, 4);
        gridPane.add(submitButton, 1, 5);
        gridPane.setStyle("-fx-background-color: white;");

        citiesNumberLabel.setStyle("-fx-font:  normal 20px 'sans-serif' ");
        mutationPercentLabel.setStyle("-fx-font:  normal 20px 'sans-serif' ");
        iterationsNumberLabel.setStyle("-fx-font:  normal 20px 'sans-serif' ");

        Stage stageStart = new Stage();
        Scene scene = new Scene(gridPane, 500, 350);
        stageStart.setTitle("Lab 1 Genetic algorithm");
        stageStart.setScene(scene);
        stageStart.initStyle(StageStyle.DECORATED);
        submitButton.setCursor(Cursor.HAND);
        submitButton.setOnAction(event -> {
            AlgorithmParametersInput algorithmParameters = new AlgorithmParametersInput(
                    Integer.valueOf(iterationsNumber.getText()),
                    Integer.valueOf(citiesNumber.getText()),
                    Double.valueOf(mutationPercent.getText())
            );
            this.setUpPrimaryStage(primaryStage, algorithmParameters);
            stageStart.close();
        });
        return stageStart;
    }

    private void setUpPrimaryStage(Stage primaryStage, AlgorithmParametersInput algorithmParameters) {
        Pane pane = new Pane();
        cities = mapService.generateCitiesList(algorithmParameters.getCitiesNumber(), PRIMARY_SCENE_WIDTH, PRIMARY_SCENE_HEIGHT);
        cities.forEach(city -> {
            Circle circle = new Circle(city.getHorizontalLocation(), city.getVerticalLocation(), 5);
            pane.getChildren().add(circle);
            pane.getChildren().add(new Text(city.getHorizontalLocation(), city.getVerticalLocation() + 17, String.valueOf(city.getNumber())));
        });
        primaryStage.setScene(new Scene(pane, PRIMARY_SCENE_WIDTH, PRIMARY_SCENE_HEIGHT));
        population = geneticAlgorithmService.generatePopulation(cities);
        AtomicReference<Solution> bestSolution = new AtomicReference<>();
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(0),
                        actionEvent -> {
                            population = geneticAlgorithmService.evolvePopulation(population, algorithmParameters.getMutationPercent());
                            bestSolution.set(population.get(0));
                            System.out.println("Best solution: " + bestSolution.get().getPathLength());
                            currentIteration++;
                            if(currentIteration == algorithmParameters.getIterationNumber()) {
                                pane.getChildren().addAll(drawPathFromSolution(bestSolution.get(), Color.RED));
                            } else {
                                pane.getChildren().addAll(drawPathFromSolution(bestSolution.get(), null));
                            }
                        }
                ), new KeyFrame(Duration.seconds(0.6))
        );
        timeline.setCycleCount(algorithmParameters.getIterationNumber());
        timeline.play();
    }

    private List<Line> drawPathFromSolution(Solution solution, Color color) {
        City prevCity = null;
        List<Line> lines = new ArrayList<>();
        for(City city : solution.getCities()) {
            if(prevCity != null) {
                lines.add(this.lineBetweenDots(prevCity, city, color));
            }
            prevCity = city;
        }
        return lines;
    }

    private Line lineBetweenDots(City city1, City city2, Color color) {
        final Line line = new Line(
                city1.getHorizontalLocation(),
                city1.getVerticalLocation(),
                city2.getHorizontalLocation(),
                city2.getVerticalLocation()
        );
        line.setStroke(Objects.requireNonNullElseGet(color, () -> new Color(Math.random(), Math.random(), Math.random(), Math.random())));
        line.setStrokeWidth(color == null ? 2.0 : 4.0);
        return line;
    }

}
