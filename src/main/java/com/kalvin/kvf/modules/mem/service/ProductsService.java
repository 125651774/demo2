package com.kalvin.kvf.modules.mem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.mem.entity.Products;

/**
 * <p>
 * 产品信息 服务类
 * </p>
 * @since 2021-01-05 16:32:39
 */
public interface ProductsService extends IService<Products> {

    /**
     * 获取列表。分页
     * @param products 查询参数
     * @return page
     */
    Page<Products> listProductsPage(Products products);

}
