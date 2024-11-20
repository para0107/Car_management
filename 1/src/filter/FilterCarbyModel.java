package filter;

import domain.Car;
public class FilterCarbyModel implements AbstractFilter<Car>{

    private final String model;

    public FilterCarbyModel(String model) {
        this.model = model;
    }
    @Override
    public boolean accept(Car car) {
        return car.getModel().equals(this.model);

    }
}
