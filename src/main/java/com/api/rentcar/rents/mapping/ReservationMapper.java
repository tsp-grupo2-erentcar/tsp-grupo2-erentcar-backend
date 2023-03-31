package com.api.rentcar.rents.mapping;

import com.api.rentcar.rents.domain.model.entity.Reservation;
import com.api.rentcar.rents.resource.CreateReservationResource;
import com.api.rentcar.rents.resource.ReservationResource;
import com.api.rentcar.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ReservationMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ReservationResource toResource (Reservation model){
        return mapper.map(model,ReservationResource.class);
    }
    public Page<ReservationResource> modelListToPage(List<Reservation> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,ReservationResource.class),pageable,modelList.size());
    }
    public Reservation toModel(CreateReservationResource resource){return mapper.map(resource,Reservation.class);}
}
