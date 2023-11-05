package com.example.RubyChinaQAService.dao;

import com.example.RubyChinaQAService.entity.po.ProgrammingLanguage;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PLRepository extends Neo4jRepository<ProgrammingLanguage, Long> {
    ProgrammingLanguage findByName(String name);
}
