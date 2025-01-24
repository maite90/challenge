package com.challenge.cost.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.cost.entities.Cost;
import com.challenge.cost.repositories.CostRepository;
import com.challenge.cost.services.CostService;

@RestController
@RequestMapping ("/api")
public class CostController {

    private static final Logger logger = Logger.getLogger(CostController.class.getName());

    @Autowired
    private CostRepository costRepository;

    final private CostService costService;

    public CostController(CostService costService){
        this.costService=costService;
    }

    //All Costs options
    @GetMapping("/costs")
    public List<Cost> findAll() {
        return this.costService.findAll();
    }

    //find by destination ID
    @GetMapping("/costsB/{idB}")
    public List<Cost> getByIdB (@PathVariable Long idB) {
        //logger.log(Level.INFO, "Cantidad de IDB enxistentes: " + costService.findByIdB(idB).size());
        return this.costService.findByIdB(idB);
    }

    //Creates a brand new Cost option
    @PostMapping("/newcost")
    public ResponseEntity<Object> createCost(@RequestBody Cost cost){
        //valida que el id origen o destino no sea nulo
        if (cost.getIdA() == null || cost.getIdB() == null ){
            return new ResponseEntity<>("Missing data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //valida un costo valido
        if ((cost.getIdA() == cost.getIdB()) && (cost.getCost() != 0)){
            return new ResponseEntity<>("The cost must be = $0", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if((cost.getIdA() != cost.getIdB()) && (cost.getCost() <= 0)){
            return new ResponseEntity<>("The cost must be more than $0", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<Cost>list = costRepository.findByIdA(cost.getIdA());
        //valida que ya no exista la combinacion
        if(list.size() > 0){
            logger.log(Level.INFO, "Cantidad de IDA enxistentes: " + costRepository.findByIdA(cost.getIdA()).size());
            for(int i = 0; i < list.size(); i++){
                Cost costUpdate=costRepository.findByIdA(cost.getIdA()).get(i);
                //logs temporales para ver por que falla - BORRAR
                String idOrigen=String.valueOf(costUpdate.getIdA());
                String idDestino=String.valueOf(costUpdate.getIdB());
                logger.log(Level.INFO, "IDA: " + idOrigen + " IDB: " + idDestino);
                String id2Origen=String.valueOf(cost.getIdA());
                String id2Destino=String.valueOf(cost.getIdB());
                //Hasta aca
                logger.log(Level.INFO, "IDA2: " + id2Origen + " IDB2: " + id2Destino);             
                if ((costUpdate.getIdA().equals(cost.getIdA()) && costUpdate.getIdB().equals(cost.getIdB()))){
                    return new ResponseEntity<>("The combination cost allready exists", HttpStatus.INTERNAL_SERVER_ERROR);
                }            
            }  
        }          

        List<Cost>listB = costRepository.findByIdA(cost.getIdB());
        //logger.log(Level.INFO, "Cantidad de IDB enxistentes: " + listB.size());
        //valida que ya no exista la combinacion
        if(listB.size() > 0){
            for(int j = 0; j < listB.size(); j++){
                Cost costUpdate=costRepository.findByIdA(cost.getIdB()).get(j);
                /*//logs temporales para ver si trae los valores OK
                String idOrigen=String.valueOf(costUpdate.getIdA());
                String idDestino=String.valueOf(costUpdate.getIdB());
                //logger.log(Level.INFO, "IDA: " + idOrigen + " IDB: " + idDestino);
                String id2Origen=String.valueOf(cost.getIdA());
                String id2Destino=String.valueOf(cost.getIdB());
                //Hasta aca */
                //logger.log(Level.INFO, "IDA2: " + id2Origen + " IDB2: " + id2Destino);
                if ((costUpdate.getIdA().equals(cost.getIdB())) && (costUpdate.getIdB().equals(cost.getIdA()))){
                    return new ResponseEntity<>("The combination cost allready exists inverse", HttpStatus.INTERNAL_SERVER_ERROR);
                }          
            }  
        }       

        //IF everything goes well, guarda un nuevo costo
        costRepository.save(new Cost(cost.getIdA(), cost.getIdB(), cost.getCost()));
        //IF everything still goes well, retorno un CREATED
        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDateTime.now().toString());
        response.put("status", "OK");
        response.put("message", "New Selling Point successfully created");
        response.put("cost", cost);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    //Deletes cost
    @DeleteMapping("/cost/{id}")
    public ResponseEntity<Object> deleteCost(@PathVariable Long id) {
        costRepository.deleteById(id);
        return new ResponseEntity<>("Cost successfully deleted", HttpStatus.OK);
    }
    
    //updates cost
    @PutMapping("/cost/{id}")
    public ResponseEntity<Cost> updateCost(@PathVariable long id,@RequestBody Cost costDetails) {
        Cost costUpdate=costRepository.findById(id).get();
        if (costUpdate.getIdA() > 0 && costUpdate.getIdB() > 0){
            costUpdate.setIdA(costDetails.getIdA());
            costUpdate.setIdB(costDetails.getIdB());
            costUpdate.setCost(costDetails.getCost());

            costRepository.save(costUpdate);
            return ResponseEntity.ok(costUpdate);
        }
        return ResponseEntity.notFound().build();
    }

    //how much it costs going from pint A to point x
    @GetMapping("/costsA/{idA}")
    public List<String> getByIdA (@PathVariable Long idA) {
        //logger.log(Level.INFO, "Cantidad de ID origen enxistentes: " + costService.findByIdA(idA).size());
        List<String> costsForOriginSellpoint = new ArrayList<>();
        for (int i = 0; i < costService.findByIdA(idA).size(); i++){
            costService.findByIdA(idA).get(i);
            costsForOriginSellpoint.add("From origin: " + String.valueOf(costService.findByIdA(idA).get(i).getIdA()) + ", to:  " + String.valueOf(costService.findByIdA(idA).get(i).getIdB()) + " the shipping cost is: $" + String.valueOf(costService.findByIdA(idA).get(i).getCost()) + ". ");
        }        
        return costsForOriginSellpoint;
    }

    //PROBAR DESDE ACA!! 
    //finding the cheapest way from A to B
    @GetMapping("/cheapest/{idA}/{idB}")
    public String getCheapest(@PathVariable Long idA, @PathVariable Long idB) {
        
        //si existe directo     
        List<Cost> directList = new ArrayList<>();           
        int cheapestCost = 100;
        for(int i = 0; i < costRepository.findByIdA(idA).size(); i++){
            if(costRepository.findByIdA(idA).get(i).getIdB().equals(idB)){
                if (cheapestCost > costRepository.findByIdA(idA).get(i).getCost()){
                    cheapestCost = costRepository.findByIdA(idA).get(i).getCost();
                    directList.add(costRepository.findByIdA(idA).get(i));
                    //logger.log(Level.INFO,"Cargo costo Directo" + i);
                }
            }/*else{
                logger.log(Level.INFO,"else, no hay directo + " + i );
            }*/
        }

        //sino
        if (directList.isEmpty()){    
            //logger.log(Level.INFO,"No cargo directo, va por combinado. cheapest= $" + cheapestCost);       
            for(int j = 0; j < costRepository.findByIdA(idA).size(); j++){
                //logger.log(Level.INFO," j = " + j);       
                for (int k = 0; k < costRepository.findByIdB(idB).size(); k++){
                    //logger.log(Level.INFO," k = " + k);
                    if (costRepository.findByIdA(idA).get(j).getIdB().equals(costRepository.findByIdB(idB).get(k).getIdA())){
                        //logger.log(Level.INFO,"Origen: " + costRepository.findByIdA(idA).get(j).getIdA() + " - Destino del origen: " + costRepository.findByIdA(idA).get(j).getIdB());
                        //logger.log(Level.INFO,"Destino: " + costRepository.findByIdB(idB).get(k).getIdB() + " - Origen del destino: " + costRepository.findByIdB(idB).get(k).getIdA());
                        int temporalCost = costRepository.findByIdA(idA).get(j).getCost() + costRepository.findByIdB(idB).get(k).getCost();
                        if (cheapestCost > temporalCost) {
                            cheapestCost = temporalCost;
                        } 
                    }
                }
            }
        }

    return "The cheapest cost from origin: " + idA + " to destination: " + idB + " is: $" + cheapestCost;
    }
}


