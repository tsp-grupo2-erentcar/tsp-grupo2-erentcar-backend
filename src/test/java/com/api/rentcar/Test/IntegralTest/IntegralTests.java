package com.api.rentcar.Test.IntegralTest;

import com.api.rentcar.cars.domain.model.entity.Brand;
import com.api.rentcar.cars.domain.model.entity.Car;
import com.api.rentcar.cars.domain.persistence.BrandRepository;
import com.api.rentcar.cars.domain.persistence.CarRepository;
import com.api.rentcar.cars.service.CarServiceImpl;
import com.api.rentcar.rents.domain.model.entity.Rent;
import com.api.rentcar.rents.domain.model.entity.Reservation;
import com.api.rentcar.rents.domain.persistence.RentRepository;
import com.api.rentcar.rents.domain.persistence.ReservationRepository;
import com.api.rentcar.rents.service.RentServiceImpl;
import com.api.rentcar.rents.service.ReservationServiceImpl;
import com.api.rentcar.users.domain.model.entity.Client;
import com.api.rentcar.users.domain.model.entity.Owner;
import com.api.rentcar.users.domain.model.entity.Plan;
import com.api.rentcar.users.domain.persistence.ClientRepository;
import com.api.rentcar.users.domain.persistence.OwnerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Validator;
import static org.mockito.ArgumentMatchers.isA;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@SpringBootTest
public class IntegralTests {
    @Mock
    private RentRepository rentRepository;
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private BrandRepository brandRepository;
    @InjectMocks
    private ReservationServiceImpl reservationService;
    @InjectMocks
    private CarServiceImpl carService;
    @Mock
    private Validator validator;
    @Test
    void MakeReservationWithRating() throws Exception{
        Client client=new Client();
        client.setRating(2.0);
        /*RATING*/
        Car car =new Car();
        car.setDayCost(0.0);
        car.setState(0);

        Reservation reservationMock=new Reservation();

        Rent rent1=new Rent();
        Rent rent2=new Rent();
        Rent rent3=new Rent();
        Rent rent4=new Rent();
        reservationMock.setReserveDate(new Date());
        reservationMock.setReturnDate(new Date());


        when(clientRepository.findById(5L)).thenReturn(java.util.Optional.of(client));
        when(carRepository.findById(5L)).thenReturn(java.util.Optional.of(car));
        when(carRepository.save(isA(Car.class))).thenReturn(car);
        when(rentRepository.getRentsByClientId(5L)).thenReturn(List.of(rent1,rent2,rent3));
        when(reservationRepository.save(isA(Reservation.class))).thenReturn(reservationMock);

        Assertions.assertNotNull(reservationService.create(5L,5L,reservationMock));
    }


    @Test
    void PostCar() throws Exception {
        Plan plan=new Plan();
        plan.setName("Free");
        /*PLANS*/
        Car car1=new Car();
        Car car2=new Car();
        Car car3=new Car();
        Car carMock=new Car();

        Brand brand=new Brand();

        Owner owner=new Owner();
        owner.setPlan(plan);
        owner.setCars(List.of(car1));

        when(brandRepository.findById(1L)).thenReturn(java.util.Optional.of(brand));
        when(ownerRepository.findById(1L)).thenReturn(java.util.Optional.of(owner));
        when(carRepository.save(isA(Car.class))).thenReturn(carMock);
        Assertions.assertNotNull(carService.create(1L,1L,carMock));
    }
}
