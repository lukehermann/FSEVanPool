package com.vanpool.vanpool.Service;

import com.vanpool.vanpool.DAO.DriverDao;
import com.vanpool.vanpool.Entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DriverService {
    @Autowired
    private DriverDao driverDAO;

    public Collection<Driver> getAllDrivers() {
        return driverDAO.getAllDrivers();
    }

    public Driver getDriverByID(int id)
    {
        return this.driverDAO.getDriverByID(id);
    }
}
