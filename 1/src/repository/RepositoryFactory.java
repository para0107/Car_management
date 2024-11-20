package repository;

import database.CarDataBase;
import database.ReservationDataBase;
import domain.Car;
import domain.Reservation;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.Properties;

public class RepositoryFactory {
    private static final String databaseUrl = "jdbc:mysql://localhost:3306/lab5";
    private static final String propertiesPath = "src/settings.properties";

    public static class Repositories {
        public MemoryRepository<String, Car> carRepository;
        public MemoryRepository<String, Reservation> reservationRepository;
    }

    public static Repositories createRepositories() throws IOException {
        Properties props = new Properties();
        props.load(new FileReader(propertiesPath));

        String repositorytype = props.getProperty("Repository");
        String carsPath = props.getProperty("cars");
        String reservationPath = props.getProperty("reservations");

        if (repositorytype == null) {
            throw new RuntimeException("Repository type not found");
        }
        Repositories repos = new Repositories();

        switch (repositorytype) {

            case "console": {
                repos.carRepository = new CarRepo();
                repos.reservationRepository = new ReservationRepo();

                Car car1 = new Car("1", "Peugeot", 2000, true);
                Car car2 = new Car("2", "Mazda", 2010, true);
                Car car3 = new Car("3", "BMW", 1994, true);
                Car car4 = new Car("4", "Mercedes", 2024, true);
                repos.carRepository.add(car1.getId(), car1);
                repos.carRepository.add(car2.getId(), car2);
                repos.carRepository.add(car3.getId(), car3);
                repos.carRepository.add(car4.getId(), car4);

                Reservation res1 = new Reservation("1", Date.valueOf("2020-10-10"), 100);
                Reservation res2 = new Reservation("2", Date.valueOf("1999-10-11"), 578);
                Reservation res3 = new Reservation("3", Date.valueOf("2024-10-12"), 200);
                Reservation res4 = new Reservation("4", Date.valueOf("2020-10-13"), 300);
                repos.reservationRepository.add(res1.getId(), res1);
                repos.reservationRepository.add(res2.getId(), res2);
                repos.reservationRepository.add(res3.getId(), res3);
                repos.reservationRepository.add(res4.getId(), res4);

                break;
            }
            case "database": {
                repos.carRepository = new CarDataBase(databaseUrl);
                repos.reservationRepository = new ReservationDataBase(databaseUrl);
                break;
            }
            case "binary": {
                repos.carRepository = new CarRepositoryBinaryFile(carsPath);
                repos.reservationRepository = new ReservationRepositoryBinaryFile(reservationPath);
                break;
            }
            case "text": {
                repos.carRepository = new CarRepositoryTextFile(carsPath);
                repos.reservationRepository = new ReservationRepositoryTextFile(reservationPath);
                break;
            }
            default: {
                throw new RuntimeException("Invalid repository type");
            }
        }
        return repos;
    }
}
