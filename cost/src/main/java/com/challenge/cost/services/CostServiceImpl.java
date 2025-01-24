package com.challenge.cost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.cost.entities.Cost;
import com.challenge.cost.repositories.CostRepository;


@Service
public class CostServiceImpl implements CostService{

    final private CostRepository costRepository;

    public CostServiceImpl(CostRepository costRepository){
        this.costRepository=costRepository;
    }

/*    final private Map<Integer, Cost> cacheCosts = new HashMap<>();

    public CostServiceImpl(){
        cacheCosts.put(1, new Cost(1L, 1L, 2L, 2));
        cacheCosts.put(2, new Cost(2l, 1L, 3L, 3));
        cacheCosts.put(3, new Cost(3L, 2L, 3L, 5));
        cacheCosts.put(4, new Cost(4L, 2L, 4L, 10));
        cacheCosts.put(5, new Cost(5L, 1L, 4L, 11));
        cacheCosts.put(6, new Cost(6L, 4L, 5L, 5));
        cacheCosts.put(7, new Cost(7L, 2L, 5L, 14));
        cacheCosts.put(8, new Cost(8L, 6L, 7L, 32));
        cacheCosts.put(9, new Cost(9L, 8L, 9L, 11));
        cacheCosts.put(10, new Cost(10L, 10L, 7L, 5));
        cacheCosts.put(11, new Cost(11L, 3L, 8L, 10));
        cacheCosts.put(12, new Cost(21L, 5L, 8L, 30));
        cacheCosts.put(13, new Cost(13L, 10L, 5L, 5));
        cacheCosts.put(14, new Cost(14L, 4L, 6L, 6));
    }
*/ 

    @Override
    @Transactional(readOnly = true)
    public List<Cost> findAll(){
        return (List<Cost>) costRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cost> findById(Long id) {
        return costRepository.findById(id);
    }


    @Override
    public List<Cost> findByIdA(Long idA) {
        return costRepository.findByIdA(idA);    
    }


    @Override
    public List<Cost> findByIdB(Long idB) {
        return costRepository.findByIdB(idB);
    }

}