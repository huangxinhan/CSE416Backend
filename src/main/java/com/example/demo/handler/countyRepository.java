package com.example.demo.handler;

import com.example.demo.entities.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface countyRepository extends JpaRepository<County,String> {
}
