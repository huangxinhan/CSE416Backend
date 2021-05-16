package com.example.demo.handler;

import com.example.demo.entities.Districting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictingRepository extends JpaRepository<Districting, String> {
    List<Districting> findByjobID(String jobID);
}
