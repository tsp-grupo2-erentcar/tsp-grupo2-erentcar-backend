package com.api.rentcar.shared.mapping;

import com.api.rentcar.cars.mapping.BrandMapper;
import com.api.rentcar.cars.mapping.CarMapper;
import com.api.rentcar.cars.mapping.FeatureMapper;
import com.api.rentcar.ratings.mapping.RatingCarMapper;
import com.api.rentcar.ratings.mapping.RatingClientMapper;
import com.api.rentcar.ratings.mapping.RatingOwnerMapper;
import com.api.rentcar.rents.mapping.RentMapper;
import com.api.rentcar.rents.mapping.ReservationMapper;
import com.api.rentcar.users.mapping.ClientMapper;
import com.api.rentcar.users.mapping.FavoriteMapper;
import com.api.rentcar.users.mapping.OwnerMapper;
import com.api.rentcar.users.mapping.PlanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("rentCarAppMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }
    @Bean
    public BrandMapper brandMapper(){return new BrandMapper();}
    @Bean
    public OwnerMapper ownerMapper(){return new OwnerMapper();}
    @Bean
    public CarMapper carMapper(){return new CarMapper();}
    @Bean
    public FeatureMapper featureMapper(){return new FeatureMapper();}
    @Bean
    public ClientMapper clientMapper(){return new ClientMapper();}
    @Bean
    public PlanMapper planMapper(){return new PlanMapper();}
    @Bean
    public FavoriteMapper favoriteMapper(){return new FavoriteMapper();}
    @Bean
    public ReservationMapper reservationMapper(){return new ReservationMapper();}
    @Bean
    public RatingCarMapper ratingCarMapper(){return new RatingCarMapper();}
    @Bean
    public RatingClientMapper ratingClientMapper(){return new RatingClientMapper();}
    @Bean
    public RatingOwnerMapper ratingOwnerMapper(){return new RatingOwnerMapper();}
    @Bean
    public RentMapper rentMapper(){return new RentMapper();}
}