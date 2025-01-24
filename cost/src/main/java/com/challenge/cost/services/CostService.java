package com.challenge.cost.services;

import java.util.List;
import java.util.Optional;

import com.challenge.cost.entities.Cost;

public interface CostService {

    public Optional<Cost> findById(Long id);//Costs by id A

    public List<Cost> findAll(); //Costs list

    public List<Cost> findByIdA (Long idA);

    public List<Cost> findByIdB (Long idB);

}
