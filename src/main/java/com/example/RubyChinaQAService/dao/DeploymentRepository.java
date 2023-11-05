package com.example.RubyChinaQAService.dao;

import com.example.RubyChinaQAService.entity.po.Deployment;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeploymentRepository extends Neo4jRepository<Deployment, Long> {
    Deployment findByName(String name);
}
