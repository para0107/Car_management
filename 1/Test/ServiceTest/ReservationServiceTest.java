//package ServiceTest;
//
//import domain.Reservation;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import service.ReservationService;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//public class ReservationServiceTest {
//
//    private ReservationService reservationService;
//    private Reservation reservation1;
//    private Reservation reservation2;
//    private Reservation reservation3;
//
//    @BeforeEach
//    public void setUp() {
//        reservationService = new ReservationService();
//
//        reservation1 = new Reservation("1", new Date(), 100);
//        reservation2 = new Reservation("2", new Date(), 200);
//        reservation3 = new Reservation("3", new Date(), 300);
//
//        reservationService.addReservation(reservation1);
//        reservationService.addReservation(reservation2);
//    }
//
//    @Test
//    public void testAddReservation() {
//        reservationService.addReservation(reservation3);
//        Optional<Reservation> retrievedReservation = reservationService.memo_repository.findbyId("3");
//        assertNotNull(retrievedReservation);
//        assertEquals(reservation3, retrievedReservation);
//    }
//
//    @Test
//    public void testAddReservationThrowsExceptionIfExists() {
//        assertThrows(RuntimeException.class, () -> {
//            reservationService.addReservation(reservation1);
//        });
//    }
//
//    @Test
//    public void testDeleteReservation() {
//        reservationService.deleteReservation(reservation1.getId());
//        assertNull(reservationService.memo_repository.findbyId("1"));
//    }
//
//    @Test
//    public void testDeleteReservationThrowsExceptionIfNotExists() {
//        assertThrows(RuntimeException.class, () -> {
//            reservationService.deleteReservation("10");
//        });
//    }
//
//    @Test
//    public void testUpdateReservation() {
//        Date newDate = new Date();
//        reservationService.updateReservation("2", newDate, 250);
//
//        Optional<Reservation> updatedReservation = reservationService.memo_repository.findbyId("2");
//        assertNotNull(updatedReservation);
//        assertEquals(newDate, updatedReservation.getDate());
//        assertEquals(250, updatedReservation.getPrice());
//    }
//
//    @Test
//    public void testGetAllReservations() {
//        List<Reservation> allReservations = reservationService.getAllReservations();
//        assertEquals(2, allReservations.size());
//        assertTrue(allReservations.contains(reservation1));
//        assertTrue(allReservations.contains(reservation2));
//    }
//
//    @Test
//    public void testGetReservationByPrice() {
//        Collection<Reservation> filteredReservations = reservationService.getReservationbyPrice(150, 350);
//        assertEquals(1, filteredReservations.size());
//        assertTrue(filteredReservations.contains(reservation2));
//        assertFalse(filteredReservations.contains(reservation3));
//    }
//}
