//import UI.CarUI;
//import UI.MainUI;
//import UI.ReservationUI;
//import domain.Car;
//import domain.Reservation;
//import repository.*;
//import service.CarService;
//import service.ReservationService;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Properties;
//
////import UI.CarUI;
////import UI.MainUI;
////import domain.Reservation;
////import domain.Car;
////import repository.*;
////import service.CarService;
////import service.ReservationService;
////import java.io.Serializable;
////
////import java.util.ArrayList;
////import java.util.Date;
////import java.util.HashMap;
////import java.util.Calendar;
////
////public class Main {
//////    public static void main(String[] args) {
//////        // Step 1: Initialize the car list and add cars
//////        ArrayList<Car> cs = new ArrayList<>();
//////        Car c1 = new Car(1, "Peugeot", 2000, true);
//////        Car c2 = new Car(2, "Mazda", 2010, true);
//////        Car c3 = new Car(3, "BMW", 1994, true);
//////        Car c4 = new Car(4, "Mercedes", 2024, true);
//////        cs.add(c1);
//////        cs.add(c2);
//////        cs.add(c3);
//////        cs.add(c4);
//////
//////        // Step 2: Initialize CarRepo
//////        CarRepo cars = new CarRepo(cs);
//////
//////        // Step 3: Use Calendar to create specific dates
//////        Calendar cal = Calendar.getInstance();
//////
//////        cal.set(2000, Calendar.JULY, 1, 8, 25, 50);
//////        Date reservationDate1 = cal.getTime();
//////
//////        cal.set(2001, Calendar.SEPTEMBER, 15, 18, 50, 10);
//////        Date reservationDate2 = cal.getTime();
//////
//////        cal.set(2004, Calendar.DECEMBER, 31, 23, 59, 59);
//////        Date reservationDate3 = cal.getTime();
//////
//////        // Step 4: Create Reservation objects
//////        Reservation r1 = new Reservation(1, reservationDate1, 1); // Assuming car ID 1
//////        Reservation r2 = new Reservation(3, reservationDate2, 2); // Assuming car ID 2
//////        Reservation r3 = new Reservation(4, reservationDate3, 4); // Assuming car ID 4
//////
//////        // Step 5: Initialize HashMap and ReservationRepo
//////        HashMap<Reservation, Car> h1 = new HashMap<>();
//////        ReservationRepo repo = new ReservationRepo(h1, cars);
//////
//////        // Step 6: Add reservations
//////        repo.addReservation(r1);
//////        repo.addReservation(r2);
//////        repo.addReservation(r3);
//////
//////        // Step 7: Print reservations to verify
//////        System.out.println("Reservations in the system: " + repo);
//////    }
////    public static void main(String[] args) {
////
////        CarRepo carRepo = new CarRepo(new ArrayList<>());
////        MemoryRepository<String,Car> memoryrepo1 = new MemoryRepository<>();
////        MemoryRepository<String,Reservation> memoryrepo2 = new MemoryRepository<>();
////        HashMap<Reservation, Car> reservationsMap = new HashMap<>();
////        Car c1 = new Car("1", "Peugeot", 2000, true);
////        Car c2 = new Car("2", "Mazda", 2010, true);
////        Car c3 = new Car("3", "BMW", 1994, true);
////        Car c4 = new Car("4", "Mercedes", 2024, true);
////        carRepo.addCar(c1);
////        carRepo.addCar(c2);
////        carRepo.addCar(c3);
////        carRepo.addCar(c4);
////    memoryrepo1.add(c1.getId(),c1);
////    memoryrepo1.add(c2.getId(),c2);
////    memoryrepo1.add(c3.getId(),c3);
////    memoryrepo1.add(c4.getId(),c4);
////        ReservationRepo reservationRepo = new ReservationRepo(reservationsMap, carRepo,memoryrepo2);
////        CarService carService = new CarService(carRepo,memoryrepo1);
////        ReservationService reservationService = new ReservationService(reservationRepo, memoryrepo2);
////
////        // Start the Main UI
////        MainUI mainUI = new MainUI(carService, reservationService);
////      mainUI.start();
////}
////}
////
//public class Main{
//    public static void main(String[] args)
//    {
//        //CarRepo cars = null;
//        MemoryRepository<String, Car> cars = null;
//        MemoryRepository<String, Reservation> ress= null;
//        Properties properties = System.getProperties();
//        try {
//            properties.load(new FileReader("src/settings.properties"));
//            String repositoryType = properties.getProperty("Repository");
//            if (repositoryType == null)
//                throw new RuntimeException("Invalid Repository Type.\n");
//
//            if (repositoryType.equals("console")) {
//                cars = new CarRepo();
//                Car car1 = new Car("1", "Peugeot", 2000, true);
//                Car car2 = new Car("2", "Mazda", 2010, true);
//                Car car3 = new Car("3", "BMW", 1994, true);
//                Car car4 = new Car("4", "Mercedes", 2024, true);
//                cars.add(car1.getId(),car1);
//                cars.add(car2.getId(),car2);
//                cars.add(car3.getId(),car3);
//                cars.add(car4.getId(),car4);
//                //  carMemoryRepository = new MemoryRepository<>();
////                carMemoryRepository.add(car1.getId(), car1);
////                carMemoryRepository.add(car2.getId(), car2);
////                carMemoryRepository.add(car3.getId(), car3);
////                carMemoryRepository.add(car4.getId(), car4);
//
//            }
//            if (repositoryType.equals("text")) {
//                String carPath = properties.getProperty("cars");
//                String resPath = properties.getProperty("reservations");
//
//                cars = new CarRepositoryTextFile(carPath);
//                ress = new ReservationRepositoryTextFile(resPath);
//            }
//            if(repositoryType.equals("binary")){
//                String carpath = properties.getProperty("cars");
//                String respath = properties.getProperty("reservations");
//                cars = new CarRepositoryBinaryFile(carpath);
//                ress = new ReservationRepositoryBinaryFile(respath);
//            }
//
//        } catch (IOException e){
//            System.out.println("Error: " + e.getMessage());
//        }
//        CarService carService = new CarService(cars);
//        CarUI ui = new CarUI(carService);
//        ReservationService reservationService = new ReservationService(ress);
//        ReservationUI reservationUI = new ReservationUI(reservationService);
//        MainUI mainUI = new MainUI(carService, reservationService);
//        mainUI.start();
//    }
//}
import UI.CarUI;
import UI.MainUI;
import UI.ReservationUI;
import database.CarDataBase;
import domain.Car;
import domain.Reservation;
import repository.*;
import service.CarService;
import service.ReservationService;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {


//        MemoryRepository<String, Car> cars = null;
//        MemoryRepository<String, Reservation> res = null;
//        Properties properties = System.getProperties();
//
//        try{
//            properties.load(new FileReader("src/settings.properties"));
//            String repositoryType = properties.getProperty("Repository");
//            if(repositoryType == null)
//                throw new RuntimeException("Invalid Repository Type.\n");
//
//            if(repositoryType.equals("console")){
//            cars = new MemoryRepository<>();
//            Car car1 = new Car("1", "Peugeot", 2000, true);
//            Car car2 = new Car("2", "Mazda", 2010, true);
//            Car car3 = new Car("3", "BMW", 1994, true);
//            Car car4 = new Car("4", "Mercedes", 2024, true);
//            cars.add(car1.getId(), car1);
//            cars.add(car2.getId(), car2);
//            cars.add(car3.getId(), car3);
//            cars.add(car4.getId(), car4);
//
//            res = new MemoryRepository<>();
//            Reservation res1 = new Reservation("1", Date.valueOf("2020-10-10"), 100);
//            Reservation res2 = new Reservation("2", Date.valueOf("1999-10-11"), 578);
//            Reservation res3 = new Reservation("3", Date.valueOf("2024-10-12"), 200);
//            Reservation res4 = new Reservation("4", Date.valueOf("2020-10-13"), 300);
//            res.add(res1.getId(), res1);
//            res.add(res2.getId(), res2);
//            res.add(res3.getId(), res3);
//            res.add(res4.getId(), res4);
//            }
//            if(repositoryType.equals("text")){
//                String carPath = properties.getProperty("cars");
//                String resPath = properties.getProperty("reservations");
//                cars = new CarRepositoryTextFile(carPath);
//                res = new ReservationRepositoryTextFile(resPath);
//            }
//            if(repositoryType.equals("binary")){
//                String carPath = properties.getProperty("cars");
//                String resPath = properties.getProperty("reservations");
//                cars = new CarRepositoryBinaryFile(carPath);
//                res = new ReservationRepositoryBinaryFile(resPath);
//            }
//            if(repositoryType.equals("database")){
//                cars = new MemoryRepository<>();
//               CarDataBase carDataBase = new CarDataBase();
//
//                Car car1 = new Car("1", "Peugeot", 2000, true);
//                Car car2 = new Car("2", "Mazda", 2010, true);
//                carDataBase.addCar(car1);
//                carDataBase.addCar(car2);
//
//                // Get a car by ID
//                Car retrievedCar = carDataBase.getById("1");
//                System.out.println("Retrieved Car: " + retrievedCar);
//
//                // Get all cars
//                List<Car> allCars = carDataBase.getAll();
//                System.out.println("All Cars: " + allCars);
//
//                // Delete a car by ID
//                carDataBase.deleteCar("2");
//                System.out.println("Car with ID 2 deleted");
//
//                // Get all cars after deletion
//                allCars = carDataBase.getAll();
//                System.out.println("All Cars after deletion: " + allCars);
//
//                carDataBase.modifyCar("1", 2020, "Mercedes");
//            }
//        }
//        catch(IOException e){
//            System.out.println("Error: " + e.getMessage());
//        }
//
//
//
//
//        // Add cars to the database
//
//
//
//
//        CarService carService = new CarService(cars);
//        ReservationService reservationService = new ReservationService(res);
//        CarUI carUI = new CarUI(carService);
//        ReservationUI reservationUI = new ReservationUI(reservationService);
//        MainUI mainUI = new MainUI(carService, reservationService);
//        mainUI.start();
//    }
try{
    RepositoryFactory.Repositories repositories = RepositoryFactory.createRepositories();
    MemoryRepository<String, Car> carRepository = repositories.carRepository;
    MemoryRepository<String, Reservation> reservationRepository = repositories.reservationRepository;

    CarService carService = new CarService(carRepository);
    ReservationService reservationService = new ReservationService(reservationRepository);

    CarUI carUI = new CarUI(carService);
    ReservationUI reservationUI = new ReservationUI(reservationService);
    MainUI mainUI = new MainUI(carService, reservationService);
    mainUI.start();
}
catch (Exception e){
    System.out.println(e.getMessage());
}
    }
}
