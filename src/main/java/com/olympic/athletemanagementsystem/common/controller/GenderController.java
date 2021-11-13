package com.olympic.athletemanagementsystem.common.controller;

import com.olympic.athletemanagementsystem.common.entity.Gender;
import com.olympic.athletemanagementsystem.common.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.olympic.athletemanagementsystem.common.constants.Routes.API_GENDER;
import static com.olympic.athletemanagementsystem.common.constants.Routes.API_GENDER_BY_ID;

@CrossOrigin
@RestController
@RequestMapping( value = API_GENDER)
public class GenderController {
    public static final Logger log = Logger.getLogger(GenderController.class.getName());
    @Autowired
    private GenderService genderService;

    @PostMapping
    public ResponseEntity<?> saveGender(@RequestBody Gender gender){
        try{
            return new ResponseEntity<Object>(genderService.saveGender(gender), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllGenders(){
        try{
            return new ResponseEntity<Object>(genderService.getAllGenders(), HttpStatus.OK);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(API_GENDER_BY_ID)
    public ResponseEntity<?> getGenderById(@PathVariable Long genderId){
        try{
            return new ResponseEntity<Object>(genderService.getGenderById(genderId), HttpStatus.OK);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
