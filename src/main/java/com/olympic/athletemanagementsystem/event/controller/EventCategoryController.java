package com.olympic.athletemanagementsystem.event.controller;

import com.olympic.athletemanagementsystem.event.entity.EventCategory;
import com.olympic.athletemanagementsystem.event.service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.olympic.athletemanagementsystem.common.constants.Routes.*;

@CrossOrigin
@RestController
@RequestMapping( value = API_CATEGORY)
public class EventCategoryController {
    public static final Logger log = Logger.getLogger(EventCategoryController.class.getName());
    @Autowired
    private EventCategoryService eventCategoryService;

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody EventCategory eventCategory){
        try{
            return new ResponseEntity<Object>(eventCategoryService.saveEventCategory(eventCategory), HttpStatus.OK);
        }catch (Exception e){
            log.log(Level.SEVERE, e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
