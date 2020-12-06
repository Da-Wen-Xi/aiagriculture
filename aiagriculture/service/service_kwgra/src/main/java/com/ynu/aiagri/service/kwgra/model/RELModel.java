package com.ynu.aiagri.service.kwgra.model;


import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "RELATION")
@Data
public class RELModel {

    @StartNode
    HuDongItemModel startNode;

    @EndNode
    HuDongItemModel endNode;

    @Property(name = "type")
    private String type;



}
