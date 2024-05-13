package cz.uhk.kpro2;

import cz.uhk.kpro2.model.Car;
import cz.uhk.kpro2.repository.CarRepository;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarTest {

    @Test
    void testNajeto(){
        Car car = new Car();
        car.setNajetoKm(50000);

        assertEquals(50000 + 1000, car.pridejKm(1000), "Má být najeto jiný počet km");
    }

    @Test
    void delimNulou(){
        Car car = new Car();
        car.setNajetoKm(50000);

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
           car.vydelNulou(5, 0);
        });

        assertEquals("Nelze delit nulou", e.getMessage());
    }

    @Test
    void mockovani(){
        CarRepository carRepository = mock(CarRepository.class);
        when(carRepository.findById(5L)).thenReturn(Optional.of(new Car(5L)));

        Optional<Car> car = carRepository.findById(5L);

        assertEquals(5L, car.get().getId());
    }

}
