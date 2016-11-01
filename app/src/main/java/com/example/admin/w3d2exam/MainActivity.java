package com.example.admin.w3d2exam;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.w3d2exam.entities.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private CheckBox checkBox;
    private String json;
    private String userN;
    private ArrayList<User> users;
    private JsonParser jsonParser;
    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_AGE = "KEY_AGE";
    public static final String KEY_GRADE = "KEY_GRADE";
    public static final String KEY_CHK = "KEY_CHK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        username = (EditText) findViewById(R.id.aMainTxtUser);
        password = (EditText) findViewById(R.id.aMAinTxtPass);
        checkBox = (CheckBox) findViewById(R.id.aMainChkRemember);
        if(getIntent().getStringExtra(KEY_NAME) != null){
            userN = getIntent().getStringExtra(KEY_NAME);
            getIntent().removeExtra(KEY_NAME);
        }
        if(getIntent().getStringExtra(KEY_AGE) != null) getIntent().removeExtra(KEY_AGE);
        if(getIntent().getStringExtra(KEY_GRADE) != null) getIntent().removeExtra(KEY_GRADE);

        if(getIntent().getBooleanExtra(KEY_CHK, false)){
            checkBox.setChecked(true);
            username.setText(userN);
        }

    }

    public void login(View view) {
        URL url = null;
        try {
            String user;
            String password;
            url = new URL("http://www.mocky.io/v2/57a4dfb40f0000821dc9a3b8");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            json = this.getJson(is);
            jsonParser = new JsonParser(json);
            users = jsonParser.parseJson();
            user = username.getText().toString();
            password = this.password.getText().toString();
            User userObj = validateUser(user);
            System.out.println(userObj);
            if(userObj != null && password != null && !"".equals(password)) {
                showSecondActivity(userObj);
            }else{
                Toast.makeText(this, "Invalid User", Toast.LENGTH_LONG).show();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getJson(InputStream is) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(is, "UTF-8");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }

    private User validateUser(String user){
        for(User userObj : users){
            if(userObj.getName().equals(user))
                return userObj;
        }
        return null;
    }

    private void showSecondActivity(User user){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(KEY_NAME, user.getName());
        intent.putExtra(KEY_AGE, user.getAge() +"");
        intent.putExtra(KEY_GRADE, user.getGrade()+"");
        intent.putExtra(KEY_CHK, checkBox.isChecked());
        startActivity(intent);
    }

}
