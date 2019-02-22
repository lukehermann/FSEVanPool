package com.vanpool.vanpool.Service;

import com.vanpool.vanpool.DAO.DriverDao;
import com.vanpool.vanpool.Entity.Driver;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DriverService {

    private DriverDao driverDAO;

    public Collection<Driver> getAllDrivers()
    {
        return driverDAO.getAllDrivers();
    }
}
