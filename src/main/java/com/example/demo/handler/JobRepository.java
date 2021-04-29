package com.example.demo.handler;

import com.example.demo.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {
}
