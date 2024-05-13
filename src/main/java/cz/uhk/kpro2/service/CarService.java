package cz.uhk.kpro2.service;

import cz.uhk.kpro2.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    boolean createCar(Car car);
    boolean updateCar(Car car);
    Car getCarDetails(Long id);
    Car deleteCar(Long id);
    List<Car> getAllCars();
}
