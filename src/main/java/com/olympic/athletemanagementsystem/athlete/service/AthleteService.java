package com.olympic.athletemanagementsystem.athlete.service;

import com.olympic.athletemanagementsystem.athlete.dto.AthleteDTO;
import com.olympic.athletemanagementsystem.athlete.dto.ImageOnlyDTO;
import com.olympic.athletemanagementsystem.athlete.entity.Athlete;
import com.olympic.athletemanagementsystem.athlete.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

@Service
public class AthleteService {
    @Autowired
    private AthleteRepository athleteRepository;

    @Transactional
    public Athlete saveAthlete(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

    public Page<Athlete> getAllAthletes(Pageable pageable) {
        return athleteRepository.findAll(pageable);
    }

    public Page<AthleteDTO> searchAthletesByGenderId(Pageable pageable, Long genderId) {
        return athleteRepository.findAthleteByGender_Id(pageable, genderId);
    }

    public Page<AthleteDTO> getAllAthletesByEventEnabled(Pageable pageable, boolean enabled) {
        return athleteRepository.findAthleteByEventsEnabled(pageable, enabled);
    }

    public Athlete getAthleteById(Long athleteId){
        return athleteRepository.findAthleteByAthleteId(athleteId);
    }

    public Page<AthleteDTO> searchAthletesByFirstNameAndLastName(Pageable pageable, String firstName, String lastName){
        return athleteRepository.findAthleteByFirstNameContainsAndLastNameContains(pageable, firstName, lastName);
    }

    public ImageOnlyDTO getImageByAthleteId(Long athleteId){
        return athleteRepository.findAthleteByAthleteIdAndFirstNameContains(athleteId, "");
    }

    @Transactional
    public boolean deleteAthleteById(Long athleteId){
        athleteRepository.deleteById(athleteId);
        return true;
    }
}
