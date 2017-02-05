package com.eselman.medisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eselman.medisys.clients.AuthenticationClientTask;
import com.eselman.medisys.entities.User;
import com.eselman.medisys.helpers.SessionHelper;

public class LoginActivity extends AppCompatActivity implements AuthenticationClientTask.Callback {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Async Taks to call authentication service.
        final AuthenticationClientTask authenticationClient = new AuthenticationClientTask(this, this);

        // Layout elements.
        usernameInput = (EditText) findViewById(R.id.username);
        passwordInput = (EditText) findViewById(R.id.password);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    authenticationClient.execute(user);
                } else {
                    // TODO: Mostrar mensaje de invalid user.
                }
            }
        });
    }

    @Override
    public void authenticationCallback(Object sessionKey) {
        if (sessionKey != null) {
            SessionHelper sessionHelper = SessionHelper.getInstance();
            sessionHelper.saveSessionKey(sessionKey.toString(), LoginActivity.this);
            Intent intent = new Intent(this, MainLandingActivity.class);
            startActivity(intent);
        }
    }
}