package com.challenge.sellingPoints.services;

import java.util.List;
import java.util.Optional;

import com.challenge.sellingPoints.entities.SellingPoint;
import com.challenge.sellingPoints.request.CreateSellingPointRequest;
import com.challenge.sellingPoints.response.SellingPointResponse;

public interface SellingPointService {

    public List<SellingPoint> findAll(); //SellingPoints list

    public SellingPointResponse findById(Long id);//SellingPoints by id

    Optional<SellingPoint> findBySellingPointName (String sellingPointName); //SellingPoints by Name
    
    public SellingPointResponse createSellingPoint(CreateSellingPointRequest createSellingPointRequest);

    //public SellingPointResponse getById(Long id);
}
