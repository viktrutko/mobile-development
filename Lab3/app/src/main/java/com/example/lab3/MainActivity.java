package com.example.lab3;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab3.dialog.ExitDialogFragment;

public class MainActivity extends AppCompatActivity {

    EditText editQuestion;
    TextView textAnswer;
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editQuestion = findViewById(R.id.answer);
        this.textAnswer = findViewById(R.id.question);
        this.btnOK = findViewById(R.id.btnOK);

        this.btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra("question", editQuestion.getText().toString());
                startActivityForResult(intent, 2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 2) {
            String answer = data.getStringExtra("answer");
            this.textAnswer.setText(answer);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.exit:
                ExitDialogFragment exitDialogFragment = new ExitDialogFragment();
                exitDialogFragment.show(getSupportFragmentManager(), "ExitDialogFragment");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}