//package Test_Filter;
//
//import domain.Car;
//import filter.FilterCarbyModel;
//import org.junit.jupiter.api.Test;
//import repository.FilteredRepository;
//import repository.MemoryRepository;
//
//public class FIlterCarByModelTest {
//    private MemoryRepository<String, Car> carRepo = null;
//    private Car car1 = null;
//    private Car car2 = null;
//    String model = null;
//  private FilterCarbyModel filter = null;
//    private FilteredRepository<Car, String> filteredRepo = null;
////
//    void setUp()
//    {
//        carRepo = new MemoryRepository<>();
//        car1 = new Car("1", "Audi", 2010, false);
//        car2 = new Car("2", "BMW", 2011, false);
//    }
//    @Test
//    void FilterCarByModelTest()
//    {  setUp();
//        model = "Audi";
//        filter = new FilterCarbyModel(model);
//        filteredRepo = new FilteredRepository<>(filter);
//        filteredRepo.add(car1.getId(), car1);
//        filteredRepo.add(car2.getId(), car2);
//      //  System.out.println(filteredRepo.getAll());
//
//        assert filteredRepo.getAll().size() == 1;
//
//    }
//}
