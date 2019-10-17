package com.example.lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    EditText etAnswer;
    TextView question;
    Button btnOK2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        etAnswer = findViewById(R.id.answer);
        question = findViewById(R.id.question);
        btnOK2 = findViewById(R.id.btnOK2);
        question.setText(getIntent().getStringExtra("question"));

        btnOK2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("answer", etAnswer.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
