package com.olympic.athletemanagementsystem.athlete.controller;

import com.olympic.athletemanagementsystem.athlete.entity.Athlete;
import com.olympic.athletemanagementsystem.athlete.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.olympic.athletemanagementsystem.common.constants.Routes.*;

@CrossOrigin
@RestController
@RequestMapping( value = API_ATHLETE)
public class AthleteController {
    public static final Logger log = Logger.getLogger(AthleteController.class.getName());
    @Autowired
    private AthleteService athleteService;

    @PostMapping
    public ResponseEntity<?> saveAthlete(@RequestBody Athlete athlete){
        try{
            return new ResponseEntity<Object>(athleteService.saveAthlete(athlete), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAthletes(@RequestParam int page,
                                            @RequestParam int limit){
        try{
            Pageable pageObj = PageRequest.of(page, limit);
            return new ResponseEntity<Object>(athleteService.getAllAthletes(pageObj), HttpStatus.OK);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(API_ATHLETE_BY_GENDER)
    public ResponseEntity<?> getAllAthletesByGenderId(@RequestParam int page,
                                            @RequestParam int limit, @PathVariable Long genderId){
        try{
            Pageable pageObj = PageRequest.of(page, limit);
            return new ResponseEntity<Object>(athleteService.getAllAthletesByGenderId(pageObj, genderId), HttpStatus.OK);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(API_ATHLETE_BY_EVENT_ENABLED)
    public ResponseEntity<?> getAllAthletesByGenderIdAndEventEnabled(@RequestParam int page,
                                                                     @RequestParam int limit,
                                                                     @RequestParam String sortBy,
                                                                     @RequestParam String orderBy,
                                                                     @RequestParam boolean enabled){
        try{
            Pageable pageObj;

            if (orderBy.equals("desc"))
                pageObj = PageRequest.of(page, limit, Sort.by(sortBy).descending());
            else
                pageObj = PageRequest.of(page, limit, Sort.by(sortBy).ascending());

            return new ResponseEntity<Object>(athleteService.getAllAthletesByEventEnabled(pageObj, enabled), HttpStatus.OK);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
