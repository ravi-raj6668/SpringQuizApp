package com.test.SpringQuizApp.service;

import com.test.SpringQuizApp.Doa.QuestionDao;
import com.test.SpringQuizApp.dto.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QuestionService {

    @Autowired
    public QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addNewQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("New Question Inserted Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while inserting data", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            boolean idPresent = questionDao.existsById(id);
            if (idPresent) {
                questionDao.deleteById(id);
                return new ResponseEntity<>("Question is deleted with id : " + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Question with id : " + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Question with id : " + " not found", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> getDataByColumnValue(String columnName) {
        List<String> columnData = new ArrayList<>();
        try {
            List<Question> questionList = questionDao.findAll();
            for (Question questionData : questionList) {
                // Using reflection to dynamically access the getter method based on the column name
                Method getter = Question.class.getMethod("get" + StringUtils.capitalize(columnName));
                Object value = getter.invoke(questionData);
                columnData.add(value != null ? value.toString() : "null");
            }
        } catch (Exception e) {
            log.error("Error while fetching data from db : " + " for column Name : " + columnName, e);
            e.printStackTrace();
        }
        return ResponseEntity.ok(columnData.toString());
    }
}
