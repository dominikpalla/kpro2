package cz.uhk.kpro2.repository;

import cz.uhk.kpro2.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
