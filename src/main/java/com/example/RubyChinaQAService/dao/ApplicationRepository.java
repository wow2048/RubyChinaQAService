package com.example.RubyChinaQAService.dao;

import com.example.RubyChinaQAService.entity.po.ApplicationNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends Neo4jRepository<ApplicationNode, Long> {
    ApplicationNode findByName(String name);
}
