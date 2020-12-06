package com.ynu.aiagri.service.kwgra.mapper;

import com.ynu.aiagri.service.kwgra.model.HuDongItemModel;
import com.ynu.aiagri.service.kwgra.model.RELModel;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HudongMapper extends Neo4jRepository<HuDongItemModel, Long> {

    @Query("match(n1{title: {name}}) - [rel] -> (n2) return n1")
    List<HuDongItemModel> getNode1ByName(@Param("name") String name);

    @Query("match(n1{title: {name}}) - [rel] -> (n2) return rel")
    List<RELModel> getRelByName(@Param("name") String name);


    //@Query("MATCH p=()-[r:Weather2Plant]->() RETURN p LIMIT 25")
    @Query("MATCH p=()-[r:RELATION]->() RETURN p LIMIT 25")
    List<RELModel> getRel();



}
