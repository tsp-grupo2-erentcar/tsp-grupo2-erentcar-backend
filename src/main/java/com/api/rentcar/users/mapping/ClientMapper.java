package com.api.rentcar.users.mapping;

import com.api.rentcar.shared.mapping.EnhancedModelMapper;
import com.api.rentcar.users.domain.model.entity.Client;
import com.api.rentcar.users.resource.ClientResource;
import com.api.rentcar.users.resource.CreateClientResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ClientMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ClientResource toResource(Client model){return mapper.map(model,ClientResource.class);}
    public Page<ClientResource> modelListToPage(List<Client> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,ClientResource.class),pageable,modelList.size());
    }
    public Client toModel (CreateClientResource resource){return mapper.map(resource,Client.class);}
}