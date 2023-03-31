package com.api.rentcar.cars.mapping;

import com.api.rentcar.cars.domain.model.entity.Car;
import com.api.rentcar.cars.resource.CarResource;
import com.api.rentcar.cars.resource.CreateCarResource;
import com.api.rentcar.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CarMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public CarResource toResource(Car model){return mapper.map(model,CarResource.class);}
    public Page<CarResource> modelListToPage(List<Car> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,CarResource.class),pageable,modelList.size());
    }
    public Car toModel (CreateCarResource resource){return mapper.map(resource,Car.class);}
}