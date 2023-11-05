package com.example.RubyChinaQAService.dao;

import com.example.RubyChinaQAService.entity.po.Ruby;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubyRepository extends Neo4jRepository<Ruby, Long> {
    Ruby findByName(String name);
}
