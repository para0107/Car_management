//package Test_Repo;
//
//import domain.Car;
//import repository.CarRepo;
//import repository.CarRepositoryBinaryFile;
//import repository.FileRepository;
//
//public class CarRepoBinaryFileTest {
//    void setUp() {
//        FileRepository<String, Car> carRepo = new CarRepositoryBinaryFile("carsTest.binary");
//        Car car = new Car("1", "Audi", 2010, false);
//    }
//    public void readFromFileTest()
//    {
//        setUp();
//        CarRepositoryBinaryFile carRepo = new CarRepositoryBinaryFile("carsTest.binary");
//
//        assert carRepo.getAll().size() == 0;
//    }
//}
