package repository;
import java.util.*;

import domain.Reservation;

public class ReservationRepo extends MemoryRepository<String, Reservation> {
//    private HashMap<Reservation,Car> reservations = null;
//    private CarRepo carRepo;
//    private MemoryRepository<String, Reservation> memo_repository;
//
//    public ReservationRepo( HashMap<Reservation,Car> reservations , CarRepo carRepo, MemoryRepository<String, Reservation> memo_repository) {
//        this.reservations=reservations;
//        this.carRepo=carRepo;
//        this.memo_repository = memo_repository;
//    }
//
//    public HashMap<Reservation,Car> getReservations() {
//        return reservations;
//    }
//    public void addReservation( Reservation reservation)
//    { String id = String.valueOf(reservation.getId());
//        Car car = carRepo.getCarbyId(id);
////        if(car != null && car.isAvailable())
////        {
////            reservations.put(reservation,car);
//////        }
////        else System.out.println("Car with id" + id + " not found");
//try{        reservations.put(reservation, car);}
//catch (Exception e){ System.out.println("Car with id"  + id +" not found");}
//    }
//    public void deleteReservation(String id)
//    {
//        Reservation r = null;
//        for (Reservation reservation : reservations.keySet()) {
////            if(reservation.getId().equals(id)){
////            reservations.remove(reservation);
////            System.out.println("Reservation with ID " + reservation + " has been removed.");
////        } else {
////            System.out.println("No reservation found with ID " + r);
////
////        }
//            try{ if(reservation.getId().equals(id))
//                reservations.remove(reservation);
//            }
//            catch (Exception e){System.out.println("No reservation found with ID " + r);}
//    }}
//
//    public void updateReservation(String id, Date date, int price) {
//        Reservation toUpdate = null;
//        for (Reservation reservation : reservations.keySet()) {
//            if (reservation.getId().equals(id)) {
//                toUpdate = reservation;
//                break;
//            }
//        }
//        if (toUpdate != null) {
//            Car car = reservations.get(toUpdate);
//            reservations.remove(toUpdate); // Remove the old reservation
//            toUpdate.setDate(date);
//            toUpdate.setPrice(price);
//            reservations.put(toUpdate, car); // Add the updated reservation back with the same car
//        } else {
//            System.out.println("No reservation found with ID " + id);
//        }
//    }
//
//    public Reservation getReservationById(String id)
//    {
//        for(Reservation reservation : reservations.keySet())
//        {
//            if(Objects.equals(reservation.getId(), id))
//               return reservation;
//        }
//        return null;
//    }
//    @Override
//    public String toString() {
//        return "ReservationRepo{" +
//                "reservations=" + reservations +
//                ", carRepo=" + carRepo +
//                '}';
//    }
    public ReservationRepo() {
        super();
    }
    public void addRes(Reservation reservation) {
        add(reservation.getId(), reservation);
    }
    public void removeRes(Reservation reservation) {
        delete(reservation.getId());
    }
    public void updateRes(Reservation reservation) {
        modify(reservation.getId(), reservation);
    }
    public Optional<Reservation> getResById(String id) {
        return findbyId(id);
    }
    public ArrayList<Reservation> getReservations() {
        return new ArrayList<>((Collection<? extends Reservation>) getAll());
    }

}
