package com.springboot.controller;

import com.springboot.model.Route;
import com.springboot.model.User;
import com.springboot.model.Vehicle;
import com.springboot.service.RouteService;
import com.springboot.service.UserService;
import com.springboot.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    private String outputBill = "";

    @RequestMapping(value={"/addRoute"}, method= RequestMethod.GET)
    public ModelAndView addRoute(@RequestParam(value ="routes", defaultValue = "emptyRouteList") List<String> routeids, @RequestParam("routeButton") String buttonClicked)
    {
        ModelAndView model=new ModelAndView();

        if(buttonClicked.equals("add")) {
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
        }
        else {
            if(routeids != null){
                for(String id : routeids){
                    if(!id.contains("emptyRouteList")) {
                        // Convert string to integer
                        int routeid = Integer.parseInt(id);
                        // Gets the vehicle ID from the route
                        int vehicleid = routeService.getVehicleID(routeid);
                        List<String> dayList = routeService.getDays(routeid);
                        vehicleService.updateDays(dayList, vehicleid, false);
                        routeService.deleteRoute(routeid);
                    }
                }
            }

            model.addObject("routeList", routeService.listAll());

            model.setViewName("redirect:/home/home");
        }

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
            for (String day: dayList) {

                switch (day) {
                    case "Sunday":
                        route.setSunday(true);
                        break;
                    case "Monday":
                        route.setMonday(true);
                        break;
                    case "Tuesday":
                        route.setTuesday(true);
                        break;
                    case "Wednesday":
                        route.setWednesday(true);
                        break;
                    case "Thursday":
                        route.setThursday(true);
                        break;
                    case "Friday":
                        route.setFriday(true);
                        break;
                    case "Saturday":
                        route.setSaturday(true);
                        break;
                    default:
                        // code block
                }

            }
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
            vehicleService.updateDays(dayList, vehicleid, true);
            Vehicle vehicle = vehicleService.getVehicle(vehicleid);

            // Sets the passenger capacity to vehicle capacity
            routeService.upadteCapacity(routeid, vehicle.getCapacity());

            model.addObject("msg", "Route has been added successfully");
            model.setViewName("redirect:/home/home");

        }

        return model;

    }

    @RequestMapping(value= {"/signUpDriverRoute"}, method=RequestMethod.GET)
    public ModelAndView signUpDriverRoute(@RequestParam("routes") List<String> routesids) {
        ModelAndView model = new ModelAndView();
        Route tempRoute = new Route();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        String userRoutes;
        if(routesids != null) {
            for (String id : routesids) {
                int routeid = Integer.parseInt(id);
                tempRoute = routeService.findRouteByRouteid(routeid);
                int userid = user.getId();

                if (canSignUp(tempRoute, user))
                {
                    if (tempRoute.getDriverid() == 0) {
                        routeService.addDriverToRoute(userid, routeid);
                        routeService.setRouteToActive(routeid);
                        model.addObject("msg", "Successfully signed up for route!");
                    }
                    userRoutes=userService.getRoutes(userid);
                    if (userRoutes != null)
                    {
                        userRoutes=userRoutes.concat(" ");
                        userRoutes=userRoutes.concat(Integer.toString(routeid));
                    }
                    else
                    {
                        userRoutes=Integer.toString(routeid);
                    }
                    userService.updateRoutes(userRoutes, userid);
                }
            }
            List<Route> myRoutes=new ArrayList<>();
        }
        List<Route> routeList=routeService.listNoDriverID();
        model.addObject("routeList", routeList);

        model.setViewName("redirect:/home/home");
        return model;
    }

    @RequestMapping(value = {"/endRoute"}, method = RequestMethod.GET)
    public ModelAndView endRoute(@RequestParam("routes") List<String> routesids)
    {
        ModelAndView model =new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        String routes = userService.getRoutes(user.getId());
        if (routesids !=null)
        {
            for (String id : routesids) {
                String routeid = Integer.toString(Integer.parseInt(id));
                if (routeid!=null)
                {
                    routeService.endDriverShift(Integer.parseInt(id));
                }
                int i=0;
                int j=0;
                String temp;
                while (j < routes.length()) {
                    if (routes.length() == 1 || routes.length()== 2)
                    {
                        if (routes.equals(routeid))
                        {
                            routes=null;

                        }
                        break;
                    }
                    else if (routes.substring(j, j+1).equals(" "))
                    {
                        if (routes.substring(i, j).equals(routeid))
                        {
                            temp =routes.substring(j+1);
                            routes=routes.substring(0, i);
                            routes= routes.concat(temp);
                        j=0;
                        i = j;

                        }
                        else
                        {
                            j++;
                            i=j;
                        }
                    }
                    else {
                        j++;
                    }
                     if (i==routes.length()-1) {
                            if (routeid.equals(routes.substring(i))) {
                                routes = routes.substring(0, i - 1);
                            }
                        }

                }
            }
            userService.updateRoutes(routes, user.getId());
            model.setViewName("redirect:/home/home");
        }
        return model;
    }

    @RequestMapping(value= {"/signUpRiderRoute"}, method=RequestMethod.GET)
    public ModelAndView signUpRiderRoute(@RequestParam("routes") List<String> routesids) {
        ModelAndView model = new ModelAndView();
        Route tempRoute = new Route();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        String userRoutes;

        //int userRouteIDs[] = new int[100];
        List <Integer> userRoutesIDs = new ArrayList<>();


        if(routesids != null) {
            for (String id : routesids) {
                String riderRoutes = userService.getRoutes(user.getId());
                String userHistory = userService.getHistory(user.getId());

                if (riderRoutes  != null) {
                    String[] riderRoutesSplit = riderRoutes .split(" ");

                    for(int i = 0; i < riderRoutesSplit.length; i++){
                        userRoutesIDs.add(i, Integer.parseInt(riderRoutesSplit[i]));


                    }
                }

                int routeid = Integer.parseInt(id);
                tempRoute = routeService.findRouteByRouteid(routeid);
                int userid = user.getId();
                int routeid2 = Integer.parseInt(id);

                System.out.println("ROUTE: " + tempRoute.isSunday());
                System.out.println("USER: " + user.isSunday());

                if ((tempRoute.getActive() == 1) && (tempRoute.getNumberofpassengers() != tempRoute.getPassengercapacity()) &&
                        !findRoute(routeid2, userRoutesIDs) && canSignUp(tempRoute, user)) {
                    System.out.println("HERE!!!!!!!!!!");
                    int numberofpassengers = tempRoute.getNumberofpassengers();
                    numberofpassengers ++;
                    routeService.signUpRiderRoute(numberofpassengers , (long) routeid2);
                    userService.updateSunday(userid, true);


                    String temp = "Added\nroute\nfrom\n" + tempRoute.getStartlocation() + "\nto\n" + tempRoute.getDropofflocation() + "\non\n"
                            + findDays(tempRoute) + "\nfor\n$" + tempRoute.getRate() + "</br></br>";

                    userHistory += temp;
                    if(userHistory.contains("null")){
                        userHistory = userHistory.substring(4);
                    }
                    userService.updateHistory(userHistory, userid);

                    model.addObject("msg", "Successfully signed up for route!");
                }
                userRoutes=userService.getRoutes(userid);
                if(!findRoute(routeid2, userRoutesIDs)){

                    if (userRoutes != null) {
                        userRoutes=userRoutes.concat(" ");
                        userRoutes=userRoutes.concat(Integer.toString(routeid));
                    }
                    else {
                        userRoutes=Integer.toString(routeid);
                    }
                }

                userService.updateRoutes(userRoutes, userid);
            }
        }



        List<Route> routeList = new ArrayList<Route>();
        for(int i = 0; i < userRoutesIDs.size(); i++){
            tempRoute = routeService.findRouteByRouteid(userRoutesIDs.get(i));
            routeList.add(tempRoute);
        }

        //List<Route> routeList=routeService.listNoDriverID();
        model.addObject("routeList", routeList);
        model.setViewName("redirect:/home/home");
        return model;
    }

    public boolean findRoute(int route, List<Integer> array){
        for (int i1 : array) {
            if (i1 == route) {
                return true;
            }
        }
        return false;
    }

    public boolean canSignUp(Route route, User user){
        if(route.isSunday() && user.isSunday()){

            return false;

        }
        else if (route.isSaturday() && user.isSaturday())
        {
            return false;
        }
        else if (route.isMonday() && user.isMonday())
        {
            return false;
        }
        else if (route.isTuesday() && user.isTuesday())
        {
            return false;
        }
        else if (route.isWednesday() && user.isWednesday())
        {
            return false;
        }
        else if (route.isThursday() && user.isThursday())
        {
            return false;
        }
        else if (route.isFriday() && user.isFriday())
        {
            return false;
        }
        return true;
    }


    public String findDays(Route route){
        String days = "";
        if(route.isMonday()){
            days += "Monday\n";
        }
        else if(route.isTuesday()){
            days += "Tuesday\n";
        }
        else if(route.isWednesday()){
            days += "Wednesday\n";
        }
        else if(route.isThursday()){
            days += "Thursday\n";
        }
        else if(route.isFriday()){
            days += "Friday\n";
        }
        else if(route.isSaturday()){
            days += "Saturday\n";
        }
        else if(route.isSunday()){
            days += "Sunday\n";
        }
        return days;
    }


    @RequestMapping(value = {"/removeRiderRoute"}, method = RequestMethod.GET)
    public ModelAndView removeRiderRoute(@RequestParam("routes") List<String> routesids) {
        ModelAndView model =new ModelAndView();
        Route tempRoute = new Route();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        String routes = userService.getRoutes(user.getId());

        List <Integer> userRoutesIDs = new ArrayList<>();

        if (routesids !=null) {
            for (String id : routesids) {
                String routeid = Integer.toString(Integer.parseInt(id));
                String userHistory = userService.getHistory(user.getId());

                int routeid2 = Integer.parseInt(id);
                tempRoute = routeService.findRouteByRouteid(routeid2);
                tempRoute.subtractPassengers();
                routeService.signUpRiderRoute(tempRoute.getNumberofpassengers(), (long) routeid2);

                userService.updateSunday(user.getId(), false);


                String temp2 = "Removed\nroute\nfrom\n" + tempRoute.getStartlocation() + "\nto\n" + tempRoute.getDropofflocation() + "\non\n"
                        + findDays(tempRoute) + "\nfor\n$" + tempRoute.getRate() + "</br></br>";
                //String temp2 = " -" + routeid2;
                userHistory += temp2;
                if(userHistory.contains("null")){
                    userHistory = userHistory.substring(4);
                }
                userService.updateHistory(userHistory, user.getId());

                int i=0;
                int j=0;
                String temp;

                while (j < routes.length()) {

                    if (routes.length() == 2 || routes.length() == 1) {
                        if (routes.equals(routeid)) {
                            routes=null;

                        }
                        break;
                    }
                    else if (routes.substring(j, j+1).equals(" ")) {
                        if (routes.substring(i, j).equals(routeid)) {
                            temp =routes.substring(j+1);
                            routes=routes.substring(0, i);
                            routes= routes.concat(temp);
                            j=0;
                            i = j;
                        }
                        else {
                            j++;
                            i=j;
                        }
                    }
                    else {
                        j++;
                    }
                    if (i==routes.length()-1) {
                        if (routeid.equals(routes.substring(i))) {
                            routes=routes.substring(0, i-1);
                        }
                    }
                }
            }

            userService.updateRoutes(routes, user.getId());
            model.setViewName("redirect:/home/home");
        }

        return model;
    }

    @RequestMapping(value={"/basicBilling"}, method= RequestMethod.GET)
    public ModelAndView basicBilling()
    {
        ModelAndView model = new ModelAndView();
        Route tempRoute = new Route();


        model.setViewName("/functions/basicBilling");


        return model;
    }
}