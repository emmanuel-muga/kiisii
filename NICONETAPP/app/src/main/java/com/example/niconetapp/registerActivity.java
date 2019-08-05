package com.example.niconetapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class registerActivity extends AppCompatActivity  {
EditText Name,dateOfBirth,gender,email,phoneNumber,userName,Password;
Button Register;



    DatePickerDialog datePickerDialog;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name = (EditText) findViewById(R.id.name);
        dateOfBirth=(EditText)findViewById(R.id.dob);
        gender=(EditText)findViewById(R.id.gender1);
        email=(EditText)findViewById(R.id.email);
        phoneNumber=(EditText)findViewById(R.id.phone);
        userName=(EditText)findViewById(R.id.username);
        Password=(EditText)findViewById(R.id.password);
        Register=(Button)findViewById(R.id.register);
dateOfBirth.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        final Calendar c=Calendar.getInstance();
        int mYear=c.get(Calendar.YEAR);
        int mMonth=c.get(Calendar.MONTH);
        int mDay=c.get(Calendar.DAY_OF_MONTH);
        //Date picker dialogue
        datePickerDialog= new DatePickerDialog(registerActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                // set day of month , month and year value in the edit text
                dateOfBirth.setText(dayOfMonth + "/"
                        + (monthOfYear + 1) + "/" + year);

            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
});
// Write a message to the database
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           String _Name=Name.getText().toString();
            String _dateOfBirth=dateOfBirth.getText().toString();
            String _gender=gender.getText().toString();
            String _email =email.getText().toString();
            String _phoneNumber=phoneNumber.getText().toString();
            String _userName=userName.getText().toString();
            String _password=Password.getText().toString();
            if(_Name.length()==0){
                Name.requestFocus();
                Name.setError("Field could not be empty");
            }
            else if(_dateOfBirth.length()==0){
                dateOfBirth.requestFocus();
                dateOfBirth.setError("Field could not be empty");
            }
            else if(_gender.length()==0){
                gender.requestFocus();
                gender.setError("Field could not be empty");
            }
            else if(_email.length()==0){
                email.requestFocus();
                email.setError("Field could not be empty");
            }
            else if(_password.length()==0){
                phoneNumber.requestFocus();
                phoneNumber.setError("Field could not be empty");
            }
            else if(_userName.length()==0){
                userName.requestFocus();
                userName.setError("Field could not be empty");
            }
            else if(_password.length()==0){
                Password.requestFocus();
                Password.setError("Field could not be empty");
            }
            else {
                Map<String, String> userMap = new HashMap<>();
                userMap.put("name", _Name);
                userMap.put("dob", _dateOfBirth);
                userMap.put("gender", _gender);
                userMap.put("email", _email);
                userMap.put("phone", _phoneNumber);
                userMap.put("username", _userName);
                userMap.put("password", _password);

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("users").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(registerActivity.this, "user added successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(registerActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error = e.getMessage();
                        Toast.makeText(registerActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();
                    }
                });

            } };
        });
        }}