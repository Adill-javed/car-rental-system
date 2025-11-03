//package com.example.carrental.repo;
//
//import com.example.carrental.model.Car;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class CarRepository {
//    private final JdbcTemplate jdbc;
//    private final RowMapper<Car> rowMapper = (rs, rowNum) ->
//        new Car(rs.getInt("id"), rs.getString("name"), rs.getDouble("base_price_per_day"), rs.getBoolean("available"));
//
//    public CarRepository(JdbcTemplate jdbc) {
//        this.jdbc = jdbc;
//    }
//
//    public List<Car> findAvailableCars() {
//        String sql = "SELECT * FROM cars WHERE available = TRUE";
//        return jdbc.query(sql, rowMapper);
//    }
//
//    public Boolean isAvailable(int id) {
//        String sql = "SELECT available FROM cars WHERE id = ?";
//        try {
//            return jdbc.queryForObject(sql, Boolean.class, id);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public Double getPricePerDay(int id) {
//        String sql = "SELECT base_price_per_day FROM cars WHERE id = ?";
//        try {
//            return jdbc.queryForObject(sql, Double.class, id);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public boolean rentCar(int id) {
//        String sql = "UPDATE cars SET available = FALSE WHERE id = ? AND available = TRUE";
//        int updated = jdbc.update(sql, id);
//        return updated > 0;
//    }
//}
package com.example.carrental.repo;

import com.example.carrental.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class CarRepository {
    private final JdbcTemplate jdbc;
    private final RowMapper<Car> rowMapper = (rs, rowNum) ->
            new Car(rs.getInt("id"), rs.getString("name"), rs.getDouble("base_price_per_day"), rs.getBoolean("available"));

    // fallback demo data
    private final List<Car> fallbackCars = new ArrayList<>(List.of(
            new Car(1, "Lamborghini Avantador", 75, true),
            new Car(2, "Ford Mustang", 95, true),
            new Car(3, "Toyota Fortuner", 65, true)
    ));

    public CarRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Car> findAvailableCars() {
        try {
            return jdbc.query("SELECT * FROM cars WHERE available = TRUE", rowMapper);
        } catch (Exception e) {
            System.out.println("⚠️ Using fallback demo data (DB not connected)");
            return fallbackCars.stream().filter(Car::isAvailable).toList();
        }
    }

    public Boolean isAvailable(int id) {
        try {
            return jdbc.queryForObject("SELECT available FROM cars WHERE id = ?", Boolean.class, id);
        } catch (Exception e) {
            return fallbackCars.stream()
                    .filter(c -> c.getId() == id)
                    .map(Car::isAvailable)
                    .findFirst()
                    .orElse(null);
        }
    }

    public Double getPricePerDay(int id) {
        try {
            return jdbc.queryForObject("SELECT base_price_per_day FROM cars WHERE id = ?", Double.class, id);
        } catch (Exception e) {
            return fallbackCars.stream()
                    .filter(c -> c.getId() == id)
                    .map(Car::getBasePricePerDay)
                    .findFirst()
                    .orElse(null);
        }
    }

    public boolean rentCar(int id) {
        try {
            int updated = jdbc.update("UPDATE cars SET available = FALSE WHERE id = ? AND available = TRUE", id);
            return updated > 0;
        } catch (Exception e) {
            for (Car c : fallbackCars) {
                if (c.getId() == id && c.isAvailable()) {
                    c.setAvailable(false);
                    return true;
                }
            }
            return false;
        }
    }
}

