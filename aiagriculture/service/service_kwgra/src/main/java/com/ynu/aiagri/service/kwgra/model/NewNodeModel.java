package com.ynu.aiagri.service.kwgra.model;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@NodeEntity(label = "NewNode")
public class NewNodeModel {

    @Id
    @GeneratedValue
    private Long id;


    @Property(name = "title")
    private String title;



}
