package com.kalvin.kvf.modules.mem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.mem.entity.Products;

import java.util.List;

/**
 * <p>
 * 产品信息 Mapper 接口
 * </p>
 * @since 2021-01-05 16:32:39
 */
public interface ProductsMapper extends BaseMapper<Products> {

    /**
     * 查询列表(分页)
     * @param products 查询参数
     * @param page 分页参数
     * @return list
     */
    List<Products> selectProductsList(Products products, IPage page);

}
