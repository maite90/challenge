package com.challenge.sellingPoints.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.challenge.sellingPoints.entities.SellingPoint;


public interface SellingPointRepository extends CrudRepository<SellingPoint, Long>{
    
    Optional<SellingPoint> findBySellingPointName (String sellingPointName);

}
