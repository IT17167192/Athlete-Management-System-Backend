package com.olympic.athletemanagementsystem.result.repository;

import com.olympic.athletemanagementsystem.result.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
    public Result findResultByResultId(Long resultId);
}
