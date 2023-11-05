package com.example.RubyChinaQAService.entity.po;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import java.util.List;

@Data
@Node("Ruby")
public class Ruby {
    @Id
    @GeneratedValue
    private long id;

    @Property
    private String name;

    @Property
    private String description;

    @Property
    private String pros;

    @Property
    private String cons;

    @Relationship(type = "SimilarTo", direction = Direction.OUTGOING)
    private List<ProgrammingLanguage> similarLanguage;

    @Relationship(type = "HasBlogOf", direction = Direction.OUTGOING)
    private List<Blog> blogs;

    @Relationship(type = "HasFeatureOf", direction = Direction.OUTGOING)
    private List<Feature> features;

    @Relationship(type = "HasApplicationOf", direction = Direction.OUTGOING)
    private List<ApplicationNode> applications;

    @Relationship(type = "HasToolOf", direction = Direction.OUTGOING)
    private List<Tool> tools;
}
