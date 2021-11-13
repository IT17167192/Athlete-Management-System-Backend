package com.olympic.athletemanagementsystem.event.service;

import com.olympic.athletemanagementsystem.event.entity.EventCategory;
import com.olympic.athletemanagementsystem.event.repository.EventCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventCategoryService {
    @Autowired
    private EventCategoryRepository eventCategoryRepository;


    public EventCategory saveEventCategory(EventCategory eventCategory){
        return eventCategoryRepository.save(eventCategory);
    }
}
