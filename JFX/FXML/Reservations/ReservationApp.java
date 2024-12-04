package JFX.FXML.Reservations;

import JFX.FXML.Cars.ApplicationCarClass;
import domain.Reservation;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ReservationService;

import java.util.Date;

public class ReservationApp extends Application {
    public static ReservationService reservationService;
    public TextField idField;
    public TextField Date;
    public TextField price;
    private TableView<Reservation> table;
    private ObservableList<Reservation> reservationList;

    public static void setReservationService(ReservationService Service){
        reservationService = Service;
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Reservation Management");
        reservationList = FXCollections.observableArrayList(reservationService.getAllReservations());
        table = new TableView<>();
        table.setItems(reservationList);
        TableColumn<Reservation, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Reservation, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Reservation, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(idColumn, dateColumn, priceColumn);
        TextField idField = new TextField();
        idField.setPromptText("ID");

        TextField Date = new TextField();
        Date.setPromptText("Date");

       TextField price = new TextField();
        price.setPromptText("Price");

        Button addButton = new Button("Add");

        addButton.setOnAction(e -> {
            String id = idField.getText();
            String[] date = Date.getText().split("\\-");
            String datetime = date[0] + "." + date[1] + "." + date[2];
            Date date1 = new Date(datetime);
            Integer price1 = Integer.parseInt(this.price.getText());
            reservationService.addReservation(new Reservation(id, date1, price1));
            reservationList.setAll(reservationService.getAllReservations());
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            String id = idField.getText();
            reservationService.deleteReservation(id);
            reservationList.setAll(reservationService.getAllReservations());
        });

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            String id = idField.getText();
            String[] date = Date.getText().split("-");
            String datetime = date[0] + "." + date[1] + "." + date[2];
            Date date1 = new Date(datetime);
            Integer price1 = Integer.parseInt(this.price.getText());
            reservationService.updateReservation(id, date1, price1);
            reservationList.setAll(reservationService.getAllReservations());
        });
        Button viewButton = new Button("View");
        viewButton.setOnAction(e -> {
            reservationList.setAll(reservationService.getAllReservations());
        });
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> {
            idField.clear();
            Date.clear();
            price.clear();
        });

        GridPane inputGrid = new GridPane();
        inputGrid.add(new Label("ID"), 0, 0);
        inputGrid.add(idField, 1, 0);
        inputGrid.add(new Label("Date"), 0, 1);
        inputGrid.add(Date, 1, 1);
        inputGrid.add(new Label("Price"), 0, 2);
        inputGrid.add(price, 1, 2);

        inputGrid.add(addButton, 1, 3);
        inputGrid.add(deleteButton, 2, 3);
        inputGrid.add(updateButton, 3, 3);
        inputGrid.add(viewButton, 4, 3);
        inputGrid.add(clearButton, 5, 3);



        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, inputGrid);

        Scene scene = new Scene(vBox, 800, 600);
    primaryStage.setWidth(1000);
    primaryStage.setHeight(800);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
