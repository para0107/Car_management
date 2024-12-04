package JFX.FXML.Reservations;

import domain.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ReservationService;

import java.util.Date;

public class ReservationAppController {
    private  ReservationService reservationService;

    @FXML
    private TextField idField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField priceField;

    @FXML
    private TableView<Reservation> table;

    @FXML
    private TableColumn<Reservation, String> idColumn;

    @FXML
    private TableColumn<Reservation, String> dateColumn;

    @FXML
    private TableColumn<Reservation, String> priceColumn;

    private ObservableList<Reservation> reservationList;

    public  void setReservationService(ReservationService service) {
        reservationService = service;
    }
    public ReservationAppController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @FXML
    public void initialize() {
        reservationList = FXCollections.observableArrayList(reservationService.getAllReservations());
        table.setItems(reservationList);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    private void handleAdd() {
        try {
            String id = idField.getText();
            String[] dateParts = dateField.getText().split("-");
            Date date = new Date(Integer.parseInt(dateParts[0]) ,Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
            int price = Integer.parseInt(priceField.getText());

            reservationService.addReservation(new Reservation(id, date, price));
            reservationList.setAll(reservationService.getAllReservations());
        } catch (Exception e) {
            showAlert("Error", "Invalid input. Please try again.");
        }
    }

    @FXML
    private void handleDelete() {
        String id = idField.getText();
        reservationService.deleteReservation(id);
        reservationList.setAll(reservationService.getAllReservations());
    }

    @FXML
    private void handleUpdate() {
        try {
            String id = idField.getText();
            String[] dateParts = dateField.getText().split("-");
            Date date = new Date(Integer.parseInt(dateParts[0])-1900 ,Integer.parseInt(dateParts[1]) -1, Integer.parseInt(dateParts[2]));
            int price = Integer.parseInt(priceField.getText());

            reservationService.updateReservation(id, date, price);
            reservationList.setAll(reservationService.getAllReservations());
        } catch (Exception e) {
            showAlert("Error", "Invalid input. Please try again.");
        }
    }

    @FXML
    private void handleView() {
        reservationList.setAll(reservationService.getAllReservations());
    }

    @FXML
    private void handleClear() {
        idField.clear();
        dateField.clear();
        priceField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void handleBack() {
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }
}
