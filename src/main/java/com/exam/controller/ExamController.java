package com.exam.controller;

import com.exam.service.*;
import com.exam.model.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ExamController {

    private final StudentService studentService = new StudentService();
    private final QuestionService questionService = new QuestionService();
    private final AnalyticsService analyticsService = new AnalyticsService();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String rollNo, Model model) {
        int studentId = studentService.registerStudent(name, rollNo);
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("studentId", studentId);
        model.addAttribute("studentName", name);
        model.addAttribute("questions", questions);
        return "exam";
    }

    @PostMapping("/submit-exam")
    public String submitExam(
            @RequestParam(required = false) Integer studentId,
            @RequestParam Map<String, String> allParams,
            Model model) {
        
        if (studentId == null) {
            return "redirect:/";
        }
        
        try {
            int score = 0;
            List<Question> questions = questionService.getAllQuestions();
            System.out.println("Total questions: " + questions.size());
            System.out.println("Student ID: " + studentId);

            for (Question q : questions) {
                String answer = allParams.get("q" + q.getId());
                String correct = q.getCorrectAns();
                boolean isCorrect = answer != null && answer.equalsIgnoreCase(correct);
                System.out.println("Question " + q.getId() + " - Your answer: " + answer + ", Correct: " + correct + ", isCorrect: " + isCorrect);
                
                questionService.saveResponse(studentId, q.getId(), isCorrect);
                if (isCorrect) score++;
            }

            studentService.updateScore(studentId, score);
            Student student = studentService.getStudent(studentId);

            model.addAttribute("score", score);
            model.addAttribute("total", questions.size());
            model.addAttribute("student", student);
            model.addAttribute("questions", questions);
            model.addAttribute("answers", allParams);
            return "result";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error: " + e.getMessage());
            model.addAttribute("score", 0);
            model.addAttribute("total", 10);
            return "result";
        }
    }

    @GetMapping("/teacher-login")
    public String teacherLogin() {
        return "teacher-login";
    }

    @PostMapping("/teacher-login")
    public String teacherLogin(@RequestParam String password, Model model) {
        System.out.println("Teacher login attempt with password: " + password);
        try {
            if ("123".equals(password)) {
                System.out.println("Password correct, fetching data...");
                List<Student> students = studentService.getAllStudents();
                System.out.println("Students fetched: " + students.size());
                List<QuestionInsight> insights = analyticsService.getQuestionInsights();
                System.out.println("Insights fetched: " + insights.size());
                model.addAttribute("students", students);
                model.addAttribute("insights", insights);
                return "teacher-dashboard";
            }
            model.addAttribute("error", "Invalid password");
            return "teacher-login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error: " + e.getMessage());
            return "teacher-login";
        }
    }
}