package com.hikari.executor.configuration;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Component
public class OracleConnectionPool {

    private static HikariDataSource oracleDatasource;

    public OracleConnectionPool(HikariDataSource oracleDatasource) {
        OracleConnectionPool.oracleDatasource = oracleDatasource;
    }

    public static synchronized Connection getOracleConnection() {
        try {
            log.info("Create a new oracle hikari connection.");
            return oracleDatasource.getConnection();
        } catch (SQLException ex) {
            log.error("Failed to create a new oracle hikari connection.");
        }
        return null;
    }
}
