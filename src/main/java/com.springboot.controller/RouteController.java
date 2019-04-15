package com.springboot.controller;

import com.springboot.model.Route;
import com.springboot.model.Vehicle;
import com.springboot.service.RouteService;
import com.springboot.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value={"/addRoute"}, method= RequestMethod.GET)
    public ModelAndView addRoute()
    {
        ModelAndView model=new ModelAndView();
        Route route = new Route();
        List<Route> routeList = routeService.listAll();
        model.addObject("route", route);
        model.addObject("routeList", routeList);
        List<String> dayList = new ArrayList<>();
        dayList.add("Sunday");
        dayList.add("Monday");
        dayList.add("Tuesday");
        dayList.add("Wednesday");
        dayList.add("Thursday");
        dayList.add("Friday");
        dayList.add("Saturday");

        List<Vehicle> vehicleList = new ArrayList<>();
        //model.addObject("vehicleList", vehicleList);

        model.addObject("dayList", dayList);
        model.setViewName("functions/addRoute");

        return model;
    }

    @RequestMapping(value = {"/addRoute"}, method=RequestMethod.POST)
    public ModelAndView createRoute(@ModelAttribute("route") Route route,@RequestParam("days") List<String> dayList, BindingResult bindingResult) //
    {
        ModelAndView model = new ModelAndView();

        if(bindingResult.hasErrors()) {
            model.setViewName("functions/addRoute");
        }
        else {
            //model.setViewName("redirect:/home/home");
            routeService.saveRoute(route);
            //model.addObject("msg", "Route has been added successfully");
            //model.addObject("route", new Route());
            //model.setViewName("redirect:/home/home");
            model.addObject("route", route);
            model.addObject("dayList", dayList);
            List<Vehicle> vehicleList = vehicleService.findVehicle(dayList);
            model.addObject("vehicleList", vehicleList);
            model.setViewName("functions/findVehicle");
        }
        return model;
    }

    @RequestMapping(value = {"/findVehicle"}, method=RequestMethod.POST)
    public ModelAndView findRoute(long routeid, @ModelAttribute("vehicle") int vehicleid, @RequestParam List<String> dayList, BindingResult bindingResult)
    {

        ModelAndView model = new ModelAndView();

        if(bindingResult.hasErrors()) {
            model.setViewName("functions/findVehicle");
        }
        else {
            model.setViewName("redirect:/home/home");
            model.addObject("msg", "Route has been added successfully");

            // Adds the vehicleid to the route id
            routeService.updateRouteVehicle(routeid, vehicleid);

            // Updates vehicle to reflect true on days
            vehicleService.updateDays(dayList, vehicleid);
            Vehicle vehicle = vehicleService.getVehicle(vehicleid);

            // Sets the passenger capacity to vehicle capacity
            routeService.upadteCapacity(routeid, vehicle.getCapacity());

            model.addObject("msg", "Route has been added successfully");


            model.setViewName("redirect:/home/home");

        }

        return model;

    }

    @RequestMapping(value= {"/signUpRiderRoute"}, method=RequestMethod.GET)
    public ModelAndView signUpRiderRoute(@RequestParam("routes") List<String> routesids) {
        ModelAndView model = new ModelAndView();
        Route tempRoute = new Route();

        if(routesids != null){
            for(String id : routesids){
                int routeid = Integer.parseInt(id);
                tempRoute = routeService.findRouteByRouteid(routeid);
                int passengerCapacity;
                passengerCapacity = tempRoute.getPassengercapacity();
                int numberofpassengers = tempRoute.getNumberofpassengers();

                if(numberofpassengers  == passengerCapacity){
                    continue;
                }
                else{
                    numberofpassengers ++;
                }

                /*if (passengerCapacity==0)
                {
                    
                }
                else
                {
                    passengerCapacity--;
                }*/
                //routeService.deleteRoute(routeid);
                //routeService.signUpRiderRoute(passengerCapacity, (long) routeid);
                routeService.signUpRiderRoute(numberofpassengers , (long) routeid);
            }
        }

        model.addObject("routeList", routeService.listAll());

        model.setViewName("redirect:/home/home");

        return model;
    }
}