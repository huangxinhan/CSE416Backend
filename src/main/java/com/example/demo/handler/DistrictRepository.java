package com.example.demo.handler;

import com.example.demo.entities.District;
import com.example.demo.entities.Precinct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, String> {
    Streamable<District> findBydistrictIDContaining(String firstname);
    List<District> findBydistrictIDStartsWith(String firstname);

}
