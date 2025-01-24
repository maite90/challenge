package com.challenge.cost.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.challenge.cost.entities.Cost;


public interface CostRepository extends CrudRepository<Cost, Long>{

    List<Cost> findByIdA (Long idA);
    List<Cost> findByIdB (Long idB);
    
}
