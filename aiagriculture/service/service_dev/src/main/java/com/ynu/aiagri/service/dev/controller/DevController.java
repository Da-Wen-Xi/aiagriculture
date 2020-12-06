package com.ynu.aiagri.service.dev.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ynu.aiagri.base.result.R;
import com.ynu.aiagri.service.dev.entity.Dev;
import com.ynu.aiagri.service.dev.service.DevService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author star
 * @since 2020-11-04
 */
@RestController
@RequestMapping(value = "/admin/dev/device", method = RequestMethod.POST)
@CrossOrigin(allowCredentials="true",maxAge = 3600,origins = "*")
public class DevController {

    @Autowired
    private DevService devService;

    @ApiOperation("所有设备列表")
    @GetMapping("list")
    public R listAll() {
        List<Dev> list = devService.list();
        return R.ok().data("items", list);
    }


    //删除设备
    @ApiOperation(value = "根据ID删除设备", notes = "根据ID删除设备，逻辑删除")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam(value = "设备ID", required = true) @PathVariable String id) {

        //删除讲师
        boolean result = devService.removeById(id);
        if (result) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("设备分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit,
                      @ApiParam("设备列表查询对象") Dev devQueryVo) {

        Page<Dev> pageParam = new Page<>(page, limit);
        IPage<Dev> pageModel = devService.selectPage(pageParam, devQueryVo);
        List<Dev> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }


    @ApiOperation("新增设备")
    @PostMapping("save")
    public R save(@ApiParam(value = "设备对象", required = true) @RequestBody Dev dev){
        devService.save(dev);
        return R.ok().message("保存成功");
    }


    @ApiOperation("更新设备")
    @PutMapping("update")
    public R updateById(@ApiParam(value = "设备对象",required = true) Dev dev){
        boolean result = devService.updateById(dev);
        if(result){
            return R.ok().message("修改成功");
        }else{
            return R.error().message("数据不存在");
        }
    }


    @ApiOperation(value = "根据ID列表删除")
    @DeleteMapping("batch-remove")
    public R removeRows(
            @ApiParam(value = "设备ID列表", required = true)
            @RequestBody List<String> idList){
        boolean result = devService.removeByIds(idList);
        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("数据不存在");
        }
    }


}

