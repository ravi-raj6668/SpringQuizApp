package com.test.SpringQuizApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWrapper {
    private Integer id;
    private String question_title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuestionWrapper() {

    }
}
