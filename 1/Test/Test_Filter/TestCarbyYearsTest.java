//package Test_Filter;
//
//import domain.Car;
//import filter.FilterCarbyYears;
//import org.junit.jupiter.api.Test;
//import repository.FilteredRepository;
//import repository.MemoryRepository;
//
//public class TestCarbyYearsTest {
//    private MemoryRepository<String, Car> carRepo = null;
//    private Car car1 = null;
//    private Car car2 = null;
//    private int year1 = 0;
//    private int year2 = 0;
//    private FilterCarbyYears filter = null;
//    private FilteredRepository<Car, String> filteredRepo = null;
//    public void setUp()
//    {
//        carRepo = new MemoryRepository<>();
//        car1 = new Car("1", "Audi", 2010, false);
//        car2 = new Car("2", "BMW", 2024, false);
//        year1 = 2007;
//        year2 = 2015;
//        filter = new FilterCarbyYears(year1, year2);
//        filteredRepo = new FilteredRepository<>(filter);
//
//    }
//    @Test
//    public void FilterCarByYearsTest()
//    {
//        setUp();
//        filteredRepo.add(car1.getId(), car1);
//        filteredRepo.add(car2.getId(), car2);
//      //  System.out.println(filteredRepo.getAll());
//        assert filteredRepo.getAll().size() == 1;
//    }
//}
