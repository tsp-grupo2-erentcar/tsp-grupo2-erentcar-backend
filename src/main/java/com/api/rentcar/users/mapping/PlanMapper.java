package com.api.rentcar.users.mapping;

import com.api.rentcar.shared.mapping.EnhancedModelMapper;
import com.api.rentcar.users.domain.model.entity.Plan;
import com.api.rentcar.users.resource.CreatePlanResource;
import com.api.rentcar.users.resource.PlanResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PlanMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public PlanResource toResource (Plan model){return mapper.map(model,PlanResource.class);}
    public Page<PlanResource> modelListToPage(List<Plan> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,PlanResource.class),pageable,modelList.size());
    }
    public Plan toModel(CreatePlanResource resource){return mapper.map(resource,Plan.class);}
}
