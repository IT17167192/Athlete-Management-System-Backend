package com.olympic.athletemanagementsystem.athlete.service;

import com.olympic.athletemanagementsystem.athlete.entity.Athlete;
import com.olympic.athletemanagementsystem.athlete.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class AthleteService {
    @Autowired
    private AthleteRepository athleteRepository;

    public Athlete saveAthlete(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

    public Page<Athlete> getAllAthletes(Pageable pageable) {
        return athleteRepository.findAll(pageable);
    }

    public Page<Athlete> getAllAthletesByGenderId(Pageable pageable, Long genderId) {
        return athleteRepository.findAthleteByGender_Id(pageable, genderId);
    }

    public Page<Athlete> getAllAthletesByEventEnabled(Pageable pageable, boolean enabled) {
        return athleteRepository.findAthleteByEventsEnabled(pageable, enabled);
    }
}
