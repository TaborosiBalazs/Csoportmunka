package com.example.f1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity
{
    Button registerButton;
    EditText emailEditText, usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = findViewById(R.id.emailEditText);
        registerButton = findViewById(R.id.registerButton);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                User user = new User(
                        String.valueOf(emailEditText.getText()),
                        String.valueOf(usernameEditText.getText()),
                        String.valueOf(passwordEditText.getText()));
                RegisterUser.putDataToDB(user, getApplicationContext());
            }
        });
    }
}