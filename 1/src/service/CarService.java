package service;

import domain.Car;
import domain.Identifiable;
import filter.FilterCarbyModel;
import filter.FilterCarbyYears;
import repository.CarRepo;
import repository.FilteredRepository;
import repository.MemoryRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class CarService {
   // private CarRepo carRepo;
    private final MemoryRepository<String, Car> memo_repository;
    public CarService()
    {
       // this.carRepo = carRepo;
        this.memo_repository = new MemoryRepository<>();
       // this.memo_repository = memo_repository;
    }
    public CarService(MemoryRepository<String, Car> memo_repository) {
        this.memo_repository = memo_repository;

    }
    public void addCar(String id, String model, int year, boolean isAvailable) {
        Car car = new Car(id, model, year, isAvailable);
       if(memo_repository.findbyId(id).isPresent())
            throw new RuntimeException("The id already exists");
       memo_repository.add(id, car);

        }
        //carRepo.addCar(car);
      // memo_repository.add(id, car);

    public void removeCarById(String id) {
        if(memo_repository.findbyId(id).isEmpty())
            throw new RuntimeException("The car does not exist");
        memo_repository.delete(id);
    }

    public Optional<Car> getCarById(String id) {
        return memo_repository.findbyId(id);
    }
    public boolean updateCar(String id, int year, String model) {
        Optional<Car> car = memo_repository.findbyId(id);
        Car c2 = new Car(id, model, year, car.get().isAvailable());
        // carRepo.updateCar(id, year, model);
        memo_repository.modify(id,c2);
        return true;
    }


    public Collection<Car> getAllCars() {
        System.out.println("Merge fraiere");
        return memo_repository.getAll()
               ;
    }

    public ArrayList<Car> getAvailableCars() {
        ArrayList<Car> availableCars = new ArrayList<>();
        for (Car car : memo_repository.getAll()) {
            if (car.isAvailable()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }
    public Collection<Car> getCarsbyModel(String model) {
        FilterCarbyModel filter = new FilterCarbyModel(model);
        FilteredRepository<Car, String> filteredRepository = new FilteredRepository<>(filter);
//        for(Car c : memo_repository.getAll())
//            filteredRepository.add(c.getId(),c);
//        return filteredRepository.getAll();
        memo_repository.getAll().forEach(car -> filteredRepository.add(car.getId(), car));
        return filteredRepository.getAll();
    }

    public Collection<Car> getCarsbyYears(int startyear, int endyear){
        FilterCarbyYears filter = new FilterCarbyYears(startyear, endyear);
//        FilteredRepository<Car, String> filtered = new FilteredRepository<>(filter);
//        for(Car c : memo_repository.getAll())
//            filtered.add(c.getId(),c);
//        return filtered.getAll();
        FilteredRepository<Car, String> filteredRepository = new FilteredRepository<>(filter);
        memo_repository.getAll().forEach(car -> filteredRepository.add(car.getId(), car));
        return filteredRepository.getAll();
    }
    public double getCarsbyYear(int year){
        return memo_repository.getAll().stream()
                .filter(car ->  car.getYear() == year)
                .count();
    }
    public double getAveragePrice() {
        return memo_repository.getAll().stream()
                .mapToInt(Car::getYear)
                .average()
                .orElse(0);
    }


}
