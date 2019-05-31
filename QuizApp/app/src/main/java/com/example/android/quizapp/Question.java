package com.example.android.quizapp;

import java.util.Arrays;
import java.util.List;

public class Question {

    private List<String> answers;

    public Question(String... answers) {
        this.answers = Arrays.asList(answers);
    }

    public Boolean isCorrect(String... userAnswers) {

        for (int i = 0; i < answers.size(); i++) {

            if (!answers.get(i).equalsIgnoreCase(userAnswers[i])) {
                return false;
            }
        }
        return true;
    }

}
