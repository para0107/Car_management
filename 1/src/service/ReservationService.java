package service;
import domain.Car;
import domain.Reservation;
import filter.FIlterReservationbyPrice;
import filter.FilterCarbyModel;
import repository.CarRepo;
import repository.FilteredRepository;
import repository.MemoryRepository;
import repository.ReservationRepo;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ReservationService {
    //private final ReservationRepo reservationRepo;
    public final MemoryRepository<String, Reservation> memo_repository;

    public ReservationService(MemoryRepository<String, Reservation> memo_repository) {
        // this.reservationRepo = reservationRepo;
        this.memo_repository = memo_repository;
    }

    public ReservationService() {
        this.memo_repository = new MemoryRepository<>();
    }

    public void addReservation(Reservation reservation) {
        //reservationRepo.addReservation(reservation);
        if (memo_repository.findbyId(reservation.getId()).isEmpty())
            memo_repository.add(reservation.getId(), reservation);
        else throw
                new RuntimeException("Reservation already exists");
    }

    public void deleteReservation(String id) {
        if (memo_repository.findbyId(id).isPresent())
            //reservationRepo.deleteReservation(id);
            memo_repository.delete(id);
        else
            throw new RuntimeException("Reservation does not exist");
    }

    public void updateReservation(String id, Date date, int price) {
        Reservation r = new Reservation(id, date, price);
        // reservationRepo.updateReservation(id, date, price);
        memo_repository.modify(id, r);

    }

    public List<Reservation> getAllReservations() {
        System.out.println("Merge fraiere");
        return memo_repository.getAll();
    }

    public Collection<Reservation> getReservationbyPrice(int price1, int price2) {
        FIlterReservationbyPrice filter = new FIlterReservationbyPrice(price1, price2);
        FilteredRepository<Reservation, String> filteredRepository = new FilteredRepository<>(filter);
        for (Reservation r : memo_repository.getAll()) {
            filteredRepository.add(r.getId(), r);
        }
        return filteredRepository.getAll();
    }

    public double getAveragePrice() {
        return memo_repository.getAll().stream()
                .mapToDouble(Reservation::getPrice)
                .average()
                .orElse(0);
    }

    public Date getResbyLatestDate() {
        return memo_repository.getAll().stream()
                .map(Reservation::getDate)
                .max(Date::compareTo)
                .orElse(null);
    }
    public String getResbyMaxId(){
        return memo_repository.getAll().stream()
                .map(Reservation::getId)
                .max(String::compareTo)
                .orElse(null);
    }
}
