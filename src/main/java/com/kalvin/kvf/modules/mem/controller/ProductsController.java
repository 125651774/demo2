package com.kalvin.kvf.modules.mem.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.mem.entity.Products;
import com.kalvin.kvf.modules.mem.service.ProductsService;

import java.util.List;


/**
 * <p>
 * 产品信息 前端控制器
 * </p>
 * @since 2021-01-05 16:32:39
 */
@RestController
@RequestMapping("mem/products")
public class ProductsController extends BaseController {

    @Autowired
    private ProductsService productsService;

    @RequiresPermissions("mem:products:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("mem/products");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("mem/products_edit");
        Products products;
        if (id == null) {
            products = new Products();
        } else {
            products = productsService.getById(id);
        }
        mv.addObject("editInfo", products);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(Products products) {
        Page<Products> page = productsService.listProductsPage(products);
        return R.ok(page);
    }

    @RequiresPermissions("mem:products:add")
    @PostMapping(value = "add")
    public R add(Products products) {
        productsService.save(products);
        return R.ok();
    }

    @RequiresPermissions("mem:products:del")
    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        productsService.removeByIds(ids);
        return R.ok();
    }

    @RequiresPermissions("mem:products:edit")
    @PostMapping(value = "edit")
    public R edit(Products products) {
        productsService.updateById(products);
        return R.ok();
    }

    @RequiresPermissions("mem:products:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        productsService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(productsService.getById(id));
    }

}

