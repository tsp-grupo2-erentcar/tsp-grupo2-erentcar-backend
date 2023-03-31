package com.api.rentcar.users.mapping;

import com.api.rentcar.shared.mapping.EnhancedModelMapper;
import com.api.rentcar.users.domain.model.entity.Owner;
import com.api.rentcar.users.resource.CreateOwnerResource;
import com.api.rentcar.users.resource.OwnerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class OwnerMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public OwnerResource toResource (Owner model){return mapper.map(model,OwnerResource.class);}
    public Page<OwnerResource> modelListToPage(List<Owner> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,OwnerResource.class),pageable,modelList.size());
    }
    public Owner toModel(CreateOwnerResource resource){return mapper.map(resource,Owner.class);}
}
