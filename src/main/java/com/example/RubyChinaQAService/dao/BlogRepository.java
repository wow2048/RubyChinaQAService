package com.example.RubyChinaQAService.dao;

import com.example.RubyChinaQAService.entity.po.Blog;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends Neo4jRepository<Blog, Long> {
    List<Blog> findByTitle(String title);
}
