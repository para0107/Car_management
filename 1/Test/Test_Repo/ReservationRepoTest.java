//package Test_Repo;
//
//import domain.Reservation;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import repository.MemoryRepository;
//
//import static org.junit.jupiter.api.Assertions.*;
//import java.util.Date;
//import java.util.List;
//
//public class ReservationRepoTest {
//
//    private MemoryRepository<String, Reservation> repository;
//    private Reservation reservation;
//
//    @BeforeEach
//    public void setUp() {
//        repository = new MemoryRepository<>();
//        reservation = new Reservation("1", new Date(), 100);
//    }
//
//    @Test
//    public void testAddAndFindById() {
//        repository.add(reservation.getId(), reservation);
//        assertEquals(reservation, repository.findbyId(reservation.getId()));
//    }
//
//    @Test
//    public void testModify() {
//        repository.add(reservation.getId(), reservation);
//        Reservation modifiedReservation = new Reservation("1", new Date(), 200);
//        repository.modify(reservation.getId(), modifiedReservation);
//        assertEquals(modifiedReservation, repository.findbyId(reservation.getId()));
//    }
//
//    @Test
//    public void testDelete() {
//        repository.add(reservation.getId(), reservation);
//        repository.delete(reservation.getId());
//        assertNull(repository.findbyId(reservation.getId()));
//    }
//
//    @Test
//    public void testGetAll() {
//        repository.add(reservation.getId(), reservation);
//        List<Reservation> allReservations = repository.getAll();
//        assertEquals(1, allReservations.size());
//        assertTrue(allReservations.contains(reservation));
//    }
//
//    @Test
//    public void testModifyThrowsExceptionWhenNotFound() {
//        assertThrows(RuntimeException.class, () -> {
//            Reservation newReservation = new Reservation("2", new Date(), 150);
//            repository.modify("2", newReservation);
//        });
//    }
//}
