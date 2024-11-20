package repository;
import domain.Car;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class CarRepo extends MemoryRepository<String, Car>{
    //private ArrayList<Car> cars;
    public CarRepo()
    {
        super();
    }
    public void addCar(Car car) {
//      try{  cars.add(car);}
//        catch (Exception e){
//            System.out.println("Car already exists");
//        }
        add(car.getId(), car);
    }
    public void removeCar(Car car){
//     try{   cars.remove(car);}
//        catch (Exception e){
//            System.out.println("Car not found");
//        }
        delete(car.getId());
    }
    public Optional<Car> getCarbyId(String Id){
//       try{ for(Car car : cars)
//            if(car.getId().equals(Id))
//                return car;}
//         catch (Exception e){
//            System.out.println("Car not found"+e);}
//        return null;
        return findbyId(Id);
    }
    public void updateCar(String Id, int year, String model){
//        for(Car car : cars)
//            if(car.getId().equals(Id)){
//                car.setYear(year);
//                car.setModel(model);}
       modify(Id, new Car(Id, model, year, true));
    }
    public ArrayList<Car> getCars(){
        return new ArrayList<>((Collection<? extends Car>) getAll());
    }

}
