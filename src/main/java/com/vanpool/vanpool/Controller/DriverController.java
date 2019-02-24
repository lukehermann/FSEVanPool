package com.vanpool.vanpool.Controller;

import com.vanpool.vanpool.Entity.Driver;
import com.vanpool.vanpool.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Driver> getAllDrivers()
    {
        return driverService.getAllDrivers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Driver getDriverByID(int id)
    {
        return this.driverService.getDriverByID(id);

    }


}
