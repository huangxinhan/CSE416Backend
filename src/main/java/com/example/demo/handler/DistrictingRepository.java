package com.example.demo.handler;

import com.example.demo.entities.Districting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictingRepository extends JpaRepository<Districting, String> {
}
