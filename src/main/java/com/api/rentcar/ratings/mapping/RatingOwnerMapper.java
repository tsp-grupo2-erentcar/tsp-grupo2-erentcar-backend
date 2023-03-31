package com.api.rentcar.ratings.mapping;

import com.api.rentcar.ratings.domain.model.entity.RatingOwner;
import com.api.rentcar.ratings.resource.CreateRatingOwnerResource;
import com.api.rentcar.ratings.resource.RatingOwnerResource;
import com.api.rentcar.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class RatingOwnerMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public RatingOwnerResource toResource(RatingOwner model){
        return mapper.map(model,RatingOwnerResource.class);
    }
    public Page<RatingOwnerResource> modelListToPage(List<RatingOwner> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,RatingOwnerResource.class),pageable,modelList.size());
    }
    public RatingOwner toModel (CreateRatingOwnerResource resource){
        return mapper.map(resource,RatingOwner.class);
    }
}