package com.quizService.quizservice.model;

import lombok.Data;

@Data
public class Quizdto {

    private String categoryName;
    private Integer numQuestions;
    private String title;
}
