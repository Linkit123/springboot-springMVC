/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.productmng;

import com.zaxxer.hikari.HikariDataSource;
import java.beans.Statement;
import java.sql.*;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author linhtn
 */
@Configuration
public class ConnectionPoolManager {
//    @Value("${db.schemaname}")
//    public String schemaName;
    @Value("${db.username}")
    public String UserName;
    @Value("${db.password}")
    public String Password;
    @Value("${db.dbconnect}")
    public String Dbconnect;
//    @Value("${db.maxSizePool}")
//    public int MaxSizePool;
//    @Value("${db.dataSourceClassName}")
//    public String DataSourceClassName;
    
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setMaximumPoolSize(10);
        hikariDataSource.setDataSourceClassName("oracle.jdbc.pool.OracleDataSource");
        hikariDataSource.addDataSourceProperty("user", UserName);
        hikariDataSource.addDataSourceProperty("password", Password);
        hikariDataSource.addDataSourceProperty("url", Dbconnect);
        return hikariDataSource;
    }
}
