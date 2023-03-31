package com.api.rentcar.cars.mapping;

import com.api.rentcar.cars.domain.model.entity.Brand;
import com.api.rentcar.cars.resource.BrandResource;
import com.api.rentcar.cars.resource.CreateBrandResource;
import com.api.rentcar.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class BrandMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public BrandResource toResource (Brand model){return mapper.map(model,BrandResource.class);}
    public Page<BrandResource> modelListToPage(List<Brand> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,BrandResource.class),pageable,modelList.size());
    }
    public Brand toModel(CreateBrandResource resource){return mapper.map(resource,Brand.class);}
}