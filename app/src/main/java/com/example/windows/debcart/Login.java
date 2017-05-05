package com.example.windows.debcart;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Login extends AppCompatActivity {
    DbHelper mydb;
    ArrayList<Person_details> arrPd = null;
    Context context;
    EditText et_loginemail, et_loginpassword;
    DbHelper DB = null;
   TextView tv_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_singup);
        Button bt_show = (Button) findViewById(R.id.bt_show);
        Button bt_login = (Button) findViewById(R.id.bt_login);
        assert bt_show != null;
        if(getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        bt_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Sing_up.class);
                startActivity(intent);
            }
        });
         tv_pass = (TextView)findViewById(R.id.tv_pass);
        tv_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Login.this);
                dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.forgetpassword);
                dialog.show();
//                final EditText security = (EditText)dialog.findViewById(R.id.securityhint_edt);
//                final TextView getpass = (TextView)dialog.findViewById(R.id.textView3);
                Button ok =(Button)dialog.findViewById(R.id.getpassword_btn);
                Button cancel=(Button)dialog.findViewById(R.id.cancel_btn);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                   String userName = security.getText().toString();
//                        if (userName.equals("")){
//                    Toast.makeText(getApplicationContext(),"Please enter your  Email",Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            String storedPassword=dbHelper.getAllTags(userName);
//                            if(storedPassword==null)
//                            {
//                                Toast.makeText(getApplicationContext(), "Please enter your  Email", Toast.LENGTH_SHORT).show();
//                            }else{
//                                Log.d("GET PASSWORD",storedPassword);
//                                getpass.setText(storedPassword);
//                            }
//                        }
                        switch (v.getId()){
                            case R.id.getpassword_btn:
                                final EditText security = (EditText)dialog.findViewById(R.id.securityhint_edt);
                                final TextView getpass = (TextView)dialog.findViewById(R.id.textView3);
                                String userName = security.getText().toString();
                                if (!validateuserName(userName)) {
                                    security.setFocusable(true);
                                    security.setError("Invalid email");
                                    security.requestFocus();
                                }
                                if (userName.equals("") || userName == null || (!validateuserName(userName)))


                                {
                                    Toast.makeText(getApplicationContext(), "Please enter Email", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    String storedPassword = DB.getAllTags(userName);
                                    if(storedPassword==null)
                                    {
                                        Toast.makeText(getApplicationContext(), "Please enter your  Email", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Log.d("GET PASSWORD",storedPassword);
                                        getpass.setText(storedPassword);
                                    }
                                }

                        }


                    }

                    private boolean validateuserName(String userName) {
                        Pattern pattern;
                        Matcher matcher;
                        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                        pattern = Pattern.compile(EMAIL_PATTERN);
                        matcher = pattern.matcher(userName);
                        return matcher.matches();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

        });

////        final EditText et_loginemail=(EditText)findViewById(R.id.et_loginemail);
//        final EditText et_loginpassword=(EditText)findViewById(R.id.et_loginpassword);
//        assert bt_login != null;
//        bt_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String logEmail = et_loginemail.getText().toString();
//                String logPassword= et_loginpassword.getText().toString();
//
//                BaseEmployeeDetails m_BaseEmpDetails = new BaseEmployeeDetails(app);
//                arrPd=m_BaseEmpDetails.GetAllAvailableEmpList(logEmail);
//                if(arrPd!=null){
//                    for(int i=0;i<arrPd.size();i++){
//                        Person_details mpDetails;
//                        mpDetails = arrPd.get(i);
//                        //String email=mpDetails.getEmail();
//                       // String password=mpDetails.getPassword();
//                        Intent ii=new Intent(getApplication(),Profile.class);
//                       // ii.putExtra("Email", email);
//                       // ii.putExtra("Password", password);
//                        startActivity(ii);
//                    }
//                }
//                else
//                {
//                    Toast.makeText(MainActivity.this, "No record found..",
//                            Toast.LENGTH_SHORT).show();
//                    et_loginemail.setText("");
//                    et_loginpassword.setText("");
//
//                }
//
//                    }
//        });

        assert bt_login != null;
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.bt_show:
                        Intent i = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(i);
                        break;

                    case R.id.bt_login:

                        et_loginemail = (EditText) findViewById(R.id.et_loginemail);
                        et_loginpassword = (EditText) findViewById(R.id.et_loginpassword);

                        String email = et_loginemail.getText().toString();
                        String password = et_loginpassword.getText().toString();


                        if (!validateEmail(email)) {
                            et_loginemail.setFocusable(true);
                            et_loginemail.setError("Invalid email");
                            et_loginemail.requestFocus();
                        }
                        if (email.equals("") || email == null || (!validateEmail(email)))


                        {
                            Toast.makeText(getApplicationContext(), "Please enter Email", Toast.LENGTH_SHORT).show();
                        }

                        if (password.length() == 0) {
                            et_loginpassword.setFocusable(true);
                            et_loginpassword.setError("Invalid Password");
                            et_loginpassword.requestFocus();
                        } else if (password.equals("") || password == null) {
                            Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean validLogin = validateLogin(email, password, getBaseContext());
                            if (validLogin) {
                                //System.out.println("In Valid");
                                Intent in = new Intent(getBaseContext(), MainActivity.class);
                                in.putExtra("Email", et_loginemail.getText().toString());
                                startActivity(in);
                                //finish();
                            }

                        }
                        break;

                }


            }

            private boolean validateEmail(String email) {

                Pattern pattern;
                Matcher matcher;
                String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                pattern = Pattern.compile(EMAIL_PATTERN);
                matcher = pattern.matcher(email);
                return matcher.matches();
            }

            private boolean validateLogin(String email, String password, Context baseContext) {

                DB = new DbHelper(getBaseContext());
                SQLiteDatabase db = DB.getReadableDatabase();

                String[] columns = {"password"};

                String selection = "email=? AND password=?";
                String[] selectionArgs = {email, password};

                Cursor cursor = null;
                try {

                    cursor = db.query(DbHelper.CONTACTS_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
                    startManagingCursor(cursor);
                } catch (Exception e)

                {
                    e.printStackTrace();
                }
                int numberOfRows = cursor.getCount();

                if (numberOfRows <= 0) {

                    Toast.makeText(getApplicationContext(), "Email and Password miss match..\nPlease Try Again", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), Login.class);
                    startActivity(intent);
                    return false;
                }


                return true;

            }


        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DB.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}



