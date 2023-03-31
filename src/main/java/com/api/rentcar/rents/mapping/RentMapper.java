package com.api.rentcar.rents.mapping;

import com.api.rentcar.rents.domain.model.entity.Rent;
import com.api.rentcar.rents.resource.CreateRentResource;
import com.api.rentcar.rents.resource.RentResource;
import com.api.rentcar.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class RentMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public RentResource toResource (Rent model){return mapper.map(model,RentResource.class);}
    public Page<RentResource> modelListToPage(List<Rent> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,RentResource.class),pageable,modelList.size());
    }
    public Rent toModel(CreateRentResource resource){return mapper.map(resource,Rent.class);}
}
