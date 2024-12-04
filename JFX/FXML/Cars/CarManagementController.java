package JFX.FXML.Cars;

import domain.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.CarService;
import service.ReservationService;

public class CarManagementController {

    @FXML
    private TextField idField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField isAvailableField;
    @FXML
    private TableView<Car> table;
    @FXML
    private TableColumn<Car, String> idColumn;
    @FXML
    private TableColumn<Car, String> modelColumn;
    @FXML
    private TableColumn<Car, Integer> yearColumn;
    @FXML
    private TableColumn<Car, Boolean> isAvailableColumn;

    private CarService carService;
    private ReservationService reservationService;
    private ObservableList<Car> carList;

    public CarManagementController(CarService carService, ReservationService reservationService) {
        this.carService = carService;
        this.reservationService = reservationService;
    }

    public void setServices(CarService carService, ReservationService reservationService) {
        this.carService = carService;
        this.reservationService = reservationService;
        initdata();
    }

    @FXML
    public void initialize() {
        carList = FXCollections.observableArrayList(carService.getAllCars());
        table.setItems(carList);
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        isAvailableColumn.setCellValueFactory(cellData -> cellData.getValue().isAvailableProperty().asObject());
    }

    private void initdata() {
        carList = FXCollections.observableArrayList(carService.getAllCars());
    }

    @FXML
    private void handleAdd() {
        String id = idField.getText();
        String model = modelField.getText();
        int year = Integer.parseInt(yearField.getText());
        boolean isAvailable = Boolean.parseBoolean(isAvailableField.getText());
        carService.addCar(id, model, year, isAvailable);
        carList.setAll(carService.getAllCars());
    }

    @FXML
    private void handleDelete() {
        String id = idField.getText();
        carService.removeCarById(id);
        reservationService.deleteReservation(id);
        carList.setAll(carService.getAllCars());
    }

    @FXML
    private void handleUpdate() {
        String id = idField.getText();
        String model = modelField.getText();
        int year = Integer.parseInt(yearField.getText());
        boolean isAvailable = Boolean.parseBoolean(isAvailableField.getText());
        carService.updateCar(id, year, model);
        carList.setAll(carService.getAllCars());
    }

    @FXML
    private void handleViewAll() {
        carList.setAll(carService.getAllCars());
    }

    @FXML
    private void handleClear() {
        idField.clear();
        modelField.clear();
        yearField.clear();
        isAvailableField.clear();
    }

    @FXML
    private void handleBack() {
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }
}