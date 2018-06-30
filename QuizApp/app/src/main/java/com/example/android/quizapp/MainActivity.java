package com.example.android.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int scored = 0;
    EditText questionOne, questionFour;
    RadioGroup radioGroupQ2, radioGroupQ3;
    CheckBox q5OptionA, q5OptionB, q5OptionC, q5OptionD, q6OptionA, q6OptionB, q6OptionC, q6OptionD;
    RadioButton radioButtonQ2, radioButtonQ3;
    int grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //This method first checks that all the questions have been answered then assigns a score.
    public void gradeQuiz(View view) {
        grade = 0;
        //Validating Question 1
        questionOne = findViewById(R.id.questionOneText);
        String questionOneInput = questionOne.getText().toString();
        if (questionOneInput.isEmpty()) {
            Toast.makeText(this, "Please answer all the questions first!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Validating Question 2
        radioGroupQ2 = findViewById(R.id.radioGroupQ2);
        if (radioGroupQ2.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please answer all the questions first!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Validating Question 3
        radioGroupQ3 = findViewById(R.id.radioGroupQ3);
        if (radioGroupQ3.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please answer all the questions first!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Validating Question 4
        questionFour = findViewById(R.id.questionFourText);
        String questionFourInput = questionFour.getText().toString();
        if (questionFourInput.isEmpty()) {
            Toast.makeText(this, "Please answer all the questions first!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Validating Question 5
        q5OptionA = findViewById(R.id.question5OptionA);
        q5OptionB = findViewById(R.id.question5OptionB);
        q5OptionC = findViewById(R.id.question5OptionC);
        q5OptionD = findViewById(R.id.question5OptionD);
        if (!(q5OptionA.isChecked() || q5OptionB.isChecked() || q5OptionC.isChecked() || q5OptionD.isChecked())) {
            Toast.makeText(this, "Please answer all the questions first!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Validating Question 6
        q6OptionA = findViewById(R.id.q6OptionA);
        q6OptionB = findViewById(R.id.q6OptionB);
        q6OptionC = findViewById(R.id.q6OptionC);
        q6OptionD = findViewById(R.id.q6OptionD);
        if (!(q6OptionA.isChecked() || q6OptionB.isChecked() || q6OptionC.isChecked() || q6OptionD.isChecked())) {
            Toast.makeText(this, "Please answer all the questions first!", Toast.LENGTH_SHORT).show();
            return;
        }
        //Once we have verified that all the questions have been answered we now mark the quiz
        // Mark Question 1
        if (questionOneInput.equalsIgnoreCase("incorrectly")) {
            grade = grade + 1;
        }
        //Mark Question 2
        radioButtonQ2 = findViewById(R.id.radio_optionD);
        if (radioButtonQ2.isChecked()) {
            grade = grade + 1;
        }
        //Mark Question 3
        radioButtonQ3 = findViewById(R.id.q3Radio_optionC);
        if (radioButtonQ3.isChecked()) {
            grade = grade + 1;
        }
        //Mark Question 4
        if (questionFourInput.equalsIgnoreCase("alphabet")) {
            grade = grade + 1;
        }
        //Mark Question 5. For this one gets a 0 if one option is wrong and 3 points if all options are right.
        if ((q5OptionB.isChecked() && q5OptionD.isChecked()) && !q5OptionA.isChecked() && !q5OptionC.isChecked()) {
            grade = grade + 3;
        }
        if (q6OptionA.isChecked() && q6OptionC.isChecked() && q6OptionD.isChecked() && !q6OptionB.isChecked()) {

            grade = grade + 3;
        }
        grade = grade * 10;
        if (grade == 100) {
            Toast.makeText(this, "Congratulations!!!\nYou have a perfect SCORE!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Well done, you scored, " + grade + "%", Toast.LENGTH_LONG).show();
        }
    }

    //This method resets the app so that the user can have another go at the quiz
    public void resetQuiz(View view) {
        try {
            Intent intent;
            intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}