package com.ynu.aiagri.service.dev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ynu.aiagri.service.dev.entity.Dev;
import com.ynu.aiagri.service.dev.mapper.DevMapper;
import com.ynu.aiagri.service.dev.service.DevService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author star
 * @since 2020-11-04
 */
@Service
public class DevServiceImpl extends ServiceImpl<DevMapper, Dev> implements DevService {

    @Override
    public IPage<Dev> selectPage(Page<Dev> pageParam, Dev DevQuery) {
        //显示分页查询列表
//        1、排序：按照sort字段排序
        QueryWrapper<Dev> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByAsc("sort");

//        2、分页查询
        if(DevQuery == null){
            return baseMapper.selectPage(pageParam, queryWrapper);
        }

//        3、条件查询
        String name = DevQuery.getName();


        if(!StringUtils.isEmpty(name)){
            queryWrapper.eq("name", name.replace(" ", ""));
        }

        return baseMapper.selectPage(pageParam, queryWrapper);
    }
}
