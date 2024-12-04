package JFX.FXML.Cars;


import domain.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.CarService;
import service.ReservationService;

import java.util.stream.Collectors;

public class ApplicationControllerCar {

    private CarService carService;
    private ObservableList<Car> carList;
    private ReservationService reservationService;
    private Stage primaryStage;
    private Scene initialScene;

    @FXML
    private VBox contentBox;

    public void setCarService(CarService carService, ReservationService reservationService, Stage s , Scene i) {
        this.carService = carService;
        this.reservationService = reservationService;
        this.primaryStage = s;
        this.initialScene = i;

    }



    @FXML
    private void handleFilterByYears() {
        Stage filterStage = new Stage();
        filterStage.setTitle("Filter cars by year");

        TextField yearField = new TextField();
        yearField.setPromptText("Enter Start Year");
        TextField year2 = new TextField();
        year2.setPromptText("Enter End Year");

        Button filterButton = new Button("Filter");
        filterButton.setOnAction(event -> {
            int year = Integer.parseInt(yearField.getText());
            int year1 = Integer.parseInt(year2.getText());
            carList.setAll(carService.getAllCars().stream()
                    .filter(car -> car.getYear() >= year && car.getYear() <= year1)
                    .collect(Collectors.toList()));
        });

        Button viewAllButton = new Button("View All Cars");
        viewAllButton.setOnAction(event -> {
            carList.setAll(carService.getAllCars());
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> filterStage.close());

        carList = FXCollections.observableArrayList();
        TableView<Car> table = new TableView<>();
        table.setItems(carList);

        TableColumn<Car, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<Car, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());

        TableColumn<Car, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());

        TableColumn<Car, Boolean> isAvailableColumn = new TableColumn<>("Available");
        isAvailableColumn.setCellValueFactory(cellData -> cellData.getValue().isAvailableProperty().asObject());

        table.getColumns().addAll(idColumn, modelColumn, yearColumn, isAvailableColumn);

        GridPane inputGrid = new GridPane();
        inputGrid.add(new Label("Start Year"), 0, 0);
        inputGrid.add(yearField, 1, 0);
        inputGrid.add(new Label("End Year"), 0, 1);
        inputGrid.add(year2, 1, 1);
        inputGrid.add(filterButton, 1, 2);
        inputGrid.add(viewAllButton, 2, 2);
        inputGrid.add(backButton,3,2);

        VBox vbox1 = new VBox(inputGrid, table);
        Scene scene1 = new Scene(vbox1, 960, 600);
        filterStage.setScene(scene1);
        filterStage.show();
    }

    @FXML
    private void handleFilterByModel() {
        Stage filterStage = new Stage();
        filterStage.setTitle("Filter cars by model");

        TextField modelField = new TextField();
        modelField.setPromptText("Enter Model");

        Button filterButton = new Button("Filter");
        filterButton.setOnAction(event -> {
            String model = modelField.getText();
            carList.setAll(carService.getAllCars().stream()
                    .filter(car -> car.getModel().equals(model))
                    .collect(Collectors.toList()));
        });

        Button viewAllButton = new Button("View All Cars");
        viewAllButton.setOnAction(event -> {
            carList.setAll(carService.getAllCars());
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> filterStage.close());

        carList = FXCollections.observableArrayList();
        TableView<Car> table = new TableView<>();
        table.setItems(carList);

        TableColumn<Car, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<Car, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());

        TableColumn<Car, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());

        TableColumn<Car, Boolean> isAvailableColumn = new TableColumn<>("Available");
        isAvailableColumn.setCellValueFactory(cellData -> cellData.getValue().isAvailableProperty().asObject());

        table.getColumns().addAll(idColumn, modelColumn, yearColumn, isAvailableColumn);

        GridPane inputGrid = new GridPane();
        inputGrid.add(new Label("Model"), 0, 0);
        inputGrid.add(modelField, 1, 0);
        inputGrid.add(filterButton, 1, 1);
        inputGrid.add(viewAllButton, 2, 1);
        inputGrid.add(backButton,3,1);

        VBox vbox1 = new VBox(inputGrid, table);
        Scene scene1 = new Scene(vbox1, 960, 600);
        filterStage.setScene(scene1);
        filterStage.show();
    }

    @FXML
    private void handleGetReports() {
        Stage reportStage = new Stage();
        reportStage.setTitle("Car Reports");

        TextField yearField = new TextField();
        yearField.setPromptText("Enter Year");

        Button generateReportButton = new Button("Generate Report");
        generateReportButton.setOnAction(event -> {
            int year = Integer.parseInt(yearField.getText());
            Label averageYearLabel = new Label("Average Year: " + carService.getAveragePrice());
            Label carsByYearLabel = new Label("Cars by Year: " + carService.getCarsbyYear(year));
            VBox vbox1 = new VBox(averageYearLabel, carsByYearLabel);
            Scene scene1 = new Scene(vbox1, 960, 600);
            reportStage.setScene(scene1);
            reportStage.show();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> reportStage.close());

        VBox vbox1 = new VBox(yearField, generateReportButton,backButton);
        Scene scene1 = new Scene(vbox1, 400, 200);
        reportStage.setScene(scene1);
        reportStage.show();
    }@FXML
    private void handleCarManagement() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/JFX/FXML/Cars/CarManagementApp.fxml"));
            loader.setControllerFactory((param -> new CarManagementController(carService, reservationService)));
            Parent root = loader.load();

//            CarManagementController controller = loader.getController();
//            controller.setServices(carService , reservationService);

            Stage stage = new Stage();
            stage.setTitle("Car Management");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleBack()
    {
        primaryStage.setScene(initialScene);
    }
}
