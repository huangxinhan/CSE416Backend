package com.example.demo.handler;

import com.example.demo.entities.Precinct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface precintRepository extends JpaRepository<Precinct,String> {
}
