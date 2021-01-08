package com.kalvin.kvf.modules.mem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.mem.entity.Products;
import com.kalvin.kvf.modules.mem.mapper.ProductsMapper;

import java.util.List;

/**
 * <p>
 * 产品信息 服务实现类
 * </p>
 * @since 2021-01-05 16:32:39
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements ProductsService {

    @Override
    public Page<Products> listProductsPage(Products products) {
        Page<Products> page = new Page<>(products.getCurrent(), products.getSize());
        List<Products> productss = baseMapper.selectProductsList(products, page);
        return page.setRecords(productss);
    }

}
