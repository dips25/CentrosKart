package com.example.windows.debcart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Windows on 1/12/2017.
 */
public class Sing_up extends AppCompatActivity {
    EditText et_n, et_em, et_add, et_password;
    Button bt_save;
    DbHelper mydb;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singup);
        mydb = new DbHelper(this);
        et_n = (EditText) findViewById(R.id.et_name);
if(getSupportActionBar()!=null){

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
}
        et_em = (EditText) findViewById(R.id.et_email);
        et_add = (EditText) findViewById(R.id.et_address);
        et_password = (EditText) findViewById(R.id.et_password);

        bt_save = (Button) findViewById(R.id.bt_save);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s_name = et_n.getText().toString();
                String s_pass = et_password.getText().toString();
                String s_email = et_em.getText().toString();
                String s_add = et_add.getText().toString();
                if (s_name.length() == 0 || s_pass.length() == 0 || (!validateEmail(s_email)) || s_add.length() == 0) {

                    if (s_name.length() == 0) {
                        et_n.setFocusable(true);
                        et_n.setError("Invalid name");
                        et_n.requestFocus();
                    }
                    if (s_pass.length() == 0) {
                        et_password.setFocusable(true);
                        et_password.setError("Invalid Password");
                        et_password.requestFocus();
                    }


                    if (!validateEmail(s_email)) {
                        et_em.setFocusable(true);
                        et_em.setError("Invalid email");
                        et_em.requestFocus();
                    }
                    if (s_add.length() == 0) {
                        et_add.setFocusable(true);
                        et_add.setError("Invalid address");
                        et_add.requestFocus();
                    }
                } else {
                    if (mydb.insertContact(s_name, s_email, s_add, s_pass)) {
                        Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                        et_n.setText("");
                        et_em.setText("");
                        et_password.setText("");
                        et_add.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });






    }

    public boolean validateEmail(String email) {

        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
