package com.ynu.aiagri.service.dev.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ynu.aiagri.service.dev.entity.Dev;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author star
 * @since 2020-11-04
 */
public interface DevService extends IService<Dev> {
    IPage<Dev> selectPage(Page<Dev> pageParam, Dev DevQueryVo);

}
