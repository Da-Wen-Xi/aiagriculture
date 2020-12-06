package com.ynu.aiagri.service.kwgra.controller;

import com.ynu.aiagri.service.kwgra.mapper.HudongMapper;
import com.ynu.aiagri.service.kwgra.model.HuDongItemModel;
import com.ynu.aiagri.base.result.R;
import com.ynu.aiagri.service.kwgra.model.RELModel;

import com.ynu.aiagri.service.kwgra.result.ResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping(value = "/kwgraph", method = RequestMethod.POST)
@CrossOrigin(allowCredentials="true",maxAge = 3600,origins = "*")
public class Neo4jController {

    @Autowired
    private HudongMapper userNeo4jMapper;


    @GetMapping("/getByName/{name}")
    public R getByName(@PathVariable String name){
        //List<HuDongItemModel> model = userNeo4jMapper.getNode1ByName(name);

        List<RELModel> model = userNeo4jMapper.getRel();

        return R.ok().data("rows", model);
    }
}
