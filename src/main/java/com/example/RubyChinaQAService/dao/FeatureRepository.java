package com.example.RubyChinaQAService.dao;

import com.example.RubyChinaQAService.entity.po.Feature;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends Neo4jRepository<Feature, Long> {
    Feature findByName(String name);
}
