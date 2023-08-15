package com.quizService.quizservice.controller;
import com.quizService.quizservice.model.QuestionWrapper;
import com.quizService.quizservice.model.Quiz;
import com.quizService.quizservice.model.Quizdto;
import com.quizService.quizservice.model.Response;
import com.quizService.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;


    @PostMapping("create")
    public ResponseEntity<String> quizCreated(@RequestBody Quizdto quizdto) {
        return quizService.quizCreated(quizdto.getCategoryName(), quizdto.getNumQuestions(), quizdto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> response){
        return quizService.calculateResult(id,response);
    }


}