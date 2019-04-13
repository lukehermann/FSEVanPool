package com.springboot.controller;

import com.springboot.model.Route;
import com.springboot.model.Vehicle;
import com.springboot.service.RouteService;
import com.springboot.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
            model.setViewName("redirect:/home/home");
            routeService.saveRoute(route);
            model.addObject("msg", "Route has been added successfully");
            model.addObject("route", new Route());
            model.setViewName("redirect:/home/home");
        }
        return model;
    }

    @RequestMapping(value = {"/findRoute"}, method=RequestMethod.GET)
    public ModelAndView findRoute(@ModelAttribute("route") Route route,@RequestParam("days") List<String> dayList, BindingResult bindingResult) //
    {

        ModelAndView model = new ModelAndView();

        List<Vehicle> vehicleList = vehicleService.findVehicle(dayList);

        model.addObject("vehicleList", vehicleList);

        if(bindingResult.hasErrors()) {
            model.setViewName("functions/addRoute");
        }
        else {
            model.setViewName("functions/findVehicles");
        }

        return model;

    }


}

