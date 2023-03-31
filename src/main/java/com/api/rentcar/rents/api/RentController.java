package com.api.rentcar.rents.api;

import com.api.rentcar.rents.domain.service.RentService;
import com.api.rentcar.rents.mapping.RentMapper;
import com.api.rentcar.rents.resource.CreateRentResource;
import com.api.rentcar.rents.resource.RentResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/rents")
@CrossOrigin
public class RentController {
    private final RentService rentService;
    private final RentMapper mapper;

    public RentController(RentService rentService, RentMapper mapper) {
        this.rentService = rentService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<RentResource> getAllRents(Pageable pageable){
        return mapper.modelListToPage(rentService.getAll(),pageable);
    }

    @GetMapping("{rentId}")
    public RentResource getRentById(@PathVariable Long rentId){
        return  mapper.toResource(rentService.getById(rentId));
    }

    @PostMapping("/reservation/{reservationId}")
    public RentResource createRent(@PathVariable("reservationId")Long reservationId,
                                   @Valid @RequestBody CreateRentResource request){
        return mapper.toResource(rentService.create(reservationId,mapper.toModel(request)));
    }
    @GetMapping("client/{clientId}")
    public Page<RentResource> getAllRentsByClientId(@PathVariable("clientId")Long clientId,
                                                    Pageable pageable){
        return mapper.modelListToPage(rentService.getRentsByClientId(clientId),pageable);
    }
    @GetMapping("owner/{ownerId}")
    public Page<RentResource> getAllRentsByOwnerId(@PathVariable("ownerId")Long ownerId,
                                                    Pageable pageable){
        return mapper.modelListToPage(rentService.getRentsByOwnerId(ownerId),pageable);
    }
}
