package JFX;

import JFX.FXML.Cars.ApplicationControllerCar;
import JFX.FXML.Reservations.AppliationResClass;
import JFX.FXML.Cars.ApplicationCarClass;
import JFX.FXML.Reservations.ApplicationResController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.CarService;
import service.ReservationService;

public class MyGUI extends Application {
    public static CarService carService;
    public static void setService(CarService Service){
        carService = Service;
    }
    public static ReservationService reservationService;
    private Scene initial;
    public static void setReservationService(ReservationService Service){
        reservationService = Service;
    }
    public static void main(String[] args){

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Menu options");
        Menu filemenu = new Menu("Options");
        MenuItem carManagement = new MenuItem("Car Management");
        MenuItem reservationManagement = new MenuItem("Reservation Management");

        filemenu.getItems().add(carManagement);
        filemenu.getItems().add(reservationManagement);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(filemenu);

        VBox vbox = new VBox(menuBar);
        Scene scene = new Scene(vbox, 960, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        initial = scene;


//        carManagement.setOnAction(e->{ApplicationCarClass.setService(carService);
//                Application.launch(ApplicationCarClass.class);
//            });
        carManagement.setOnAction(e->{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/JFX/FXML/Cars/ApplicationCarClass.fxml"));
                Parent root = loader.load();
                ApplicationControllerCar controllerCar = loader.getController();
                controllerCar.setCarService(carService, reservationService, primaryStage, initial);
                Scene scene1 = new Scene(root);
                primaryStage.setScene(scene1);
               //  controllerCar.setCarService(carService, reservationService, primaryStage);

            }
            catch (Exception exception){
                exception.printStackTrace();
            }

        });
        reservationManagement.setOnAction(e-> {
            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/JFX/FXML/Reservations/ApplicationRes.fxml"));
//                Parent root = loader.load();
//                ApplicationResController controller = loader.getController();
//                controller.setReservationService(reservationService);
//                Scene scene1 = new Scene(root);
//                primaryStage.setScene(scene1);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/JFX/FXML/Reservations/ApplicationRes.fxml"));
                Parent root = loader.load();
                ApplicationResController controller = loader.getController();
                controller.setReservationService(reservationService,primaryStage,initial);
                Scene scene1 = new Scene(root);
                primaryStage.setScene(scene1);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

}
}

