package org.gravitytwog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.Objects;

@SpringBootApplication
public class Main implements CommandLineRunner {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... strings) {
        try (var conn = Objects.requireNonNull(
                this.jdbcTemplate.getDataSource()).getConnection()) {

            conn.prepareStatement("""
                CREATE TABLE IF NOT EXISTS students(
                    id SERIAL PRIMARY KEY,
                    name text NOT NULL,
                    last_name text NOT NULL,
                    patronymic text NOT NULL,
                    birth_date date NOT NULL,
                    "group" text NOT NULL
                );
            """).execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}