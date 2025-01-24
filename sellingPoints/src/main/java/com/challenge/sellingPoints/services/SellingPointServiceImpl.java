package com.challenge.sellingPoints.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.challenge.sellingPoints.entities.SellingPoint;
import com.challenge.sellingPoints.repositories.SellingPointRepository;
import com.challenge.sellingPoints.request.CreateSellingPointRequest;
import com.challenge.sellingPoints.response.SellingPointResponse;

@Service
public class SellingPointServiceImpl implements SellingPointService{

    final private SellingPointRepository sellingPointRepository;

    Logger logger = LoggerFactory.getLogger(SellingPointServiceImpl.class);


    public SellingPointServiceImpl(SellingPointRepository sellingPointRepository){
        this.sellingPointRepository=sellingPointRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SellingPoint> findAll() {
        return (List<SellingPoint>)sellingPointRepository.findAll();
    }

    /*@Override
    @Transactional(readOnly = true)
    public Optional<SellingPoint> findById(Long id) {
        return sellingPointRepository.findById(id);
    }*/

    @Override
    @Transactional(readOnly = true)
    public SellingPointResponse findById(Long id) {
        SellingPoint sellingPoint = sellingPointRepository.findById(id).get();
        return new SellingPointResponse(sellingPoint);
    }

    @Override
    public Optional<SellingPoint> findBySellingPointName(String sellingPointName) {
        return sellingPointRepository.findBySellingPointName(sellingPointName);
    }

    //request response
    @Override
    public SellingPointResponse createSellingPoint(CreateSellingPointRequest createSellingPointRequest) {        
        
        SellingPoint sellingPoint = new SellingPoint();
        sellingPoint.setSellingPointName(createSellingPointRequest.getSellingPointName());

        sellingPointRepository.save(sellingPoint);

        return new SellingPointResponse(sellingPoint);
    }
/*
    @Override
    public SellingPointResponse getById(Long id){
        SellingPoint sellingPoint = sellingPointRepository.findById(id).get();
        return new SellingPointResponse(sellingPoint);
    }
*/    

}
