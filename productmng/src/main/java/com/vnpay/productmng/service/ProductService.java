/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.productmng.service;

import com.vnpay.productmng.entities.Product;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author linhtn
 */
public interface ProductService {
    List<Product> getAllProduct();
    
    boolean addProduct(Product product);
    
    void updateProduct(Product product, int id);
    
    boolean deleteProduct(int id);
    
    List<Product> searchProduct(String productName, int pageIndex, int pageSize);
}
