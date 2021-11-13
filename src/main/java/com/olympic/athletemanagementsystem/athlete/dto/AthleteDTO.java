package com.olympic.athletemanagementsystem.athlete.dto;

import com.olympic.athletemanagementsystem.common.entity.Gender;
import com.olympic.athletemanagementsystem.event.entity.Event;
import lombok.*;

import java.util.Date;
import java.util.List;

public interface AthleteDTO {
    public Long getAthleteId();
    public String getFirstName();
    public String getLastName();
    public String getCountry();
    public Date getDob();
    public List<Event> getEvents();
    public Gender getGender();
}
