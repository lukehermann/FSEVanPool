package com.springboot.controller;

import com.springboot.model.Route;
import com.springboot.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;

    @RequestMapping(value={"/addRoute"}, method= RequestMethod.GET)
    public ModelAndView addRoute()
    {
        ModelAndView model=new ModelAndView();
        Route route = new Route();
        List<Route> routeList = routeService.listAll();
        model.addObject("route", route);
        model.addObject("routeList", routeList);
        model.setViewName("functions/addRoute");

        return model;
    }

    @RequestMapping(value = {"/addRoute"}, method=RequestMethod.POST)
    public ModelAndView createRoute(@ModelAttribute("route") Route route, BindingResult bindingResult) //
    {
        ModelAndView model = new ModelAndView();

        if(bindingResult.hasErrors()) {
            model.setViewName("functions/addRoute");
        }
        else {
            routeService.saveRoute(route);
            model.addObject("msg", "Route has been added successfully");
            model.addObject("route", new Route());
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
                passengerCapacity--;
                //routeService.deleteRoute(routeid);
                routeService.signUpRiderRoute(passengerCapacity, (long) routeid);
            }
        }

        model.addObject("routeList", routeService.listAll());

        model.setViewName("redirect:/home/home");

        return model;
    }
}

