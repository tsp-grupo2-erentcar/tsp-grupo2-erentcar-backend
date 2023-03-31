package com.api.rentcar.ratings.api;

import com.api.rentcar.ratings.domain.service.RatingClientService;
import com.api.rentcar.ratings.mapping.RatingClientMapper;
import com.api.rentcar.ratings.resource.CreateRatingCarResource;
import com.api.rentcar.ratings.resource.CreateRatingClientResource;
import com.api.rentcar.ratings.resource.RatingClientResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/ratingClients")
@CrossOrigin
public class RatingClientController {
    private final RatingClientService ratingClientService;
    private final RatingClientMapper mapper;

    public RatingClientController(RatingClientService ratingClientService, RatingClientMapper mapper) {
        this.ratingClientService = ratingClientService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<RatingClientResource> getAllRatingClient(Pageable pageable){
        return mapper.modelListToPage(ratingClientService.getAll(),pageable);
    }

    @GetMapping("{ratingClientId}")
    public RatingClientResource getRatingClientById(@PathVariable Long ratingClientId){
        return  mapper.toResource(ratingClientService.getById(ratingClientId));
    }
    @PostMapping("client/{clientId}/owner/{ownerId}")
    public RatingClientResource createRatingClient(@PathVariable("clientId")Long clientId,
                                             @PathVariable("ownerId")Long ownerId,
                                             @Valid @RequestBody CreateRatingClientResource request){
        return mapper.toResource(ratingClientService.create(clientId,ownerId, mapper.toModel(request)));
    }
    @DeleteMapping("{ratingClientId}")
    public ResponseEntity<?> deleteRatingClient(@PathVariable Long ratingClientId) {
        return ratingClientService.delete(ratingClientId);
    }
}
