package UI;
import service.CarService;
import service.ReservationService;

import java.util.Scanner;
public class MainUI {
    private final CarService carService;
    private final ReservationService reservationService;
    private final Scanner scanner;

    public MainUI(CarService carService, ReservationService reservationService) {
        this.carService = carService;
        this.reservationService = reservationService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Main Management System ---");
            System.out.println("1. Car Management");
            System.out.println("2. Reservation Management");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    new CarUI(carService).start(); // Start Car Management UI
                    break; // Prevent fall-through
                case 2:
                    new ReservationUI(reservationService).start(); // Start Reservation Management UI
                    break; // Prevent fall-through
                case 0:
                    System.out.println("\nExiting...");
                    return;
                default:
                    System.out.println("\nInvalid option. Please try again.");
                    break; // Added break for clarity
            }
        }
    }
}

