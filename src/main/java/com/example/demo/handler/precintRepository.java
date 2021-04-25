package com.example.demo.handler;

import com.example.demo.entities.precint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface precintRepository extends JpaRepository<precint,String> {
}
