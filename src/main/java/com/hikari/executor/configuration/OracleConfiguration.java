package com.hikari.executor.configuration;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
public class OracleConfiguration {

    @Bean(name="oracleDataSource")
    @ConfigurationProperties("oracle")
    public HikariDataSource oracleDataSource() {
        log.info("Created new oracle hikari datasource.");
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }
}
