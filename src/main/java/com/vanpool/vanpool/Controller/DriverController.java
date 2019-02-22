package com.vanpool.vanpool.Controller;

import com.vanpool.vanpool.Entity.Driver;
import com.vanpool.vanpool.Service.DriverService;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DriverController {
    private DriverService driverService;

    public Collection<Driver> getAllDrivers()
    {
        return driverService.getAllDrivers();
    }
}
