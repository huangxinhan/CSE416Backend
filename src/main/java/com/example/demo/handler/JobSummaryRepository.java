package com.example.demo.handler;

import com.example.demo.entities.JobSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSummaryRepository extends JpaRepository<JobSummary,String> {
}
