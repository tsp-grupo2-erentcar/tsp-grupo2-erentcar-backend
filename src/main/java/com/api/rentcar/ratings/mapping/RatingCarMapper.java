package com.api.rentcar.ratings.mapping;

import com.api.rentcar.ratings.domain.model.entity.RatingCar;
import com.api.rentcar.ratings.resource.CreateRatingCarResource;
import com.api.rentcar.ratings.resource.RatingCarResource;
import com.api.rentcar.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class RatingCarMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public RatingCarResource toResource(RatingCar model){return mapper.map(model,RatingCarResource.class);}
    public Page<RatingCarResource> modelListToPage(List<RatingCar> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,RatingCarResource.class),pageable,modelList.size());
    }
    public RatingCar toModel (CreateRatingCarResource resource){return mapper.map(resource,RatingCar.class);}
}
