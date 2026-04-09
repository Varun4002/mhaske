package com.exam.service;

import com.exam.database.DatabaseConnection;
import com.exam.model.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM questions");

            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setText(rs.getString("text"));
                q.setOpA(rs.getString("opA"));
                q.setOpB(rs.getString("opB"));
                q.setOpC(rs.getString("opC"));
                q.setOpD(rs.getString("opD"));
                q.setCorrectAns(rs.getString("correct_ans"));
                questions.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public Question getQuestion(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM questions WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setText(rs.getString("text"));
                q.setOpA(rs.getString("opA"));
                q.setOpB(rs.getString("opB"));
                q.setOpC(rs.getString("opC"));
                q.setOpD(rs.getString("opD"));
                q.setCorrectAns(rs.getString("correct_ans"));
                return q;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveResponse(int studentId, int questionId, boolean isCorrect) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO responses (student_id, question_id, is_correct) VALUES (?, ?, ?)"
            );
            ps.setInt(1, studentId);
            ps.setInt(2, questionId);
            ps.setInt(3, isCorrect ? 1 : 0);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}