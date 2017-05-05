package com.example.windows.debcart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by SULAGNA on 24-03-2017.
 */
public class Receipt extends AppCompatActivity {
    private static final String TAG = "ProductActivity";
    TextView pname;
    EditText otid;
    EditText edittext4;
    Button button;
    Product product;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt);

        if(getSupportActionBar()!=null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Bundle data = getIntent().getExtras();
        product = (Product) data.getSerializable("product");
        Log.d(TAG, "Product hashCode: " + product.hashCode());
        retrieveViews();


    }
    private void retrieveViews(){

        pname=(TextView)findViewById(R.id.pname);
        otid=(EditText) findViewById(R.id.otid);
        edittext4=(EditText)findViewById(R.id.etdd);
        button=(Button) findViewById(R.id.button);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
