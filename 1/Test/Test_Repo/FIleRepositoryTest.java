//package Test_Repo;
//
//import domain.Car;
//import org.junit.jupiter.api.Test;
//import repository.CarRepositoryBinaryFile;
//
//public class FIleRepositoryTest {
//private final CarRepositoryBinaryFile carRepo = new CarRepositoryBinaryFile("carsTest.binary");
//public void setUp()
//        {
//    CarRepositoryBinaryFile carRepo = new CarRepositoryBinaryFile("carsTest.binary");
//    }
//    public void testReadFromFile() {
//        setUp();
//       // CarRepositoryBinaryFile carRepo = new CarRepositoryBinaryFile("carsTest.binary");
//
//        assert carRepo.getAll().isEmpty();
//    }
//@Test
//    public void testWriteToFile() {
//        setUp();
//        CarRepositoryBinaryFile carRepo = new CarRepositoryBinaryFile("carsTest.binary");
//        Car car = new Car("1", "Audi", 2010, false);
//        carRepo.add(car.getId(), car);
//        carRepo.writeToFile();
//        CarRepositoryBinaryFile carRepo2 = new CarRepositoryBinaryFile("carsTest.binary");
//        assert carRepo2.getAll().size() == 1;
//    }
//    @Test
//    public void testAdd() {
//        setUp();
//        CarRepositoryBinaryFile carRepo = new CarRepositoryBinaryFile("carsTest.binary");
//        Car car = new Car("1", "Audi", 2010, false);
//        carRepo.add(car.getId(), car);
//        assert carRepo.getAll().size() == 1;
//    }
//    @Test
//    public void testFindById() {
//        setUp();
//        CarRepositoryBinaryFile carRepo = new CarRepositoryBinaryFile("carsTest.binary");
//        Car car = new Car("1", "Audi", 2010, false);
//        carRepo.add(car.getId(), car);
//        Car car1 = carRepo.findbyId("1");
//        assert car1.equals(car);
//    }
//    @Test
//    public void testModify() {
//        setUp();
//        CarRepositoryBinaryFile carRepo = new CarRepositoryBinaryFile("carsTest.binary");
//        Car car = new Car("1", "Audi", 2010, false);
//        carRepo.add(car.getId(), car);
//        Car car1 = new Car("1", "Audi", 2010, true);
//        carRepo.modify("1", car1);
//        Car car2 = carRepo.findbyId("1");
//        assert car2.equals(car1);
//    }
//    @Test
//    public void testDelete() {
//        setUp();
//        CarRepositoryBinaryFile carRepo = new CarRepositoryBinaryFile("carsTest.binary");
//        Car car = new Car("1", "Audi", 2010, false);
//        carRepo.add(car.getId(), car);
//        carRepo.delete("1");
//        assert carRepo.getAll().size() == 0;
//    }
//    @Test
//    public void testGetAll() {
//        setUp();
//        CarRepositoryBinaryFile carRepo = new CarRepositoryBinaryFile("carsTest.binary");
//    }
//}