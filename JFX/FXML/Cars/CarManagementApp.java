package JFX.FXML.Cars;

import domain.Car;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.CarService;
import service.ReservationService;

public class CarManagementApp extends Application {

    private TextField idField;
    private TextField modelField;
    private TextField yearField;
    private TextField isAvaliableField;
    private static CarService carService ;
    private static ReservationService reservationService;
    private TableView<Car> table;
    private ObservableList<Car> carList;

public CarManagementApp() {

}
    public static void setCarService(CarService Service, ReservationService res) {
        carService = Service;
        reservationService = res;
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Car Management");


       carList = FXCollections.observableArrayList(carService.getAllCars());
        table = new TableView<>();
        table.setItems(carList);
        TableColumn<Car, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<Car,String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());

        TableColumn<Car,Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());

        TableColumn<Car,Boolean> isAvailableColumn = new TableColumn<>("Available");
        isAvailableColumn.setCellValueFactory(cellData -> cellData.getValue().isAvailableProperty().asObject());
        table.getColumns().addAll(idColumn, modelColumn, yearColumn, isAvailableColumn);
        TextField idField = new TextField();
        idField.setPromptText("ID");

        TextField modelField = new TextField();
        modelField.setPromptText("Model");

        TextField yearField = new TextField();
        yearField.setPromptText("Year");

        TextField isAvailableField = new TextField();
        isAvailableField.setPromptText("Available");

        Button addButton = new Button("Add");

        addButton.setOnAction(e->{
            String id = idField.getText();
            String model = modelField.getText();
            int year = Integer.parseInt(yearField.getText());
            boolean isAvailable = Boolean.parseBoolean(isAvailableField.getText());
            carService.addCar(id, model, year, isAvailable);
            carList.setAll(carService.getAllCars());
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e->{
            String id = idField.getText();
            carService.removeCarById(id);
            reservationService.deleteReservation(id);
            carList.setAll(carService.getAllCars());
        });

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e->{
            String id = idField.getText();
            String model = modelField.getText();
            int year = Integer.parseInt(yearField.getText());
            boolean isAvailable = Boolean.parseBoolean(isAvailableField.getText());
            carService.updateCar(id, year, model);
            carList.setAll(carService.getAllCars());
        });

        Button viewAllButton = new Button("View All");
        viewAllButton.setOnAction(e->{
            carList.setAll(carService.getAllCars());
        });

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e->{
            idField.clear();
            modelField.clear();
            yearField.clear();
            isAvailableField.clear();
        });

        GridPane inputGrid = new GridPane();
        inputGrid.add(new Label("ID"), 0, 0);
        inputGrid.add(idField, 1, 0);
        inputGrid.add(new Label("Model"), 0, 1);
        inputGrid.add(modelField, 1, 1);
        inputGrid.add(new Label("Year"), 0, 2);
        inputGrid.add(yearField, 1, 2);
        inputGrid.add(new Label("Available"), 0, 3);
        inputGrid.add(isAvailableField, 1, 3);

        inputGrid.add(addButton, 0, 4);
        inputGrid.add(deleteButton, 1, 4);
        inputGrid.add(updateButton, 2, 4);
        inputGrid.add(viewAllButton, 3, 4);
        inputGrid.add(clearButton, 4, 4);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table, inputGrid);

        Scene scene = new Scene(vbox, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();





}

}