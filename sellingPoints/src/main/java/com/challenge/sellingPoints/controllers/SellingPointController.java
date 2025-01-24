package com.challenge.sellingPoints.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.sellingPoints.entities.SellingPoint;
import com.challenge.sellingPoints.repositories.SellingPointRepository;
import com.challenge.sellingPoints.request.CreateSellingPointRequest;
import com.challenge.sellingPoints.response.SellingPointResponse;
import com.challenge.sellingPoints.services.SellingPointService;
//import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping ("/api")
public class SellingPointController {

    final private SellingPointService sellingPointService;

    @Autowired
    private SellingPointRepository sellingPointRepository;

    public SellingPointController(SellingPointService sellingPointService){
        this.sellingPointService=sellingPointService;
    }

    //get all existent sellingpoints
    @GetMapping("/sellingpoints")
    public List<SellingPoint> findAll() {
        return this.sellingPointService.findAll();
    }

    //get sellpoint byid
    @GetMapping("/sellingpoints/{id}")
    public SellingPointResponse findById(@PathVariable Long id) {
        return sellingPointService.findById(id);
    }   

    //Deletes a Selling Point
    @DeleteMapping("/sellingpoints/{id}")
    public ResponseEntity<Object> deleteSellingPoint(@PathVariable Long id) {
        sellingPointRepository.deleteById(id);
        return new ResponseEntity<>("Selling Point successfully deleted", HttpStatus.OK);
    }    

    //Creates a brand new Selling Point
    @PostMapping("/newsellingpoint")
    public ResponseEntity<Object> createSellingPoint(@RequestBody CreateSellingPointRequest createSellingPointRequest){
        //valida que el nombre no sea nulo
        if (createSellingPointRequest.getSellingPointName().isEmpty()){
            return new ResponseEntity<>("Missing data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //valida que no sea igual a uno ya existente
        if (sellingPointRepository.findBySellingPointName(createSellingPointRequest.getSellingPointName()).isPresent()){
            return new ResponseEntity<>("Selling Point Name already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //IF everything goes well, guarda un nuevo punto
        sellingPointService.createSellingPoint(createSellingPointRequest);
        //IF everything still goes well, retorno un CREATED
        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDateTime.now().toString());
        response.put("status", "OK");
        response.put("message", "New Selling Point successfully created");
        response.put("sellingPoint", createSellingPointRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //updates a created sellingPoint
    @PutMapping("/sellingpoints/{id}")
    public ResponseEntity<SellingPoint> updateSellingPoint(@PathVariable long id,@RequestBody SellingPoint sellingPointDetails) {
        SellingPoint sellingPointUpdate=sellingPointRepository.findById(id).get();
        if (!sellingPointUpdate.getSellingPointName().isEmpty()){
            sellingPointUpdate.setSellingPointName(sellingPointDetails.getSellingPointName());
            sellingPointRepository.save(sellingPointUpdate);
            return ResponseEntity.ok(sellingPointUpdate);
        }
        return ResponseEntity.notFound().build();
    }
    


}
