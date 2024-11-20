package domain;

import java.util.Objects;
import java.io.Serializable;
public class Car implements Identifiable<String>,Serializable {
    private String Id;
    private String Model;
    private int Year;
    private boolean isAvailable;

    public Car(String id, String model, int year, boolean isAvailable) {
        Id = id;
        Model = model;
        Year = year;
        this.isAvailable = isAvailable;
    }
@Override
    public String getId() {
        return Id;
    }
@Override
    public void setId(String id) {
        Id = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Id == car.Id && Year == car.Year && isAvailable == car.isAvailable && Objects.equals(Model, car.Model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Model, Year, isAvailable);
    }

    @Override
    public String toString() {
        return "Car{" +
                "Id=" + Id +
                ", Model='" + Model + '\'' +
                ", Year=" + Year +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
