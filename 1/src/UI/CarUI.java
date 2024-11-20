package UI;

import domain.Car;
//import service.CarFilterService;
import service.CarService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class CarUI {
    private final CarService carService;
  //  private final CarFilterService carFilterService;
    private final Scanner scanner;
    public CarUI(CarService carService) {
        this.carService = carService;
        this.scanner = new Scanner(System.in);
       // this.carFilterService = carFilterService;
    }
    public void start() {
        while(true){
            System.out.println("\n--- Car Management System ---");
            System.out.println("1. Add a Car");
            System.out.println("2. View All Cars");
            System.out.println("3. View Available Cars");
            System.out.println("4. Update a Car");
            System.out.println("5. Remove a Car");
            System.out.println("6. Filter Cars by Year Range");
            System.out.println("7. Filter Cars by Model");
            System.out.println("8. Get Reposrts");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int  option = scanner.nextInt();
            scanner.nextLine();
            switch(option){
                case 1: addCar();
                break;
                case 2: viewAllCars();
                break;
                case 3:viewAvailableCars();
                break;
                case 4:updateCar();
                break;
                case 5:removeCar();
                break;
                case 6: filterCarsByYearRange();
                break;
                case 7: filterCarsByModel();
                break;
                case 8 : getReports();
                break;
                case 0:{ System.out.println("\n Exiting..."); return; }
                default: System.out.println("\n Invalid option. Please try again.");
                 break;
            }
        }
    }
private void addCar(){
        System.out.println("\nEnter Car Id: ");
       String id = scanner.nextLine();
        System.out.println("Enter Car Model: ");
        String model = scanner.nextLine();
        System.out.println("Enter Car Year: ");
        int year = scanner.nextInt();
        System.out.println("Is car available?");
        boolean avaliable = scanner.nextBoolean();
    scanner.nextLine();

        try{carService.addCar(id, model, year, avaliable);
            System.out.println("Car added successfully.");}
        catch (Exception e)
        {
            System.out.println("Car already exists");
        }


}
    private void viewAllCars() {
        Collection<Car> cars = carService.getAllCars();
        if (cars.isEmpty()) {
            System.out.println("No cars available.");
        } else {
            System.out.println("All Cars:");
            for (Car car : cars) {
                System.out.println(car);
            }
        }
    }
    private void viewAvailableCars() {
        ArrayList<Car> availableCars = carService.getAvailableCars();
        if (availableCars.isEmpty()) {
            System.out.println("No available cars at the moment.");
        } else {
            System.out.println("Available Cars:");
            for (Car car : availableCars) {
                System.out.println(car);
            }
        }
    }
    private void updateCar() {
        System.out.print("Enter Car ID to update: ");
        String id = scanner.nextLine();
        System.out.print("Enter new Car Year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        System.out.print("Enter new Car Model: ");
        String model = scanner.nextLine();
try{
        boolean updated = carService.updateCar(id, year, model);
    }
catch (Exception e)
    {
        System.out.println("Car not found");
    }
    }
    private void removeCar() {
        try{System.out.print("Enter Car ID to remove: ");
        String id = scanner.nextLine();

        carService.removeCarById(id);}
        catch (Exception e)
        {
            System.out.println("Car not found");}
    }

//        System.out.print("Enter Car ID to remove: ");
//      String id = scanner.nextLine();
//      carService.removeCarById(id);
//       // System.out.println("Car removed successfully!");


    private void filterCarsByYearRange()
    {
        System.out.println("Enter start year: ");
        int startYear = scanner.nextInt();
        System.out.println("Enter end year: ");
        int endYear = scanner.nextInt();
        scanner.nextLine();
        Collection<Car> cars = carService.getCarsbyYears(startYear, endYear);
        if (cars.isEmpty()) {
            System.out.println("No cars found in the given year range.");
        } else {
            System.out.println("Cars in the given year range:");
            for (Car car : cars) {
                System.out.println(car);
            }
        }
    }
    private void filterCarsByModel()
    {
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        Collection<Car> cars = carService.getCarsbyModel(model);
        if (cars.isEmpty()) {
            System.out.println("No cars found with the given model.");
        } else {
            System.out.println("Cars with the given model:");
            for (Car car : cars) {
                System.out.println(car);
            }
        }
    }
    private void getReports()
    {   System.out.println("The average year of the cars is: ");
        System.out.println(carService.getAveragePrice());
        //System.out.println("The model of the cars is: ");
        //String model = scanner.nextLine();
        System.out.println("The year of the cars is: ");
        int year = scanner.nextInt();
        System.out.println(carService.getCarsbyYear(year));
    }
}
