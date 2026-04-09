package com.exam;

import com.exam.database.DatabaseConnection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        DatabaseConnection.initializeDatabase();
    }
}