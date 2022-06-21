package com.kevin.easyandroid.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kevin.easyandroid.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextInputLayout passwordTextInput = findViewById(R.id.password_layout);
        final TextInputEditText passwordEditText = findViewById(R.id.password_input);
        MaterialButton nextButton = findViewById(R.id.login_but);

        nextButton.setOnClickListener(view -> {
            if (!isPasswordValid(passwordEditText.getText())) {
                passwordTextInput.setError(getString(R.string.shr_error_password));
            } else {
                passwordTextInput.setError(null);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });

        passwordEditText.setOnKeyListener((view, i, keyEvent) -> {
            if (isPasswordValid(passwordEditText.getText())) {
                passwordTextInput.setError(null);
            }
            return false;

        });
    }

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }
}