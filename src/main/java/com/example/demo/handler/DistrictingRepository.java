package com.example.demo.handler;

import com.example.demo.entities.Districting;
import com.example.demo.entities.Job;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictingRepository extends JpaRepository<Districting, String> {
    //@EntityGraph(value = "Review.comments", type = EntityGraph.EntityGraphType.FETCH)
    Streamable<Districting> findByjob(String jobID);
}
