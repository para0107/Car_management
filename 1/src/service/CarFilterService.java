//package service;
//
//import domain.Car;
//import domain.Identifiable;
//import filter.FilterCarbyModel;
//import filter.FilterCarbyYears;
//import repository.CarRepo;
//import repository.FilteredRepository;
//import repository.IRepository;
//import repository.MemoryRepository;
//
//import java.util.Collection;
//
//public class CarFilterService {
//   // private final MemoryRepository<String, Car> repository;
//    private final CarRepo carRepo;
//
//    public CarFilterService(CarRepo carRepo) {
//
//        this.carRepo = carRepo;
//    }
//
////    public Collection<Car> getCarsbyModel(String model) {
////        FilterCarbyModel filter = new FilterCarbyModel(model);
////        FilteredRepository<Car, String> filteredRepository = new FilteredRepository<>(filter);
////        for(Car c : carRepo.getAll())
////            filteredRepository.add(c.getId(), c);
////        return filteredRepository.getAll();
////    }
////
////    public Collection<Car> getCarsbyYears(int startyear, int endyear){
////        FilterCarbyYears filter = new FilterCarbyYears(startyear, endyear);
////        FilteredRepository<Car, String> filtered = new FilteredRepository<>(filter);
////        for(Car c : carRepo.getAll())
////            filtered.add(c.getId(), c);
////        return filtered.getAll();
////    }
//}
