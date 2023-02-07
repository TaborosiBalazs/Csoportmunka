package com.example.f1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button loginButton;
    LoginUser loginUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginUser = new LoginUser();
        usernameEditText = findViewById(R.id.editTextTextPersonName);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.button3);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password;
                username = String.valueOf(usernameEditText.getText());
                password = String.valueOf(passwordEditText.getText());
                User user = new User("",username,password);
                loginUser.login(user, getApplicationContext());
            }
        });
    }

}
