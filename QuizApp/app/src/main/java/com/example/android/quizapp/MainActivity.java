package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final Integer MAX_SCORE = 8;

    private Integer mTotalScore = 0;
    private EditText mAnswer1;
    private RadioGroup mAnswer2;
    private List<CheckBox> mAnswer3 = new LinkedList<>();
    private RadioGroup mAnswer4;
    private RadioGroup mAnswer5;
    private EditText mAnswer6;
    private RadioGroup mAnswer7;
    private RadioGroup mAnswer8;
    private List<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAnswer1 = findViewById(R.id.answer_1);
        mAnswer2 = findViewById(R.id.answer_2);
        mAnswer3.addAll(Arrays.asList(findViewById(R.id.answer_3_a), findViewById(R.id.answer_3_b), findViewById(R.id.answer_3_c), findViewById(R.id.answer_3_d)));
        mAnswer4 = findViewById(R.id.answer_4);
        mAnswer5 = findViewById(R.id.answer_5);
        mAnswer6 = findViewById(R.id.answer_6);
        mAnswer7 = findViewById(R.id.answer_7);
        mAnswer8 = findViewById(R.id.answer_8);

        questions.add(new Question(getString(R.string.is)));
        questions.add(new Question(getString(R.string.am)));
        questions.add(new Question(getString(R.string.false_value), getString(R.string.true_value), getString(R.string.true_value), getString(R.string.true_value)));
        questions.add(new Question(getString(R.string.is)));
        questions.add(new Question(getString(R.string.are)));
        questions.add(new Question(getString(R.string.it)));
        questions.add(new Question(getString(R.string.is)));
        questions.add(new Question(getString(R.string.is)));
    }

    public void onSubmit(View view) {

        mTotalScore = 0;

        verifyTextResponse(mAnswer1.getText().toString(), 0);
        verifyRadioButtonResponse(mAnswer2, 1);
        verifyCheckBoxResponse(mAnswer3, 2);
        verifyRadioButtonResponse(mAnswer4, 3);
        verifyRadioButtonResponse(mAnswer5, 4);
        verifyTextResponse(mAnswer6.getText().toString(), 5);
        verifyRadioButtonResponse(mAnswer7, 6);
        verifyRadioButtonResponse(mAnswer8, 7);

        if (mTotalScore.equals(MAX_SCORE)) {
            Toast.makeText(this, this.getString(R.string.max_score_msg), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, this.getString(R.string.default_score_msg) + mTotalScore, Toast.LENGTH_SHORT).show();
        }
    }

    private void verifyTextResponse(String answer, Integer questionIndex) {

        if (!answer.isEmpty() && questions.get(questionIndex).isCorrect(answer)) {
            mTotalScore++;
        }
    }

    private void verifyRadioButtonResponse(RadioGroup radioGroup, Integer questionIndex) {

        int chosenId = radioGroup.getCheckedRadioButtonId();

        if (chosenId != -1) {
            RadioButton chosenRadioButton = radioGroup.findViewById(chosenId);
            verifyTextResponse((String) chosenRadioButton.getText(), questionIndex);
        }
    }

    private void verifyCheckBoxResponse(List<CheckBox> checkBoxes, Integer questionIndex) {

        ArrayList<String> alternatives = new ArrayList<>();

        for (int alternative = 0; alternative < checkBoxes.size(); alternative++) {

            if (checkBoxes.get(alternative).isChecked()) {
                alternatives.add(getString(R.string.true_value));
            } else {
                alternatives.add(getString(R.string.false_value));
            }
        }

        if (questions.get(questionIndex).isCorrect(alternatives.toArray(new String[checkBoxes.size()]))) {
            mTotalScore++;
        }
    }


}
