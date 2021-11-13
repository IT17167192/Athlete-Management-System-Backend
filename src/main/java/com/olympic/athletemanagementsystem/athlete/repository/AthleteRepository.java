package com.olympic.athletemanagementsystem.athlete.repository;

import com.olympic.athletemanagementsystem.athlete.entity.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    Page<Athlete> findAthleteByGender_Id(Pageable pageable, Long id);

    @Query(
            value = "FROM Athlete a JOIN FETCH a.events e WHERE e.enabled = :enabled",
            countQuery = "select count(a) FROM Athlete a LEFT JOIN a.events e WHERE e.enabled = :enabled"
    )
    Page<Athlete> findAthleteByEventsEnabled(Pageable pageable, @Param("enabled") boolean enabled);
}
