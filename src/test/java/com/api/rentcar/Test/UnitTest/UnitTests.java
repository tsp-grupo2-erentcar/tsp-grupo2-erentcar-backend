package com.api.rentcar.Test.UnitTest;

import com.api.rentcar.cars.domain.model.entity.Car;
import com.api.rentcar.cars.domain.persistence.CarRepository;
import com.api.rentcar.cars.service.CarServiceImpl;
import com.api.rentcar.rents.domain.model.entity.Rent;
import com.api.rentcar.rents.domain.model.entity.Reservation;
import com.api.rentcar.rents.domain.persistence.RentRepository;
import com.api.rentcar.rents.domain.persistence.ReservationRepository;
import com.api.rentcar.rents.service.RentServiceImpl;
import com.api.rentcar.rents.service.ReservationServiceImpl;
import com.api.rentcar.users.domain.model.entity.Client;
import com.api.rentcar.users.domain.persistence.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Validator;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UnitTests {
    @Mock
    private CarRepository carRepository;
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private RentRepository rentRepository;
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private CarServiceImpl carService;
    @InjectMocks
    private RentServiceImpl rentService;
    @InjectMocks
    private ReservationServiceImpl reservationService;
    @Mock
    private Validator validator;
    @Test
    void GetCarsNotRent() throws Exception{
        Car car1=new Car();
        Car car2=new Car();
        Car car3=new Car();
        when(carRepository.getCarsNotRents()).thenReturn(List.of(car1,car2,car3));
        Assertions.assertEquals(3,carService.getCarsNotRent().size());
    }
    @Test
    void RentedCar() throws Exception {

        Reservation reservation=new Reservation();
        Car car=new Car();
        car.setId(1L);
        car.setState(0);
        reservation.setCar(car);
        Rent rent=new Rent();

        when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car));
        when(reservationRepository.findById(1L)).thenReturn(java.util.Optional.of(reservation));
        when(carRepository.save(isA(Car.class))).thenReturn(car);
        when(rentRepository.save(isA(Rent.class))).thenReturn(rent);

        Assertions.assertNotNull(rentService.create(1L,rent));
    }
    @Test
    void ReservedCar() throws Exception{
        Reservation reservation3=new Reservation();
        Client client=new Client();
        Car car=new Car();
        car.setState(2);

        when(clientRepository.findById(1L)).thenReturn(java.util.Optional.of(client));
        when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car));
        when(reservationRepository.findById(5L)).thenReturn(java.util.Optional.of(reservation3));
        when(reservationRepository.save(isA(Reservation.class))).thenReturn(reservation3);

        Assertions.assertNull(reservationService.create(1L,1L,reservation3));
    }
}
