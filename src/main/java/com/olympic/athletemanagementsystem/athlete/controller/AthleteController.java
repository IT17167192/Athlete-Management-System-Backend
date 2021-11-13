package com.olympic.athletemanagementsystem.athlete.controller;

import com.olympic.athletemanagementsystem.athlete.entity.Athlete;
import com.olympic.athletemanagementsystem.athlete.service.AthleteService;
import com.olympic.athletemanagementsystem.common.util.ObjectInitializer;
import com.olympic.athletemanagementsystem.event.entity.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
                                            @RequestParam int limit,
                                            @RequestParam String sortBy,
                                            @RequestParam String orderBy){
        try{
            Pageable pageObj;
            if (orderBy.equals("desc"))
                pageObj = PageRequest.of(page, limit, Sort.by(sortBy).descending());
            else
                pageObj = PageRequest.of(page, limit, Sort.by(sortBy).ascending());

            return new ResponseEntity<Object>(athleteService.getAllAthletes(pageObj), HttpStatus.OK);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(API_SEARCH_ATHLETE_BY_GENDER)
    public ResponseEntity<?> searchAthletesByGenderId(@RequestParam int page,
                                                      @RequestParam int limit,
                                                      @RequestParam String sortBy,
                                                      @RequestParam String orderBy,
                                                      @PathVariable Long genderId){
        try{
            Pageable pageObj;
            if (orderBy.equals("desc"))
                pageObj = PageRequest.of(page, limit, Sort.by(sortBy).descending());
            else
                pageObj = PageRequest.of(page, limit, Sort.by(sortBy).ascending());

            return new ResponseEntity<Object>(athleteService.searchAthletesByGenderId(pageObj, genderId), HttpStatus.OK);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(API_SEARCH_ATHLETE_BY_NAMES)
    public ResponseEntity<?> searchAthletesByNames(@RequestParam int page,
                                                   @RequestParam int limit,
                                                   @RequestParam String sortBy,
                                                   @RequestParam String orderBy,
                                                   @RequestParam String firstName,
                                                   @RequestParam String lastName){
        try{
            Pageable pageObj;
            if (orderBy.equals("desc"))
                pageObj = PageRequest.of(page, limit, Sort.by(sortBy).descending());
            else
                pageObj = PageRequest.of(page, limit, Sort.by(sortBy).ascending());
            return new ResponseEntity<Object>(athleteService.searchAthletesByFirstNameAndLastName(pageObj, firstName, lastName), HttpStatus.OK);
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

    @PutMapping(API_ATHLETE_UPLOAD_IMAGE)
    public ResponseEntity<?> uploadImage(@PathVariable Long athleteId, @RequestParam("image") MultipartFile file) {
        try {
            Athlete dbAthlete = athleteService.getAthleteById(athleteId);

            Athlete athlete = Athlete.builder().build();
            athlete.setImage(file.getBytes());

            if (dbAthlete == null)
                return new ResponseEntity<Object>("Athlete not found!", HttpStatus.NOT_FOUND);

            ObjectInitializer<Athlete> init = new ObjectInitializer<>(athlete, dbAthlete);
            dbAthlete = init.updateObject();
            return new ResponseEntity<Object>(athleteService.saveAthlete(dbAthlete), HttpStatus.OK);
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
