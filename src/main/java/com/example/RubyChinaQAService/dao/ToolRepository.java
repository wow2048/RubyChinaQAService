package com.example.RubyChinaQAService.dao;

import com.example.RubyChinaQAService.entity.po.Tool;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends Neo4jRepository<Tool, Long> {
    Tool findByName(String name);
}
