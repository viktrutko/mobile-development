package com.example.lab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab5.db.App;
import com.example.lab5.db.AppDatabase;
import com.example.lab5.db.User;
import com.example.lab5.db.UserDao;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.database = App.getInstance().getDatabase();
        this.userDao = this.database.userDao();

        final TextView textViewPersons = findViewById(R.id.person);
        final EditText inputLastName = findViewById(R.id.lastName);
        final EditText inputFirstName = findViewById(R.id.firstName);
        final EditText inputBirthday = findViewById(R.id.birthday);
        final Button insertButton = findViewById(R.id.insertButton);
        final Button searchButton = findViewById(R.id.searchButton);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.firstName = inputFirstName.getText().toString();
                user.lastName = inputLastName.getText().toString();
                user.birthday = inputBirthday.getText().toString();
                if (!user.lastName.equals("")&&!user.firstName.equals("")){
                    userDao.insert(user);
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = inputFirstName.getText().toString();
                String lastName = inputLastName.getText().toString();
                if (firstName.equals("") && !lastName.equals("")) {
                    textViewPersons.setText(userDao.getLastNameOrPartOfThem(lastName).toString());
                } else {
                    if (!firstName.equals("") && lastName.equals("")) {
                        textViewPersons.setText(userDao.getFirstNameOrPartOfThem(firstName).toString());
                    } else {
                        textViewPersons.setText(userDao.getFirstAndLastNameOrPartOfThem(firstName, lastName).toString());
                    }
                }
            }
        });
    }
}
