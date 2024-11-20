//package Test_domain;
//
//import domain.Reservation;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import java.util.Date;
//
//public class ReservationTest {
//
//    @Test
//    public void testReservationCreationAndGetters() {
//        Date date = new Date();
//        Reservation reservation = new Reservation("1", date, 100);
//
//        assertEquals("1", reservation.getId());
//        assertEquals(date, reservation.getDate());
//        assertEquals(100, reservation.getPrice());
//    }
//
//    @Test
//    public void testSetters() {
//        Date date = new Date();
//        Reservation reservation = new Reservation("1", date, 100);
//
//        reservation.setId("2");
//        Date newDate = new Date(date.getTime() + 86400000); // 1 day later
//        reservation.setDate(newDate);
//        reservation.setPrice(150);
//
//        assertEquals("2", reservation.getId());
//        assertEquals(newDate, reservation.getDate());
//        assertEquals(150, reservation.getPrice());
//    }
//
//    @Test
//    public void testEqualsAndHashCode() {
//        Date date = new Date();
//        Reservation reservation1 = new Reservation("1", date, 100);
//        Reservation reservation2 = new Reservation("1", date, 100);
//
//        assertEquals(reservation1, reservation2);
//        assertEquals(reservation1.hashCode(), reservation2.hashCode());
//    }
//
//    @Test
//    public void testToString() {
//        Date date = new Date();
//        Reservation reservation = new Reservation("1", date, 100);
//
//        String expected = "Reservation{id=1, date=" + date + ", price=100}";
//        assertEquals(expected, reservation.toString());
//    }
//}
