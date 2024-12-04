package JFX.FXML.Reservations;

import domain.Reservation;
import filter.FIlterReservationbyPrice;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ReservationService;

import java.io.IOException;

public class AppliationResClass extends Application {
    public static ReservationService reservationService;
    private TextField idField;
    private TextField DateField;
    private TextField startprice;
    private TextField endprice;
    private TextField priceField;
    private TableView<Reservation> table;
    private ObservableList<Reservation> reservationList;

    public static void setReservationService(ReservationService Service){
        reservationService = Service;
    }
    @Override
    public void start(Stage stage) throws IOException{
        stage.setTitle("Reservation Management");

        Menu fileMenu = new javafx.scene.control.Menu("Options for reservations");
        MenuItem reservationManagement = new MenuItem("CRUD operations");
       MenuItem filterbyPrice = new MenuItem("Filter by price");
       MenuItem getReports = new MenuItem("Get reports");
       MenuItem getBack = new MenuItem("Get Back!");
        fileMenu.getItems().add(reservationManagement);
        fileMenu.getItems().add(filterbyPrice);
        fileMenu.getItems().add(getReports);
        fileMenu.getItems().add(getBack);


       MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        VBox vbox = new VBox(menuBar);
        Scene scene = new Scene(vbox, 960, 600);
        stage.setScene(scene);
        stage.show();

        reservationManagement.setOnAction(e -> {
            ReservationApp reservationApp = new ReservationApp();
            try {
                reservationApp.start(new Stage());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    filterbyPrice.setOnAction(e->{
        Stage filterStage = new Stage();
        filterStage.setTitle("Filter by price");

        startprice= new TextField();
        startprice.setPromptText("Enter start Price");

        endprice = new TextField();
        endprice.setPromptText("Enter end Price");

        Button filterButton = new Button("Filter");
        filterButton.setOnAction(e1->{
        int start = Integer.parseInt(startprice.getText());
        int end = Integer.parseInt(endprice.getText());
        FIlterReservationbyPrice filterReservationbyPrice = new FIlterReservationbyPrice(start, end);
        reservationList.setAll(reservationService.getAllReservations().stream()
                .filter(filterReservationbyPrice::accept)
                .toList());}
    );
    Button view = new Button("View all Reservations");
    view.setOnAction(event-> {
        reservationList.setAll(reservationService.getAllReservations());
    });
                Button backButton = new Button("Back");
                backButton.setOnAction(event -> filterStage.close());
    reservationList = FXCollections.observableArrayList();
    table = new TableView<>();
    table.setItems(reservationList);

    TableColumn<Reservation, String> idColumn = new TableColumn<>("ID");
    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn<Reservation, String> dateColumn = new TableColumn<>("Date");
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

    TableColumn<Reservation, String> priceColumn = new TableColumn<>("Price");
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    table.getColumns().addAll(idColumn, dateColumn, priceColumn);
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Start price"), 0, 0);
        gridPane.add(startprice, 1, 0);
        gridPane.add(new Label("End price"), 0, 1);
        gridPane.add(endprice, 1, 1);
        gridPane.add(filterButton, 0, 2);
        gridPane.add(view, 1, 2);
        gridPane.add(backButton, 1,3);

        VBox vbox1 = new VBox(gridPane, table);
        Scene scene1 = new Scene(vbox1, 960, 600);
        filterStage.setScene(scene1);
        filterStage.show();

}
    );
    getReports.setOnAction(event->{
        Stage reportStage = new Stage();
        reportStage.setTitle("Reports Reservations");

        priceField = new TextField();
        priceField.setPromptText("Price");

        Button reportButton = new Button("Get report");

        reportButton.setOnAction(e->{
          int price = Integer.parseInt(priceField.getText());
          Label average = new Label("Average price: " + reservationService.getAveragePrice());
            Label max = new Label("Newest Reservation:  " + reservationService.getResbyLatestDate());
            Label maxID = new Label("Reservation with the highest ID: " + reservationService.getResbyMaxId());
            VBox vBox = new VBox(average, max, maxID);
            Scene scene2 = new Scene(vBox, 960, 600);
            reportStage.setScene(scene2);
            reportStage.show();
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> reportStage.close());

        VBox vbox1 = new VBox(priceField, reportButton, backButton);
        Scene scene2 = new Scene(vbox1, 960, 600);
        reportStage.setScene(scene2);
        reportStage.show();
    });

}
}