package filter;

import domain.Reservation;

public class FIlterReservationbyPrice implements AbstractFilter<Reservation> {

    private double price1;
    private double price2;

    public FIlterReservationbyPrice(double price1, double price2) {
        this.price1 = price1;

        this.price2 = price2;
    }

    @Override
    public boolean accept(Reservation reservation) {
        return reservation.getPrice() >= price1 && reservation.getPrice() <= price2;
    }
}
