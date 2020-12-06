package com.ynu.aiagri.service.kwgra.model;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@NodeEntity(label = "HudongItem")
public class HuDongItemModel {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "title")
    private String title;

    @Property(name = "image")
    private String image;

    @Property(name = "baseInfoValueList")
    private String baseInfoValueList;

    @Property(name = "url")
    private String url;

    @Property(name = "openTypeList")
    private String openTypeList;



}
