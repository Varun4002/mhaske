package com.exam.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;

public class DatabaseConnection {
    private static final String DB_URL;

    static {
        String dbPath = new File("").getAbsolutePath() + "/exam_system.db";
        DB_URL = "jdbc:sqlite:" + dbPath;
        System.out.println(">>> Database path: " + DB_URL);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection()) {
            var statement = conn.createStatement();

            statement.execute("""
                CREATE TABLE IF NOT EXISTS students (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    roll_no TEXT UNIQUE NOT NULL,
                    score INTEGER DEFAULT 0
                )
            """);

            statement.execute("""
                CREATE TABLE IF NOT EXISTS questions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    text TEXT NOT NULL,
                    opA TEXT NOT NULL,
                    opB TEXT NOT NULL,
                    opC TEXT NOT NULL,
                    opD TEXT NOT NULL,
                    correct_ans TEXT NOT NULL
                )
            """);

            statement.execute("""
                CREATE TABLE IF NOT EXISTS responses (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    student_id INTEGER NOT NULL,
                    question_id INTEGER NOT NULL,
                    is_correct INTEGER NOT NULL,
                    FOREIGN KEY (student_id) REFERENCES students(id),
                    FOREIGN KEY (question_id) REFERENCES questions(id)
                )
            """);

            var rs = statement.executeQuery("SELECT COUNT(*) as count FROM questions");
            if (rs.next() && rs.getInt("count") == 0) {
                populateQuestions(statement);
            }

            System.out.println("Database initialized successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void populateQuestions(java.sql.Statement statement) {
        String[] questions = {
            "INSERT INTO questions (text, opA, opB, opC, opD, correct_ans) VALUES ('What does CPU stand for?', 'Central Processing Unit', 'Computer Personal Unit', 'Central Program Unit', 'Computer Processing Unit', 'A')",
            "INSERT INTO questions (text, opA, opB, opC, opD, correct_ans) VALUES ('Which data structure uses LIFO?', 'Queue', 'Stack', 'Array', 'Linked List', 'B')",
            "INSERT INTO questions (text, opA, opB, opC, opD, correct_ans) VALUES ('What is the binary of decimal 10?', '1000', '1010', '1100', '1110', 'B')",
            "INSERT INTO questions (text, opA, opB, opC, opD, correct_ans) VALUES ('Which sorting algorithm has O(n log n) average time?', 'Bubble Sort', 'Selection Sort', 'Merge Sort', 'Linear Search', 'C')",
            "INSERT INTO questions (text, opA, opB, opC, opD, correct_ans) VALUES ('What does SQL stand for?', 'Simple Query Language', 'Structured Query Language', 'Standard Query Language', 'System Query Language', 'B')",
            "INSERT INTO questions (text, opA, opB, opC, opD, correct_ans) VALUES ('Which port does HTTP use?', '80', '443', '21', '25', 'A')",
            "INSERT INTO questions (text, opA, opB, opC, opD, correct_ans) VALUES ('What is the default value of int in Java?', '0', '1', 'null', 'undefined', 'A')",
            "INSERT INTO questions (text, opA, opB, opC, opD, correct_ans) VALUES ('Which is not a primitive type in Java?', 'int', 'boolean', 'String', 'char', 'C')",
            "INSERT INTO questions (text, opA, opB, opC, opD, correct_ans) VALUES ('What does HTML stand for?', 'Hyper Text Markup Language', 'High Tech Modern Language', 'Hyper Transfer Markup Language', 'Home Tool Markup Language', 'A')",
            "INSERT INTO questions (text, opA, opB, opC, opD, correct_ans) VALUES ('Which is an OS?', 'Microsoft', 'Windows', 'Linux', 'Google', 'C')"
        };

        for (String q : questions) {
            try {
                statement.execute(q);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}