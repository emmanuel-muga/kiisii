package com.example.niconetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
   EditText Username, Password;
    Button Login,Register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
Username=(EditText)findViewById(R.id.username);
Password=(EditText)findViewById(R.id.password);
Login=(Button)findViewById(R.id.login);
//progress dialog box
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false); progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _username=Username.getText().toString();
                String _password=Password.getText().toString();
                if(_username.length()==0){
                    Username.requestFocus();
                    Username.setError("Field could not be empty");
                }
                else if(_password.length()==0){
                    Password.requestFocus();
                    Password.setError("Field could not be empty");
                }
                else {
                    Intent intent = new Intent(MainActivity.this, home.class);
                    startActivity(intent);
                }

            }
        });
        Register=(Button)findViewById(R.id.register);

        Register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,registerActivity.class);
        startActivity(intent);
    }
});

    }
}
