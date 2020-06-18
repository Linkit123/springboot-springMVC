/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.productmng.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author linhtn
 */
public class BaseDAO {
    private static final Logger log = LogManager.getLogger("demoLog4j");
    public static String schemaName = "";

    @Autowired
    DataSource dataSource;

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            log.fatal("Get connection failure:" + ex);
            return null;
        }
    }

    protected void closeConnection(Connection cnn) {
        try {
            if (cnn != null) {
                cnn.close();
            }
        } catch (SQLException ex) {
            log.fatal(ex);
        }
    }

    protected void closeCstmt(CallableStatement cstmt) {
        try {
            if (cstmt != null) {
                cstmt.close();
            }
        } catch (Exception ex) {
            log.fatal(ex);
        }
    }

    protected void closePstmt(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (Exception ex) {
            log.fatal(ex);
        }

    }

    protected void closeRs(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            log.fatal(ex);
        }
    }

    protected String getValue(String inputData, int size) {
        if (inputData != null && inputData.length() > size) {
            if (inputData.trim().length() > size) {
                return inputData.substring(0, size);
            } else {
                return inputData.trim();
            }
        } else {
            return inputData;
        }
    }

    protected java.sql.Timestamp getDate(java.util.Date inputDate) {
        if (inputDate != null) {
            return new java.sql.Timestamp(inputDate.getTime());
        }
        return null;
    }
}
