package com.example.demo.handler;

import com.example.demo.entities.Precinct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface precintRepository extends JpaRepository<Precinct,String> {
    Streamable<Precinct> findByprecinctIDContaining(String firstname);
}
