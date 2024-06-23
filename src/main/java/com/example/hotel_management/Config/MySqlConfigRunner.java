package com.example.hotel_management.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Configuration
public class MySqlConfigRunner {

    @Autowired
    private DataSource dataSource;

    @Bean
    public CommandLineRunner setMaxAllowedPacket() {
        return args -> {
            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement()) {
                // Set max_allowed_packet to 16MB
                statement.execute("SET GLOBAL max_allowed_packet=16777216");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
