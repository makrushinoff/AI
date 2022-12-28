package lab3.view;

import com.sun.javafx.scene.control.DoubleField;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lab3.dao.UniversityDao;
import lab3.model.City;
import lab3.model.DecisionDto;
import lab3.model.Subject;
import lab3.model.University;
import lab3.service.DecisionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExpertSystemGUI extends Application {

    private static final Integer PRIMARY_SCENE_WIDTH = 700;
    private static final Integer PRIMARY_SCENE_HEIGHT = 500;

    private final DecisionService decisionService = new DecisionService(new UniversityDao());

    private DecisionDto.DecisionDtoBuilder builder = DecisionDto.builder();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.setUpPrimaryStage(primaryStage);
        primaryStage.show();
    }

    private void setUpPrimaryStage(Stage primaryStage) {
        VBox root = new VBox();
        root.setMinSize(500, 500);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(this.favouriteSubject(root));
        Scene scene = new Scene(root, PRIMARY_SCENE_WIDTH, PRIMARY_SCENE_HEIGHT);
        primaryStage.setTitle("Lab 3 Expert system");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
    }

    private VBox favouriteSubject(VBox root) {
        VBox favouriteSubject = new VBox();
        Text text = new Text("Який улюблений предмет у школі?");
        ComboBox<String> favouriteSubjectDropDown = new ComboBox<>();
        favouriteSubjectDropDown.getItems().addAll(Subject.getAvailableSubjectsValues());
        Button button = new Button("Продовжити");
        favouriteSubject.getChildren().add(text);
        favouriteSubject.getChildren().add(favouriteSubjectDropDown);
        favouriteSubject.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            builder.favouriteSubject(Subject.getFromValue(favouriteSubjectDropDown.getValue()));
            root.getChildren().remove(favouriteSubject);
            root.getChildren().add(this.averageMark(root));
        });
        return favouriteSubject;
    }

    private VBox averageMark(VBox root) {
        VBox averageMark = new VBox();
        Text text = new Text("Який середній бал атестату?");
        DoubleField averageMarkInput = new DoubleField();
        Button button = new Button("Продовжити");
        averageMark.getChildren().add(text);
        averageMark.getChildren().add(averageMarkInput);
        averageMark.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            builder.averageMark(averageMarkInput.getValue());
            root.getChildren().remove(averageMark);
            root.getChildren().add(this.likeMath(root));
        });
        return averageMark;
    }

    private VBox likeMath(VBox root) {
        VBox likeMath = new VBox();
        Text text = new Text("Чи подобається математика?");
        ComboBox<String> likeMathDropDown = new ComboBox<>();
        likeMathDropDown.getItems().addAll("Так", "Ні");
        Button button = new Button("Продовжити");
        likeMath.getChildren().add(text);
        likeMath.getChildren().add(likeMathDropDown);
        likeMath.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            builder.likeMath(likeMathDropDown.getValue().contentEquals("Так"));
            root.getChildren().remove(likeMath);
            root.getChildren().add(this.workWithPeople(root));
        });
        return likeMath;
    }

    private VBox workWithPeople(VBox root) {
        VBox workWithPeople = new VBox();
        Text text = new Text("Як більше подобаєтсья виконувати роботу: говоривши з людьми, чи самостійно виконувати задачі?");
        ComboBox<String> workWithPeopleDropDown = new ComboBox<>();
        workWithPeopleDropDown.getItems().addAll("З людьми", "Самостійно");
        Button button = new Button("Продовжити");
        workWithPeople.getChildren().add(text);
        workWithPeople.getChildren().add(workWithPeopleDropDown);
        workWithPeople.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            builder.workWithPeople(workWithPeopleDropDown.getValue().contentEquals("З людьми"));
            root.getChildren().remove(workWithPeople);
            root.getChildren().add(this.likeAnalysis(root));
        });
        return workWithPeople;
    }

    private VBox likeAnalysis(VBox root) {
        VBox likeAnalysis = new VBox();
        Text text = new Text("Чи подобається аналізувати речі навколо?");
        ComboBox<String> likeAnalysisDropDown = new ComboBox<>();
        likeAnalysisDropDown.getItems().addAll("Так", "Ні");
        Button button = new Button("Продовжити");
        likeAnalysis.getChildren().add(text);
        likeAnalysis.getChildren().add(likeAnalysisDropDown);
        likeAnalysis.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            builder.likeAnalysis(likeAnalysisDropDown.getValue().contentEquals("Так"));
            root.getChildren().remove(likeAnalysis);
            root.getChildren().add(this.likeCreation(root));
        });
        return likeAnalysis;
    }

    private VBox likeCreation(VBox root) {
        VBox likeCreation = new VBox();
        Text text = new Text("Що більше подобається: створювати щось нове, чи бути майстром у існуючих речах?");
        ComboBox<String> likeCreationDropDown = new ComboBox<>();
        likeCreationDropDown.getItems().addAll("Створювати щось нове", "Бути майстром у існуючих речах");
        Button button = new Button("Продовжити");
        likeCreation.getChildren().add(text);
        likeCreation.getChildren().add(likeCreationDropDown);
        likeCreation.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            builder.likeCreation(likeCreationDropDown.getValue().contentEquals("Створювати щось нове"));
            root.getChildren().remove(likeCreation);
            root.getChildren().add(this.likeWorkRate(root));
        });
        return likeCreation;
    }

    private VBox likeWorkRate(VBox root) {
        VBox likeWorkRate = new VBox();
        Text text = new Text("Наскільки ти працелюбний?");
        ComboBox<Integer> likeWorkRateDropDown = new ComboBox<>();
        likeWorkRateDropDown.getItems().addAll(IntStream.range(1, 11).boxed().collect(Collectors.toList()));
        Button button = new Button("Продовжити");
        likeWorkRate.getChildren().add(text);
        likeWorkRate.getChildren().add(likeWorkRateDropDown);
        likeWorkRate.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            builder.likeWorkRate(likeWorkRateDropDown.getValue());
            root.getChildren().remove(likeWorkRate);
            root.getChildren().add(this.targetForcedRate(root));
        });
        return likeWorkRate;
    }

    private VBox targetForcedRate(VBox root) {
        VBox targetForcedRate = new VBox();
        Text text = new Text("Наскільки ти цілеспрямований?");
        ComboBox<Integer> targetForcedRateDropDown = new ComboBox<>();
        targetForcedRateDropDown.getItems().addAll(IntStream.range(1, 11).boxed().collect(Collectors.toList()));
        Button button = new Button("Продовжити");
        targetForcedRate.getChildren().add(text);
        targetForcedRate.getChildren().add(targetForcedRateDropDown);
        targetForcedRate.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            builder.targetForcedRate(targetForcedRateDropDown.getValue());
            root.getChildren().remove(targetForcedRate);
            root.getChildren().add(this.likeHelpPeople(root));
        });
        return targetForcedRate;
    }

    private VBox likeHelpPeople(VBox root) {
        VBox likeHelpPeople = new VBox();
        Text text = new Text("Чи любиш допомагати людям?");
        ComboBox<String> likeHelpPeopleDropDown = new ComboBox<>();
        likeHelpPeopleDropDown.getItems().addAll("Так", "Ні");
        Button button = new Button("Продовжити");
        likeHelpPeople.getChildren().add(text);
        likeHelpPeople.getChildren().add(likeHelpPeopleDropDown);
        likeHelpPeople.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            builder.likeHelpPeople(likeHelpPeopleDropDown.getValue().contentEquals("Так"));
            root.getChildren().remove(likeHelpPeople);
            root.getChildren().add(this.canPayContract(root));
        });
        return likeHelpPeople;
    }

    private VBox canPayContract(VBox root) {
        VBox canPayContract = new VBox();
        Text text = new Text("Чи можеш оплатити контракт?");
        ComboBox<String> canPayContractDropDown = new ComboBox<>();
        canPayContractDropDown.getItems().addAll("Так", "Ні");
        Button button = new Button("Продовжити");
        canPayContract.getChildren().add(text);
        canPayContract.getChildren().add(canPayContractDropDown);
        canPayContract.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            builder.canPayContract(canPayContractDropDown.getValue().contentEquals("Так"));
            root.getChildren().remove(canPayContract);
            root.getChildren().add(this.cityMatters(root));
        });
        return canPayContract;
    }

    private VBox cityMatters(VBox root) {
        VBox cityMatters = new VBox();
        Text text = new Text("Чи має значення місто де розташований університет?");
        ComboBox<String> cityMattersDropDown = new ComboBox<>();
        cityMattersDropDown.getItems().addAll("Так", "Ні");
        Button button = new Button("Продовжити");
        cityMatters.getChildren().add(text);
        cityMatters.getChildren().add(cityMattersDropDown);
        cityMatters.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            final boolean yes = cityMattersDropDown.getValue().contentEquals("Так");
            builder.cityMatters(yes);
            root.getChildren().remove(cityMatters);
            if(yes) {
                root.getChildren().add(this.city(root));
            } else {
                root.getChildren().add(this.universities(root));
            }
        });
        return cityMatters;
    }

    private VBox city(VBox root) {
        VBox city = new VBox();
        Text text = new Text("Вибери місто:");
        ComboBox<String> cityDropDown = new ComboBox<>();
        cityDropDown.getItems().addAll(City.getAvailableCitiesValues());
        Button button = new Button("Продовжити");
        city.getChildren().add(text);
        city.getChildren().add(cityDropDown);
        city.getChildren().add(button);

        button.setOnAction(actionEvent -> {
            builder.city(City.getFromValue(cityDropDown.getValue()));
            root.getChildren().remove(city);
            root.getChildren().add(this.universities(root));
        });
        return city;
    }

    private VBox universities(VBox root) {
        VBox universities = new VBox();
        DecisionDto decisionDto = builder.build();
        List<University> relevantUniversities = decisionService.findRelevantUniversities(decisionDto);
        List<String> availableUniversities = relevantUniversities.stream()
                .map(University::getName)
                .collect(Collectors.toList());
        ListView<String> universityViewList = new ListView<>();
        universityViewList.getItems().addAll(availableUniversities);

        Text text = new Text("Наступні університети підходять найбільше:");
        universities.getChildren().add(text);
        universities.getChildren().add(universityViewList);
        return universities;
    }
}
