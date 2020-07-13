package com.example.retrofitgithubapi.activities.java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.retrofitgithubapi.R;
import com.google.android.material.textfield.TextInputLayout;

public class LogInActivity extends AppCompatActivity {

    private TextInputLayout mInputUserName;
    private Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mInputUserName = findViewById(R.id.input_username);
        mLoginBtn = findViewById(R.id.login_btn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputUserName = mInputUserName.getEditText().getText().toString().trim();

                if (inputUserName.isEmpty()) {
                    mInputUserName.setError("Enter UserName");
                } else {
                    mInputUserName.setErrorEnabled(false);
                    startActivity(new Intent(getApplicationContext(), UserActivity.class).putExtra("STRING_I_NEED", inputUserName));
                }


            }
        });

    }
}
