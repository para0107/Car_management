package database;

import domain.Car;
import repository.MemoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDataBase extends MemoryRepository<String, Car> {
    private final String URL; //= "jdbc:mysql://localhost:3306/lab5";
    private final String user = "root";
    private final String password = "costincnva2016";

    public CarDataBase(String URL) {
        this.URL = URL;
    }

    public void add(String id, Car car) {
        try (
                Connection conn = DriverManager.getConnection(URL, user, password);
                PreparedStatement ps = conn.prepareStatement("INSERT INTO cars VALUES (?, ?, ?, ?);")
        ) {
            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYear());
            ps.setBoolean(4, car.isAvailable());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<Car> delete(String id) {
        try (
                Connection conn = DriverManager.getConnection(URL, user, password);
                PreparedStatement ps = conn.prepareStatement("DELETE FROM cars WHERE ID = ?;")
        ) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
    public void modify(String id, Car car) {
        try (
                Connection conn = DriverManager.getConnection(URL, user, password);
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE cars SET " +
                                "model = ?, " +
                                "year = ?, " +
                                "isAvailable = ? " +
                                "WHERE ID = ?;"
                )
        ) {
            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYear());
            ps.setBoolean(4, car.isAvailable());


            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Optional<Car> findbyId(String id) {
        try (
                Connection conn = DriverManager.getConnection(URL, user, password);
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM cars WHERE ID = ?;")
        ) {
            ps.setInt(1, Integer.parseInt(id));
            var rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Car(
                        id,
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getBoolean("isAvailable")
                ));
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(URL, user, password);
                Statement st = conn.createStatement();
                ResultSet rs  = st.executeQuery("SELECT * FROM cars;")
        ) {

            while (rs.next()) {
                cars.add(new Car(
                        Integer.toString(rs.getInt("ID")),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getBoolean("isAvailable")
                ));
            }
            return cars;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
