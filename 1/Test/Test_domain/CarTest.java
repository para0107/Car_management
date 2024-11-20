//package Test_domain;
//
//import domain.Car;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CarTest {
//    @Test
//    public void testCarCreationAndGetters() {
//        Car car = new Car("1", "Toyota", 2020, true);
//        assertEquals("1", car.getId());
//        assertEquals("Toyota", car.getModel());
//        assertEquals(2020, car.getYear());
//        assertTrue(car.isAvailable());
//    }
//
//    @Test
//    public void testSetters() {
//        Car car = new Car("1", "Toyota", 2020, true);
//        car.setModel("Honda");
//        car.setYear(2021);
//        car.setAvailable(false);
//        assertEquals("Honda", car.getModel());
//        assertEquals(2021, car.getYear());
//        assertFalse(car.isAvailable());
//    }
//
//    @Test
//    public void testEqualsAndHashCode() {
//        Car car1 = new Car("1", "Toyota", 2020, true);
//        Car car2 = new Car("1", "Toyota", 2020, true);
//        assertEquals(car1, car2);
//        assertEquals(car1.hashCode(), car2.hashCode());
//    }
//
//    @Test
//    public void testToString() {
//        Car car = new Car("1", "Toyota", 2020, true);
//        String expected = "Car{Id=1, Model='Toyota', Year=2020, isAvailable=true}";
//        assertEquals(expected, car.toString());
//    }
//}
