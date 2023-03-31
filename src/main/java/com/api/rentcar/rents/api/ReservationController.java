package com.api.rentcar.rents.api;

import com.api.rentcar.rents.domain.service.ReservationService;
import com.api.rentcar.rents.mapping.RentMapper;
import com.api.rentcar.rents.mapping.ReservationMapper;
import com.api.rentcar.rents.resource.CreateRentResource;
import com.api.rentcar.rents.resource.CreateReservationResource;
import com.api.rentcar.rents.resource.RentResource;
import com.api.rentcar.rents.resource.ReservationResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/reservations")
@CrossOrigin
public class ReservationController {
    private final ReservationService reservationService;
    private final RentMapper rentMapper;
    private final ReservationMapper mapper;

    public ReservationController(ReservationService reservationService, RentMapper rentMapper, ReservationMapper mapper) {
        this.reservationService = reservationService;
        this.rentMapper = rentMapper;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<ReservationResource> getAllReservations(Pageable pageable){
        return mapper.modelListToPage(reservationService.getAll(),pageable);
    }

    @GetMapping("{reservationId}")
    public ReservationResource getReservationById(@PathVariable Long reservationId){
        return  mapper.toResource(reservationService.getById(reservationId));
    }

    @PostMapping("client/{clientId}/car/{carId}")
    public ReservationResource createReservation(@PathVariable("clientId")Long clientId,
                                                 @PathVariable("carId")Long carId,
                                                 @Valid @RequestBody CreateReservationResource request){
        return mapper.toResource(reservationService.create(clientId,carId,mapper.toModel(request)));
    }

    @DeleteMapping("{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId) {
        return reservationService.delete(reservationId);
    }
    @PutMapping("{reservationId}/setStatus/{status}")
    public ReservationResource updateStatus(@PathVariable("reservationId")Long reservationId,
                                            @PathVariable("status")int status){
        return mapper.toResource(reservationService.updateStatus(reservationId,status));
    }
    @PutMapping("cancel/{reservationId}")
    public ReservationResource cancelReservation(@PathVariable("reservationId")Long reservationId)
    {
        return mapper.toResource(reservationService.cancel(reservationId));
    }
    @PutMapping("cancelPay/{reservationId}")
    public ReservationResource cancelPay(@PathVariable("reservationId")Long reservationId)
    {
        return mapper.toResource(reservationService.cancelPay(reservationId));
    }
    @GetMapping("owner/{ownerId}")
    public Page<ReservationResource> getReservationsByOwner(Pageable pageable,
                                                         @PathVariable("ownerId") Long ownerId){
        return mapper.modelListToPage(reservationService.getReservationsByOwner(ownerId),pageable);
    }
    @PostMapping("{reservationId}/pay")
    public RentResource pay(@PathVariable("reservationId")Long reservationId,
                                          @Valid @RequestBody CreateRentResource request){
        return rentMapper.toResource(reservationService.pay(reservationId,rentMapper.toModel(request)));
    }
    @GetMapping("client/{clientId}")
    public Page<ReservationResource> getReservationsByClient(Pageable pageable,
                                                            @PathVariable("clientId") Long clientId){
        return mapper.modelListToPage(reservationService.getReservationsByClient(clientId),pageable);
    }
}
