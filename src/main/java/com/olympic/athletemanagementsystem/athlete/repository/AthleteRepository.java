package com.olympic.athletemanagementsystem.athlete.repository;

import com.olympic.athletemanagementsystem.athlete.dto.AthleteDTO;
import com.olympic.athletemanagementsystem.athlete.entity.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    Page<AthleteDTO> findAthleteByGender_Id(Pageable pageable, Long id);
    Athlete findAthleteByAthleteId(Long athleteId);

    @Query(
            value = "FROM Athlete a JOIN FETCH a.events e WHERE e.enabled = :enabled",
            countQuery = "select count(a) FROM Athlete a LEFT JOIN a.events e WHERE e.enabled = :enabled"
    )
    Page<AthleteDTO> findAthleteByEventsEnabled(Pageable pageable, @Param("enabled") boolean enabled);
    Page<AthleteDTO> findAthleteByFirstNameContainsAndLastNameContains(Pageable pageable, String firstName, String lastName);
}
