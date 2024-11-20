//package ServiceTest;
//
//import domain.Car;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import service.CarService;
//
//import static org.junit.jupiter.api.Assertions.*;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//
//public class CarServiceTest {
//
//    private CarService carService;
//
//    @BeforeEach
//    public void setUp() {
//        carService = new CarService();
//        carService.addCar("1", "Toyota", 2020, true);
//        carService.addCar("2", "Honda", 2018, false);
//        carService.addCar("3", "Ford", 2022, true);
//    }
//
//    @Test
//    public void testAddCar() {
//        carService.addCar("4", "BMW", 2021, true);
//        //Car car = carService.getCarById("4");
//        List<Car> allCars = new ArrayList<>(carService.getAllCars());
//        assertEquals(1, allCars.size() );
//        assertEquals("BMW", allCars.get(0).getModel());
////        assertEquals(2021, car.getYear());
////        assertTrue(car.isAvailable());
//    }
//
//    @Test
//    public void testAddCarThrowsExceptionForDuplicateId() {
//        assertThrows(RuntimeException.class, () -> {
//            carService.addCar("1", "Tesla", 2023, true);
//        });
//    }
//
//    @Test
//    public void testRemoveCarById() {
//        carService.removeCarById("1");
//        assertNull(carService.getCarById("1"));
//    }
//
//    @Test
//    public void testRemoveCarByIdThrowsExceptionIfNotFound() {
//        assertThrows(RuntimeException.class, () -> {
//            carService.removeCarById("10");
//        });
//    }
//
//    @Test
//    public void testGetCarById() {
//        carService.addCar("5", "Mazda", 2023, true);
//        Car cars = carService.getAvailableCars().get(0);
//        assertEquals("Mazda", cars.getModel());
//        assertEquals(2023, cars.getYear());
//        assertFalse(cars.isAvailable());
//    }
//
//    @Test
//    public void testGetAllCars() {
//        Collection<Car> allCars = carService.getAllCars();
//        assertEquals(3, allCars.size());
//    }
//
//    @Test
//    public void testUpdateCar() {
//        boolean updated = carService.updateCar("2", 2019, "Hyundai");
//        assertTrue(updated);
//        Car car = carService.getAvailableCars().get(0);
//        assertEquals("Hyundai", car.getModel());
//        assertEquals(2019, car.getYear());
//    }
//
//    @Test
//    public void testUpdateCarThrowsExceptionIfNotFound() {
//        assertThrows(RuntimeException.class, () -> {
//            carService.updateCar("10", 2020, "Mazda");
//        });
//    }
//
//    @Test
//    public void testGetAvailableCars() {
//        ArrayList<Car> availableCars = carService.getAvailableCars();
//        assertEquals(2, availableCars.size());
//        assertTrue(availableCars.stream().allMatch(Car::isAvailable));
//    }
//
//    @Test
//    public void testGetCarsbyModel() {
//        Collection<Car> toyotaCars = carService.getCarsbyModel("Toyota");
//        assertEquals(1, toyotaCars.size());
//        assertTrue(toyotaCars.iterator().next().getModel().equals("Toyota"));
//    }
//
//    @Test
//    public void testGetCarsbyYears() {
//        Collection<Car> carsByYearRange = carService.getCarsbyYears(2019, 2023);
//        assertEquals(2, carsByYearRange.size());
//        assertTrue(carsByYearRange.stream().allMatch(car -> car.getYear() >= 2019 && car.getYear() <= 2023));
//    }
//}
