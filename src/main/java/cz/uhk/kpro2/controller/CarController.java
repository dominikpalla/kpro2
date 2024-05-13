package cz.uhk.kpro2.controller;

import cz.uhk.kpro2.model.Car;
import cz.uhk.kpro2.service.CarService;
import cz.uhk.kpro2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

    private CarService carService;
    private UserService userService;

    @Autowired
    public CarController(CarService carService, UserService userService){
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping("/cars")
    public String listOfAllCars(Model model){
        model.addAttribute("cars", carService.getAllCars());
        return "list_cars";
    }

    @GetMapping("/cars/detail/{id}")
    public String carDetail(@PathVariable Long id,  Model model){
        model.addAttribute("car", carService.getCarDetails(id));
        return "detail_car";
    }

    @GetMapping("/cars/create")
    public String createCarForm(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("edit", false);
        model.addAttribute("users", userService.getAllUsers());
        return "edit_car";
    }

    @PostMapping("/cars/create")
    public String createCarSubmit(@Valid Car car, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("edit", false);
            return "edit_car";
        }
        carService.createCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/cars/edit/{id}")
    public String editCarForm(Model model, @PathVariable Long id){
        model.addAttribute("car", carService.getCarDetails(id));
        model.addAttribute("edit", true);
        model.addAttribute("users", userService.getAllUsers());
        return "edit_car";
    }

    @PostMapping("/cars/edit")
    public String editCarSubmit(@Valid Car car, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("edit", true);
            return "edit_car";
        }
        carService.updateCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return "redirect:/cars";
    }

}
