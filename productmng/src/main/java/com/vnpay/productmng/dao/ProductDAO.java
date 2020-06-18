/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.productmng.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vnpay.productmng.entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author linhtn
 */
public interface ProductDAO{
    List<Product> getAllProduct();
    
    boolean addProduct(Product product);
    
    void updateProduct(Product product);
    
    boolean deleteProductById(int id);
    
    List<Product> searchProduct(String productName, int pageIndex, int pageSize);
}
