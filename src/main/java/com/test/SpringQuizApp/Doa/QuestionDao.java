package com.test.SpringQuizApp.Doa;

import com.test.SpringQuizApp.dto.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "select * from question q where q.category=:category", nativeQuery = true)
    List<Question> findQuestionByCategory(String category);
}
