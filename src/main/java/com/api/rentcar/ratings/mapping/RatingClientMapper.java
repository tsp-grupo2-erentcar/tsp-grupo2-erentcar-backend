package com.api.rentcar.ratings.mapping;

import com.api.rentcar.ratings.domain.model.entity.RatingClient;
import com.api.rentcar.ratings.resource.CreateRatingClientResource;
import com.api.rentcar.ratings.resource.RatingClientResource;
import com.api.rentcar.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class RatingClientMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public RatingClientResource toResource(RatingClient model){return mapper.map(model,RatingClientResource.class);}
    public Page<RatingClientResource> modelListToPage(List<RatingClient> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,RatingClientResource.class),pageable,modelList.size());
    }
    public RatingClient toModel (CreateRatingClientResource resource){return mapper.map(resource,RatingClient.class);}
}
