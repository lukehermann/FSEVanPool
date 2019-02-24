package com.vanpool.vanpool.DAO;

import com.vanpool.vanpool.Entity.Driver;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DriverDao {
    private static Map<Integer, Driver> drivers;

    static {
        drivers = new HashMap<Integer, Driver>() {
            {
                put(1, new Driver(1234, "Alan", "1234"));
                put(2, new Driver(1235, "Amanda", "1235"));
                put(3, new Driver(1236, "Luke", "1236"));
            }
        };

    }

    public Collection<Driver> getAllDrivers() {
        return this.drivers.values();
    }
}
