//package Test_Filter;
//
//import domain.Reservation;
//import filter.FIlterReservationbyPrice;
//import org.junit.jupiter.api.Test;
//import repository.FilteredRepository;
//import repository.MemoryRepository;
//
//import java.sql.Date;
//
//public class FilterreservationbyPriceTest {
//    private MemoryRepository<String, Reservation> carRepo = null;
//    private Reservation res1 = null;
//    private Reservation res2 = null;
//    private double price1 = 0;
//    private double price2 = 0;
//    private FIlterReservationbyPrice filter = null;
//    private FilteredRepository<Reservation, String> filteredRepo = null;
//
//    public void setUp()
//    {
//        carRepo = new MemoryRepository<>();
//        res1 = new Reservation("1", Date.valueOf("2020-04-08"), 100);
//        res2 = new Reservation("2", Date.valueOf("2020-04-08"), 2000);
//        price1 = 150;
//        price2 = 2500;
//        filter = new FIlterReservationbyPrice(price1, price2);
//        filteredRepo = new FilteredRepository<>(filter);
//    }
//    @Test
//    public void FilterReservationByPriceTest()
//    {
//        setUp();
//        filteredRepo.add(res1.getId(), res1);
//        filteredRepo.add(res2.getId(), res2);
//        //  System.out.println(filteredRepo.getAll());
//        assert filteredRepo.getAll().size() == 1;
//    }
//}
