/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.productmng.service;

import com.vnpay.productmng.dao.ProductDAO;
import com.vnpay.productmng.entities.Product;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author linhtn
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductDAO productDAO;
    
    @Override
    public List<Product> getAllProduct() {
        return productDAO.getAllProduct();
    }

    @Override
    public boolean addProduct(Product product) {
       return productDAO.addProduct(product);
    }

    @Override
    public void updateProduct(Product product, int id) {
        product.setProductId(id);
        productDAO.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(int id) {
       return productDAO.deleteProductById(id);
    }

    @Override
    public List<Product> searchProduct(String productName, int pageIndex, int pageSize) {
       return productDAO.searchProduct(productName, pageIndex, pageSize);
    }
    
}