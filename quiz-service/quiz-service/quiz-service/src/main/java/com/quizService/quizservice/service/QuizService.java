package com.quizService.quizservice.service;


import com.quizService.quizservice.dao.QuizDao;
import com.quizService.quizservice.feign.QuizInterface;
import com.quizService.quizservice.model.QuestionWrapper;
import com.quizService.quizservice.model.Quiz;
import com.quizService.quizservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

   public ResponseEntity<String> quizCreated(String category, int numQ, String title) {
       List<Integer> questions=quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionId(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Quiz quiz = quizDao.findById(id).get();
       List<Integer> questionIds = quiz.getQuestionId();
       ResponseEntity<List<QuestionWrapper>> questions= quizInterface.getQuestionsFromId(questionIds);
        return questions;

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
       ResponseEntity<Integer> score= quizInterface.getScore(response);
        return score;
    }
}
