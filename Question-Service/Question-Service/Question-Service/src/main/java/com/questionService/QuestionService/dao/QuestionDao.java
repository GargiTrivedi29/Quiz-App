package com.questionService.QuestionService.dao;



import com.questionService.QuestionService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q Where q.category= ?1 ORDER BY RAND() LIMIT ?2",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String categoryName, int numQuestions);
}
