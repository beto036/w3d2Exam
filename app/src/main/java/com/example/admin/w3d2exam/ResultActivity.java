package com.example.admin.w3d2exam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtAge;
    private TextView txtGrade;
    private boolean remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtName = (TextView) findViewById(R.id.aResultTxtName);
        txtAge = (TextView) findViewById(R.id.aResultTxtAge);
        txtGrade = (TextView) findViewById(R.id.aResultTxtGrade);
        txtName.setText("Name: " + getIntent().getStringExtra(MainActivity.KEY_NAME));
        txtAge.setText("Age: " + getIntent().getStringExtra(MainActivity.KEY_AGE));
        txtGrade.setText("Grade: " + getIntent().getStringExtra(MainActivity.KEY_GRADE));
        remember = getIntent().getBooleanExtra(MainActivity.KEY_CHK, false);
    }

    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.KEY_NAME, getIntent().getStringExtra(MainActivity.KEY_NAME));
        intent.putExtra(MainActivity.KEY_CHK, remember);
        startActivity(intent);
    }
}
