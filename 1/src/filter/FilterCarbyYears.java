package filter;

import domain.Car;

import java.util.logging.Filter;

public class FilterCarbyYears implements AbstractFilter<Car>
{
    private final int startYear;
    private final int endYear;

    public FilterCarbyYears(int startYear, int endYear)
    {
        this.startYear = startYear;
        this.endYear = endYear;
    }
    @Override
    public boolean accept(Car car)
    {
        int carYear = car.getYear();
        return carYear >= startYear && carYear <= endYear;
    }

}
