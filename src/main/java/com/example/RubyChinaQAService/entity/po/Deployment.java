package com.example.RubyChinaQAService.entity.po;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Data
public class Deployment {
    @Id
    @GeneratedValue
    private long id;

    @Property
    private String name;

    @Relationship(type = "HasBlogOf", direction = Relationship.Direction.OUTGOING)
    private List<Blog> blogs;
}
