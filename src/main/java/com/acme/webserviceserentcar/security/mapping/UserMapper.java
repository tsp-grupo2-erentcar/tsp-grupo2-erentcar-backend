package com.acme.webserviceserentcar.security.mapping;

import com.acme.webserviceserentcar.security.domain.model.entity.Role;
import com.acme.webserviceserentcar.security.domain.model.entity.User;
import com.acme.webserviceserentcar.security.resource.UserResource;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.acme.webserviceserentcar.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    Converter<Role, String> roleToString = new AbstractConverter<>() {
        @Override
        protected String convert(Role role) {
            return role == null ? null : role.getName().name();
        }
    };

    // Object Mapping
    public UserResource toResource(User model) {
        mapper.addConverter(roleToString);
        return mapper.map(model, UserResource.class);
    }

    public Page<UserResource> modelListToPage(List<User> modelList, Pageable pageable) {
        mapper.addConverter(roleToString);
        return new PageImpl<>(mapper.mapList(modelList, UserResource.class), pageable, modelList.size());
    }



}
