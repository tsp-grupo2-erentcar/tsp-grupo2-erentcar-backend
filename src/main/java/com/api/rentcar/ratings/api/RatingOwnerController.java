package com.api.rentcar.ratings.api;

import com.api.rentcar.ratings.domain.service.RatingOwnerService;
import com.api.rentcar.ratings.mapping.RatingOwnerMapper;
import com.api.rentcar.ratings.resource.CreateRatingOwnerResource;
import com.api.rentcar.ratings.resource.RatingOwnerResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/ratingOwners")
@CrossOrigin
public class RatingOwnerController {
    private final RatingOwnerService ratingOwnerService;
    private final RatingOwnerMapper mapper;

    public RatingOwnerController(RatingOwnerService ratingOwnerService, RatingOwnerMapper mapper) {
        this.ratingOwnerService = ratingOwnerService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<RatingOwnerResource> getAllRatingOwner(Pageable pageable){
        return mapper.modelListToPage(ratingOwnerService.getAll(),pageable);
    }

    @GetMapping("{ratingOwnerId}")
    public RatingOwnerResource getRatingOwnerById(@PathVariable Long ratingOwnerId){
        return  mapper.toResource(ratingOwnerService.getById(ratingOwnerId));
    }
    @PostMapping("owner/{ownerId}/client/{clientId}")
    public RatingOwnerResource createRatingOwner(@PathVariable("ownerId")Long ownerId,
                                                 @PathVariable("clientId")Long clientId,
                                                   @Valid @RequestBody CreateRatingOwnerResource request){
        return mapper.toResource(ratingOwnerService.create(ownerId,clientId, mapper.toModel(request)));
    }
    @DeleteMapping("{ratingOwnerId}")
    public ResponseEntity<?> deleteRatingOwner(@PathVariable Long ratingOwnerId) {
        return ratingOwnerService.delete(ratingOwnerId);
    }
}
