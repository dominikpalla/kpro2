package cz.uhk.kpro2.rest;

import cz.uhk.kpro2.model.Car;
import cz.uhk.kpro2.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarRestController {

    private CarService carService;

    @Autowired
    public CarRestController(CarService carService){
        this.carService = carService;
    }

    @GetMapping("/rest/getAllCars")
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }

    @GetMapping("/rest/getCarById")
    public Car getCarById(@RequestParam Long id){
        return carService.getCarDetails(id);
    }

    @PostMapping("/rest/addNewCar")
    public String getCarById(@RequestBody Car car){
        carService.createCar(car);
        return "OK";
    }
}
