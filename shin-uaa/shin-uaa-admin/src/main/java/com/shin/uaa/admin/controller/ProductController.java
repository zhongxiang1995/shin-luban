package com.shin.uaa.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shin.common.core.util.Result;
import com.shin.uaa.admin.service.ProductService;
import com.shin.uaa.common.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

/**
 * @Author shin
 * @Date 2024/3/31 10:06
 */
@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/page")
    public Result page(Page page, Product product){
        return Result.ok(productService.page(page, Wrappers.query(product)));
    }

    @PostMapping
    public Result save(@RequestBody Product product){
        try {
            productService.save(product);
        } catch (DuplicateKeyException e) {
            return Result.failed("重复名称产品");
        }
        return Result.ok(Boolean.TRUE);
    }

    @PutMapping
    public Result updateById(@RequestBody Product product){
        try {
            productService.updateById(product);
        } catch (DuplicateKeyException e) {
            return Result.failed("重复名称产品");
        }
        return Result.ok(Boolean.TRUE);
    }

    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable String id){
        return Result.ok(productService.removeById(id));
    }
}
