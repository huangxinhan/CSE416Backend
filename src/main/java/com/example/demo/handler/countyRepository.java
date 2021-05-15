package com.example.demo.handler;

import com.example.demo.entities.County;
import com.example.demo.entities.Precinct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface countyRepository extends JpaRepository<County,String> {
    Streamable<County> findBycountyIDContaining(String firstname);
}
