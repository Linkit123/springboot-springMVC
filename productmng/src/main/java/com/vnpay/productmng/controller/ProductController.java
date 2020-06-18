/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.productmng.controller;

import com.vnpay.productmng.CustomPaging;
import com.vnpay.productmng.CustomResponse;
import com.vnpay.productmng.entities.Product;
import com.vnpay.productmng.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author linhtn
 */
@RestController
@RequestMapping("api")
public class ProductController {

    @Autowired //test commit
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @RequestMapping(value = "/add_product", method = RequestMethod.POST)
    public boolean addProduct(@RequestBody Product product) {
       return productService.addProduct(product);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public void updateProduct(@RequestBody Product product, @RequestParam(value = "id", required = false) int id) {
        productService.updateProduct(product, id);
    }

    @RequestMapping(value = "/product", method = RequestMethod.DELETE)
    public boolean deleteProduct(@RequestParam(value = "id", required = false) int id) {
       return productService.deleteProduct(id);
    }

    @RequestMapping(value = "/products/_search_by_name", method = RequestMethod.GET)
    public CustomResponse searchProduct(
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "pageIndex", required = false) int pageIndex,
            @RequestParam(value = "pageSize", required = false) int pageSize) {
        List<Product> products = productService.searchProduct(productName, pageIndex, pageSize);
        
        CustomPaging paging = new CustomPaging(pageIndex, pageSize, products.size());
        
        return new CustomResponse("505", "SUCCESS", products, paging);
    }
}
