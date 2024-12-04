package JFX.FXML.Cars;

import domain.Car;
import filter.FilterCarbyYears;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.RepositoryFactory;
import service.CarService;

import java.io.IOException;

public class ApplicationCarClass extends Application {
    RepositoryFactory repositoryFactory = new RepositoryFactory();

    public static CarService carService ;
    private TextField yearField;
    private TextField year2;
    private TextField modelField;
    private TableView<Car> table;
    private ObservableList<Car> carList;
    private  Stage primaryStage;

    public static void setService(CarService Service){
        carService = Service;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        stage.setTitle("Car rental management");

        Menu fileMenu = new Menu("Options cars");
        MenuItem carManagement = new MenuItem("CRUD OPERATIONS");
        MenuItem filterbyyears = new MenuItem("Filter cars by year");
        MenuItem filtercarsbymodel = new MenuItem("Filter cars by model");
        MenuItem getReports = new MenuItem("Get Reports");
        MenuItem back = new MenuItem("Get Back!!");

        back.setOnAction(e->primaryStage.close());
        fileMenu.getItems().add(carManagement);
        fileMenu.getItems().add(filterbyyears);
        fileMenu.getItems().add(filtercarsbymodel);
        fileMenu.getItems().add(getReports);
        fileMenu.getItems().add(back);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        VBox vbox = new VBox(menuBar);
        Scene scene = new Scene(vbox, 960, 600);
        stage.setScene(scene);
        stage.setWidth(960);
        stage.setHeight(600);
        stage.setX(100);
        stage.setY(200);
        stage.show();

        carManagement.setOnAction(e -> {
            CarManagementApp carManagementApp = new CarManagementApp();
            try {
                carManagementApp.start(new Stage());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        filterbyyears.setOnAction(e -> {
            Stage filterStage = new Stage();
            filterStage.setTitle("Filter cars by year");

            yearField = new TextField();
            yearField.setPromptText("Enter Start Year");
            year2 = new TextField();
            year2.setPromptText("Enter End Year");

            Button filterButton = new Button("Filter");
            filterButton.setOnAction(event -> {
                int year = Integer.parseInt(yearField.getText());
                int year1 = Integer.parseInt(year2.getText());
                FilterCarbyYears filterCarbyYears = new FilterCarbyYears(year, year1);
                carList.setAll(carService.getAllCars().stream()
                        .filter(filterCarbyYears::accept)
                        .toList());
            });

            Button viewAllButton = new Button("View All Cars");
            viewAllButton.setOnAction(event -> {
                carList.setAll(carService.getAllCars());
            });

            Button backButton = new Button("Back");
            backButton.setOnAction(event -> filterStage.close());

            carList = FXCollections.observableArrayList();
            table = new TableView<>();
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
            inputGrid.add(backButton, 3, 2);

            VBox vbox1 = new VBox(inputGrid, table);
            Scene scene1 = new Scene(vbox1, 960, 600);
            filterStage.setScene(scene1);
            filterStage.show();
        });

        filtercarsbymodel.setOnAction(e -> {
            Stage filterStage = new Stage();
            filterStage.setTitle("Filter cars by model");

            modelField = new TextField();
            modelField.setPromptText("Enter Model");

            Button filterButton = new Button("Filter");
            filterButton.setOnAction(event -> {
                String model = modelField.getText();
                carList.setAll(carService.getAllCars().stream()
                        .filter(car -> car.getModel().equals(model))
                        .toList());
            });

            Button viewAllButton = new Button("View All Cars");
            viewAllButton.setOnAction(event -> {
                carList.setAll(carService.getAllCars());
            });

            Button backButton = new Button("Back");
            backButton.setOnAction(event -> filterStage.close());

            carList = FXCollections.observableArrayList();
            table = new TableView<>();
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
            inputGrid.add(backButton, 3, 1);

            VBox vbox1 = new VBox(inputGrid, table);
            Scene scene1 = new Scene(vbox1, 960, 600);
            filterStage.setScene(scene1);
            filterStage.show();
        });

        getReports.setOnAction(e -> {
            Stage reportStage = new Stage();
            reportStage.setTitle("Car Reports");
            yearField = new TextField();
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

            VBox vbox1 = new VBox(yearField, generateReportButton, backButton);
            Scene scene1 = new Scene(vbox1, 400, 200);
            reportStage.setScene(scene1);
            reportStage.show();
        });
    }
}