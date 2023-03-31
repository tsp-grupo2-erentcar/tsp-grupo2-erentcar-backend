package com.api.rentcar.users.mapping;

import com.api.rentcar.shared.mapping.EnhancedModelMapper;
import com.api.rentcar.users.domain.model.entity.Favorite;
import com.api.rentcar.users.resource.FavoriteResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class FavoriteMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public FavoriteResource toResource (Favorite model){return mapper.map(model,FavoriteResource.class);}
    public Page<FavoriteResource> modelListToPage(List<Favorite> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,FavoriteResource.class),pageable,modelList.size());
    }
}
