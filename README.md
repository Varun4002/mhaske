# 📝 mhaske — Online Examination System

A lightweight **Spring Boot** web application for conducting online MCQ exams with a student portal and a teacher analytics dashboard.

---

## ✨ Features

### 👨‍🎓 Student Portal
- Register with name & roll number
- Attempt a 10-question MCQ exam
- View instant results with score and correct answers

### 👩‍🏫 Teacher Dashboard
- Password-protected login
- View all student scores in a sortable table
- Analytics: questions with the highest miss rate (wrong attempt %)

---

## 🛠️ Tech Stack

| Layer      | Technology                  |
|------------|-----------------------------|
| Backend    | Java 17+, Spring Boot 3.2   |
| Frontend   | Thymeleaf, HTML/CSS         |
| Database   | SQLite (embedded)           |
| Build Tool | Maven                       |

---

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.8+

### Run Locally

```bash
git clone https://github.com/Varun4002/mhaske.git
cd mhaske
mvn spring-boot:run
```

Then open your browser at: **http://localhost:8080**

Or use the included script:
```bash
chmod +x run.sh
./run.sh
```

---

## 📂 Project Structure

```
mhaske/
├── src/
│   └── main/
│       ├── java/com/exam/
│       │   ├── controller/     # ExamController (routes)
│       │   ├── database/       # SQLite connection
│       │   ├── model/          # Student, Question, QuestionInsight
│       │   └── service/        # StudentService, QuestionService, AnalyticsService
│       └── resources/
│           └── templates/      # Thymeleaf HTML pages
├── pom.xml
└── run.sh
```

---

## 🔐 Teacher Login

Default password: **`123`**

> ⚠️ Change this in `ExamController.java` before deploying to production.

---

## 📊 How It Works

1. Student registers → stored in SQLite `students` table
2. Student answers 10 MCQ questions → responses stored in `responses` table
3. Score calculated and saved automatically
4. Teacher logs in → sees all scores + question-level miss analytics

---

## 📄 License

MIT License — free to use and modify.
