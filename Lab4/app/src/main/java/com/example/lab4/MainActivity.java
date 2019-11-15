package com.example.lab4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,new IntentFilter("MainActivity"));

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = findViewById(R.id.editNumber);
                int number = Integer.valueOf(editText.getText().toString());
                Intent intent = new Intent(MainActivity.this, MainIntentService.class);
                intent.putExtra("number",number);
                startService(intent);
                button.setEnabled(false);
            }
        });
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String numbers = intent.getStringExtra("prime");
            int amount = intent.getIntExtra("amount",0);
            final TextView amountTextView = findViewById(R.id.textView);
            amountTextView.setText(String.valueOf(amount));
            final TextView allNumbersTextView = findViewById(R.id.textViewScroll);
            allNumbersTextView.setText(numbers);
            final Button button = findViewById(R.id.button);
            button.setEnabled(true);
        }
    };
}