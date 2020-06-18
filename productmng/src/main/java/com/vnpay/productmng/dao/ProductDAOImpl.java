/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.productmng.dao;

import com.vnpay.productmng.ConnectionPoolManager;
import com.vnpay.productmng.entities.Product;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author linhtn
 */
@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    DataSource dataSource;

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<Product>();
        CallableStatement stmt = null;
        ResultSet rset = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to get connection", ex);
        }

        try {
            String proc_in_package = "{call LINHTN_TEST.GET_ALL_PRODUCT(?)}"; // call procedure get all product from package
            stmt = connection.prepareCall(proc_in_package);
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.executeQuery();

            rset = (ResultSet) stmt.getObject(1);
            while (rset.next()) {
                Product product = new Product();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
                product.setCreatedBy(rset.getString("CREATED_BY"));
                product.setProductId(rset.getInt("PRODUCT_ID"));
                product.setStatus(rset.getString("STATUS"));
                product.setName(rset.getString("PRODUCT_NAME"));
                product.setDescription(rset.getString("DESCRIPTION"));
                product.setPrice(rset.getInt("PRICE"));
                if(rset.getTimestamp("CREATED_DATE") != null) {
                    product.setCreatedDate(dateFormat.format(rset.getTimestamp("CREATED_DATE")));
                }
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to get all product", ex);
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to close connection", ex);
            }
        }

        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        CallableStatement stmt = null;
        ResultSet rset = null;
        Connection connection = null;
        boolean status = false;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to get connection", ex);
        }

        try {
            String proc_in_package = "{call LINHTN_TEST.INSERT_PRODUCT(?,?,?,?,?,?)}";
            stmt = connection.prepareCall(proc_in_package);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
            Date currentDate = new Date();
            
            int i = 1;
            stmt.registerOutParameter(i++, OracleTypes.NUMBER);
            stmt.setString(i++, product.getName());
            stmt.setString(i++, product.getDescription());
            stmt.setString(i++, product.getStatus());
            stmt.setString(i++, product.getCreatedBy());
            stmt.setInt(i++, product.getPrice());
            
            stmt.executeQuery();
            status = true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to insert", ex);
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to close connection", ex);
            }
        }
        return status;
    }

    @Override
    public void updateProduct(Product product) {
        CallableStatement stmt = null;
        ResultSet rset = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to get connection", ex);
        }

        try {
            String proc_in_package = "{call LINHTN_TEST.UPDATE_PRODUCT(?,?,?,?,?,?,?)}";
            stmt = connection.prepareCall(proc_in_package);
            int i = 1;
            
            stmt.setInt(i++, product.getProductId());
            stmt.setString(i++, product.getName());
            stmt.setString(i++, product.getDescription());
            stmt.setString(i++, product.getStatus());
            stmt.setString(i++, product.getCreatedDate());
            stmt.setString(i++, product.getCreatedBy());
            stmt.setInt(i++, product.getPrice());
            
            stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to insert", ex);
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to close connection", ex);
            }
        }
    }

    @Override
    public boolean deleteProductById(int id) {
        boolean status = false;
        CallableStatement stmt = null;
        ResultSet rset = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to get connection", ex);
        }

        try {
            String proc_in_package = "{call LINHTN_TEST.DELETE_PRODUCT(?)}";
            stmt = connection.prepareCall(proc_in_package);
            int i = 1;
            
            stmt.setInt(i, id);
            
            stmt.executeQuery();
            status = true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to insert", ex);
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to close connection", ex);
            }
        }
        return status;
    }

    @Override
    public List<Product> searchProduct(String productName, int pageIndex, int pageSize) {
        List<Product> products = new ArrayList<Product>();
        CallableStatement stmt = null;
        ResultSet rset = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to get connection", ex);
        }

        try {
            String proc_in_package = "{call LINHTN_TEST.FIND_PRODUCTS_BY_NAME(?,?,?,?,?)}";
            stmt = connection.prepareCall(proc_in_package);
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2, productName);
            stmt.setInt(3, pageIndex);
            stmt.setInt(4, pageSize);
            stmt.registerOutParameter(5, OracleTypes.NUMBER);
            stmt.executeQuery();

//            int records = stmt.getInt(5);
            
            rset = (ResultSet) stmt.getObject(1);
            while (rset.next()) {
                Product product = new Product();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
                product.setCreatedBy(rset.getString("CREATED_BY"));
                product.setProductId(rset.getInt("PRODUCT_ID"));
                product.setStatus(rset.getString("STATUS"));
                product.setName(rset.getString("PRODUCT_NAME"));
                product.setDescription(rset.getString("DESCRIPTION"));
                product.setPrice(rset.getInt("PRICE"));
                if(rset.getTimestamp("CREATED_DATE") != null) {
                    product.setCreatedDate(dateFormat.format(rset.getTimestamp("CREATED_DATE")));
                }
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to get all product", ex);
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, "fail to close connection", ex);
            }
        }

        return products;
    }
    
}
