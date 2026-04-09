package com.exam.service;

import com.exam.database.DatabaseConnection;
import com.exam.model.QuestionInsight;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnalyticsService {

    public List<QuestionInsight> getQuestionInsights() {
        List<QuestionInsight> insights = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = """
                SELECT q.id as question_id, q.text as question_text,
                       COUNT(r.id) as total_attempts,
                       COALESCE(SUM(CASE WHEN r.is_correct = 0 THEN 1 ELSE 0 END), 0) as wrong_attempts
                FROM questions q
                LEFT JOIN responses r ON q.id = r.question_id
                GROUP BY q.id
                HAVING COUNT(r.id) > 0
                ORDER BY wrong_attempts DESC
            """;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                insights.add(new QuestionInsight(
                    rs.getInt("question_id"),
                    rs.getString("question_text"),
                    rs.getInt("total_attempts"),
                    rs.getInt("wrong_attempts")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insights;
    }
}