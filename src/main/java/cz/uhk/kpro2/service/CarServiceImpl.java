package cz.uhk.kpro2.service;

import cz.uhk.kpro2.model.Car;
import cz.uhk.kpro2.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public boolean createCar(Car car) {
        carRepository.save(car);
        return true;
    }

    @Override
    public boolean updateCar(Car car) {
        Optional<Car> carDB = carRepository.findById(car.getId());
        if(carDB.isPresent()){
            carRepository.save(car);
            return true;
        }
        return false;
    }

    @Override
    public Car getCarDetails(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public Car deleteCar(Long id) {
        Optional<Car> carDB = carRepository.findById(id);
        if(carDB.isPresent()){
            Car car = carDB.get();
            carRepository.delete(car);
            return car;
        }
        return null;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

}
