package database;

import domain.Reservation;
import repository.MemoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDataBase extends MemoryRepository<String, Reservation> {
    private final String URL; //= "jdbc:mysql://localhost:3306/lab5";
    private final String user = "root";
    private final String password = "costincnva2016";

    public ReservationDataBase(String URL) {
        this.URL = URL;
    }

    public void add(String id, Reservation res) {
        try (
                Connection conn = DriverManager.getConnection(URL, user, password);
                PreparedStatement ps = conn.prepareStatement("INSERT INTO reservations (id, date, price) VALUES (?, ?, ?);")
        ) {
            Date sqlDate = new Date(res.getDate().getTime());
            ps.setInt(1, Integer.parseInt(id));
            ps.setDate(2, sqlDate);
            ps.setInt(3, res.getPrice());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void modify(String id, Reservation res) {
        try (
                Connection conn = DriverManager.getConnection(URL, user, password);
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE reservations SET " +
                                "date = ?, " +
                                "price = ? " +
                                "WHERE id = ?;"
                )
        ) {
            Date sqlDate = new Date(res.getDate().getTime());
            ps.setDate(1, sqlDate);
            ps.setInt(2, res.getPrice());
            ps.setInt(3, Integer.parseInt(id));

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Reservation> findbyId(String id) {
        try (
                Connection conn = DriverManager.getConnection(URL, user, password);
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM reservations WHERE id = ?;")
        ) {
            ps.setInt(1, Integer.parseInt(id));
            var rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Reservation(
                        id,
                        rs.getDate("date"),
                        rs.getInt("price")
                ));
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(URL, user, password);
                Statement st = conn.createStatement();
                ResultSet rs  = st.executeQuery("SELECT * FROM reservations;")
        ) {
            while (rs.next()) {
                reservations.add(new Reservation(
                        Integer.toString(rs.getInt("id")),
                        rs.getDate("date"),
                        rs.getInt("price")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return reservations;
    }
}
