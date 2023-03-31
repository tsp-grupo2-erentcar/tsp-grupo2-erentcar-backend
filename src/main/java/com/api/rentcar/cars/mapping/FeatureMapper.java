package com.api.rentcar.cars.mapping;

import com.api.rentcar.cars.domain.model.entity.Feature;
import com.api.rentcar.cars.resource.CreateFeatureResource;
import com.api.rentcar.cars.resource.FeatureResource;
import com.api.rentcar.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class FeatureMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public FeatureResource toResource (Feature model){return mapper.map(model,FeatureResource.class);}
    public Page<FeatureResource> modelListToPage(List<Feature> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,FeatureResource.class),pageable,modelList.size());
    }
    public Feature toModel(CreateFeatureResource resource){return mapper.map(resource,Feature.class);}
}
