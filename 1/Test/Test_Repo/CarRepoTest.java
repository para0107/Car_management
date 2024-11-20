//package Test_Repo;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import java.util.List;
//import domain.Car;
//import repository.MemoryRepository;
//
//public class CarRepoTest {
//    private MemoryRepository<String, Car> carRepo= new MemoryRepository<>();
//    private Car car = car = new Car("1", "Audi",  2010,  false);
//
//    public void setUp() {
//        carRepo = new MemoryRepository<>();
//        car = new Car("1", "Audi",  2010,  false);
//    }
//    @Test
//    public void testAdd() {
//        carRepo.add("1", car);
//        List<Car> cars = carRepo.getAll();
//        assertEquals(1, cars.size());
//        assertEquals(car, cars.getFirst());
//    }
//    @Test
//    public void testFindById() {
//        carRepo.add("1", car);
//        Car car1 = carRepo.findbyId("1");
//        assertEquals(car, car1);
//    }
//    @Test
//    public void testModify()
//    {
//        carRepo.add(car.getId(), car);
//        Car car1 = new Car("1", "Audi", 2010, true);
//        carRepo.modify("1", car1);
//        Car car2 = carRepo.findbyId("1");
//        assertEquals(car1, car2);
//    }
//    @Test
//    public void testDelete()
//    {
//        carRepo.add(car.getId(), car);
//        carRepo.delete("1");
//        List<Car> cars = carRepo.getAll();
//        assertEquals(0, cars.size());
//    }
//    @Test
//    public void testGetAll()
//    {
//        carRepo.add(car.getId(), car);
//        List<Car> cars = carRepo.getAll();
//        assertEquals(1, cars.size());
//        assertEquals(car, cars.getFirst());
//    }
//
//}
