package UI;
import domain.Car;
import domain.Reservation;
import repository.MemoryRepository;
import service.ReservationService;
import java.sql.Date;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class ReservationUI {
    private final ReservationService reservationService;
    private final Scanner scanner;
 //   private final MemoryRepository<String, Reservation> memo_repository;
    public ReservationUI(ReservationService reservationService)
    {
        this.reservationService = reservationService;
        this.scanner = new Scanner(System.in);
        //this.memo_repository = memo_repository;
}
private void addReservation(){
        System.out.print("\n Enter Reservation ID: ");
    String id = scanner.nextLine();
    //scanner.nextLine(); // Consume newline
    System.out.println("Enter Reservation Date (YYYY-MM-DD): ");
    String dateInput = scanner.nextLine();
    System.out.println("Enter Reservation Price: ");
    int price = scanner.nextInt();

    // Assuming the date is entered in a valid format (for simplicity)
  try{  Date reservationDate = Date.valueOf(dateInput);
    Reservation reservation = new Reservation(id, reservationDate, price);

    reservationService.addReservation(reservation);
 //   memo_repository.add(id, reservation);
    System.out.println("Reservation successfully added");}
    catch (RuntimeException e)
    {
        System.out.println(e.getMessage());
}}
private void viewReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        System.out.println(reservations);

if(reservations.isEmpty())
    System.out.println("No reservations found");


}

    private void updateReservation() {
        System.out.print("Enter Reservation ID to update: ");
        String id = scanner.nextLine();
       // scanner.nextLine(); // Consume newline
        System.out.print("Enter new Reservation Date (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        System.out.print("Enter new Reservation Price: ");
        int price = scanner.nextInt();

        // Assuming the date is entered in a valid format (for simplicity)
        Date reservationDate = Date.valueOf(dateInput);
        reservationService.updateReservation(id, reservationDate, price);
       // memo_repository.modify(id, new Reservation(id, reservationDate, price));
        System.out.println("Reservation updated successfully!");
    }
    private void deleteReservation() {
        System.out.print("Enter Reservation ID to delete: ");
        String id = scanner.nextLine();
      try{  reservationService.deleteReservation(id);System.out.println("Reservation deleted successfully!");}
        catch (RuntimeException e)
        {
            System.out.println(e.getMessage());
        }
      //  memo_repository.delete(id);

    }
    void getReservationbyPrice()
    {
        System.out.println("Enter the frist price to filter by: ");
        int price = scanner.nextInt();
        System.out.println("Enter the second price to filter by: ");
        int price2 = scanner.nextInt();
        System.out.println(reservationService.getReservationbyPrice(price, price2));

    }
    public void start() {
        while (true) {
            System.out.println("\n--- Reservation Management System ---");
            System.out.println("1. Add a Reservation");
            System.out.println("2. View All Reservations");
            System.out.println("3. Update a Reservation");
            System.out.println("4. Delete a Reservation");
            System.out.println("5. Filter Reservations by Price");
            System.out.println("6. Get reposrts");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addReservation();
                    break; // Prevent fall-through
                case 2:
                    viewReservations();
                    break; // Prevent fall-through
                case 3:
                    updateReservation();
                    break; // Prevent fall-through
                case 4:
                    deleteReservation();
                    break; // Prevent fall-through
                case 5:
                    getReservationbyPrice();
                    break; // Prevent fall-through
                case 6:
                    getReports();
                    break;
                case 0:
                    System.out.println("\nExiting...");
                    return;
                default:
                    System.out.println("\nInvalid option. Please try again.");
                    break; // Added break for clarity
            }
        }
    }
    public void getReports()
    {
        System.out.println("The average price of all reservations is: " + reservationService.getAveragePrice());
        System.out.println("The Latest Date of all reservations is: " + reservationService.getResbyLatestDate());
        System.out.println("The Max Id price of all reservations is: " + reservationService.getResbyMaxId());
    }
}
