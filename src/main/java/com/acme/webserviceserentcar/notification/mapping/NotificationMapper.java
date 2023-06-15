package com.acme.webserviceserentcar.notification.mapping;

import com.acme.webserviceserentcar.notification.domain.model.entity.Notification;
import com.acme.webserviceserentcar.notification.resource.CreateNotificationResource.CreateNotificationResource;
import com.acme.webserviceserentcar.notification.resource.NotificationResource;
import com.acme.webserviceserentcar.rent.resource.update.UpdateRentResource;
import com.acme.webserviceserentcar.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class NotificationMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    //Object Mapping
    public NotificationResource toResource(Notification model){
        return mapper.map(model, NotificationResource.class);
    }

    public Page<NotificationResource> modelListToPage
            (List<Notification> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, NotificationResource.class),
                pageable,
                modelList.size());
    }

    public Notification toModel(CreateNotificationResource resource){
        return mapper.map(resource, Notification.class);
    }

   // public Notification toModel(UpdateRentResource resource){
   //     return mapper.map(resource, Notification.class);
   // }
}
