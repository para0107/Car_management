package JFX.FXML.Reservations;

import domain.Reservation;
import filter.FIlterReservationbyPrice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ReservationService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationResController {
    public ReservationService reservationService;

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
    private Stage primary;
    private Scene initial;

    public  void setReservationService(ReservationService service, Stage s, Scene si) {
        reservationService = service;
        primary = s;
        initial = si;

    }


    @FXML
    private void handleFilterByPrice() {
        Stage filterStage = new Stage();
        filterStage.setTitle("Filter reservations by price");

        TextField priceField = new TextField();
        priceField.setPromptText("Enter Start Price");
        TextField price2 = new TextField();
        price2.setPromptText("Enter End Price");

        Button filterButton = new Button("Filter");
        filterButton.setOnAction(event -> {
            int price = Integer.parseInt(priceField.getText());
            int price1 = Integer.parseInt(price2.getText());
            reservationList.setAll(reservationService.getAllReservations().stream()
                    .filter(reservation -> reservation.getPrice() >= price && reservation.getPrice() <= price1)
                    .collect(Collectors.toList()));
        });
        Button viewAll = new Button("View All Reservations");
        viewAll.setOnAction(event -> {
            reservationList.setAll(reservationService.getAllReservations());
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> filterStage.close());
        reservationList = FXCollections.observableArrayList();
        TableView<Reservation> table = new TableView<>();
        table.setItems(reservationList);

        TableColumn<Reservation, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Reservation, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Reservation, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(idColumn, dateColumn, priceColumn);

        GridPane inputgrid = new GridPane();
        inputgrid.add(new Label("Start Price"), 0, 0);
        inputgrid.add(priceField, 1, 0);
        inputgrid.add(new Label("End Price"), 0, 1);
        inputgrid.add(price2, 1, 1);
        inputgrid.add(filterButton, 1, 2);
        inputgrid.add(viewAll, 2, 2);
        inputgrid.add(backButton, 3,2);

        VBox vBox = new VBox(inputgrid, table);
        Scene scene = new Scene(vBox);
        filterStage.setScene(scene);
        filterStage.show();
    }

    @FXML
    private void handleGetReports() {
        Stage reportStage = new Stage();
        reportStage.setTitle("Reports");

        Button reportButton = new Button("Get Reports");
        reportButton.setOnAction(event -> {
            List<Reservation> reservations = reservationService.getAllReservations();
            int total = reservations.stream().mapToInt(Reservation::getPrice).sum();
            int max = reservations.stream().mapToInt(Reservation::getPrice).max().getAsInt();
            int min = reservations.stream().mapToInt(Reservation::getPrice).min().getAsInt();
            double avg = reservations.stream().mapToInt(Reservation::getPrice).average().getAsDouble();
            int count = (int) reservations.stream().count();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reports");
            alert.setHeaderText("Reports");
            alert.setContentText("Total: " + total + "\n" + "Max: " + max + "\n" + "Min: " + min + "\n" + "Average: " + avg + "\n" + "Count: " + count);
            alert.show();
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> reportStage.close());

        VBox vBox = new VBox(reportButton, backButton);
        Scene scene = new Scene(vBox);
        reportStage.setScene(scene);
        reportStage.show();
    }

    @FXML
    private void handleResManager() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/JFX/FXML/Reservations/ResApp.fxml"));
            loader.setControllerFactory(param -> new ReservationAppController(reservationService));
            Parent root = loader.load();
            Scene scene1 = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Reservation Management");
            stage.setScene(scene1);
            stage.show();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleBack()
    {
primary.setScene(initial);
    }

}